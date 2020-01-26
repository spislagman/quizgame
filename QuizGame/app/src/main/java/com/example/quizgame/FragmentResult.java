package com.example.quizgame;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentResult.OnFragmentResultInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentResult#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentResult extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String KEY_QUIZ_COUNT = "quiz_count";
    private static final String KEY_CORRECT_COUNT = "correct_count";
    private static final String KEY_USER_NAME = "user_name";

    // TODO: Rename and change types of parameters
    private int mQuizCount= 0;
    private int mCorrectCount = 0;
    private String mUserName = "";

    private OnFragmentResultInteractionListener mListener;

    public FragmentResult() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param question_count Parameter 1.
     * @param correct_count Parameter 2.
     * @return A new instance of fragment FragmentResult.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentResult newInstance(int question_count, int correct_count, String user_name) {
        FragmentResult fragment = new FragmentResult();
        Bundle args = new Bundle();
        args.putInt(KEY_QUIZ_COUNT, question_count);
        args.putInt(KEY_CORRECT_COUNT, correct_count);
        args.putString(KEY_USER_NAME, user_name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mQuizCount = getArguments().getInt(KEY_QUIZ_COUNT);
            mCorrectCount = getArguments().getInt(KEY_CORRECT_COUNT);
            mUserName= getArguments().getString(KEY_USER_NAME);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView)view.findViewById(R.id.textViewResult)).setText(String.format("%sさんの結果\n%d問中%d問正解",mUserName,mQuizCount,mCorrectCount));
        view.findViewById(R.id.buttonTitle).setOnClickListener(this);
        view.findViewById(R.id.buttonRetry).setOnClickListener(this);
        view.findViewById(R.id.buttonUserCreate).setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentResultInteractionListener) {
            mListener = (OnFragmentResultInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        mListener.onFragmentResultInteraction(v.getId());
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentResultInteractionListener {
        // TODO: Update argument type and name
        void onFragmentResultInteraction(int button_id);
    }
}
