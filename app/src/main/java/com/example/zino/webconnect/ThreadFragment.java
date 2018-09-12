package com.example.zino.webconnect;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ThreadFragment extends Fragment implements View.OnClickListener{
    String TAG=this.getClass().getName();
    Button bt_crawling;
    TextView txt_content;
    Thread thread;
    Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.thread_layout, container, false);

        bt_crawling=(Button)view.findViewById(R.id.bt_crawling);
        txt_content=(TextView) view.findViewById(R.id.txt_content);

        bt_crawling.setOnClickListener(this);

        handler=new Handler(){
            /*동생 쓰레드들이 요청을 할때 아래의 메서드가 실행됨
            * 그리고 아래의 메서드는 GUI 를 제어할 수 있음.
            * 즉 메인쓰레드(Original Thread)에 의해 수행되기 때문..
            * */
            public void handleMessage(Message msg) {
                /*UI에반영하기*/
                String data=msg.getData().getString("data");
                txt_content.setText(data);
            }
        };

        return view;
    }

    public String loadFromUrl(){
        BufferedReader buffr=null;
        StringBuffer sb = new StringBuffer();

        try {
            URL url = new URL("http://172.30.1.26:8888/data.json");
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            InputStream is=httpURLConnection.getInputStream();
            buffr = new BufferedReader(new InputStreamReader(is));

            while(true){
                String data=buffr.readLine();
                if(data==null)break;
                    sb.append(data+"\n");
            }
            Log.d(TAG, sb.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(buffr!=null){
                try {
                    buffr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    @Override
    public void onClick(View v) {
        thread = new Thread(){
            public void run() {
                String data=loadFromUrl();
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("data", data);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        };

        thread.start();
    }

}
