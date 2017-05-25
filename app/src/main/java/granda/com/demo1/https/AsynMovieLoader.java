package granda.com.demo1.https;


import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import granda.com.demo1.entity.Movie;
import granda.com.demo1.entity.MovieCard;
import granda.com.demo1.entity.MoviePic;

/**
 * Created by Granda on 5/23/2017.
 */
public class AsynMovieLoader extends AsyncTask<Object, Object, Object> {
    private  Handler mHandler;
    private String mUrl;

    public AsynMovieLoader(String url, Handler handler){
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
            JSONObject movie = new JSONObject(result.toString()).getJSONObject("movie");
            List<MoviePic> picLists = new ArrayList<>();



            //新建一个JSON对象，该对象是某个数组里的其中一个对象
            Movie entity = new Movie();
            entity.setId(movie.getString("id")); //获取数据
            entity.setName(movie.getString("name"));
            entity.setAlias(movie.getString("alias"));
            entity.setTags(movie.getString("tags"));
            entity.setRegion(movie.getString("region"));
            entity.setYear(movie.getString("year"));
            entity.setDirector(movie.getString("director"));
            entity.setScreenWriter(movie.getString("screenWriter"));
            entity.setActor(movie.getString("actor"));
            entity.setPoint(movie.getString("point"));
            entity.setDouBanId(movie.getString("douBanId"));
            entity.setPicUrl(movie.getString("picUrl"));

            JSONArray pics = movie.getJSONArray("moviePics");
            for(int i = 0; i<pics.length(); i++) {
                //新建一个JSON对象，该对象是某个数组里的其中一个对象
                MoviePic moviePic = new MoviePic();
                JSONObject jsonObject = (JSONObject)pics.opt(i);
                moviePic.setId(Integer.parseInt(jsonObject.getString("id"))); //获取数据
                moviePic.setMovieId(jsonObject.getString("movieId"));
                moviePic.setPicUrl(jsonObject.getString("picUrl"));
                picLists.add(moviePic);
            }

            entity.setMoviePics(picLists);


            Message msg = mHandler.obtainMessage();

            msg.what = 1;
            msg.obj = entity;

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

