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
    public MovieCardView(Context context) {
        super(context);
        init();
    }

    public MovieCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        inflate(getContext(), R.layout.movie_card, this);
    }
}
