package ch.ulrichfiedler.exp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import androidx.annotation.NonNull;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private final List<SamplePagerItem> mTabs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mTabs.add(new SamplePagerItem(getString(R.string.tab_call), // Title
                Color.BLUE, // Indicator color
                Color.GRAY, // Divider color
                new LaunchCallFragment()));

        mTabs.add(new SamplePagerItem(getString(R.string.tab_map), // Title
                Color.RED, // Indicator color
                Color.GRAY, // Divider color
                new LaunchMapFragment()));

        mTabs.add(new SamplePagerItem(getString(R.string.tab_web), // Title
                Color.YELLOW, // Indicator color
                Color.GRAY, // Divider color
                new LaunchWebSiteFragment()));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(), 0));

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        addPageListener();
    }

    private void addPageListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                tabLayout.setSelectedTabIndicatorColor(mTabs.get(position).getIndicatorColor());
            }
        });
    }


    static class SamplePagerItem {
        private final CharSequence mTitle;
        private final int mIndicatorColor;
        private final int mDividerColor;
        private final Fragment fragment;

        SamplePagerItem(CharSequence title, int indicatorColor,
                        int dividerColor, Fragment fragment) {
            mTitle = title;
            mIndicatorColor = indicatorColor;
            mDividerColor = dividerColor;
            this.fragment = fragment;
        }

        Fragment getFragment() {
            return this.fragment;
        }

        CharSequence getTitle() {
            return mTitle;
        }


        int getIndicatorColor() {
            return mIndicatorColor;
        }

        int getDividerColor() {
            return mDividerColor;
        }
    }


    class SampleFragmentPagerAdapter extends FragmentPagerAdapter {


        public SampleFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int i) {
            return mTabs.get(i).getFragment();
        }

        @Override
        public int getCount() {
            return mTabs.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mTabs.get(position).getTitle();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted
                Uri phoneNumber = Uri.parse("tel:+41797643480"); // U. Fiedler's cell phone
                Intent callIntent = new Intent(Intent.ACTION_CALL, phoneNumber);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        }
    }
}
