package edu.fullerton.kevin.checklistbubble;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Kevin on 2/4/2018.
 */

public class EditCheckList extends AppCompatActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.edit_checklist);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
