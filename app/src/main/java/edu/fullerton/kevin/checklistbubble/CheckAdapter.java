package edu.fullerton.kevin.checklistbubble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kevin on 1/29/2018.
 */

public class CheckAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<CheckList> checkList;
    private Button deleteButton;
    private CheckListDB checkListDB;
    private int position;

    public CheckAdapter(Context context, ArrayList<CheckList> checkList) {
        this.context = context;
        this.checkList = checkList;
    }

    @Override
    public int getCount() {
        return checkList.size();
    }

    @Override
    public Object getItem(int i) {
        return checkList.get(i);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        CheckLayout checkLayout = null;
        CheckList list = checkList.get(position);
        final int listID = list.getId();
        this.position = position;

        checkListDB = new CheckListDB(context);

        if(view == null){
            checkLayout = new CheckLayout(context, list);

        }
        else{
            checkLayout = (CheckLayout) view;
            checkLayout.setList(list);
        }

        deleteButton = (Button) checkLayout.findViewById(R.id.delete);

        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {

                checkListDB.deleteName(listID);
                updateData();
            }
        });

        return checkLayout;
    }

    public void updateData(){
        checkList.remove(position);
        this.notifyDataSetChanged();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
