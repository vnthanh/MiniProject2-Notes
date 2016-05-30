package com.project.vnthanh.notes;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class AddNoteActivity extends AppCompatActivity {

    EditText et_title, et_content;
    TimePicker timePicker;
    DatePicker datePicker;

    // Dummy code: load everything, write everything
    ArrayList<Note> NotesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // Dummy, load all from file, then write again
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

        et_title = (EditText) findViewById(R.id.et_NoteTitle);
        et_content = (EditText) findViewById(R.id.et_NoteContent);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
    }

    public void bt_AddNoteNow_Clicked(View view) {
        String title = et_title.getText().toString();
        String content = et_content.getText().toString();
        Time time = new Time(timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0) ; // o second
        Date date = new Date(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth());

        /*Intent intent = new Intent();
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        setResult(RESULT_OK, intent);
        finish();*/
        // dont need result

        // Add note taken to array list
        NotesList.add(new Note(title,content, time, date));

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

        // just start the main activity ?! to reset it, easy!!!??
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent); // bug, return bug
    }
}
