package com.example.zino.webconnect;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* 스마트폰 내의 raw 리소스 디렉토리 안의 자원 중 data.json 을 가져오기*/
public class MovieDAO {
    Context context;
    BufferedReader buffr;
    StringBuffer sb=new StringBuffer();

    public MovieDAO(Context context) {
        this.context=context;
    }

    public String getData(){
        InputStream is=context.getResources().openRawResource(R.raw.data);

        buffr = new BufferedReader(new InputStreamReader(is));

        try {
            while(true) {
                String data=buffr.readLine();
                if(data==null)break;
                sb.append(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(buffr!=null)buffr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
