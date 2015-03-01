package com.lukeli.appaday.day5;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class StartGameActivity extends Activity {
    private Model m;
    private HashMap<String, TextView> userIDTextMap;
    private String desiredRole;
    private Spinner roleSpinner;
    private String yourName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int desiredIndex= getIntent().getExtras().getInt("desired_role_index");
        setContentView(R.layout.start_game_layout);
        desiredRole = Roles.DESIRED_ROLE_STRING[desiredIndex];
        yourName = getIntent().getExtras().getString("players_name");
        generateRoles();
    }

    private void generateRoles(){
        m = new Model();
        userIDTextMap = new HashMap<String, TextView>();
        m.addPlayer(new Player(yourName, "youID"));
        userIDTextMap.put("youID", (TextView) findViewById(R.id.player1_name));
        userIDTextMap.get("youID").setText(yourName);
        String random_name = getRandomPlayerName();
        m.addPlayer(new Player(random_name, "player_2"));
        userIDTextMap.put("player_2", (TextView) findViewById(R.id.player2_name));
        userIDTextMap.get("player_2").setText(random_name);

        random_name = getRandomPlayerName();
        while(m.playerExists(random_name)){
            random_name = getRandomPlayerName();
        }
        m.addPlayer(new Player(random_name, "player_3"));
        userIDTextMap.put("player_3", (TextView) findViewById(R.id.player3_name));
        userIDTextMap.get("player_3").setText(random_name);

        random_name = getRandomPlayerName();
        while(m.playerExists(random_name)){
            random_name = getRandomPlayerName();
        }
        m.addPlayer(new Player(random_name, "player_4"));
        userIDTextMap.put("player_4", (TextView) findViewById(R.id.player4_name));
        userIDTextMap.get("player_4").setText(random_name);

        random_name = getRandomPlayerName();
        while(m.playerExists(random_name)){
            random_name = getRandomPlayerName();
        }
        m.addPlayer(new Player(random_name, "player_5"));
        userIDTextMap.put("player_5", (TextView) findViewById(R.id.player5_name));
        userIDTextMap.get("player_5").setText(random_name);

        random_name = getRandomPlayerName();
        while(m.playerExists(random_name)){
            random_name = getRandomPlayerName();
        }
        m.addPlayer(new Player(random_name, "player_6"));
        userIDTextMap.put("player_6", (TextView) findViewById(R.id.player6_name));
        userIDTextMap.get("player_6").setText(random_name);

        random_name = getRandomPlayerName();
        while(m.playerExists(random_name)){
            random_name = getRandomPlayerName();
        }
        m.addPlayer(new Player(random_name, "player_7"));
        userIDTextMap.put("player_7", (TextView) findViewById(R.id.player7_name));
        userIDTextMap.get("player_7").setText(random_name);

        m.assignRoles(desiredRole);

    }

    private String getRandomPlayerName(){
        String[] possibleNames = {"Joel_N", "Deanna", "Lil_Lee_N", "Al_Lane", "Kay_Win", "Ping_Lee", "Alexa", "Brianna", "Steefanie", "Janeis"};
        Random rand = new Random();
        int rand_index =  rand.nextInt(possibleNames.length);
        return possibleNames[rand_index];

    }

    public void onShowRolesClick(View view) {
        Player p = m.getPlayerFromID("youID");
        String knowledge = p.getRole().getInfo();
        final ArrayList<String> playerIDs = p.getRole().getRelevantPlayers();
        for(String id : playerIDs){
            userIDTextMap.get(id).setBackgroundColor (Color.RED);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Your Info");
        builder.setMessage(knowledge);
        builder.setCancelable(true);

        final AlertDialog dlg = builder.create();

        dlg.show();

        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dlg.dismiss(); // when the task active then close the dialog
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                makeNotRed(playerIDs);

            }
        }, 2000); // after 2 second (or 2000 miliseconds), the task will be active.
    }
    private void makeNotRed(final ArrayList<String> playerIDs){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for(String id : playerIDs){
                    userIDTextMap.get(id).setBackgroundColor (Color.TRANSPARENT);
                }
//stuff that updates ui

            }
        });
    }
}
