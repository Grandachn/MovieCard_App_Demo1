package granda.com.demo1.https;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import granda.com.demo1.entity.MovieCard;

/**
 * Created by Granda on 5/23/2017.
 */
public class AsynMovieCardLoader  extends AsyncTask<Object, Object, Object> {
    private  Handler mHandler;
    private String mUrl;

    public AsynMovieCardLoader(String url,Handler handler){
        this.mUrl = url;
        this.mHandler = handler;
    }
    @Override
    protected Object doInBackground(Object... params){
        String strResult = "";
        try {
           strResult = readParse(mUrl);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return strResult;
    }

    public static String readParse(String urlPath) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        URL url = new URL(urlPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream inStream = conn.getInputStream();
        while ((len = inStream.read(data)) != -1) {

            outStream.write(data, 0, len);

        }
        inStream.close();
        String test = outStream.toString();
        return outStream.toString();
    }



    @Override

    protected void onCancelled(Object result) {

        // TODO Auto-generated method stub

        super.onCancelled(result);

    }



    @Override

    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        try {
            //创建一个JSON对象
            //JSONObject jsonObject = new JSONObject(result.toString()).getJSONObject("lists");

            String test = result.toString();
            //获取某个对象的JSON数组
            JSONArray jsonArray = new JSONObject(result.toString()).getJSONArray("lists");
            StringBuilder builder = new StringBuilder();
            List<MovieCard> lists = new ArrayList<>();


            for(int i = 0; i<jsonArray.length(); i++) {
                //新建一个JSON对象，该对象是某个数组里的其中一个对象
                MovieCard entity = new MovieCard();
                JSONObject jsonObject2 = (JSONObject)jsonArray.opt(i);
                entity.setId(jsonObject2.getString("id")); //获取数据
                entity.setName(jsonObject2.getString("name"));
                entity.setPicUrl(jsonObject2.getString("picUrl"));
                entity.setPoint(jsonObject2.getString("point"));
                lists.add(entity);
            }

            Message msg = mHandler.obtainMessage();
            if(lists !=null && lists.size() > 0){
                msg.what = 1;
                msg.obj = lists;
            }else{
                msg.what = 2;
            }
            mHandler.sendMessage(msg);
        }

        catch (JSONException e) {
            e.printStackTrace();

        }
    }



    @Override

    protected void onPreExecute() {

        // TODO Auto-generated method stub

    }



    @Override

    protected void onProgressUpdate(Object... values) {

        // TODO Auto-generated method stub

        super.onProgressUpdate(values);

    }

}

