package edu.fullerton.kevin.checklistbubble;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

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
    private int checkListID;

    public CheckLayout(Context context) {
        super(context);
    }

    public CheckLayout(Context context, CheckList name){
        super(context);

        this.context = context;

        checkListDB = new CheckListDB(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_check, this, true);

        nameTextView = (TextView) findViewById(R.id.name);
        addButton = (Button) findViewById(R.id.add);
        deleteButton = (Button) findViewById(R.id.delete);

        setList(name);

        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {

                checkListDB.deleteName(checkListID);
            }
        });

    }

    public void setList(CheckList c){
        checkList = c;
        nameTextView.setText((c.getName()));
        checkListID = c.getId();
    }
}
