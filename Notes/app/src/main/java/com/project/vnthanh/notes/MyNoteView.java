package com.project.vnthanh.notes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

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

    String titleRectColorString = "#64FFDA"; // rather than hardcode, String , parse later
    //int contentColor = 0xBBDEFB; dont need here, cuz we have it outside-> kinda dumb

    // currently hard-coding =====================================================
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas); // nothing to do so far

        this.setHeight(height); // animation update each time

        int rectWidth = this.getWidth();

        Paint paint = new Paint();
        paint.setColor(Color.parseColor(titleRectColorString));

        canvas.drawRect(new Rect(0, 0, rectWidth, 40), paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD)); // Bold title

        canvas.drawText(note.Title,5,25, paint); // currently hardcode

        ////////////////////////////////////////////// use these code to print long text
        TextPaint mTextPaint=new TextPaint();
        mTextPaint.setTextSize(18);
        StaticLayout mTextLayout = new StaticLayout(note.Content, mTextPaint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);

        canvas.save();
        // calculate x and y position where your text will be placed
        int textX = 5;
        int textY = 50;

        canvas.translate(textX, textY);
        mTextLayout.draw(canvas);
        canvas.restore();
        ///////////////////////////////

        // Draw date/time
        Paint newPaint = new Paint();
        canvas.drawText(note.RemindDate.toString()+" " + note.RemindTime.toString(),300,130, newPaint);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.clock);

        canvas.drawBitmap(bitmap
                , 280, 116, null);


    }

    int width = 0, height=0;

    private void update()
    {
        //Log.d("test", "update() called");

        //width ++; // hard-coded, increase without limit
        if (height < 500) height = height + 10; //!!
        // hardcoded limit

        ////////////////////
        invalidate();
    }

    // hardcode : no limit count, count affects frame speed ?!!
    CountDownTimer countDownTimer = new CountDownTimer(300, 1) {
        @Override
        public void onTick(long millisUntilFinished)
        {
            update(); // next state
        }

        @Override
        public void onFinish() {

        }
    };

    public void Start()
    {
        countDownTimer.start();
    }
}
