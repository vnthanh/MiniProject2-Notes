package com.project.vnthanh.notes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by USER on 5/28/2016.
 */
public class MyNoteView extends TextView {

    public MyNoteView(Context context){
        super(context);
    }

    /*String noteTitlte;
    String noteContent;*/
    Note note; // the note to display by View

    public void setNoteToView(Note input){
        note = input;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //String text = note.Title + "\n" + note.Content;

        /*Paint paint = new Paint();
        paint.setTextSize(20);
        paint.setColor(0xFF000000);
        canvas.drawColor(0xFFFFFFFF);
        canvas.drawText(note.Title, 30, 30, paint);
        canvas.drawText(note.Content, 30, 60, paint);*/
    }
}
