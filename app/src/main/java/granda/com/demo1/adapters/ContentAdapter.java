package granda.com.demo1.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import granda.com.demo1.R;
import granda.com.demo1.activity.MovieDetailActivity;
import granda.com.demo1.https.AsynImageLoader;
import granda.com.demo1.widget.MovieCardView;

/**
 * Created by Granda on 4/30/2017.
 * 主页的ListView的Adapter
 */
public class ContentAdapter extends BaseAdapter implements View.OnClickListener{
    private List<String> mDatas;
    private Context mContext;

    public ContentAdapter(List<String> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.movie_card_list, viewGroup, false);
            viewHolder = new ViewHolder();

            viewHolder.imageButton1 = (MovieCardView) view.findViewById(R.id.imageButton1);
            viewHolder.imageButton2 = (MovieCardView) view.findViewById(R.id.imageButton2);
            viewHolder.imageButton3 = (MovieCardView) view.findViewById(R.id.imageButton3);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //获取viewHolder实例
        viewHolder = (ViewHolder)view.getTag();

        //设置数据

        ImageView imageView = (ImageView) viewHolder.imageButton1.findViewById(R.id.movie_card_image);
        String imageUrl = "https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2454868217.jpg";
        AsynImageLoader asynImageLoader = new AsynImageLoader();
        asynImageLoader.showImageAsyn(imageView, imageUrl, R.drawable.test);

        ImageView imageView2 = (ImageView) viewHolder.imageButton2.findViewById(R.id.movie_card_image);
        String imageUrl2 ="https://img3.doubanio.com/view/photo/thumb/public/p2456056900.jpg";
        new AsynImageLoader().showImageAsyn(imageView2, imageUrl2, R.drawable.test);


        ImageView imageView3 = (ImageView) viewHolder.imageButton3.findViewById(R.id.movie_card_image);
        String imageUrl3 =" https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2455156816.jpg";
        new AsynImageLoader().showImageAsyn(imageView3, imageUrl3, R.drawable.test);

        viewHolder.imageButton1.setOnClickListener(this);
        viewHolder.imageButton2.setOnClickListener(this);
        viewHolder.imageButton3.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.imageButton1:
                Log.d("tag", "Btn_onClick: " + "view = " + view);
                Intent intent = new Intent(mContext,MovieDetailActivity.class);
                mContext.startActivity(intent);
               // Toast.makeText(mContext,"imageButton1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageButton2:
                Log.d("tag", "Tv_onClick: " + "view = " + view);
                Toast.makeText(mContext,"imageButton2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageButton3:
                Log.d("tag", "Btn_onClick: " + "view = " + view);
                Toast.makeText(mContext,"imageButton3",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private static class ViewHolder {


        MovieCardView imageButton1;
        MovieCardView imageButton2;
        MovieCardView imageButton3;
    }
}
