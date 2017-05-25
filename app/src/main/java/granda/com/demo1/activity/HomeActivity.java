package granda.com.demo1.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import granda.com.demo1.R;


/**
 * 主页Activity
 */
public class HomeActivity extends AppCompatActivity {

    private TextView title;

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThridFragment thridFragment;

    private LinearLayout button1;
    private LinearLayout button2;
    private LinearLayout button3;

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;

    private int count = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        setDefaultFragment();

        title = (TextView) findViewById(R.id.titlebar);

        button1 = (LinearLayout) findViewById(R.id.button);
        button2 = (LinearLayout) findViewById(R.id.button2);
        button3 = (LinearLayout) findViewById(R.id.button3);

        imageView1 = (ImageView) findViewById(R.id.image_1);
        imageView2 = (ImageView) findViewById(R.id.image_2);
        imageView3 = (ImageView) findViewById(R.id.image_3);
        imageView1.setImageResource(R.drawable.discovery_green);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                if(null == firstFragment) {
                    firstFragment = new FirstFragment();
                }
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.replace(R.id.id_content, firstFragment);
                transaction.commit();
                imageView1.setImageResource(R.drawable.discovery_green);
                imageView2.setImageResource(R.drawable.file);
                imageView3.setImageResource(R.drawable.me);
                title.setText("最新");
                count++;
                if(count == 2){
                    firstFragment.getmListView().smoothScrollToPosition(0);
                    count = 0;
                }
                new Thread(new Runnable(){
                    public void run() {
                        try {
                            Thread.sleep(500);
                            count = 0;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }}).start();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                if(null == secondFragment) {
                    secondFragment = new SecondFragment();
                }
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.replace(R.id.id_content, secondFragment);
                transaction.commit();
                imageView1.setImageResource(R.drawable.discovery);
                imageView2.setImageResource(R.drawable.file_green);
                imageView3.setImageResource(R.drawable.me);
                title.setText("收藏");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                if(null == thridFragment) {
                    thridFragment = new ThridFragment();
                }
                //淡入淡出的默认动画
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.replace(R.id.id_content, thridFragment);
                transaction.commit();
                imageView1.setImageResource(R.drawable.discovery);
                imageView2.setImageResource(R.drawable.file);
                imageView3.setImageResource(R.drawable.me_green);
                title.setText("个人");
            }
        });
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        firstFragment = new FirstFragment();
        transaction.replace(R.id.id_content, firstFragment);
        transaction.commit();
    }
}
