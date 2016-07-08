package it.fdc.dynamicbrand;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class GenerationActivity extends AppCompatActivity implements
        PrincipleFragment.OnPrincipleFragmentInteractionListener,
        AreaFragment.OnAreaFragmentInteractionListener {
    private static final String KEY_PRINCIPLES = "principles";
    private static final String KEY_AREAS = "areas";
    private String[] mSectionTitles;
    private int[] mPrinciples = new int[] {1,0,0,0,0,0,0,1,0};
    private boolean[] mAreas = new boolean[] {false,false,false,false,false,false,false,false,false,
            false,false,false};

    private BrandImageView mBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generation);

        mBrand = (BrandImageView)findViewById(R.id.imageView);
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
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor edit= getSharedPreferences(getPackageName(), Context.MODE_PRIVATE).edit();
        edit.putInt(KEY_PRINCIPLES + "_Count", mPrinciples.length);
        int pcount = 0;
        for (int i: mPrinciples){
            edit.putInt(KEY_PRINCIPLES + pcount ++, i);
        }
        edit.putInt(KEY_AREAS + "_Count", mAreas.length);
        int acount = 0;
        for (boolean i: mAreas){
            edit.putBoolean(KEY_AREAS + acount++, i);
        }
        edit.apply();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        int pcount = prefs.getInt(KEY_PRINCIPLES + "_Count", 0);
        if (pcount > 0) {
            mPrinciples = new int[pcount];
            for (int i = 0; i < pcount; i++){
                mPrinciples[i] = prefs.getInt(KEY_PRINCIPLES + i, i);
            }
        }
        int acount = prefs.getInt(KEY_AREAS + "_Count", 0);
        if (acount > 0) {
            mAreas = new boolean[acount];
            for (int i = 0; i < acount; i++){
                mAreas[i] = prefs.getBoolean(KEY_AREAS + i, false);
            }
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_generation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                reset();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    private void reset() {
        mPrinciples = new int[] {1,0,0,0,0,0,0,1,0};
        mAreas = new boolean[] {false,false,false,false,false,false,false,false,false,
                false,false,false};
        mBrand.invalidate();
        PrincipleFragment fragment1 = (PrincipleFragment) getSupportFragmentManager()
                .findFragmentByTag("android:switcher:" + R.id.pager + ":" + 0);
        if (fragment1 != null) fragment1.update();
        AreaFragment fragment2 = (AreaFragment) getSupportFragmentManager()
                .findFragmentByTag("android:switcher:" + R.id.pager + ":" + 1);
        if (fragment2 != null) fragment2.update();
    }

    // Fragments Callback

    @Override
    public int onRequestPrincipleValue(int index) {
        return mPrinciples[index];
    }

    @Override
    public void onSetPrincipleValue(int index, int value) {
        mPrinciples[index] = value;
        mBrand.setData(mPrinciples, mAreas);
        PrincipleFragment fragment = (PrincipleFragment) getSupportFragmentManager()
                .findFragmentByTag("android:switcher:" + R.id.pager + ":" + 0);
        fragment.update();
    }

    @Override
    public int onRequestPrincipleSelectedCount() {
        int count = 0;
        for (int v : mPrinciples)
            if (v > 0) count++;
        return count;
    }

    @Override
    public boolean onRequestAreaValue(int index) {
        return mAreas[index];
    }

    @Override
    public void onSetAreasValue(int index, boolean value) {
        mAreas[index] = value;
        mBrand.setData(mPrinciples, mAreas);
        AreaFragment fragment = (AreaFragment) getSupportFragmentManager()
                .findFragmentByTag("android:switcher:" + R.id.pager + ":" + 1);
        fragment.update();
    }

    // View Pager

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
