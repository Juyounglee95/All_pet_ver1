package com.example.allpet_ver1;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;


public class point_info extends Activity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    //SliderLayout
    private SliderLayout sliderLayout ;

 //   Dog dog;

    private TextView name;
    private TextView point;
    private TextView description;

    private Button want_button;

    final Context context = this;
    //String  dogname;
    String[] imgUrl = new String[3];
    int num =0;
    int money;
    //images
//    int[] HashMapForLocalRes={R.drawable.golden_1, R.drawable.golden_2, R.drawable.golden_3};
//    String[] dog_images={"https://www.dogbreedslist.info/uploads/allimg/dog-pictures/Golden-Retriever-2.jpg",
//    "https://www.dogbreedslist.info/uploads/allimg/dog-pictures/Golden-Retriever-2-2.jpg",
//    "https://www.dogbreedslist.info/uploads/allimg/dog-pictures/Golden-Retriever-2-3.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);
        Intent i = getIntent();
        final point_data p = i.getParcelableExtra("sksk");
        //num = i.getExtras().getInt("num");
        //final String p_id = i.getExtras().getString("Id");
        imgUrl[0] = i.getExtras().getString("url");
        //imgUrl[1] = p.getUrl();
        //imgUrl[2] = p.getUrl();

//
//        imgUrl[0] = i.getExtras().getString("url");
//        dogname = i.getExtras().getString("name");
//        money = i.getExtras().getInt("money");
//        //String name, int age, String gender, String operation, String from, String to, int price, String text
        //dog=new Dog(dogname,4,"남","Y","2019-04-20","2019-05-29",money,"잘부탁드립니다.",0);

        name=findViewById(R.id.name);
        description = findViewById(R.id.text);
        point = findViewById(R.id.price);
        want_button=findViewById(R.id.btn_want);


        name.setText(i.getExtras().getString("name"));
        description.setText(i.getExtras().getString("text"));
        point.setText(i.getExtras().getString("money"));

        //want_button click
        want_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // AlertDialog 셋팅
                alertDialogBuilder
                        .setMessage("구매하시겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("동의", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Toast.makeText(context, "구매가 완료되었습니다.", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 취소한다
                                dialog.cancel();
                            }
                        });

                // 다이얼로그 생성
                AlertDialog alertDialog = alertDialogBuilder.create();

                alertDialog.setTitle("주의");
                alertDialog.setIcon(R.drawable.caution);

                // 다이얼로그 보여주기
                alertDialog.show();
            }
        });


        sliderLayout = (SliderLayout) findViewById(R.id.slider);

        //stop auto cycle
        onStop();

        //insert images
        for(int index=0 ;index< 1; index++){

            //TextSliderView
            DefaultSliderView textSliderView = new DefaultSliderView(point_info.this);

            textSliderView
                    //.description(name)
                    .image(imgUrl[index])
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);

        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        sliderLayout.setCustomAnimation(new DescriptionAnimation());

        sliderLayout.setDuration(3000);

        sliderLayout.addOnPageChangeListener(point_info.this);
    }

    @Override
    protected void onStop() {
        sliderLayout.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {}

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {}

    @Override
    public void onPageScrollStateChanged(int state) {}

    //url images insert
//    public void AddImagesUrlOnline(){
//
//        HashMapForURL = new HashMap<String, String>();
//
//        HashMapForURL.put("CupCake", "http://androidblog.esy.es/images/cupcake-1.png");
//        HashMapForURL.put("Donut", "http://androidblog.esy.es/images/donut-2.png");
//        HashMapForURL.put("Eclair", "http://androidblog.esy.es/images/eclair-3.png");
//    }


    @Override
    //새로 고침
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }
}
