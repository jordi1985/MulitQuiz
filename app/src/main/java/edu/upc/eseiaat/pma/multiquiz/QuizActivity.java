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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        text_question = (TextView) findViewById(R.id.text_question);
        group = (RadioGroup) findViewById(R.id.answers_group);

        all_questions = getResources().getStringArray(R.array.all_questions);
        current_question = 0;
        showQuestion();

        //TODO: al clicar al botó s'ha de anar a la seguent pregunta

       Button btn_check = (Button) findViewById(R.id.btn_check);
       btn_check.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int id = group.getCheckedRadioButtonId();
               int answer = -1;
               for (int i = 0; i < id_answers.length; i++) {
                   if (id_answers[i] == id) {
                       answer = i;
                   }
               }

               if (answer == correct_answer) {
                   Toast.makeText(QuizActivity.this, R.string.correct, Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(QuizActivity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
               }

               current_question++;
               showQuestion();
           }
           });


        //TODO: poder anar enrera
    }

    private void showQuestion() {

        String q= all_questions [current_question];
        String[] parts =  q.split(";");

        text_question.setText(parts[0]);

        for(int i = 0; i< id_answers.length; i++){
            RadioButton rb = (RadioButton) findViewById(id_answers[i]);
            String answer = parts[i+1];
            if (answer.charAt(0)=='*'){
                correct_answer =  i;
                answer = answer.substring(1);
            }
            rb.setText(answer);
        }
    }
}
