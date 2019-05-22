package com.example.allpet_ver1;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class dog_info_upload extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    //image
    ImageView image1, image2, image3;
    ImageView[] images={image1, image2, image3};
    String[] paths = new String[3];
    int position=0;
    private AlertDialog dialog;
    //사진 경로
    String mCurrentPhotoPath;

    Button btn_image;
    EditText name;
    //age
    Spinner spin1;
    ArrayAdapter<CharSequence> adspin1;
    int age=0;

    //성별, 중성화 여부
    RadioGroup radio_gender;
    RadioGroup radio_operation;
    String gender="";
    String operation="";
    String dogname="";
    String description="";
    int deposit=0;
    EditText price;
    Boolean result= false;
    TextView date_start, date_end;
    String startDate, endDate;
    //년, 월, 일
    int mYear, mMonth, mDay;
    int flag=0;

    EditText memo;

    Button btn_upload;

    private static final int TAKE_PICTURE = 1;
    private static final int PICK_FROM_GALLERY = 2;
    String userid;
    int idx=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_info_upload);
        Intent i = getIntent();
        userid = i.getExtras().getString("Id");

        //images
        images[0]=(ImageView) findViewById(R.id.image1);
        images[1]=(ImageView) findViewById(R.id.image2);
        images[2]=(ImageView) findViewById(R.id.image3);

        btn_image=(Button)findViewById(R.id.btn_image);

        //이미지 첨부하기 클릭
        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog 실행
                dialog.show();
            }
        });

        //이미지 첨부하기
        captureImageInitialization();


        //이름
        name = (EditText) findViewById(R.id.name);
        //가격
        price=(EditText)findViewById(R.id.price);

        //부탁의 말
        memo=(EditText)findViewById(R.id.memo);
        //Upload
        btn_upload=(Button)findViewById(R.id.btn_upload);

        //age spinner
        spin1 = (Spinner)findViewById(R.id.age);
        adspin1 = ArrayAdapter.createFromResource(this, R.array.family_num,android.R.layout.simple_spinner_item);
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

        //성별, 중성화 여부
        radio_gender=(RadioGroup) this.findViewById(R.id.radio_gender);
        radio_operation = (RadioGroup) this.findViewById(R.id.radio_operation);

        radio_gender.setOnCheckedChangeListener(this);
        radio_operation.setOnCheckedChangeListener(this);


        //시작, 종료 TextView
        date_start = (TextView)findViewById(R.id.date_start);
        date_end = (TextView)findViewById(R.id.date_end);

        //현재 날짜와 시간을 가져오기위한 Calendar 인스턴스 선언
        Calendar cal = new GregorianCalendar();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        //화면에 TextView 업데이트
        if(flag==0) UpdateStart();
        else UpdateEnd();


    }
    public void updateinfo(){
        dogname = name.getText().toString();
        deposit = Integer.parseInt(price.getText().toString());
        description = memo.getText().toString();

    }

    //기간 시작, 종료 눌렀을 시
    public void mStartClick(View v){
        flag = 0;
        new DatePickerDialog(dog_info_upload.this, mDateSetListener, mYear, mMonth, mDay).show();
    }
    public void mEndClick(View v){
        flag = 1;
        new DatePickerDialog(dog_info_upload.this, mDateSetListener, mYear, mMonth, mDay).show();
    }

    //btn_upload 눌렀을 시
    public void UploadClick(View v){
        NetworkCall networkCall = new NetworkCall();
        networkCall.execute();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // AlertDialog 셋팅
        alertDialogBuilder
                .setMessage("신청되었습니다.")
                .setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //현재 Activity 종료
                        if (result) {
                            finish();
                            dialog.cancel();

                            Intent intent = new Intent(getApplicationContext(), dog_info.class);

                            puppy p = new puppy(paths[0],paths[1], paths[2], dogname, deposit, operation, description, "댕댕시",age,gender, "멍멍구", "말티즈", userid, startDate, endDate, 1, 0);
                            Dog dog = new Dog(name.getText().toString(), age, gender, operation, date_start.getText().toString(), date_end.getText().toString(),
                                    Integer.parseInt(price.getText().toString()), memo.getText().toString(), 0);

                            intent.putExtra("puppy", p);
                            startActivity(intent);
                        }
                    }
                });

        // 다이얼로그 생성
        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setTitle("확인");
        alertDialog.setIcon(R.drawable.dog_foot);

        // 다이얼로그 보여주기
        alertDialog.show();
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
        }
    }

    //텍스트뷰의 값을 업데이트 하는 메소드
    void UpdateStart(){
        date_start.setText(String.format("%d-%d-%d", mYear, mMonth + 1, mDay));
        startDate =String.format("%d-%d-%d", mYear, mMonth + 1, mDay);
    }
    void UpdateEnd(){
        date_end.setText(String.format("%d-%d-%d", mYear, mMonth + 1, mDay));
        endDate= String.format("%d-%d-%d", mYear, mMonth + 1, mDay);
    }

    //날짜 대화상자 리스너 부분
    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // TODO Auto-generated method stub

            //사용자가 입력한 값을 가져온뒤
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;

            //텍스트뷰의 값을 업데이트함
            if(flag==0) UpdateStart();
            else UpdateEnd();
        }
    };



    private void captureImageInitialization() {

        final String[] items = new String[] { "Camera", "Gallery" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("실행");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) { // pick from
                // camera
                if (item == 0) {
//                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(cameraIntent, CAMERA_PICK);
                    dispatchTakePictureIntent();

                } else {
                    // pick from file

                    Intent intent = new Intent();
                    intent.setType("image/*"); //set type for files (image type)
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_FROM_GALLERY);

                }
            }
        });

        dialog = builder.create();
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

            obj.addProperty("Id",userid);
            obj.addProperty("PetName",dogname);
            obj.addProperty("Age",age);
            obj.addProperty("Gender",gender);
            obj.addProperty("Neutral",operation);
            obj.addProperty("Deposit", deposit);
            obj.addProperty("StartDate",startDate) ;
            obj.addProperty("EndDate",endDate);
            obj.addProperty("ImgPath1",paths[0]);
            obj.addProperty("ImgPath2", paths[1]);
            obj.addProperty("ImgPath3",paths[2]);
            obj.addProperty("Description",description);
            obj.addProperty("StatusValue",1);
            obj.addProperty("Breeds","시츄");
            obj.addProperty("Address1", "서울");
            obj.addProperty("Address2","강남구");


            Call<JsonObject> call = Ret.postTest("RegPet.sk",obj);
            try {
                JsonObject object = call.execute().body();
                String s="";
                if (object != null) {
                    s = object.get("result").getAsString();

                    }
                    return s;
                }
            catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            if(s.equals("true")){
                result=true;
            }
            Log.e("TAG",s);

        }



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK) {

            try{
                switch (requestCode) {
                    case TAKE_PICTURE:
                        if (resultCode == RESULT_OK) {
                            File file = new File(mCurrentPhotoPath);
                            Bitmap bitmap = MediaStore.Images.Media
                                    .getBitmap(getContentResolver(), Uri.fromFile(file));
                            if (bitmap != null) {
                                ExifInterface ei = new ExifInterface(mCurrentPhotoPath);
                                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                                        ExifInterface.ORIENTATION_UNDEFINED);

                                Bitmap rotatedBitmap = null;
                                switch(orientation) {

                                    case ExifInterface.ORIENTATION_ROTATE_90:
                                        rotatedBitmap = rotateImage(bitmap, 90);
                                        break;

                                    case ExifInterface.ORIENTATION_ROTATE_180:
                                        rotatedBitmap = rotateImage(bitmap, 180);
                                        break;

                                    case ExifInterface.ORIENTATION_ROTATE_270:
                                        rotatedBitmap = rotateImage(bitmap, 270);
                                        break;

                                    case ExifInterface.ORIENTATION_NORMAL:
                                    default:
                                        rotatedBitmap = bitmap;
                                }

                                images[position++].setImageBitmap(rotatedBitmap);
                            }
                        }


                        break;
                }
            }catch (Exception error){
                error.printStackTrace();
            }
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
            //set photo bitmap to ImageView
//            images[position++].setImageBitmap(photo);
        } else if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            images[position++].setImageURI(selectedImage);
        }
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.allpet_ver1.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, TAKE_PICTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();


        return image;
    }
    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

}