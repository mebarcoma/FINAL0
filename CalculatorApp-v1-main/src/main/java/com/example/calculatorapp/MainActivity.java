package com.example.calculatorapp;

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
    Calculator calc;
    String operator = "";
    String errorStr;
    double numOne, numTwo, ans;
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
        //clear the textViewResult
        txtViewResult.setText("");
        calc = new Calculator();
    }


    public void calculate(View view) {
        if (operator.isEmpty()){
            errorStr = "";
            return;
        }
        //get the 2nd number
        numTwo = Double.parseDouble(txtViewResult.getText().toString());

        performCalculation();
    }

    void performCalculation(){
        calc = new Calculator(numOne, numTwo);
        ans = 0.00;
        boolean isErr = false;
        switch (operator) {
            case ADD_OPERATION:
                ans = calc.getSum();
                break;
            case SUB_OPERATION:
                ans = calc.getDiff();
                break;
            case MUL_OPERATION:
                ans = calc.getProd();
                break;
                   case DIV_OPERATION:
                if (numTwo == 0) {
                    errorStr = "Error: Division by zero is undefined.";
                    isErr = true;
                } else {
                    ans = calc.getQou();
                    break;
                }
        }
        if (isErr)
            txtViewResult.setText(errorStr);
        else
            txtViewResult.setText(String.valueOf(ans));
    }

    /* -------- NUMBERED BUTTONS -------- */

    void appendNumber(String number){
        //get the current value of the TextView
        String val = txtViewResult.getText().toString();
        //append the value seven to the text
        val = val + number;
        //update the display
        txtViewResult.setText(val);
    }

    public void buttonNine(View view){
        appendNumber("9");
    }

    public void buttonEight(View view){
        appendNumber("8");
    }

    public void buttonSeven(View view) {
        appendNumber("7");
    }

    public void buttonSix(View view){
        appendNumber("6");
    }

    public void buttonFive(View view){
        appendNumber("5");
    }

    public void buttonFour(View view){
        appendNumber("4");
    }

    public void buttonThree(View view){
        appendNumber("3");
    }

    public void buttonTwo(View view){
        appendNumber("2");
    }

    public void buttonOne(View view){
        appendNumber("1");
    }

    public void  buttonZero(View view) {
        appendNumber("0");
    }

    public void buttonPoint(View view){
        String val = txtViewResult.getText().toString();

        if (val.contains("."))
            return;
        appendNumber(".");
    }

    /* -------- OPERATOR BUTTONS -------- */
    public void setOperation(String op){
        //get the number on the textView
        String val = txtViewResult.getText().toString();
        //store on the variable numOne
        numOne = Double.parseDouble(val);
        //get the operator
        operator = op;
        //clear the textView
        txtViewResult.setText("");
    }
    public void buttonPlus(View view){
        setOperation(ADD_OPERATION);
    }

    public void buttonMinus(View view){
        setOperation(SUB_OPERATION);
    }

    public void buttonTimes(View view){
        setOperation(MUL_OPERATION);
    }

    public void buttonDivide(View view){
        setOperation(DIV_OPERATION);
    }

    /* -------- MISC BUTTONS -------- */

    public void buttonClear(View view){
        numOne = 0;
        numTwo = 0;
        operator = NO_OPERATION;
        txtViewResult.setText("");
    }

    public void buttonBack(View view){
        //get the text from the display
        String val = txtViewResult.getText().toString();
        int length = val.length();
        txtViewResult.setText(val.substring(0, length-1));
    }
}