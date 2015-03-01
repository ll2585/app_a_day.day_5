package com.lukeli.appaday.day5;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class RoleDescriptionActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }

        if(savedInstanceState == null){
            RoleDescriptionFragment role_description = new RoleDescriptionFragment();
            role_description.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(android.R.id.content, role_description).commit();
        }
    }
}
