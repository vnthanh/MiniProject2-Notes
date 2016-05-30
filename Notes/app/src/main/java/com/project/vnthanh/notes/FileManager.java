package com.project.vnthanh.notes;

import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by USER on 5/28/2016.
 */
public class FileManager {
    // Get notes from file and write notes to file
    // storage : sd card

    public static ArrayList<Note> LoadNotesFromFile(InputStream inputStream){
        ArrayList<Note> NotesFromFile = new ArrayList<>();

        // input Stream from assets
        Scanner scan = new Scanner(inputStream);

        // Be carefull , scanner type: double, int, -> mismatch bug

        Note tempNote;
        String tempTitle;
        String tempContent;
        Time tempTime;
        Date tempDate;

        int nNotes = scan.nextInt();
        scan.nextLine();
        // we need this !!!??

        // Care: what is we have a number in title, content
        for (int i = 0; i < nNotes; i++) {
            tempTitle = scan.nextLine();
            tempContent = scan.nextLine();
            tempTime = Time.valueOf(scan.nextLine());
            tempDate = Date.valueOf(scan.nextLine());

            tempNote = new Note(tempTitle, tempContent, tempTime, tempDate);
            NotesFromFile.add(tempNote);
        }

        scan.close();

        return NotesFromFile;
    }

    public static void SaveNotesToFile(ArrayList<Note> notes, PrintStream output) {
        output.println(notes.size());

        for (int i = 0; i < notes.size(); i++) {
            output.println(notes.get(i).Title);
            output.println(notes.get(i).Content);
            output.println(notes.get(i).RemindTime.toString());
            output.println(notes.get(i).RemindDate.toString());
        }

        output.close();
    }

}
