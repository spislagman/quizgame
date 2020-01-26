package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentQuestion.OnFragmentQuestionInteractionListener, FragmentAnswer.OnFragmentAnswerInteractionListener, FragmentResult.OnFragmentResultInteractionListener {
    private int mCount = 0;             // 現在の問題数
    private int mCorrectCount = 0;      // 正解数
    private final int QUIZ_COUNT = 4;   // 出題数
    private String mUserName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        mUserName = intent.getStringExtra(UserCreate.EXTRA_USER_NAME);

        ShowInitQuestion();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void OnFragmentQuestionInteraction(boolean isCorrect, String keyword) {
        mCount++;
        if(isCorrect){
            mCorrectCount++;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, FragmentAnswer.createInstance(isCorrect, keyword));
        transaction.commit();
    }

    @Override
    public void OnFragmentAnswerInteraction() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(mCount < QUIZ_COUNT) {
            transaction.replace(R.id.container, FragmentQuestion.createInstance(mCount, QUIZ_COUNT));
        }
        else {
            WriteResultOutput();
            transaction.replace(R.id.container, FragmentResult.newInstance(mCount,mCorrectCount,mUserName));
        }
        transaction.commit();
    }

    @Override
    public void onFragmentResultInteraction(int button_id) {
        switch (button_id){
            case R.id.buttonTitle:
                Intent intent = new Intent(getApplication(), Title.class);
                startActivity(intent);
                break;
            case R.id.buttonUserCreate:
                intent = new Intent(getApplication(), UserCreate.class);
                startActivity(intent);
                break;
            case R.id.buttonRetry:
                ShowInitQuestion();
                break;
        }
    }

    private void ShowInitQuestion(){
        mCount  = 0;
        mCorrectCount = 0;FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,FragmentQuestion.createInstance(mCorrectCount,QUIZ_COUNT));
        transaction.commit();
    }

    private void WriteResultOutput(){

    }
}
