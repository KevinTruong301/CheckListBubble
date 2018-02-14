package edu.fullerton.kevin.checklistbubble;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Kevin on 1/29/2018.
 */

public class CheckAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<CheckList> checkList;
    private Button deleteButton;
    private CheckListDB checkListDB;
    private int checkPosition;

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
        checkPosition = position;

        checkListDB = new CheckListDB(context);

        if(view == null){
            checkLayout = new CheckLayout(context, list);

        }
        else{
            checkLayout = (CheckLayout) view;
            checkLayout.setList(list);
        }

        deleteButton = (Button) checkLayout.findViewById(R.id.delete);
        deleteButton.setTag(position);

        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {

                checkListDB.deleteName(listID);
                int itemPosition = Integer.parseInt(view.getTag().toString());
                updateData(itemPosition);
            }
        });

        return checkLayout;
    }

    public void updateData(int itemPosition){
        checkList.remove(itemPosition);
        this.notifyDataSetChanged();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
