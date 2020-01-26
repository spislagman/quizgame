package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class Title extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        GetResultLogs();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Intent intent = new Intent(getApplication(), UserCreate.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void GetResultLogs(){

    }
}
