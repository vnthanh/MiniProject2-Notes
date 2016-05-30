package com.project.vnthanh.notes;

import java.sql.Time;
import java.util.Date;

/**
 * Created by USER on 5/28/2016.
 */
public class Note {
    String Title;
    String Content;
    Time RemindTime;
    Date RemindDate;

    public Note(String title,String content){
        Title = title;
        Content = content;
    }

    public Note(String title,String content, Time reminderTime, Date  remindDate){
        Title = title;
        Content = content;
        RemindTime = reminderTime;
        RemindDate = remindDate;
    }
}
