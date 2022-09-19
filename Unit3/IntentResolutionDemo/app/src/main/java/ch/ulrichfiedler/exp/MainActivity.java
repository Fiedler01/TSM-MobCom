package ch.ulrichfiedler.exp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private final List<SamplePagerItem> mTabs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mTabs.add(new SamplePagerItem(getString(R.string.tab_call), // Title
                Color.BLUE, // Indicator color
                Color.GRAY)); // Divider color

        mTabs.add(new SamplePagerItem(getString(R.string.tab_map), // Title
                Color.RED, // Indicator color
                Color.GRAY)); // Divider color

        mTabs.add(new SamplePagerItem(getString(R.string.tab_web), // Title
                Color.YELLOW, // Indicator color
                Color.GRAY)); // Divider color

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewStateAdapter(getSupportFragmentManager(), getLifecycle()));

        tabLayout = findViewById(R.id.tabs);

        addTabs();
        addTabListener();
        addPageListener();
    }

    private void addTabs() {
        tabLayout.addTab(tabLayout.newTab().setText(mTabs.get(0).getTitle()));
        tabLayout.addTab(tabLayout.newTab().setText(mTabs.get(1).getTitle()));
        tabLayout.addTab(tabLayout.newTab().setText(mTabs.get(2).getTitle()));
    }

    private void addTabListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void addPageListener() {
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
                tabLayout.setSelectedTabIndicatorColor(mTabs.get(position).getIndicatorColor());
            }
        });
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
