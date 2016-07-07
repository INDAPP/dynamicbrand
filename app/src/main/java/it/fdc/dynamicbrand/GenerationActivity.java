package it.fdc.dynamicbrand;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GenerationActivity extends AppCompatActivity implements
        PrincipleFragment.OnPrincipleFragmentInteractionListener,
        AreaFragment.OnAreaFragmentInteractionListener {
    private String[] mSectionTitles;
    private int[] mPrinciples = new int[] {1,0,0,0,0,0,0,1,0};
    private boolean[] mAreas = new boolean[] {false,false,false,false,false,false,false,false,false,
            false,false,false};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generation);

        mSectionTitles = getResources().getStringArray(R.array.tab_section);

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        InputPagerAdapter adapter = new InputPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);


//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.container, PrincipleFragment.newInstance(), "principles")
//                .commit();


    }

    @Override
    public int onRequestPrincipleValue(int index) {
        return mPrinciples[index];
    }

    @Override
    public void onSetPrincipleValue(int index, int value) {
        mPrinciples[index] = value;
        PrincipleFragment fragment = (PrincipleFragment) getSupportFragmentManager()
                .findFragmentByTag("android:switcher:" + R.id.pager + ":" + 0);
        fragment.update();
    }


    @Override
    public boolean onRequestAreaValue(int index) {
        return mAreas[index];
    }

    @Override
    public void onSetAreasValue(int index, boolean value) {
        mAreas[index] = value;
        AreaFragment fragment = (AreaFragment) getSupportFragmentManager()
                .findFragmentByTag("android:switcher:" + R.id.pager + ":" + 1);
        fragment.update();
    }

    class InputPagerAdapter extends FragmentPagerAdapter {

        public InputPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return PrincipleFragment.newInstance();
                case 1:
                    return AreaFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mSectionTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mSectionTitles[position];
        }
    }
}
