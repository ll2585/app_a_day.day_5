package com.lukeli.appaday.day5;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.login_fragment, container, false);
        Button loginButton = (Button) view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setClass(getActivity(), SelectRoleActivity.class);
                String players_name = ((EditText) view.findViewById(R.id.your_name_edit_text)).getText().toString();
                if(players_name.equals("")){
                    players_name = "Your name";
                }

                intent.putExtra("players_name", players_name);
                startActivity(intent);
            }
        });
        return view;

    }
}
