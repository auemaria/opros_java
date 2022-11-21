package com.example.itschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView numQuest, textQuest, progressTest;
    RadioGroup varAnswers;
    Resources resources;//для обращения к ресурсам
    String [] test;
    int k = 0;
    int res=0;
    int countQuest = 1;
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

        //показываем номер вопроса
        String tmp = "Вопрос № " + countQuest;
        numQuest.setText(tmp);
        //показываем текст первого вопроса
        textQuest.setText(test[0]);
        //показываем варианты ответов
        for (int i = 0; i < varAnswers.getChildCount(); i++) {
            //RadioButton radioButton = (RadioButton) varAnswers.getChildAt(i);
            //radioButton.setText(test[i + 1]);
            if(test[i + 1].endsWith("+")) {
                tmp = test[i + 1].substring(0, test[i + 1].length() - 1);
                ((RadioButton) varAnswers.getChildAt(i)).setText(tmp);
                k=i;
            }else
                ((RadioButton) varAnswers.getChildAt(i)).setText(test[i + 1]);
        }
        tmp = countQuest + " / " + test.length / 5;
        progressTest.setText(tmp);
    }

    public void sendAnswer(View view) {

        //TODO проверить правильность ответа, посчитать статистику (потом)
        RadioButton radioButton = (RadioButton) varAnswers.getChildAt(0);
        radioButton.isChecked();

        countQuest++;
        if(countQuest <= test.length / 5) {
            //показываем номер вопроса
            String tmp = "Вопрос № " + countQuest;
            numQuest.setText(tmp);
            //показываем текст очередного вопроса
            int indexCurQuest = (countQuest - 1) * 5;
            textQuest.setText(test[indexCurQuest]);
            //показываем варианты ответов
            for (int i = 0; i < varAnswers.getChildCount(); i++) {
                //RadioButton radioButton = (RadioButton) varAnswers.getChildAt(i);
                //radioButton.setText(test[i + 1]);
                if (test[indexCurQuest + i + 1].endsWith("+")) {
                    tmp = test[indexCurQuest + i + 1]
                            .substring(0, test[indexCurQuest + i + 1].length() - 1);
                    ((RadioButton) varAnswers.getChildAt(i)).setText(tmp);
                    k = i;
                } else
                    ((RadioButton) varAnswers.getChildAt(i))
                            .setText(test[indexCurQuest + i + 1]);
            }
            int checkedRadioButtonId = varAnswers.getCheckedRadioButtonId();
            System.out.println(checkedRadioButtonId + " " + k + " " + countQuest );
            // Найдём переключатель по его id
            RadioButton myRadioButton = findViewById(checkedRadioButtonId);
            if(checkedRadioButtonId == k){
                res++;
            }
            tmp = countQuest + " / " + test.length / 5;
            progressTest.setText(tmp);
            k=0;
        }else {
            textQuest.setText("Поздравляю! Вы прошли тест со счётом: " + res);
        }
    }
}