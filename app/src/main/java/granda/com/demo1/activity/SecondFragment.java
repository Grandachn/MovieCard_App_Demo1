package granda.com.demo1.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import granda.com.demo1.R;

/**
 * Created by Granda on 5/18/2017.
 */
public class SecondFragment extends Fragment {
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        this.view = inflater.inflate(R.layout.second_fragment, container, false);
        return this.view;
    }
}
