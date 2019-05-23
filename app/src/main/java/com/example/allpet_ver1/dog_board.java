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


public class dog_board extends Activity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    //SliderLayout
    private SliderLayout sliderLayout ;

 //   Dog dog;

    private TextView name;
    private TextView age;
    private TextView gender;
    private TextView operation;
    private TextView term;
    private TextView price;
    private TextView text;
    private TextView count;

    private Button want_button;

    final Context context = this;
    //String  dogname;
    String[] imgUrl = new String[3];
    String[] address = new String[2];
    int money;
    //images
//    int[] HashMapForLocalRes={R.drawable.golden_1, R.drawable.golden_2, R.drawable.golden_3};
//    String[] dog_images={"https://www.dogbreedslist.info/uploads/allimg/dog-pictures/Golden-Retriever-2.jpg",
//    "https://www.dogbreedslist.info/uploads/allimg/dog-pictures/Golden-Retriever-2-2.jpg",
//    "https://www.dogbreedslist.info/uploads/allimg/dog-pictures/Golden-Retriever-2-3.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_info);
        Intent i = getIntent();
        final puppy p = i.getParcelableExtra("puppy");
        final int num = i.getExtras().getInt("num");
        final String p_id = i.getExtras().getString("Id");
        imgUrl[0] = p.getUrl1();
        imgUrl[1] = p.getUrl2();
        imgUrl[2] = p.getUrl3();
        address[0] = p.getAdd1();
        address[1] = p.getAdd2();
//
//        imgUrl[0] = i.getExtras().getString("url");
//        dogname = i.getExtras().getString("name");
//        money = i.getExtras().getInt("money");
//        //String name, int age, String gender, String operation, String from, String to, int price, String text
        //dog=new Dog(dogname,4,"남","Y","2019-04-20","2019-05-29",money,"잘부탁드립니다.",0);

        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        gender=findViewById(R.id.gender);
        operation=findViewById(R.id.operation);
        term=findViewById(R.id.term);
        price=findViewById(R.id.price);
        text=findViewById(R.id.text);
        count=findViewById(R.id.count);
        want_button=findViewById(R.id.btn_want);


        name.setText(p.getname());
        age.setText(String.valueOf(p.getAge()));
        gender.setText(p.getGender());
        operation.setText(p.getNeutral());
        term.setText(p.getStartDate()+"~"+p.getEndDate());
        price.setText(String.valueOf(p.getmoney()));
        text.setText(p.getDescription());
        count.setText(String.valueOf(p.getCount()));
        if(p.getStatus() == 1) {
            want_button.setText("분양 중입니다.");
        } else if(p.getStatus() == 2) {
            want_button.setText("거래 완료 되었습니다.");
        } else {
            want_button.setText("보증이 완료 되었습니다.");
        }

        sliderLayout = (SliderLayout) findViewById(R.id.slider);

        //stop auto cycle
        onStop();

        //insert images
        for(int index=0 ;index< imgUrl.length; index++){

            //TextSliderView
            DefaultSliderView textSliderView = new DefaultSliderView(dog_board.this);

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

        sliderLayout.addOnPageChangeListener(dog_board.this);
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
