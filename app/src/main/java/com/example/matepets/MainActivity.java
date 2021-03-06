package com.example.matepets;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private SliderAdapter sliderAdapter;
    private Button nextBtn;
    private Button prevBtn;
    private int currentPage;
    private int visitCount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        if (visitCount==1){
//            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
//            visitCount++;
//            startActivity(intent);
//            finish();
//        }

        viewPager=findViewById(R.id.viewPager);
        dotsLayout=findViewById(R.id.dotsLayout);

        nextBtn=findViewById(R.id.buttonnext);
        prevBtn=findViewById(R.id.buttonprevious);

        sliderAdapter=new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        addDotesInd(0);
        viewPager.addOnPageChangeListener(viewListener);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(currentPage+1);
            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(currentPage-1);
            }
        });
    }

    public void addDotesInd(int position){
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for (int i=0;i<dots.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.transparentwhite));
            dotsLayout.addView(dots[i]);

        }
        if(dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }
    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotesInd(i);
            currentPage=i;
            if(i==0){
                nextBtn.setEnabled(true);
                prevBtn.setEnabled(false);
                prevBtn.setVisibility(View.INVISIBLE);
                nextBtn.setText("NEXT");
                prevBtn.setText("");
            }
            else if(i==dots.length-1){
                nextBtn.setEnabled(true);
                prevBtn.setEnabled(true);
                prevBtn.setVisibility(View.VISIBLE);
                nextBtn.setText("FINISH");
                prevBtn.setText("BACK");
                nextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity.this, WelcomeMainActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
            }
            else{
                nextBtn.setEnabled(true);
                prevBtn.setEnabled(true);
                prevBtn.setVisibility(View.VISIBLE);
                nextBtn.setText("NEXT");
                prevBtn.setText("BACK");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
