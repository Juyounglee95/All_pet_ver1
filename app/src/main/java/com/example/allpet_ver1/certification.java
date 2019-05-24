package com.example.allpet_ver1;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class certification extends AppCompatActivity implements View.OnClickListener {
    int success;
    String mCurrentPhotoPath;
    final String Tag = getClass().getSimpleName();
    ImageView imageView;
    Button cameraBtn, uploadBtn;
    TextView file_name;
    final static int TAKE_PICTURE=1;
    BottomNavigationView bottomNavigationView;
    String id;
    private Uri imageUri;
    String imgname;
    File image_file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);
        PieChartView pieChartView = findViewById(R.id.chart);

        Intent i = getIntent();
        id =i.getExtras().getString("Id");
       // final String pw= i.getExtras().getString("Pw");
        imageView = findViewById(R.id.cert_image);
        cameraBtn = findViewById(R.id.camera_button);
        cameraBtn.setOnClickListener(this);
        file_name = findViewById(R.id.file_name);
        uploadBtn = findViewById(R.id.upload_button);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        uploadBtn.setOnClickListener(this);
        //권한 체크
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED ) {
                Log.d(Tag, "권한 설정 완료");
            } else {
                Log.d(Tag, "권한 설정 요청");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }

        }

        List pieData = new ArrayList<>();
        success = 80;
        //#ffd933
        pieData.add(new SliceValue(success, Color.parseColor("#D93B3B")).setLabel("SUCCESS"));
        pieData.add(new SliceValue((100-success), Color.parseColor("#d3d3d3")));


        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData.setHasCenterCircle(true).setCenterText1("MY GOAL").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView.setPieChartData(pieChartData);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.enroll_dog:
                        break;
                    case R.id.home:
                        intent = new Intent(certification.this, mainpage_picture.class);
                        intent.putExtra("Id", id);
                        //intent.putExtra("Pw", pw);
                        startActivity(intent);
                        break;
                    case R.id.certificate:

                        break;
                    case R.id.profile:
                        intent = new Intent(certification.this, profile.class);
                        intent.putExtra("Id", id);
                       // intent.putExtra("Pw", pw);
                        startActivity(intent);
                        break;

                }
                return true;
            }
        });

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.upload_button:

                Log.e("TTTTTT", mCurrentPhotoPath);
                        NetworkCall networkCall = new NetworkCall();
                        networkCall.execute();
                new AlertDialog.Builder(this)
                        .setTitle("업로드 확인")
                        .setMessage("인증샷이 업로드 되었습니다.")
                        .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
                break;
            case R.id.camera_button:
                // 카메라 앱을 여는 소스
//                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, TAKE_PICTURE);
                dispatchTakePictureIntent();
                break;
        }
    }

    // 카메라로 촬영한 영상을 가져오는 부분
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
    try{
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == RESULT_OK) {
                    image_file = new File(mCurrentPhotoPath);
                    Bitmap bitmap = MediaStore.Images.Media
                            .getBitmap(getContentResolver(), Uri.fromFile(image_file));
                    if (bitmap != null) {
                        file_name.setText(image_file.getName());
                        imgname = image_file.getName();

                       // String[] names= file_name.getText().toString().split("_");
                        //imgname = names[0]+"_"+names[1];
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
                        //galleryAddPic();

                        imageView.setImageBitmap(rotatedBitmap);
                    }
                }


                break;
        }
    }catch (Exception error){
        error.printStackTrace();
    }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);

        File f = new File(mCurrentPhotoPath);

        Uri contentUri = Uri.fromFile(f);

        mediaScanIntent.setData(contentUri);

        sendBroadcast(mediaScanIntent);

        Toast.makeText(this,"사진이 저장되었습니다",Toast.LENGTH_SHORT).show();
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
                imageUri =photoURI;
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, TAKE_PICTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = id+"_"+timeStamp+"_";
       //
        //
        //
        //imgname = imageFileName;
        //File imageFile = null;
        File storageDir = new File(Environment.getExternalStorageDirectory()+"/Pictures","All_pet");

        if(!storageDir.exists()){
            storageDir.mkdirs();
        }
//        File imageFile = new File.createTempFile(imgname,".jpg", storageDir);



        File imageFile = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = imageFile.getAbsolutePath();

        return imageFile;
    }
    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }
    private class NetworkCall extends AsyncTask<Call,Void, String> {
        ArrayList<puppy> items= new ArrayList<puppy>();

        @Override
        protected String doInBackground(Call... calls) {
            Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(RetrofitExService.URL)
                    .build();
            RetrofitExService Ret= retrofit.create(RetrofitExService.class);
            JsonObject obj = new JsonObject();
            obj.addProperty("Id", id);
            obj.addProperty("PetSeq",666 );
            obj.addProperty("File", imgname);
            System.out.println("Start");
            FTPUploader ftpUploader = null;
            try {
                ftpUploader = new FTPUploader("18.191.37.77", "ftpuser", "1234");
                ftpUploader.uploadFile(image_file, imgname, "/var/lib/tomcat8/webapps/upload/");
                ftpUploader.disconnect();
                System.out.println("Done");

            } catch (Exception e) {
                e.printStackTrace();
            }

            Call<JsonObject> call = Ret.postTest("RegAuth.sk",obj);
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

            }
            Log.e("TAG",s);

        }



    }

}
