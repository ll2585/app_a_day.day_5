package com.lukeli.appaday.day5;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

public class RoleDescriptionFragment extends Fragment{

    public static RoleDescriptionFragment newInstance(int index){
        RoleDescriptionFragment r = new RoleDescriptionFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        r.setArguments(args);
        return r;
    }

    public int getCurIndex(){
        return getArguments().getInt("index", 0);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ScrollView scroller = new ScrollView(getActivity());

        TextView text = new TextView(getActivity());

        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getActivity().getResources().getDisplayMetrics());

        text.setPadding(padding,padding,padding,padding);

        scroller.addView(text);
        text.setText(Roles.ROLE_DESCRIPTION[getCurIndex()]);
        return scroller;
    }
}
