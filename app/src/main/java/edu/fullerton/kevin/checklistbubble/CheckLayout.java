package edu.fullerton.kevin.checklistbubble;

import android.content.Context;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Kevin on 1/29/2018.
 */

public class CheckLayout extends RelativeLayout{
    private TextView nameTextView;
    private Button addButton;
    private Button deleteButton;

    private CheckList checkList;
    private CheckListDB checkListDB;
    private Context context;

    public CheckLayout(Context context) {
        super(context);
    }
}
