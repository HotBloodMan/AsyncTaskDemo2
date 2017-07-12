package com.example.a1.asynctaskdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Button button;
    private ProgressBar progressBar;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button)findViewById(R.id.button03);
        progressBar = (ProgressBar)findViewById(R.id.progressBar02);
        textView = (TextView)findViewById(R.id.textView01);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownLoadTask downLoadTask = new DownLoadTask(textView, progressBar);
                downLoadTask.execute(1000);
            }
        });
    }



    class DownLoadTask extends AsyncTask<Integer,Integer,String>{

        private TextView textView;
        private ProgressBar progressBar;

        public DownLoadTask(TextView tv,ProgressBar pb){
            super();
            this.textView=tv;
            this.progressBar=pb;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("onPreExecute ");
            textView.setText("开始执行异步线程");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            System.out.println("onProgressUpdate ");
            int value=values[0];
            progressBar.setProgress(value);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println("onPostExecute ");
            textView.setText("异步操作执行结束"+s);
        }

        @Override
        protected String doInBackground(Integer... params) {
            System.out.println("doInBackground ");
            NetOperator netOperator = new NetOperator();
            int i=0;
            for(i=10;i<=100;i+=10){
                netOperator.operator();
                publishProgress(i);
            }
            return i+params[0].intValue()+"";
        }
    }
}
