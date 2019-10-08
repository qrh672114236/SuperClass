package com.example.superclass.structure;

import android.icu.text.UFormat;

public class Array<E> {

    private E [] data;
    private int size;

    public Array(int capacity) {
        data = (E[]) new Object [capacity];
        size = 0;
    }

    public Array() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public void addLast(E e) throws IllegalAccessException {
        if (size == data.length) {
            throw new IllegalAccessException("AddLastFailed");
        }
        data[size] = e;
        size++;
    }

    public void add(int index,E e) throws IllegalAccessException {

        if (index<0||index>size){
            throw new IllegalAccessException("Add Failed");
        }

        if (size == data.length) {
           resize(2*data.length);
        }
        for(int i=size-1;i>=index;i--){
            data[i+1]=data[i];
            data[index]=e;
            size++;
        }
    }

    private void resize(int newCapacity) {
        E[] newData= (E[]) new Object[newCapacity];
        for (int i=0 ;i<size;i++){
            newData[i]=data[i];
        }
        data=newData;
    }

    public void addFirst(E e) throws IllegalAccessException {
        add(0,e);
    }

    @Override
    public String toString(){
        StringBuilder res=new StringBuilder();
        res.append(String.format("Array:size= %d ,capacity =%d \n",size,data.length));
        res.append("[");
        for (int i =0; i < size; i++) {
            res.append(data[i]);
            if (i!=size-1){
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }
    E get(int index){
        if (index<0 ||index>size){

        }
        return data[index];
    }

    void set(int index,E e){
        data[index]=e;
    }

    public boolean contains(E e){
        for (int i=0;i<size;i++){
            if (data[i].equals(e) ){
                return true;
            }
        }
        return false;
    }

    public int find(E e){
        for (int i=0;i<size;i++){
            if (data[i].equals(e)){
                return i ;
            }
        }
        return -1;
    }

    public E remove(int index){
        E ret=data[index];
        for (int i=index+1;i<size;i++){
                data[i-1]=data[i];
        }
        return ret;
    }

    public void removeElement(E e){
        int index =find(e);
        if (index!=-1){
            remove(index);
        }
    }
}


