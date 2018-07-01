package beder.pl.geoquiz;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(trueButtonListener());

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(falseButtonListener());
    }

    @NonNull
    private View.OnClickListener falseButtonListener() {
        return view -> makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
    }

    @NonNull
    private View.OnClickListener trueButtonListener() {
        return view -> makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
    }
}
