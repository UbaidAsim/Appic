package com.example.muhammadubaidullah.appic;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class ContainerActivityTwo extends AppCompatActivity {

    private SectionPageAdapter mSectionPageAdapter;
    private static final String TAG="ContainerActivityTwo";
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_two);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSectionPageAdapter=new SectionPageAdapter(getSupportFragmentManager());
        mViewPager=(ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout= (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    public void setupViewPager(ViewPager viewPager){
    SectionPageAdapter adapter= new SectionPageAdapter(getSupportFragmentManager());
    adapter.addFragment(new NumberFragment(),"Numbers");
    adapter.addFragment(new FamilyFragment(),"Family");
    adapter.addFragment(new ColorFragment(),"Colors");
    adapter.addFragment(new PhrasesFragment(),"Phrases");
    viewPager.setAdapter(adapter);

    }
}
