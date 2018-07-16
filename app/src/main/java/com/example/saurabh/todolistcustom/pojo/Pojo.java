package com.example.saurabh.todolistcustom.pojo;

/**
 * Created by Saurabh on 6/13/2017.
 */

public class Pojo {
    private String task,date,time;
    private String imgres;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getImgres() {
        return imgres;
    }

    public void setImgres(String imgres) {
        this.imgres = imgres;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Pojo{" +
                "task='" + task + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", imgres=" + imgres +
                '}';
    }

}
