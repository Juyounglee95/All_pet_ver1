package com.example.allpet_ver1;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

    final Context context = this;

    Dog dog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_my_form);

        Intent intent=getIntent();
        dog=(Dog)intent.getSerializableExtra("dog");
        name=(EditText) findViewById(R.id.user_name);
        phone = (EditText) findViewById(R.id.phonenumber);
        job = (EditText) findViewById(R.id.job);
        memo = (EditText) findViewById(R.id.memo);
        btn_register=(Button) findViewById(R.id.btn_register);

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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // AlertDialog 셋팅
                alertDialogBuilder
                        .setMessage("신청되었습니다.")
                        .setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dog.setWant_cnt(dog.getWant_cnt()+1);
                                //현재 Activity 종료
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

}
