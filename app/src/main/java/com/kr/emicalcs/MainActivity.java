package com.kr.emicalcs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText pAmount, iRate, period;
    Button calc;
    TextView mCalcs, Tcalcs;
    int pVal, m;
    double rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pAmount = (EditText) findViewById(R.id.pAmount);
        iRate = (EditText) findViewById(R.id.iRate);
        period = (EditText) findViewById(R.id.period);
        calc = (Button) findViewById(R.id.calc);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double total, upper, lower, pTotal, Ptotal;
                if (!pAmount.getText().toString().isEmpty() && !iRate.getText().toString().isEmpty() && !period.getText().toString().isEmpty()){
                    pVal = Integer.parseInt(pAmount.getText().toString()); //the amount of money the user inputs
                    rate = (Double.parseDouble(iRate.getText().toString()) / 100) / 12; //assuming monthly interest rate
                    m = Integer.parseInt(period.getText().toString());  //the period of time in months
                    upper = rate * Math.pow(1 + rate, m);
                    lower = Math.pow(1 + rate, m) - 1;
                    total = pVal * (upper / lower);
                    total = (double) Math.round(total * 100) / 100;
                    pTotal = total * m;
                    Ptotal = (double) Math.round(pTotal * 100) / 100;
                    mCalcs = (TextView) findViewById(R.id.mCalcs);
                    mCalcs.setText(String.valueOf("Loan EMI: $" + total));
                    Tcalcs = (TextView) findViewById(R.id.Tcalcs);
                    Tcalcs.setText(String.valueOf("Total Interest Payable: $" + Ptotal));
                }
                else {
                    Toast.makeText(MainActivity.this, "Fields cannot be blank", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}