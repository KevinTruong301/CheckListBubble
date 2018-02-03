package edu.fullerton.kevin.checklistbubble;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Kevin on 1/29/2018.
 */

public class CheckAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<CheckList> checkList;

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        CheckLayout checkLayout = null;
        CheckList list = checkList.get(i);

        if(view == null){
            checkLayout = new CheckLayout(context, list);
        }
        else{
            checkLayout = (CheckLayout) view;
            checkLayout.setList(list);
        }

        return checkLayout;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
