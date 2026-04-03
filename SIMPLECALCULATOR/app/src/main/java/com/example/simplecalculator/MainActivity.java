package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView txtViewResult;

    String operator = "";
    double numOne = 0, numTwo = 0, ans = 0;

    static final String ADD_OPERATION = "add";
    static final String SUB_OPERATION = "sub";
    static final String MUL_OPERATION = "mul";
    static final String DIV_OPERATION = "div";
    static final String NO_OPERATION = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtViewResult = findViewById(R.id.textViewResult);
        txtViewResult.setText("");
    }

    /* -------- CALCULATE -------- */
    public void calculate(View view) {
        if (operator.isEmpty()) return;

        String val = txtViewResult.getText().toString();
        if (val.isEmpty()) return;

        numTwo = Double.parseDouble(val);

        switch (operator) {
            case ADD_OPERATION:
                ans = numOne + numTwo;
                break;

            case SUB_OPERATION:
                ans = numOne - numTwo;
                break;

            case MUL_OPERATION:
                ans = numOne * numTwo;
                break;

            case DIV_OPERATION:
                if (numTwo == 0) {
                    txtViewResult.setText("Error");
                    return;
                }
                ans = numOne / numTwo;
                break;
        }

        txtViewResult.setText(String.valueOf(ans));
        operator = NO_OPERATION;
    }

    /* -------- NUMBER BUTTONS -------- */
    void appendNumber(String number){
        String val = txtViewResult.getText().toString();
        txtViewResult.setText(val + number);
    }

    public void buttonNine(View view){ appendNumber("9"); }
    public void buttonEight(View view){ appendNumber("8"); }
    public void buttonSeven(View view){ appendNumber("7"); }
    public void buttonSix(View view){ appendNumber("6"); }
    public void buttonFive(View view){ appendNumber("5"); }
    public void buttonFour(View view){ appendNumber("4"); }
    public void buttonThree(View view){ appendNumber("3"); }
    public void buttonTwo(View view){ appendNumber("2"); }
    public void buttonOne(View view){ appendNumber("1"); }
    public void buttonZero(View view){ appendNumber("0"); }

    public void buttonPoint(View view){
        String val = txtViewResult.getText().toString();
        if (!val.contains(".")) {
            appendNumber(".");
        }
    }

    /* -------- OPERATORS -------- */
    public void setOperation(String op){
        String val = txtViewResult.getText().toString();
        if (val.isEmpty()) return;

        numOne = Double.parseDouble(val);
        operator = op;
        txtViewResult.setText("");
    }

    public void buttonPlus(View view){ setOperation(ADD_OPERATION); }
    public void buttonMinus(View view){ setOperation(SUB_OPERATION); }
    public void buttonTimes(View view){ setOperation(MUL_OPERATION); }
    public void buttonDivide(View view){ setOperation(DIV_OPERATION); }

    /* -------- MISC -------- */
    public void buttonClear(View view){
        numOne = 0;
        numTwo = 0;
        operator = NO_OPERATION;
        txtViewResult.setText("");
    }

    public void buttonBack(View view){
        String val = txtViewResult.getText().toString();
        int length = val.length();

        if (length > 0) {
            txtViewResult.setText(val.substring(0, length - 1));
        }
    }
}