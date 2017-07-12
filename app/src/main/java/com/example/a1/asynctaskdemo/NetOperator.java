package com.example.a1.asynctaskdemo;

/**
 * Created by 1 on 2017/7/12.
 */

public class NetOperator {
    public void operator(){
        try {
            //休眠1秒
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
