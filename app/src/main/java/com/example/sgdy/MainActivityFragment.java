package com.example.sgdy;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.sgdy.coreutil.DataFormatter;

import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    View view;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        initVariable();
        return view;
    }

    private void initVariable() {
        TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(DataFormatter.d(1142) + "\n" +
        DataFormatter.f(4,42) + "\n" +
        DataFormatter.x(42) + "\n" +
        DataFormatter.c(42) + "\n" +
        DataFormatter.tc(new Date()) + "\n" +
        DataFormatter.tr(new Date()) + "\n" +
        DataFormatter.taTbTd(new Date()));
    }
}
