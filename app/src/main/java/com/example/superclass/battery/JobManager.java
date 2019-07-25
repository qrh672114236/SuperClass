package com.example.superclass.battery;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;

import java.util.List;

public class JobManager {
    /**
     * 8.0以后推荐使用此方法启动service，
     *
     * JobInfo：
     *      1.setPersisted(boolean isPersisted)方法不仅可以设置重启设备后该作业仍然生效，而且在android8.0以下的设备中这是一个很好的保活手段，即使在设置中强行停止了应用，只要激活条件到了该作业仍然会启动从而达到唤醒应用的目的。
     *      2.setMinimumLatency(long minLatencyMillis)：这个即经过minLatencyMillis时间再开始执行任务，此方法不能和setPeriodic(long time)同时使用，否则会引起异常
     *      3.setOverrideDeadline(long maxExecutionDelayMillis)：设置最长等待时间，即使其他条件未满足，经过maxExecutionDelayMillis后任务都会执行。此方法不能和setPeriodic(long time)同时使用，否则会引起异常。
            4.setRequiredNetworkType ：
                                JobInfo.NETWORK_TYPE_NONE（无网络时执行，默认）、
                                JobInfo.NETWORK_TYPE_ANY（有网络时执行）、
                                JobInfo.NETWORK_TYPE_UNMETERED（网络无需付费时执行）
            5.setRequiresCharging ：是否在充电时执行
            6.setRequiresDeviceIdle ：是否在空闲时执行
            7.setBackoffCriteria: 退避策略 , 可以设置等待时间以及重连策略
            （456）可能引起任务永远无法执行，除非设置了setOverrideDeadline，即使其他条件未满足，当最长等待时间到达时都会执行任务。
     */



    static JobManager instance;

    Context context;

    private static final int jobId = 0;

    public static JobManager getInstance() {
        if (null == instance) {
            synchronized (JobManager.class) {
                if (null == instance) {
                    instance = new JobManager();
                }
            }
        }
        return instance;
    }


    //不是紧急的任务 找到合适时间去批处理
    //1.避免频繁唤醒硬件模块
    //2.避免在不合适的时间执行耗电任务
    private JobScheduler jobScheduler;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void init(Context context) {
        this.context = context.getApplicationContext();
        jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

    }

    /**
     * 每次添加一个任务
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void addJob(String location) {
        if (null == jobScheduler) {
            return;
        }
        JobInfo pendingJob = null;

        //整合多个job
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            pendingJob = jobScheduler.getPendingJob(jobId);
        } else {
            List<JobInfo> allPendingJobs = jobScheduler.getAllPendingJobs();
            for (JobInfo info : allPendingJobs) {
                if (info.getId()==jobId){
                    pendingJob=info;
                    break;
                }
            }
        }
        if (null!=pendingJob){
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                PersistableBundle extras=pendingJob.getExtras();
                //获取上一次的定位数据
                String data = extras.getString("DATA");
                //存储拼接方式 @
                location=data+"@"+location;
                jobScheduler.cancel(jobId);
            }
        }

        PersistableBundle extras=new PersistableBundle();

        extras.putString("DATA",location);
        JobInfo jobInfo=new JobInfo.Builder(jobId,new ComponentName(context,MyJobService.class))

                //在充电的时候执行
                .setRequiresCharging(true)
                //使用wifi
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setExtras(extras).build();

        //提交任务
        jobScheduler.schedule(jobInfo);




    }

}
