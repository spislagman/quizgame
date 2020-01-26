package com.example.quizgame;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentAnswer extends Fragment{
    private final static String KEY_CORRECT = "key_correct";
    private final static String KEY_KEYWORD = "key_keyword";

    private boolean mIsCorrect = false;
    private String mKeyword = "";

    private OnFragmentAnswerInteractionListener mListner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            mIsCorrect = args.getBoolean(KEY_CORRECT);
            mKeyword = args.getString(KEY_KEYWORD);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_answer,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //
        TextView text = view.findViewById(R.id.textViewAnswer);
        if(mIsCorrect){
            text.setText("正解");
        }else{
            text.setText("不正解");
        }
        String explanation = GetExplanationFromWeb(mKeyword);
        ((TextView)view.findViewById(R.id.textViewExplanation)).setText(explanation);
        view.findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mListner.OnFragmentAnswerInteraction();
            }
        });
    }

    private String GetExplanationFromWeb(String keyward){
        return "ここに説明文を表示";
    }

    @CheckResult
    public static FragmentAnswer createInstance(boolean isCorrect, String keyword){
        FragmentAnswer fragment = new FragmentAnswer();
        Bundle args = new Bundle();
        args.putBoolean(KEY_CORRECT,isCorrect);
        args.putString(KEY_KEYWORD,keyword);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentAnswerInteractionListener){
            mListner = (OnFragmentAnswerInteractionListener)context;
        }else{
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListner = null;
    }

    public interface OnFragmentAnswerInteractionListener {
        void OnFragmentAnswerInteraction();
    }
}
