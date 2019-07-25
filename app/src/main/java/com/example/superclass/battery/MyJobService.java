package com.example.superclass.battery;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {

    //在任务开始执行时触发。返回false表示执行完毕，返回true表示需要开发者自己调用jobFinished方法通知系统已执行完成。
    @Override
    public boolean onStartJob(JobParameters params) {
        new   MyAsyncTask().execute(params);
        return false;
    }
    //在任务停止执行时触发
    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    class MyAsyncTask extends AsyncTask<JobParameters,Void,Void>{
        JobParameters jobParameters;
        @Override
        protected Void doInBackground(JobParameters... object) {
            jobParameters=object[0];
            PersistableBundle extras = jobParameters.getExtras();
            String location=extras.getString("DATA","");
            //网络上传

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
//            jobFinished(jobParameters,false);
            super.onPostExecute(aVoid);
        }
    }
}
