package com.project.vnthanh.notes;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Note> NotesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String path= Environment.getExternalStorageDirectory().toString();
        String fileName = "notes.txt";
        path=path+"/" + fileName;
        File file = new File(path);
        FileInputStream fileInputStream = null; // fix not ini warning
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // not create yet??!!
        try {
            NotesList = FileManager.LoadNotesFromFile(fileInputStream); // may cause error, not error
        }catch (Exception e)
        {
            // What ??!!
        }


        // Scrollview can host only one child -> create sub ll
        LinearLayout subLayout = (LinearLayout) findViewById(R.id.SubLinearLayout);

        // loop from latest note
        int nNotes = NotesList.size();
        for(int i=nNotes-1;i>=0;i--){
            // Need to "attach" note class to note view
            MyNoteView noteView = new MyNoteView(this);

            Note currentNote = NotesList.get(i);

            // attach data
            noteView.setNoteToView(currentNote);
            noteView.setSingleLine(false);
            noteView.setLines(4); // hard-coded

            noteView.setText(currentNote.Title + "\n" + currentNote.Content);
            noteView.setBackgroundColor(Color.CYAN);

            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            llp.setMargins(0, 20, 0, 0); // llp.setMargins(left, top, right, bottom);
            noteView.setLayoutParams(llp);

            // set id and onClickListener base on ID, ID = title !!!, ID = index
            noteView.setId(i); // id = index
            noteView.setOnClickListener(this);

            subLayout.addView(noteView);
            noteView.Start();

        }
    }

    // get note click event and hanle
    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "Note " + v.getId(), Toast.LENGTH_SHORT).show();
    }

    public void bt_NewNote_Clicked(View view) {
        // Start new activity (add note)
        Intent intent =  new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }


}
