package com.lukeli.appaday.day5;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RolesFragment extends ListFragment{
    boolean is_horizontal;

    int current_role_selected;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, Roles.ROLE_NAMES);

        setListAdapter(adapter);

        View role_description_screen = getActivity().findViewById(R.id.role_description);

        is_horizontal = role_description_screen != null && role_description_screen.getVisibility() == View.VISIBLE;

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        if(savedInstanceState != null){
            current_role_selected = savedInstanceState.getInt("selected_index", 0);
        }else{
            current_role_selected = 0;
            getListView().setItemChecked(current_role_selected, true);
            ((SelectRoleActivity) getActivity()).setSelectedIndex(current_role_selected);
        }


        if(is_horizontal){

            showRoleInfo(current_role_selected);
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selected_index", current_role_selected);
        ((SelectRoleActivity) getActivity()).setSelectedIndex(current_role_selected);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showRoleInfo(position);
    }

    private void showRoleInfo(int index){
        current_role_selected = index;
        getListView().setItemChecked(index, true);
        ((SelectRoleActivity) getActivity()).setSelectedIndex(current_role_selected);
        if(is_horizontal) {


            RoleDescriptionFragment roleInfo = (RoleDescriptionFragment) getFragmentManager().findFragmentById(R.id.role_description);

            if (roleInfo == null || roleInfo.getCurIndex() != index) {
                roleInfo = RoleDescriptionFragment.newInstance(index);
                FragmentManager fragmentManager = getFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.role_description, roleInfo);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                fragmentTransaction.commit();
                getListView().setItemChecked(index, true);
            }
        }else{
                Intent intent = new Intent();

                intent.setClass(getActivity(), RoleDescriptionActivity.class);
                intent.putExtra("index", index);
                startActivity(intent);

        }

    }
}
