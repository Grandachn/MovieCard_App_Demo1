package granda.com.demo1.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import granda.com.demo1.R;
import granda.com.demo1.adapters.ContentAdapter;
import granda.com.demo1.widget.AutoLoadListView;

/**
 * Created by Granda on 5/18/2017.
 */
public class FirstFragment extends Fragment implements AutoLoadListView.Pagingable, SwipeRefreshLayout.OnRefreshListener{
    private AutoLoadListView mListView;
    private List<String> mDatas = new ArrayList<>();
    private ContentAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int mIndex;
    private View view;

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.home);
//        initUI();
//        initData();
//        addEvent();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        this.view = inflater.inflate(R.layout.home_refrsh_list, container, false);
        initUI();
        initData();
        addEvent();
        return this.view;
    }

    private void initUI() {

        mListView = (AutoLoadListView) this.view.findViewById(R.id.paging_list_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) this.view.findViewById(R.id.swp);
        //设置圈圈颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
    }

    private void initData() {
        generateData();
        mAdapter = new ContentAdapter(mDatas,this.getActivity());
        mListView.setAdapter(mAdapter);
        //设置还有更多数据加载
        mListView.setHasMoreItems(true);
    }

    /**
     * 生成数据
     */
    private void generateData() {
        for (int i = 0; i < 20; i++) {
            mDatas.add("数据" + mIndex ++);
        }
    }

    private void addEvent() {
        mListView.setPagingableListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * 加载更多数据
     */
    @Override
    public void onLoadMoreItems() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                generateData();
                mAdapter.notifyDataSetChanged();
                //设置为loading完成
                mListView.setIsLoading(false);
            }
        }, 1500);
    }

    /**
     * 刷新数据,把集合清空,把角标设置为0
     */
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas.clear();
                mIndex = 0;
                generateData();
                mAdapter.notifyDataSetChanged();
                //隐藏圈圈
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1500);
    }
}
