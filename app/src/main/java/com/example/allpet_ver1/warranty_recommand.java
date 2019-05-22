package com.example.allpet_ver1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

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

        //breeds
        dog_breeds=breeds.getText().toString();

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
                    toilet="어느 정도 가림";
                    break;
                case R.id.toilet_3:
                    toilet="전혀 못가림";
                    break;
                default:
                    break;
            }
        }
    }

    public void recommandClick(View v){
        //price.setText();
    }

}
