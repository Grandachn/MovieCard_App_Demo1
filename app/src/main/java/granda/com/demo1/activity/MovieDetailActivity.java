package granda.com.demo1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntegerRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import granda.com.demo1.R;
import granda.com.demo1.adapters.ContentAdapter;
import granda.com.demo1.adapters.DetailPicPageViewAdapter;
import granda.com.demo1.entity.Movie;
import granda.com.demo1.entity.MovieCard;
import granda.com.demo1.https.AsynImageLoader;
import granda.com.demo1.https.AsynMovieCardLoader;
import granda.com.demo1.https.AsynMovieLoader;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Granda on 5/2/2017.
 * 电影详情Activity
 */
public class MovieDetailActivity extends AppCompatActivity {

    private Movie movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);


        ImageView returnArrow = (ImageView) findViewById(R.id.return_arrow);
        returnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Intent getIntent = getIntent();
        TextView name = (TextView) findViewById(R.id.return_bar_title);
        name.setText(getIntent.getStringExtra("name"));


        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        movie = (Movie) msg.obj;
                        init(movie);
                        break;
                    default:
                        break;
                }
            }
        };

        String url = "http://123.207.31.65:8080/moviecard_bg/movie/detail.json?movieId=" + getIntent.getStringExtra("movieId") ;
        AsynMovieLoader task = new AsynMovieLoader(url, handler);
        task.execute();
    }

    private void init(final Movie movie){
        //显示图片
        ViewPager viewPager = (ViewPager) findViewById(R.id.movie_detail_viewpager);
        LayoutInflater inflater=getLayoutInflater();
        View view1 = inflater.inflate(R.layout.movie_detail_pics, null);
        ImageView imageView1 = (ImageView) view1.findViewById(R.id.movie_detail_pic);
        new AsynImageLoader().showImageAsyn(imageView1, movie.getMoviePics().get(0).getPicUrl(), R.drawable.test);

        View view2 = inflater.inflate(R.layout.movie_detail_pics, null);
        ImageView imageView2 = (ImageView) view2.findViewById(R.id.movie_detail_pic);
        new AsynImageLoader().showImageAsyn(imageView2,  movie.getMoviePics().get(1).getPicUrl(), R.drawable.test);

        View view3 = inflater.inflate(R.layout.movie_detail_pics, null);
        ImageView imageView3 = (ImageView) view3.findViewById(R.id.movie_detail_pic);
        new AsynImageLoader().showImageAsyn(imageView3,  movie.getMoviePics().get(2).getPicUrl(), R.drawable.test);

        TextView tv_point = (TextView) findViewById(R.id.detail_point_text);
        tv_point.setText(movie.getPoint());

        ImageView pointImageView = (ImageView) findViewById(R.id.detail_point_pic);
        if(Float.parseFloat(movie.getPoint()) == 0.0 ){
            pointImageView.setImageResource(R.drawable.star_0);
        }else if(Float.parseFloat(movie.getPoint()) < 2.0 ){
            pointImageView.setImageResource(R.drawable.star_1);
        }else if(Float.parseFloat(movie.getPoint()) < 4.0 ){
            pointImageView.setImageResource(R.drawable.star_2);
        }else if(Float.parseFloat(movie.getPoint()) < 6.0 ){
            pointImageView.setImageResource(R.drawable.star_3);
        }else if(Float.parseFloat(movie.getPoint()) < 8.0 ){
            pointImageView.setImageResource(R.drawable.star_4);
        }else if(Float.parseFloat(movie.getPoint()) < 10.0 ){
            pointImageView.setImageResource(R.drawable.star_5);
        }

        //豆瓣页面
        TextView douban = (TextView) findViewById(R.id.movie_douban_text);
        douban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this, DoubanPageActivity.class);
                intent.putExtra("doubanId" , movie.getDouBanId());
                startActivity(intent);
            }
        });

        ArrayList<View> viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        //小白点效果
        DetailPicPageViewAdapter adapter = new DetailPicPageViewAdapter<View>(viewList);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.movie_detail_indicator);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        adapter.registerDataSetObserver(indicator.getDataSetObserver());

    }
}
