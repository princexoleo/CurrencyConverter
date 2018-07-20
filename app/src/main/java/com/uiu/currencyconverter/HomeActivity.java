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
    String[] currencyOne = {"USD","BDT","EURO","RUPEE","JPY"};
    String[] currencyTwo = {"USD","BDT","EURO","RUPEE","JPY"};

    double u=1.0;
    double b=0.014;
    double e=1.174;
    double r=0.015;
    double j=0.0089;
    //USD TO ...
    double BDT=80,EURO=0.86,RUPEE=70.0,JPY=112.33;



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
                 outputEditText.setHint("0.00 "+text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String inputCurrency=inputEditText.getText().toString();
                    if(!isSwap)
                    {

                        if (inputCurrency == null) { //check input null or not
                            outputEditText.setText("0.00"+" "+currencyTwo[spinnerTwoPosition]);
                            Toast.makeText(HomeActivity.this, "Invalid input ", Toast.LENGTH_SHORT).show();
                        }else {
                            isSwap=false;

                            Toast.makeText(HomeActivity.this, ""+String.valueOf(spinnerTwoPosition), Toast.LENGTH_SHORT).show();
                            double result,value=Double.parseDouble(inputCurrency);
                            result=value /u; //ANT currency to USD
                            double r=convertCurrency(result,spinnerTwoPosition);
                            // double result = Exchange(inputCurrency);
                            outputEditText.setText(String.valueOf(r) + " " + currencyTwo[spinnerTwoPosition]);
                        }
                    }else {
                        if (inputCurrency == null) {
                            outputEditText.setText("0.00" + " " + currencyTwo[spinnerTwoPosition]);
                            Toast.makeText(HomeActivity.this, "Invalid input ", Toast.LENGTH_SHORT).show();
                        } else {
                            isSwap=true;
                            double res=0.0,value=Double.parseDouble(inputCurrency);
                            switch (spinnerOnePosition)
                            {
                                case 0: res = value / u; break; //usd to usd
                                case 1: res = value / BDT; break; //bdt to usd
                                case 2: res = value / EURO; break; //euro to usd
                                case 3: res = value / RUPEE; break; //rupee to usd
                                case 4: res = value / JPY; break; //jpy to usd
                            }
                            double result=convertCurrency(res,spinnerTwoPosition);
                            outputEditText.setText(String.valueOf(result) + " " + currencyTwo[spinnerTwoPosition]);

                        }

                    }

            }
        });

        swapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //swap Two Spinner Selection index

                isSwap=true;
                int temp1=spinnerTwoPosition;
                spinnerTwoPosition=spinnerOnePosition;
                spinnerOnePosition=temp1;
               spinnerOne.setSelection(spinnerOnePosition);
               spinnerTwo.setSelection(spinnerTwoPosition);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEditText.setText("0.00 "+currencyOne[spinnerOnePosition]);
                outputEditText.setText("0.00 "+currencyTwo[spinnerTwoPosition]);
            }
        });

        inputEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEditText.setText(" ");
            }
        });
    }

    //till not working
    private double convertCurrency(double value, int spinnerTwoPosition) {
        double result=0.0;
       switch (spinnerTwoPosition)
       {
           case 0: result=value*u ;//usd to ..usd
                    return result;
           case 1: result=value*BDT; //USD to ..BDT
               return result;
           case 2: result=value*EURO ; //USD to ..EURO
               return result;
           case 3: result=value*RUPEE ; //USD to ..RUPEE
               return result;
           case 4: result=value*JPY ; // USD to .. jpy
               return result;
       }
        return result;
    }

