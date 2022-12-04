package com.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private TextView percentTextView, tipTextView, totalTextView;
    private EditText amountEditText;
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText = findViewById(R.id.amountEditText);
        percentTextView = findViewById(R.id.percentTextView);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);
        seekBar = findViewById(R.id.sb_percent);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int percent = i;
                percentTextView.setText(String.valueOf(percent)+"%");
                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                calculate();
            }
        });
    }

    private void calculate(){
        if(amountEditText.getText().toString().isEmpty()){
            tipTextView.setText("$0.00");
            totalTextView.setText("$0.00");
        }else{
            double amount = Double.parseDouble(amountEditText.getText().toString());
            int percent = seekBar.getProgress();
            double tip = amount*percent/100.0;
            double total = amount + tip;
            tipTextView.setText("$"+String.valueOf(formatDecimal(tip)));
            totalTextView.setText("$"+String.valueOf(formatDecimal(total)));
        }
    }

    private String formatDecimal(Double num){
        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
        separadoresPersonalizados.setDecimalSeparator('.');
        DecimalFormat format = new DecimalFormat("#.00", separadoresPersonalizados);
        return format.format(num);
    }

}
