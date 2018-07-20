package com.uiu.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    Button convertButton, swapButton, resetButton;
    TextView input_textview, output_textview;
    EditText inputEditText,outputEditText;
    Spinner spinnerOne, spinnerTwo;

    private static boolean isSwap=false;
    int spinnerOnePosition,spinnerTwoPosition;
    String[] currencyOne = {"USD","BDT","EURO","RNR","JPY"};
    String[] currencyTwo = {"Select here","BDT","EURO","RUPEE","JPY","USD"};


    ArrayAdapter<String> fromAdapter;
    ArrayAdapter <String> toAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initiliazer();


        spinnerOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spinnerOnePosition=position;
                String text=parent.getItemAtPosition(position).toString();
                inputEditText.setHint("0.00 "+text);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        spinnerTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spinnerTwoPosition = position;
                String text=parent.getItemAtPosition(position).toString();
                if(spinnerOnePosition >0)
                {
                   outputEditText.setText("0.00 "+text);
                }
                else{
                    outputEditText.setHint("Output Currency will be shown here ");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isSwap)//default not swap
                {

                        String inputCurrency=inputEditText.getText().toString();
                    if (inputCurrency == null) {
                        outputEditText.setText("0.00"+" "+currencyTwo[spinnerTwoPosition]);
                    }else {
                        double result = Exchange(inputCurrency);
                        outputEditText.setText(String.valueOf(result) + " " + currencyTwo[spinnerTwoPosition]);
                    }


                }
            }
        });

        swapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp=spinnerTwoPosition;
                spinnerTwoPosition=spinnerOnePosition;
               spinnerOnePosition=temp;
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEditText.setText("0.00 "+currencyOne[spinnerOnePosition]);
                outputEditText.setText("0.00 "+currencyTwo[spinnerTwoPosition]);
            }
        });
    }

    private double Exchange(String inputCurrency) {
        switch (spinnerOnePosition)
        {
            case 0:  //USD to ....
                switch (spinnerTwoPosition)
                {
                    case 0:Toast.makeText(HomeActivity.this, "Please select convert to currency and try again", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        return Double.parseDouble(inputCurrency)*75;

                    case 2:
                       return Double.parseDouble(inputCurrency)*(0.85);
                       // outputEditText.setText(String.valueOf(result)+" EURO");

                    case 3:
                        return Double.parseDouble(inputCurrency)*68;
                       // outputEditText.setText(String.valueOf(result)+" RUPEE");

                    case 4:
                        return Double.parseDouble(inputCurrency)*112;
                       // outputEditText.setText(String.valueOf(result)+" JPY");
                      //  break;
                    case 5://USD to USD
                        return Double.parseDouble(inputCurrency);

                }
                break;
            case 1: //BDT to .....
                switch (spinnerTwoPosition)
                {
                    case 0:Toast.makeText(HomeActivity.this, "Please select convert to currency and try again", Toast.LENGTH_SHORT).show();
                        break;
                    case 1://BDT to BDT
                        return Double.parseDouble(inputCurrency);
                   // break;
                    case 2:
                        return Double.parseDouble(inputCurrency)*(0.010);
                    // outputEditText.setText(String.valueOf(result)+" EURO");
                    //break;
                    case 3:
                        return Double.parseDouble(inputCurrency)*0.81;
                    // outputEditText.setText(String.valueOf(result)+" RUPEE");
                   // break;
                    case 4:
                        return Double.parseDouble(inputCurrency) * 1.34;
                    // outputEditText.setText(String.valueOf(result)+" JPY");
                  //  break;
                    case 5://BDT to USD
                        return Double.parseDouble(inputCurrency)*0.014;

                }
                break;
            case 2://EURo to.....
                switch (spinnerTwoPosition)
                {
                    case 0:Toast.makeText(HomeActivity.this, "Please select convert to currency and try again", Toast.LENGTH_SHORT).show();
                        break;
                    case 1://Euro to BDT
                        return Double.parseDouble(inputCurrency)*98.91;
                    //break;
                    case 2:
                        //EURO TO EURO
                        return Double.parseDouble(inputCurrency);
                    // outputEditText.setText(String.valueOf(result)+" EURO");
                   // break;
                    case 3:
                        return Double.parseDouble(inputCurrency)*79.73;
                    // outputEditText.setText(String.valueOf(result)+" RUPEE");
                   // break;
                    case 4:
                        return Double.parseDouble(inputCurrency) * 131.14;
                    // outputEditText.setText(String.valueOf(result)+" JPY");
                  //  break;
                    case 5://EURO to USD
                        return Double.parseDouble(inputCurrency)*1.17;

                }
                break;
            case 3: //Rupee to Others....
                switch (spinnerTwoPosition)
                {
                    case 0:Toast.makeText(HomeActivity.this, "Please select convert to currency and try again", Toast.LENGTH_SHORT).show();
                        break;
                    case 1://Rupee to BDT
                        return Double.parseDouble(inputCurrency)*1.24;
                   // break;
                    case 2:
                        return Double.parseDouble(inputCurrency)*(0.013);
                    // outputEditText.setText(String.valueOf(result)+" EURO");
                   // break;
                    case 3://Rupee to Rupee
                        return Double.parseDouble(inputCurrency);
                    // outputEditText.setText(String.valueOf(result)+" RUPEE");
                   // break;
                    case 4:
                        return Double.parseDouble(inputCurrency) * 1.65;
                    // outputEditText.setText(String.valueOf(result)+" JPY");
                    //break;
                    case 5://RUPEE to USD
                        return Double.parseDouble(inputCurrency)*0.015;

                }
                break;
            case 4: //JPY  to Others....
                switch (spinnerTwoPosition)
                {
                    case 0:Toast.makeText(HomeActivity.this, "Please select convert to currency and try again", Toast.LENGTH_SHORT).show();
                        break;
                    case 1://JPY to BDT
                        return Double.parseDouble(inputCurrency)*0.75;
                   // break;
                    case 2:
                        return Double.parseDouble(inputCurrency)*(0.0076);
                    // outputEditText.setText(String.valueOf(result)+" EURO");
                    //break;
                    case 3://Rupee to Rupee
                        return Double.parseDouble(inputCurrency)*0.61;
                    // outputEditText.setText(String.valueOf(result)+" RUPEE");
                   // break;
                    case 4://JPY to JPY
                        return Double.parseDouble(inputCurrency);
                    // outputEditText.setText(String.valueOf(result)+" JPY");
                   // break;
                    case 5://JPY to USD
                        return Double.parseDouble(inputCurrency)*0.0089;

                }
                break;

        }
        return 0;
    }

    private void initiliazer() {

        input_textview=findViewById(R.id.input_currency_tv_id);
        output_textview=findViewById(R.id.output_currency_tv_id);

        convertButton=findViewById(R.id.convert_button);
        swapButton=findViewById(R.id.swap_button);
        resetButton=findViewById(R.id.reset_button);

        spinnerOne =findViewById(R.id.from_currency_id);
        fromAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,currencyOne);
        spinnerOne.setAdapter(fromAdapter);

        spinnerTwo =findViewById(R.id.to_currency_id);
        toAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,currencyTwo);
        spinnerTwo.setAdapter(toAdapter);

        inputEditText=findViewById(R.id.user_input_id);
        outputEditText=findViewById(R.id.user_output_id);
    }


}
