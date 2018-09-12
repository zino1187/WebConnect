package com.example.zino.webconnect;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyListAdapter extends BaseAdapter{
    String TAG=this.getClass().getName();

    Context context;
    LayoutInflater layoutInflater;
    MovieDAO movieDAO;
    String jsonData;
    JSONArray jsonArray;

    public MyListAdapter(Context context) {
        this.context = context;
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /*json 데이터는 현재까지  String 에 불과하므로, 객체화 시켜 제어해본다*/
    public void parseJson(){
        movieDAO = new MovieDAO(context);
        jsonData=movieDAO.getData();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            jsonArray=jsonObject.getJSONArray("marvel");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /*총 몇건의 데이터가 있는지 반환*/
    public int getCount() {
        if(jsonArray==null){
            return 0;
        }else{
            return jsonArray.length();
        }
    }

    /*position 번째 아이템 하나를 반환*/
    public Object getItem(int position) {
        return null;
    }

    /*position 번째 아이템의  고유id 반환*/
    public long getItemId(int position) {
        return 0;
    }

    /*position 번째 마다 차지하게 될 아이템 반환
    * convertView 는 화면에서 완전히 사라지게 된 뷰 주소값이 넘어오므로, 개발자는 이 뷰를 재활용할지를 결정하면 된다.
    * */
    public View getView(int position, View convertView, ViewGroup parent) {
        /*
        Button bt = new Button(context);
        bt.setText("bt "+position);
        bt.setTextSize(60);
        Log.d(TAG, "pos = "+position+","+convertView+" AND "+bt);
        */
        View view=null;

        /*자바코드로 디자인을 구성하지 말고, xml  을 이용해본다!!*/
        if(convertView !=null) {/*재활용 뷰가 넘어온다면...*/
            view = convertView;
        }else{/*안 넘어오면, 새롭게 생성한다...*/
            view = layoutInflater.inflate(R.layout.movie, parent, false);
        }

        ImageView img=(ImageView)view.findViewById(R.id.img);
        TextView txt_title=(TextView)view.findViewById(R.id.txt_title);
        TextView txt_year=(TextView)view.findViewById(R.id.txt_year);

        JSONObject json=null;
        try {
            json=(JSONObject) jsonArray.get(position);

            txt_title.setText(json.getString("title"));
            txt_year.setText(json.getString("release_year"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //img.setImageDrawable();

        return view;
    }

}










