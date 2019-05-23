package com.example.allpet_ver1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class warranty_recommand extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    TextView price;
    EditText breeds;

    //age, 보증 기간
    Spinner spin1, spin2;
    ArrayAdapter<CharSequence> adspin1,adspin2;

    RadioGroup radio_gender;
    RadioGroup radio_operation;
    RadioGroup radio_vaccine;
    RadioGroup radio_toilet;

    //나이, 종, 기간, 성별, 중성화, 백신, 화장실 값들....
    int age=0;
    String dog_breeds="";
    int warranty_period=0;
    String gender="";
    String operation="";
    String vaccine="";
    String toilet="";


    Button btn_recommand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warranty_recommand);

        //종,가격
        breeds=(EditText) findViewById(R.id.breeds);
        price=(TextView) findViewById(R.id.price);

        btn_recommand=(Button) findViewById(R.id.btn_recommand);

        //성별, 중성화, 백신, 배변
        radio_gender=(RadioGroup) this.findViewById(R.id.radio_gender);
        radio_operation = (RadioGroup) this.findViewById(R.id.radio_operation);
        radio_vaccine= (RadioGroup) this.findViewById(R.id.radio_vaccine);
        radio_toilet= (RadioGroup) this.findViewById(R.id.toilet);

        radio_gender.setOnCheckedChangeListener(this);
        radio_operation.setOnCheckedChangeListener(this);
        radio_vaccine.setOnCheckedChangeListener(this);
        radio_toilet.setOnCheckedChangeListener(this);


        //age spinner
        spin1 = (Spinner)findViewById(R.id.age);
        adspin1 = ArrayAdapter.createFromResource(this, R.array.age,android.R.layout.simple_spinner_item);
        //adapter에 값 input
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adspin1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                age = Integer.parseInt(adspin1.getItem(i)+"");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        //warranty_period spinner
        spin2 = (Spinner)findViewById(R.id.warranty_period);
        adspin2 = ArrayAdapter.createFromResource(this, R.array.warranty_period,android.R.layout.simple_spinner_item);
        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adspin2);
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                warranty_period = Integer.parseInt(adspin2.getItem(i)+"");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

    }

    //RadioButton 성별, 중성화 여부
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        int radioGroupId = radioGroup.getId();

        if (radioGroupId ==  R.id.radio_gender) {
            switch (checkedId) {
                case R.id.btn_man:
                    gender="남";
                    break;
                case R.id.btn_woman:
                    gender="여";
                    break;
                default:
                    break;
            }
        } else if (radioGroupId == R.id.radio_operation) {
            switch (checkedId) {
                case R.id.btn_yes:
                    operation="Y";
                    break;
                case R.id.btn_no:
                    operation="N";
                    break;
                default:
                    break;
            }
        }else if (radioGroupId == R.id.radio_vaccine) {
            switch (checkedId) {
                case R.id.vaccine_yes:
                    vaccine="Y";
                    break;
                case R.id.vaccine_no:
                    vaccine="N";
                    break;
                default:
                    break;
            }
        }else if (radioGroupId == R.id.toilet) {
            switch (checkedId) {
                case R.id.toilet_1:
                    toilet="잘 가림";
                    break;
                case R.id.toilet_2:
                    toilet="어느정도";
                    break;
                case R.id.toilet_3:
                    toilet="전혀못가림";
                    break;
                default:
                    break;
            }
        }
    }


    public void recommandClick(View v){

        dog_breeds=breeds.getText().toString();
        warranty_recommand.NetworkCall networkCall = new warranty_recommand.NetworkCall();
        networkCall.execute();

    }
    public void setp(Integer d){
        price.setText(String.valueOf(d));
    }

    //금액산정 후 확인 버튼
    public void btn_ok_Click(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // AlertDialog 셋팅
        alertDialogBuilder
                .setMessage(price.getText().toString()+"원 입니다.\n"
                +"이대로 하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("네", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("price",price.getText().toString());
                        setResult(RESULT_OK,resultIntent);
                        finish();

                    }
                })
                .setNegativeButton("아뇨", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // 취소한다
                        dialog.cancel();
                    }
                });

        // 다이얼로그 생성
        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setTitle("보증금 산정");
        alertDialog.setIcon(R.drawable.dog_foot);

        // 다이얼로그 보여주기
        alertDialog.show();
    }


    private class NetworkCall extends AsyncTask<Call,Void, Integer> {
        @Override
        protected Integer doInBackground(Call... calls) {
            Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(deposit_interface.URL)
                    .build();
            deposit_interface deposit_interface= retrofit.create(deposit_interface.class);
            JsonObject obj = new JsonObject();
            obj.addProperty("Breed", dog_breeds);
            obj.addProperty("Vaccin", vaccine);
            obj.addProperty("Toilet", toilet);
            obj.addProperty("Neutral", operation);
            obj.addProperty("Age", "1년이하");
            obj.addProperty("Warranty_Period", warranty_period);
           Log.e("obj", obj.toString());
            Call<JsonObject> call = deposit_interface.postTest("deposit",obj);
            try {
                String deposit="";
                int d =0;
                JsonObject object = call.execute().body();
              //  Log.e("AAAAa", object.getAsString());
                if (object != null) {
                    deposit = object.get("Price").getAsString();
                    d=Integer.parseInt(deposit)*1000;
                    Log.e("TAG",d+"======================================");

                }
                return d;
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        protected void onPostExecute(Integer s){
            super.onPostExecute(s);
            setp(s);
            Log.e("TAG",String.valueOf(s));

        }

    }

}
