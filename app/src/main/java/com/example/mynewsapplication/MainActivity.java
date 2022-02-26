package com.example.mynewsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    TabLayout tabLayout;
    TabItem mhome,msport,mhealth,mentertainment, mscience;
    pagerAdapter pagerAdapter;
    Toolbar mtoolbar;
    String api = "5017ae8d84a14d3d8d2808f4e8983194";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mtoolbar=findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);


        mhome= findViewById(R.id .home);
        mscience= findViewById(R.id .science);
        mhealth= findViewById(R.id .healt);
        mentertainment= findViewById(R.id .entertainment);
        msport=findViewById(R.id.sport);

        ViewPager  viewpager=findViewById(R.id.fragmentcontainer);
        tabLayout= findViewById(R.id.include);


        pagerAdapter= new pagerAdapter(getSupportFragmentManager(),5);
        viewpager.setAdapter(pagerAdapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0||tab.getPosition()==1||tab.getPosition()==2||tab.getPosition()==3||tab.getPosition()==4){

                    pagerAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

      
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



    }
}