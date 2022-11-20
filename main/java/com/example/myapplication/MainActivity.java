package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView numQuest, textQuest, progressTest;
    RadioGroup varAnswers;
    Resources resources;
    String [] test;
    int countquest = 1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numQuest = findViewById(R.id.num_quest);
        textQuest = findViewById(R.id.text_quest);
        progressTest = findViewById(R.id.progress_test);
        varAnswers = findViewById(R.id.var_answers);

        resources = getResources();

        test = resources.getStringArray(R.array.test);

        String tmp = "Вопрос №№ "+ countquest;
        numQuest.setText(tmp);

        textQuest.setText(test[0]);

        for (int i = 0; i < varAnswers.getChildCount(); i++) {
            //RadioButton radioButton = (RadioButton) varAnswers.getChildAt(i);
            //radioButton.setText(test[i+1]);
            if(test[i+1].endsWith("+")){
                tmp = test[i+1].substring(0,test[i+1].length()-1);
                ((RadioButton)varAnswers.getChildAt(i)).setText(tmp);
            }
            else {
                ((RadioButton)varAnswers.getChildAt(i)).setText(test[i+1]);
            }


        }
        tmp = countquest + " / " + test.length/5;
        progressTest.setText(tmp);
    }

    public void sendAnswer(View view) {
        RadioButton radioButton = (RadioButton)varAnswers.getChildAt(0);
        radioButton.isChecked();
        countquest ++;
        if(countquest<= test.length/5){
            String tmp = "Вопрос №№ "+ countquest;
            numQuest.setText(tmp);

            int indexcurquest = ((countquest-1)*5)

            textQuest.setText(test[indexcurquest]);

            for (int i = 0; i < varAnswers.getChildCount(); i++) {
                //RadioButton radioButton = (RadioButton) varAnswers.getChildAt(i);
                //radioButton.setText(test[i+1]);
                if(test[indexcurquest + i+1].endsWith("+")){
                    tmp = test[i+1].substring(0,test[indexcurquest +i+1].length()-1);
                    ((RadioButton)varAnswers.getChildAt(i)).setText(tmp);
                }
                else {
                    ((RadioButton)varAnswers.getChildAt(i)).setText(test[indexcurquest + i+1]);
                }


            }
            tmp = countquest + " / " + test.length/5;
            progressTest.setText(tmp);
        }
        //TODO проверить правильность ответов, посчитать статистику

    }
}
