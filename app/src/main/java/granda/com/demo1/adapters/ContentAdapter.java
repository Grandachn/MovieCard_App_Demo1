package granda.com.demo1.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

import granda.com.demo1.R;
import granda.com.demo1.activity.FirstFragment;
import granda.com.demo1.activity.MovieDetailActivity;
import granda.com.demo1.entity.MovieCard;
import granda.com.demo1.https.AsynImageLoader;
import granda.com.demo1.widget.MovieCardView;

/**
 * Created by Granda on 4/30/2017.
 * 主页的ListView的Adapter
 */
public class ContentAdapter extends BaseAdapter implements View.OnClickListener{
    private List<MovieCard> mDatas;
    private Context mContext;
    private Bitmap star_0;
    private Bitmap star_1;
    private Bitmap star_2;
    private Bitmap star_3;
    private Bitmap star_4;
    private Bitmap star_5;

    public ContentAdapter(List<MovieCard> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        star_0 = readBitMap(mContext, R.drawable.star_0);
        star_1 = readBitMap(mContext, R.drawable.star_1);
        star_2 = readBitMap(mContext, R.drawable.star_2);
        star_3 = readBitMap(mContext, R.drawable.star_3);
        star_4 = readBitMap(mContext, R.drawable.star_4);
        star_5 = readBitMap(mContext, R.drawable.star_5);
    }

    public  Bitmap readBitMap(Context context, int resId){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        //获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is,null,opt);
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size()/3;
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
        MovieCard entity = mDatas.get(i * 3 + 0);
        initMovieCard(entity,viewHolder.imageButton1);


        entity = mDatas.get(i * 3 + 1);
        initMovieCard(entity,viewHolder.imageButton2);


        entity = mDatas.get(i * 3 + 2);
        initMovieCard(entity,viewHolder.imageButton3);


        return view;
    }

    @Override
    public void onClick(View view) {

            Log.d("tag", "Btn_onClick: " + "view = " + view);
            Intent intent = new Intent(mContext,MovieDetailActivity.class);
            MovieCardView entity = (MovieCardView)view;
            intent.putExtra("movieId" , entity.getMovieId());
            intent.putExtra("name" , entity.getName());
            mContext.startActivity(intent);

    }

    private static class ViewHolder {

        MovieCardView imageButton1;
        MovieCardView imageButton2;
        MovieCardView imageButton3;
    }

    private void initMovieCard(MovieCard entity,final MovieCardView imageButton){
        imageButton.setDatas(entity.getId(), entity.getName(), entity.getPicUrl(), Float.parseFloat(entity.getPoint()));

        ImageView imageView = (ImageView) imageButton.findViewById(R.id.movie_card_image);
        new AsynImageLoader().showImageAsyn(imageView, imageButton.getPicUrl(), R.drawable.aphal0);

        TextView title = (TextView) imageButton.findViewById(R.id.movie_card_title);
        title.setText(String.valueOf(imageButton.getName()));
        if(String.valueOf(imageButton.getName()).length() > 7){
            String titleShorcut = String.valueOf(imageButton.getName()).subSequence(0,7) + "...";
            title.setText(titleShorcut);
        }

        TextView point = (TextView) imageButton.findViewById(R.id.movie_card_point_text);
        point.setText(String.valueOf(imageButton.getPoint()));



        ImageView pointImageView = (ImageView) imageButton.findViewById(R.id.movie_card_point_image);
        if(imageButton.getPoint() == 0.0 ){
            pointImageView.setImageBitmap(star_0);
        }else if(imageButton.getPoint() < 2.0 ){
            pointImageView.setImageBitmap(star_1);
        }else if(imageButton.getPoint() < 4.0 ){
            pointImageView.setImageBitmap(star_2);
        }else if(imageButton.getPoint() < 6.0 ){
            pointImageView.setImageBitmap(star_3);
        }else if(imageButton.getPoint() < 8.0 ){
            pointImageView.setImageBitmap(star_4);
        }else if(imageButton.getPoint() < 10.0 ){
            pointImageView.setImageBitmap(star_5);
        }

        imageButton.setOnClickListener(this);
    }
}
