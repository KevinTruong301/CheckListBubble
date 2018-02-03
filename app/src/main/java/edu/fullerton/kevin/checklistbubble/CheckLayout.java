package edu.fullerton.kevin.checklistbubble;

import android.content.Context;
import android.view.LayoutInflater;
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

    public CheckLayout(Context context, CheckList c){
        super(context);

        this.context = context;

        checkListDB = new CheckListDB(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_check, this, true);

        nameTextView = (TextView) findViewById(R.id.name);
        addButton = (Button) findViewById(R.id.add);
        deleteButton = (Button) findViewById(R.id.delete);

        setList(c);
    }

    public void setList(CheckList c){
        checkList = c;
        nameTextView.setText((c.getName()));
    }
}
