package com.example.tipcalculator;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TipCalculator tipCalculator;
    private EditText amount;
    private TextView tipAmount;
    private SeekBar tipSeeker;
    private double tip = 0.15; // default 15%
    private static final DecimalFormat decFormat = new DecimalFormat("0.00");

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

        tipCalculator = new TipCalculator();
        amount = findViewById(R.id.costOfService);
        tipAmount = findViewById(R.id.tipAmount);
        tipSeeker = findViewById(R.id.tip_seeker);

        tipSeeker.setMax(100);
        tipSeeker.setProgress(15); // default 15%

        tipSeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressValue = 15;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tip = progressValue / 100.0;
                Toast.makeText(MainActivity.this,
                        "Tip set to " + progressValue + "%", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void calculateTip(double tip, double totalAmount) {
        double tipValue = tipCalculator.calculateTip(tip, totalAmount);
        tipAmount.setText(decFormat.format(tipValue));
    }

    public void calculateTip(View view) {
        String input = amount.getText().toString();
        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter the cost of service", Toast.LENGTH_SHORT).show();
            return;
        }
        double serviceAmount = Double.parseDouble(input);
        calculateTip(tip, serviceAmount);
    }
}