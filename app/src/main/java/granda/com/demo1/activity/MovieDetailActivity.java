package granda.com.demo1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import granda.com.demo1.R;
import granda.com.demo1.adapters.DetailPicPageViewAdapter;
import granda.com.demo1.https.AsynImageLoader;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Granda on 5/2/2017.
 * 电影详情Activity
 */
public class MovieDetailActivity extends AppCompatActivity {


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
        //显示图片
        ViewPager viewPager = (ViewPager) findViewById(R.id.movie_detail_viewpager);
        LayoutInflater inflater=getLayoutInflater();
        View view1 = inflater.inflate(R.layout.movie_detail_pics, null);
        ImageView imageView1 = (ImageView) view1.findViewById(R.id.movie_detail_pic);
        String imageUrl1 ="https://img3.doubanio.com/view/photo/photo/public/p2456739325.jpg";
        new AsynImageLoader().showImageAsyn(imageView1, imageUrl1, R.drawable.test);

        View view2 = inflater.inflate(R.layout.movie_detail_pics, null);
        ImageView imageView2 = (ImageView) view2.findViewById(R.id.movie_detail_pic);
        String imageUrl2 ="https://img3.doubanio.com/view/photo/photo/public/p2456575325.jpg";
        new AsynImageLoader().showImageAsyn(imageView2, imageUrl2, R.drawable.test);

        View view3 = inflater.inflate(R.layout.movie_detail_pics, null);
        ImageView imageView3 = (ImageView) view3.findViewById(R.id.movie_detail_pic);
        String imageUrl3 ="https://img3.doubanio.com/view/photo/photo/public/p2456575321.jpg";
        new AsynImageLoader().showImageAsyn(imageView3, imageUrl3, R.drawable.test);


        //豆瓣页面
        TextView douban = (TextView) findViewById(R.id.movie_douban_text);
        douban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this, DoubanPageActivity.class);
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
