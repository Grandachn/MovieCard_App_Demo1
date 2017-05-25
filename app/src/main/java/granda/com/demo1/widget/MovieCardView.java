package granda.com.demo1.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import granda.com.demo1.R;

/**
 * Created by Granda on 4/30/2017.
 * 电影卡片的自定义View
 */
public class MovieCardView extends LinearLayout {
    private String movieId;
    private String name;
    private String picUrl;
    private float point;

    public MovieCardView(Context context) {
        super(context);
        init();
    }

    public MovieCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public String getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public float getPoint() {
        return point;
    }



    public void setDatas(String movieId , String name ,String picUrl , float point){
        this.movieId = movieId;
        this.name = name;
        this.picUrl = picUrl;
        this.point = point;
    }


    private void init() {
        inflate(getContext(), R.layout.movie_card, this);
    }
}
