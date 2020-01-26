package com.example.quizgame;

public class QuestionData {
    private String mQuestion = "";
    private String mCorrectAnswer = "";
    private String mChoice_1 = "";
    private String mChoice_2 = "";
    private String mChoice_3 = "";

    public void SetQuestion(String question){
        mQuestion = question;
    }

    public String GetQuestion(){
        return mQuestion;
    }

    public void SetCorrectAnswer(String correctAnswer){
        mCorrectAnswer = correctAnswer;
    }

    public String GetCrrectAnswer(){
        return mCorrectAnswer;
    }

    public void SetChoice1(String choice){
        mChoice_1 = choice;
    }

    public String GetChoice1(){
        return mChoice_1;
    }

    public void SetChoice2(String choice){
        mChoice_2 = choice;
    }

    public String GetChoice2(){
        return mChoice_2;
    }

    public void SetChoice3(String choice){
        mChoice_3 = choice;
    }

    public String GetChoice3(){
        return mChoice_3;
    }
}
