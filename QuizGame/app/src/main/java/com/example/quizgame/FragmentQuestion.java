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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentQuestion extends Fragment implements RecyclerViewListnerInterface {
    private final static String KEY_CURRENT = "key_current";
    private final static String KEY_QUESTION_NO = "key_question_no";

    private QuestionData mQuestion;
    List<String> mTargetList;

    private int mCurrent = -1;
    private int mQuestionNo = -1;

    private OnFragmentQuestionInteractionListener mListner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mCurrent = args.getInt(KEY_CURRENT);
            mQuestionNo = args.getInt(KEY_QUESTION_NO);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView) view.findViewById(R.id.textViewNo)).setText(String.format("%d/%d", mCurrent + 1, mQuestionNo));

        // 問題,選択肢を取得
        mQuestion = GetQuestionAndChoices(mCurrent);

        ((TextView) view.findViewById(R.id.textViewQuestion)).setText(mQuestion.GetQuestion());

        RecyclerView recyclerView = view.findViewById(R.id.recycleviewChoices);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(rLayoutManager);


        mTargetList = new ArrayList<>();
        mTargetList.add(mQuestion.GetCrrectAnswer());
        mTargetList.add(mQuestion.GetChoice1());
        mTargetList.add(mQuestion.GetChoice2());
        mTargetList.add(mQuestion.GetChoice3());

        Collections.shuffle(mTargetList);

        RecyclerView.Adapter rAdapter = new AdapterChoices(mTargetList.toArray(new String[mTargetList.size()]));

        // RecyclerViewからのListener
        ((AdapterChoices) rAdapter).setListener(this);

        recyclerView.setAdapter(rAdapter);
    }

    @CheckResult
    public static FragmentQuestion createInstance(int current, int question_no) {
        FragmentQuestion fragment = new FragmentQuestion();
        Bundle args = new Bundle();
        args.putInt(KEY_CURRENT, current);
        args.putInt(KEY_QUESTION_NO, question_no);
        fragment.setArguments(args);
        return fragment;
    }

    private QuestionData GetQuestionAndChoices(int no) {
        QuestionData data = new QuestionData();
        if(no % 3 == 0) {
            data.SetQuestion("２×２＝？");
            data.SetCorrectAnswer("4");
            data.SetChoice1("2");
            data.SetChoice2("6");
            data.SetChoice3("8");
        }else if(no % 3 == 1) {
            data.SetQuestion("３×２＝？");
            data.SetCorrectAnswer("6");
            data.SetChoice1("2");
            data.SetChoice2("4");
            data.SetChoice3("8");
        }else{
            data.SetQuestion("４×２＝？");
            data.SetCorrectAnswer("8");
            data.SetChoice1("2");
            data.SetChoice2("4");
            data.SetChoice3("6");
        }
        return data;
    }

    @Override
    public void click_item(int position) {
        String str = mTargetList.get(position);

        mListner.OnFragmentQuestionInteraction(str.equals(mQuestion.GetCrrectAnswer()), "AA");
        return;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentQuestionInteractionListener) {
            mListner = (OnFragmentQuestionInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListner = null;
    }

    public interface OnFragmentQuestionInteractionListener {
        void OnFragmentQuestionInteraction(boolean isCorrect, String Keyword);
    }
}
