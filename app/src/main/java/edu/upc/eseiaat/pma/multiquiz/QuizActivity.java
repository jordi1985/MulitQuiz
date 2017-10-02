package edu.upc.eseiaat.pma.multiquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private int id_answers[] = {

            R.id.answer1,R.id.answer2,R.id.answer3,R.id.answer4
    };
    
    private int correct_answer;
    private int current_question;
    private String[] all_questions;
    private TextView text_question;
    private RadioGroup group;
    private boolean [] answer_is_correct;
    private Button btn_check,btn_back;
    private int[]answers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        text_question = (TextView) findViewById(R.id.text_question);
        group = (RadioGroup) findViewById(R.id.answers_group);

        all_questions = getResources().getStringArray(R.array.all_questions);
        answer_is_correct = new boolean[all_questions.length];
        answers= new int [all_questions.length];

        for(int i=0;i<answers.length;i++){
        answers[i]=-1;}

        current_question = 0;
        showQuestion();
    }

    public void butoCheckClicked(View view) {
        int id = group.getCheckedRadioButtonId();
        int answer = -1;
        for (int i = 0; i < id_answers.length; i++) {
            if (id_answers[i] == id) {
                answer = i;
            }
        }

        answer_is_correct[current_question] = (answer == correct_answer);

        Button b = (Button)view;
        Button d =(Button)view;

        if(b.getId()==R.id.btn_Next && current_question < all_questions.length-1)
        {
            current_question++;
            showQuestion();
        }

        else {
            if (d.getId()==R.id.btn_back && current_question > 0) {
                current_question--;
                showQuestion();
            }

            if(current_question==all_questions.length-1 )
            {
                int correctas=0, incorrectas=0;
                for (boolean c : answer_is_correct)
                {
                    if (c) correctas++;
                    else incorrectas++;
                }
                String resultado = String.format("OK: %d ---- K.O: %d",correctas,incorrectas);
                Toast.makeText(QuizActivity.this, resultado, Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }

    private void showQuestion() {

        group.clearCheck();
        String q = all_questions[current_question];
        String[] parts = q.split(";");

        text_question.setText(parts[0]);


        for (int i = 0; i < id_answers.length; i++) {
            RadioButton rb = (RadioButton) findViewById(id_answers[i]);
            String answer = parts[i + 1];
            if (answer.charAt(0) == '*') {
                correct_answer = i;
                answer = answer.substring(1);
            }
            rb.setText(answer);
//            if (answers[current_question] == i) {
//                rb.isChecked(true);
//            }

            btn_check = (Button) findViewById(R.id.btn_Next);
            btn_back = (Button) findViewById(R.id.btn_back);

            if (current_question == 0)
            {
                btn_back.setVisibility(View.GONE);
            }
            else
            {
                btn_back.setVisibility(View.VISIBLE);
            }

            if(current_question == all_questions.length-1)
            {
                btn_check.setText(R.string.finish);
            }
            else{
                btn_check.setText(R.string.next);
            }
        }

    }
}
