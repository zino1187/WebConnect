package com.example.zino.webconnect;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*안드로이드의 비동기 요청 기능을 지원하는 객체
    안드로이드에서는 메인쓰레드를 절대로 대기상태에 두거나, 무한루프에
    빠지게 하면 안된다..이렇게 될 경우 UI 를 제어할 실행부가 묶여 있게
    되므로 화면이 멈추거나 사용자의 터치를 인식하지 못하는 등 기능을
    못하게 된다. 따라서 안드로이드에서는 아예 메인스레드의 대기,루프,
    웹요청 자체를 에러 사항으로 간주한다..
    이 문제를 해결하려면 개발자는 요청은 Thread 로 처리하고, UI 반영은
    Handler 로 처리해야 하는데, 여간 불편한게 아니다..
    AsyncTask 객체를 이용하면 별도의 쓰레드와  Handler 를 사용하지
    않고 적절한 메서드를 사용하여 비동기 요청 및 UI 반영까지 쉽게 해결
    할 수 있다..
* */

public class URLDAO extends AsyncTask<String, Integer, String>{

    /*메인쓰레드에 의해 호출: UI,디자인 제어 가능*/
    protected void onPreExecute() {

    }

    /*비동기 요청이 가능한 메서드: UI,디자인 제어 불가능*/
    protected String doInBackground(String... params) {
        BufferedReader buffr=null;
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream is=httpURLConnection.getInputStream();
            buffr = new BufferedReader(new InputStreamReader(is));

            while(true){
                String data=buffr.readLine();
                if(data==null)break;
                sb.append(data);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(buffr !=null){
                try {
                    buffr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    /*메인쓰레드에 의해 호출: UI,디자인 제어 가능*/
    protected void onProgressUpdate(Integer... values) {

    }

    /*메인쓰레드에 의해 호출: UI,디자인 제어 가능*/
    protected void onPostExecute(String s) {
        /*JsonArray를 구성하여 ListView 에 출력*/

    }


}
