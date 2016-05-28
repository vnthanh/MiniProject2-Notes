package com.project.vnthanh.notes;

import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.LinearLayout;
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

        NotesList = FileManager.LoadNotesFromFile(fileInputStream); // may cause error, not error

        // notes ini
        /*Note temp = new Note("Note 1","Hey, i am note 1, 05-28 11:01:06.7623354-23366/com.project.vnthanh.notes D/dalvikvm: DEX prep '/data/data/com.project.vnthanh.notes/files/instant-run/dex-temp/reload0x0000.dex': ");
        Note temp1 =  new Note("Note 2","Hey, i am note 2");
        Note temp2 = new Note("Note 3","Hey, i am note 3");

        NotesList.add(temp);
        NotesList.add(temp1);
        NotesList.add(temp2);*/

        LinearLayout ll = (LinearLayout) findViewById(R.id.MyLinearLayout);

        for(int i=0;i<NotesList.size();i++){
            // Need to "attach" note class to note view
            MyNoteView noteView = new MyNoteView(this);

            Note currentNote = NotesList.get(i);

            // attach data
            noteView.setNoteToView(currentNote);
            noteView.setSingleLine(false);
            noteView.setLines(4); // hard-coded

            noteView.setText(currentNote.Title + "\n" + currentNote.Content);
            noteView.setBackgroundColor(Color.CYAN);

            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            llp.setMargins(0, 20, 0, 0); // llp.setMargins(left, top, right, bottom);
            noteView.setLayoutParams(llp);

            // set id and onClickListener base on ID, ID = title !!!, ID = index
            noteView.setId(i); // id = index
            noteView.setOnClickListener(this);

            ll.addView(noteView);
        }
    }

    // get note click event and hanle
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case 0:
                Toast.makeText(MainActivity.this, "Note " + v.getId(), Toast.LENGTH_SHORT).show();
                break;

            case 1:
                Toast.makeText(MainActivity.this, "Note " + v.getId(), Toast.LENGTH_SHORT).show();
                break;

            case 2:
                Toast.makeText(MainActivity.this, "Note " + v.getId(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void Bt_NewNote_Clicked(View view) {
        // Start new activity
    }
}
