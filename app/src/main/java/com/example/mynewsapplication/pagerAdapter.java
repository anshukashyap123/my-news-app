package com.example.mynewsapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class pagerAdapter<position> extends FragmentPagerAdapter {

    int tabcount;

    public pagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount = behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

       switch(position )

    {

        case 0:
            return new HomeFragment();

        case 1:
            return new sportFragment();

        case 2:
            return new HealthFragment();

        case 3:
            return new scienceFragment();

        case 4:
            return new EntertainmentFragment();

        default:
            return null;
    }

}

    @Override
    public int getCount() {
        return tabcount;
    }
}
