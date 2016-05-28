package com.project.vnthanh.notes;

import java.io.InputStream;
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

        int nNotes = scan.nextInt();
        scan.nextLine();
        // we need this !!!??

        // Care: what is we have a number in title, content
        for (int i = 0; i < nNotes; i++) {
            tempTitle = scan.nextLine();
            tempContent = scan.nextLine();

            tempNote = new Note(tempTitle, tempContent);
            NotesFromFile.add(tempNote);
        }

        scan.close();

        return NotesFromFile;
    }

}
