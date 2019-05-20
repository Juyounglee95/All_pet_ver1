package com.example.allpet_ver1;


        import android.app.Activity;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import com.daimajia.slider.library.Animations.DescriptionAnimation;
        import com.daimajia.slider.library.SliderLayout;
        import com.daimajia.slider.library.SliderTypes.BaseSliderView;
        import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
        import com.daimajia.slider.library.Tricks.ViewPagerEx;

        import android.app.AlertDialog;



public class dog_info extends Activity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    //SliderLayout
    private SliderLayout sliderLayout ;

    Dog dog;

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

    //images
    int[] HashMapForLocalRes={R.drawable.golden_1, R.drawable.golden_2, R.drawable.golden_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String name, int age, String gender, String operation, String from, String to, int price, String text
        dog=new Dog("레몬",4,"남","Y","2019-04-20","2019-05-29",80000,"잘부탁드립니다.",0);

        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        gender=findViewById(R.id.gender);
        operation=findViewById(R.id.operation);
        term=findViewById(R.id.term);
        price=findViewById(R.id.price);
        text=findViewById(R.id.text);
        count=findViewById(R.id.count);
        want_button=findViewById(R.id.btn_want);


        name.setText(dog.getName());
        age.setText(String.valueOf(dog.getAge()));
        gender.setText(dog.getGender());
        operation.setText(dog.getOperation());
        term.setText(dog.getFrom()+"~"+dog.getTo());
        price.setText(String.valueOf(dog.getPrice()));
        text.setText(dog.getText());
        count.setText(String.valueOf(dog.getWant_cnt()));

        //want_button click
        want_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // AlertDialog 셋팅
                alertDialogBuilder
                        .setMessage("다시 한번 생각하셨나요?\n" +
                                "동의를 누르면 화면이 넘어갑니다.")
                        .setCancelable(false)
                        .setPositiveButton("동의", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dog.setWant_cnt(dog.getWant_cnt()+1);
                                Intent intent=new Intent(getApplicationContext(), RegisterMyForm.class);
                                intent.putExtra("dog", dog);
                                startActivity(intent);

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
        for(int index=0 ;index< HashMapForLocalRes.length; index++){

            //TextSliderView
            DefaultSliderView textSliderView = new DefaultSliderView(dog_info.this);

            textSliderView
                    //.description(name)
                    .image(HashMapForLocalRes[index])
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);

        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        sliderLayout.setCustomAnimation(new DescriptionAnimation());

        sliderLayout.setDuration(3000);

        sliderLayout.addOnPageChangeListener(dog_info.this);
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

}
