package com.lukeli.appaday.day5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectRoleActivity extends Activity {
    int selected_index;
    String players_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_role_screen);
        Button loginButton = (Button) findViewById(R.id.start_button);
        final Activity this_activity = this;
        selected_index = -1;
        players_name = getIntent().getExtras().getString("players_name");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setClass(this_activity, StartGameActivity.class);
                intent.putExtra("desired_role_index", selected_index);
                intent.putExtra("players_name", players_name);

                startActivity(intent);
            }
        });
    }

    public void setSelectedIndex(int index){
        selected_index = index;
    }
}
