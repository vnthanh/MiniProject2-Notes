package com.project.vnthanh.notes;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Note> NotesList = new ArrayList<>();

    LinearLayout subLayout;

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
        subLayout = (LinearLayout) findViewById(R.id.SubLinearLayout);

        // loop from latest note
        int nNotes = NotesList.size();
        for(int i=0;i<nNotes;i++){
            // Need to "attach" note class to note view
            final MyNoteView noteView = new MyNoteView(this);

            Note currentNote = NotesList.get(i);

            // attach data
            noteView.setNoteToView(currentNote);
            noteView.setSingleLine(false);
            noteView.setLines(4); // hard-coded

            /*noteView.setText(currentNote.Title + "\n" +
                    currentNote.Content + "\n\n" +
                    currentNote.RemindTime.toString() + " " +
                    currentNote.RemindDate.toString());
            */
            noteView.setBackgroundColor(Color.parseColor("#A7FFEB")); // background, # title rect color

            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            llp.setMargins(0, 20, 0, 0); // llp.setMargins(left, top, right, bottom);
            noteView.setLayoutParams(llp);

            // set id and onClickListener base on ID, ID = title !!!, ID = index
            noteView.setId(i); // id = index
            noteView.setOnClickListener(this);

            // add long click listener and event handler
            noteView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    //
                    deleteButtonClickedIdx = v.getId() + 1;

                    final Button belowDeleteButton = new Button(MainActivity.this);

                    //Button deleteButton = (Button) findViewById(R.id.bt_Delete); // just for param, nothing to use here
                    //deleteButton.setVisibility(View.VISIBLE);
                    //belowDeleteButton.setLayoutParams(deleteButton.getLayoutParams());

                    belowDeleteButton.setBackground(Drawable.createFromPath("@android:drawable/dialog_holo_light_frame"));
                    belowDeleteButton.setBackgroundColor(Color.parseColor("#EEEEEE"));
                    belowDeleteButton.setTextSize(15);
                    belowDeleteButton.setText("Delete this note");

                    subLayout.addView(belowDeleteButton, v.getId()+1); // bugs ?????!!!!!!!!!!!!!!!


                    belowDeleteButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            subLayout.removeView(belowDeleteButton); // remove right away delete button

                            //Remove from array list
                            // bug when remove by id due to update index problem, -> remove by title

                            int position = -1; // to fix bug

                            for (int i=0;i<NotesList.size();i++)
                            {
                                if (NotesList.get(i).Title==noteView.note.Title){
                                    position = i;
                                    NotesList.remove(i);
                                }
                            }

                            // Remove from parent view
                            //subLayout.removeViewAt(noteView.getId());
                            subLayout.removeViewAt(position); // fixed
                            //Toast.makeText(MainActivity.this, "size = " + NotesList.size() + "-id = " + noteView.getId(), Toast.LENGTH_SHORT).show();

                            //Update to file
                            // write all notes to file (with 1 more note -> dummy code)
                            String path= Environment.getExternalStorageDirectory().toString();
                            String fileName = "notes.txt";
                            path=path+"/" + fileName;
                            File outFile = new File(path);

                            PrintStream printStream = null;
                            try {
                                printStream = new PrintStream(outFile);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            FileManager.SaveNotesToFile(NotesList,printStream);

                            //deleteButton.setVisibility(View.GONE);
                            //belowDeleteButton.setVisibility(View.GONE);

                        }
                    });

                    return false;
                }
            });

            subLayout.addView(noteView);
            noteView.Start();
            // TODO: wait till one done, other appear later
        }
    }

    // get note click event and hanle
    @Override
    public void onClick(View v) {
        //Toast.makeText(MainActivity.this, "Note " + v.getId(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(MainActivity.this, "size = " + NotesList.size() + "-id = " + v.getId(), Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "Some note clicked", Toast.LENGTH_SHORT).show();
    }

    public void bt_NewNote_Clicked(View view) {
        // Start new activity (add note)
        Intent intent =  new Intent(this, AddNoteActivity.class);
        startActivity(intent);

        //finish();
    }

    // bug : idx problem
    int deleteButtonClickedIdx = -1;
    // temp dummy solution to handle button click flow (when to hide delete button if not thing happen)
    public void ll_Clicked(View view) {
        if(deleteButtonClickedIdx != -1)
        {
            subLayout.removeViewAt(deleteButtonClickedIdx);
        }
    }
}
