package edu.fullerton.kevin.checklistbubble;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Kevin on 2/4/2018.
 */

public class EditCheckList extends AppCompatActivity{
    private CheckListDB db;
    private Context context;

    private Intent intent;
    private int listId;
    private String listName;
    private ListView listView;
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.edit_checklist);

        intent = getIntent();

        listName = intent.getStringExtra("name");
        listId = intent.getIntExtra("checkListId", 0);

        context = getApplicationContext();
        listView = (ListView) findViewById(R.id.check_list_view);
        db = new CheckListDB(this);

        refreshCheckList();
    }

    public void refreshCheckList(){
        ArrayList<CheckList> list = db.getList(listId);

        CheckAdapter adapter = new CheckAdapter(context, list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.names_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.toString()){
            case "add": showAddDialog();
                //delete();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAddDialog(){
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText addEditDialog = (EditText) dialogView.findViewById(R.id.add_edit_dialog);

        dialogBuilder.setTitle("New Task");
        dialogBuilder.setMessage("Enter Task Name");
        dialogBuilder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.insertList(addEditDialog.getText().toString(), listId);
                refreshCheckList();
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Do Nothing
            }
        });

        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}
