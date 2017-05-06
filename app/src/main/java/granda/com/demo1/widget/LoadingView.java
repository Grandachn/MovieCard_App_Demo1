package granda.com.demo1.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import granda.com.demo1.R;

/**
 *底部显示loading的view
 */
public class LoadingView extends LinearLayout {

    public LoadingView(Context context) {
        super(context);
        init();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.loading_view, this);
    }


}
