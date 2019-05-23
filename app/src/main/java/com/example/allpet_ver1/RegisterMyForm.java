package com.example.allpet_ver1;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterMyForm extends AppCompatActivity {

    ArrayAdapter<CharSequence> adspin1, adspin2, adspin3=null; //adapter

    private Spinner spin1=null;
    private Spinner spin2=null;
    private Spinner spin3=null;

    private EditText name;
    private EditText phone;
    private String choice_do="서울";
    private String choice_se="강남구";
    private EditText job;
    private String family_num="1";
    private EditText memo;
    private Button btn_register;
    private RadioGroup breeding;

//    "PetSeq": 1,
//            "ReqName" : "홍길동1234",
//            "PhoneNum" : "010-1234-5678",
//            "Location1" : "서울",
//            "Location2" : "강남구",
//            "Job": "student",
//            "FamilyNum" : "4",
//            "CommentDesc" : "진짜 잘키울 자신 있습니다!!",
//            "Experience" : "Y"

    int num;
    String username;
    String phone_num;
    String add1;
    String add2;
    String job_string;
    String family_num_string;
    String comment;
    String experience;
    final Context context = this;

    //Dog dog;
    puppy p;
    RadioButton rb;
    String p_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_my_form);

        Intent intent=getIntent();
        p = intent.getParcelableExtra("puppy");
        num = intent.getExtras().getInt("num");
        p_id = intent.getExtras().getString("Id");

        //dog=(Dog)intent.getSerializableExtra("dog");
        name=(EditText) findViewById(R.id.user_name);
        phone = (EditText) findViewById(R.id.phonenumber);
        job = (EditText) findViewById(R.id.job);
        memo = (EditText) findViewById(R.id.memo);
        btn_register=(Button) findViewById(R.id.btn_register);
        breeding = (RadioGroup) findViewById(R.id.breeding);
        int id = breeding.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(id);
        RadioGroup.OnCheckedChangeListener radio = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId==R.id.radioyes){
                    experience="Y";
                }else{
                    experience="N";
                }
            }
        };
        // 연락처 입력시 하이픈(-) 자동 입력.
        phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        //spinner
        spin1 = (Spinner)findViewById(R.id.spinner);
        spin2 = (Spinner)findViewById(R.id.spinner2);
        spin3 = (Spinner)findViewById(R.id.family_num);

        adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner_do,android.R.layout.simple_spinner_item);

        //adapter에 값 input
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin1.setAdapter(adspin1);

        //Adapter 값들을 spinner에 넣기
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //서울시
                if (adspin1.getItem(i).equals("서울")) {
                    choice_do = "서울";
                    adspin2 = ArrayAdapter.createFromResource(RegisterMyForm.this, R.array.spinner_do_seoul, android.R.layout.simple_spinner_dropdown_item);

                } else if (adspin1.getItem(i).equals("인천")) {       //인천
                    choice_do = "인천";
                    adspin2 = ArrayAdapter.createFromResource(RegisterMyForm.this, R.array.spinner_do_incheon, android.R.layout.simple_spinner_dropdown_item);

                } else if(adspin1.getItem(i).equals("광주")) {       //광주
                    choice_do = "광주";
                    adspin2 = ArrayAdapter.createFromResource(RegisterMyForm.this, R.array.spinner_do_gwangju, android.R.layout.simple_spinner_dropdown_item);

                }else if(adspin1.getItem(i).equals("대구")) {       //대구
                    choice_do = "대구";
                    adspin2 = ArrayAdapter.createFromResource(RegisterMyForm.this, R.array.spinner_do_daegu, android.R.layout.simple_spinner_dropdown_item);

                }else if(adspin1.getItem(i).equals("울산")) {       //울산
                    choice_do = "울산";
                    adspin2 = ArrayAdapter.createFromResource(RegisterMyForm.this, R.array.spinner_do_ulsan, android.R.layout.simple_spinner_dropdown_item);

                }else if(adspin1.getItem(i).equals("대전")) {       //대전
                    choice_do = "대전";
                    adspin2 = ArrayAdapter.createFromResource(RegisterMyForm.this, R.array.spinner_do_daejeon, android.R.layout.simple_spinner_dropdown_item);
                }

                adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin2.setAdapter(adspin2);

                spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        choice_se = adspin2.getItem(i)+"";
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) { }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });


        //가구원 수
        adspin3 = ArrayAdapter.createFromResource(this, R.array.family_num,android.R.layout.simple_spinner_item);

        //adapter에 값 input
        adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin3.setAdapter(adspin3);

        //Adapter 값들을 spinner에 넣기
        spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                family_num=adspin3.getItem(i)+"";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        //등록 버튼
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NetworkCall networkCall = new NetworkCall();
                networkCall.execute();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // AlertDialog 셋팅
                alertDialogBuilder
                        .setMessage("신청되었습니다.")
                        .setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent i = new Intent(RegisterMyForm.this, mainpage_picture.class);
                                i.putExtra("Id",p_id);
                                startActivity(i);

                                finish();
                                dialog.cancel();
                            }
                        });

                // 다이얼로그 생성
                AlertDialog alertDialog = alertDialogBuilder.create();

                alertDialog.setTitle("확인");
                alertDialog.setIcon(R.drawable.dog_foot);

                // 다이얼로그 보여주기
                alertDialog.show();
            }
        });

    }//onCreate()
    public void uploadClick(View v){

    }
    public void updateinfo(){
        username = name.getText().toString();
        phone_num = phone.getText().toString();
        add1 = choice_do;
        add2 = choice_se;
        job_string = job.getText().toString();
        family_num_string =family_num;
        comment = memo.getText().toString();

    }
    private class NetworkCall extends AsyncTask<Call,Void, String> {
        ArrayList<puppy> items= new ArrayList<puppy>();
        @Override
        protected String doInBackground(Call... calls) {
            updateinfo();
            Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(RetrofitExService.URL)
                    .build();
            RetrofitExService Ret= retrofit.create(RetrofitExService.class);
            JsonObject obj = new JsonObject();
           obj.addProperty("PetSeq", num);
            obj.addProperty("ReqName", username);
            obj.addProperty("PhoneNum", phone_num);
            obj.addProperty("Location1", add1);
            obj.addProperty("Location2", add2);
            obj.addProperty("Job", job_string);
            obj.addProperty("FamilyNum", family_num_string);
            obj.addProperty("CommentDesc", comment);
            obj.addProperty("Experience", experience);

            Call<JsonObject> call = Ret.postTest("insertReqPet.sk",obj);
            JsonObject obj2 = new JsonObject();
            obj2.addProperty("Seq", num);
            Call<JsonObject> call2 = Ret.postTest("updateRequestCnt.sk",obj2);
            try {
                JsonObject object = call.execute().body();
                JsonObject object2 = call2.execute().body();
                String s="";
                String s2="";
                if (object != null) {
                    s = object.get("Result").getAsString();
                    s2 = object2.get("Result").getAsString();
                }
                return s2;
            }
            catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            if(s.equals("true")){

            }
            Log.e("TAG",s);

        }

    }

}
