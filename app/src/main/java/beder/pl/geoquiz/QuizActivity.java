package beder.pl.geoquiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex = 0;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(trueButtonListener());

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(falseButtonListener());

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(nextButtonListener());

        mQuestionTextView = findViewById(R.id.question_text_view);
        updateQuestion();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @NonNull
    private View.OnClickListener nextButtonListener() {
        return view -> {
            mCurrentIndex = (++mCurrentIndex) % mQuestionBank.length;
            updateQuestion();
        };
    }

    @NonNull
    private View.OnClickListener falseButtonListener() {
        return view -> checkAnswer(false);
    }

    @NonNull
    private View.OnClickListener trueButtonListener() {
        return view -> checkAnswer(true);
    }

    private void checkAnswer(boolean userAnswer) {
        Question question = mQuestionBank[mCurrentIndex];
        boolean questionAnswer = question.isAnswerTrue();
        boolean userWasCorrect = userAnswer == questionAnswer;
        int toastMessage = getToastMessage(userWasCorrect);
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    private int getToastMessage(boolean userWasCorrect) {
        int toastMessage;
        if (userWasCorrect) {
            toastMessage = R.string.correct_toast;
        } else {
            toastMessage = R.string.incorrect_toast;
        }
        return toastMessage;
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }
}