//    private double Exchange(String inputCurrency) {
//        switch (spinnerOnePosition)
//        {
//            case 0:  //USD to ....
//                switch (spinnerTwoPosition)
//                {
//                    case 0:Toast.makeText(HomeActivity.this, "Please select convert to currency and try again", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 1:
//                        return Double.parseDouble(inputCurrency)*75;
//
//                    case 2:
//                       return Double.parseDouble(inputCurrency)*(0.85);
//                       // outputEditText.setText(String.valueOf(result)+" EURO");
//
//                    case 3:
//                        return Double.parseDouble(inputCurrency)*68;
//                       // outputEditText.setText(String.valueOf(result)+" RUPEE");
//
//                    case 4:
//                        return Double.parseDouble(inputCurrency)*112;
//                       // outputEditText.setText(String.valueOf(result)+" JPY");
//                      //  break;
//                    case 5://USD to USD
//                        return Double.parseDouble(inputCurrency);
//
//                }
//                break;
//            case 1: //BDT to .....
//                switch (spinnerTwoPosition)
//                {
//                    case 0:Toast.makeText(HomeActivity.this, "Please select convert to currency and try again", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 1://BDT to BDT
//                        return Double.parseDouble(inputCurrency);
//                   // break;
//                    case 2:
//                        return Double.parseDouble(inputCurrency)*(0.010);
//                    // outputEditText.setText(String.valueOf(result)+" EURO");
//                    //break;
//                    case 3:
//                        return Double.parseDouble(inputCurrency)*0.81;
//                    // outputEditText.setText(String.valueOf(result)+" RUPEE");
//                   // break;
//                    case 4:
//                        return Double.parseDouble(inputCurrency) * 1.34;
//                    // outputEditText.setText(String.valueOf(result)+" JPY");
//                  //  break;
//                    case 5://BDT to USD
//                        return Double.parseDouble(inputCurrency)*0.014;
//
//                }
//                break;
//            case 2://EURo to.....
//                switch (spinnerTwoPosition)
//                {
//                    case 0:Toast.makeText(HomeActivity.this, "Please select convert to currency and try again", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 1://Euro to BDT
//                        return Double.parseDouble(inputCurrency)*98.91;
//                    //break;
//                    case 2:
//                        //EURO TO EURO
//                        return Double.parseDouble(inputCurrency);
//                    // outputEditText.setText(String.valueOf(result)+" EURO");
//                   // break;
//                    case 3:
//                        return Double.parseDouble(inputCurrency)*79.73;
//                    // outputEditText.setText(String.valueOf(result)+" RUPEE");
//                   // break;
//                    case 4:
//                        return Double.parseDouble(inputCurrency) * 131.14;
//                    // outputEditText.setText(String.valueOf(result)+" JPY");
//                  //  break;
//                    case 5://EURO to USD
//                        return Double.parseDouble(inputCurrency)*1.17;
//
//                }
//                break;
//            case 3: //Rupee to Others....
//                switch (spinnerTwoPosition)
//                {
//                    case 0:Toast.makeText(HomeActivity.this, "Please select convert to currency and try again", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 1://Rupee to BDT
//                        return Double.parseDouble(inputCurrency)*1.24;
//                   // break;
//                    case 2:
//                        return Double.parseDouble(inputCurrency)*(0.013);
//                    // outputEditText.setText(String.valueOf(result)+" EURO");
//                   // break;
//                    case 3://Rupee to Rupee
//                        return Double.parseDouble(inputCurrency);
//                    // outputEditText.setText(String.valueOf(result)+" RUPEE");
//                   // break;
//                    case 4:
//                        return Double.parseDouble(inputCurrency) * 1.65;
//                    // outputEditText.setText(String.valueOf(result)+" JPY");
//                    //break;
//                    case 5://RUPEE to USD
//                        return Double.parseDouble(inputCurrency)*0.015;
//
//                }
//                break;
//            case 4: //JPY  to Others....
//                switch (spinnerTwoPosition)
//                {
//                    case 0:Toast.makeText(HomeActivity.this, "Please select convert to currency and try again", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 1://JPY to BDT
//                        return Double.parseDouble(inputCurrency)*0.75;
//                   // break;
//                    case 2:
//                        return Double.parseDouble(inputCurrency)*(0.0076);
//                    // outputEditText.setText(String.valueOf(result)+" EURO");
//                    //break;
//                    case 3://Rupee to Rupee
//                        return Double.parseDouble(inputCurrency)*0.61;
//                    // outputEditText.setText(String.valueOf(result)+" RUPEE");
//                   // break;
//                    case 4://JPY to JPY
//                        return Double.parseDouble(inputCurrency);
//                    // outputEditText.setText(String.valueOf(result)+" JPY");
//                   // break;
//                    case 5://JPY to USD
//                        return Double.parseDouble(inputCurrency)*0.0089;
//
//                }
//                break;
//
//        }
//        return 0;
//    }



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
