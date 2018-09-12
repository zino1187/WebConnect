package com.example.zino.webconnect;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLImgFragment extends Fragment {
    Button bt_load;
    ImageView img;
    Thread thread;
    Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.urlimg_layout, container, false);
        bt_load = (Button)view.findViewById(R.id.bt_load);
        img=(ImageView)view.findViewById(R.id.img);

        handler = new Handler(){
            public void handleMessage(Message msg) {
                Bitmap bitmap=(Bitmap) msg.getData().get("bitmap");
                img.setImageBitmap(bitmap);
            }
        };

        bt_load.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                thread = new Thread(){
                    public void run() {
                        loadImg();
                    }
                };
                thread.start();
            }
        });
        return view;
    }

    /*웹상의 이미지를 가져온다*/
    public void loadImg(){
        try {
            URL url = new URL("https://images-na.ssl-images-amazon.com/images/I/51xEeVYLqrL._SX342_.jpg");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream is=httpURLConnection.getInputStream();

            Bitmap bitmap=BitmapFactory.decodeStream(is);

            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putParcelable("bitmap", bitmap);
            message.setData(bundle);

            handler.sendMessage(message);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}













