package com.wanapp.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    public static final String PAY_TEXT = "受理费用:";
    public static final String EXT_TEXT = "处理费用:";
    public static final String TOT_TEXT = "费用总计:";

    private TextView extView = null;
    private TextView payView = null;
    private TextView totView = null;

    private EditText inputSum = null;
    private Button calc = null;
    private Button clear = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        payView = findViewById(R.id.view_pay);
        extView = findViewById(R.id.view_extPay);
        totView = findViewById(R.id.view_totPay);

        inputSum = findViewById(R.id.editText);
        calc =  findViewById(R.id.btn_calc);
        clear =  findViewById(R.id.btn_clear);
        calc.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_calc:
                calc();
                break;
            case R.id.btn_clear:
                clear();
                break;
        }
    }

    public void calc(){
        try {
            double s = Double.parseDouble(inputSum.getText().
                    toString());
            double p = 0;
            if (s <= 1000) {
                p = 100;
            } else if (s <= 50000) {
                p = (100 + (s - 1000) * 0.05);
            } else if (s <= 100000) {
                p = (2550 + (s - 50000) * 0.04);
            } else if (s <= 200000) {
                p = (4550 + (s - 100000) * 0.03);
            } else if (s <= 500000) {
                p = (7550 + (s - 200000) * 0.02);
            } else if (s <= 1000000) {
                p = (13550 + (s - 500000) * 0.01);
            } else {
                p = (18550 + (s - 1000000) * 0.005);
            }

            double ext = p * 0.3;
            double tot = p * 1.3;
            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false);

            payView.setText(PAY_TEXT + nf.format(p));
            extView.setText(EXT_TEXT + nf.format(ext));
            totView.setText(TOT_TEXT + nf.format(tot));
        }catch(Throwable e) {

        }
    }

    public void clear(){
        inputSum.setText("");
        payView.setText(PAY_TEXT);
        extView.setText(EXT_TEXT);
        totView.setText(TOT_TEXT);
    }
}
