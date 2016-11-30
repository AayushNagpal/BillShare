package billshare.com.activities;


import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import billshare.com.adapters.GroupAdapter;
import billshare.com.fragments.GroupFragment;
import billshare.com.fragments.ProfileFragment;
import billshare.com.responses.ResponseStatus;
import billshare.com.restservice.RestServiceObject;
import billshare.com.utils.AmountUtil;
import billshare.com.utils.GroupInfo;
import billshare.com.utils.GroupsList;
import billshare.com.utils.PreferenceUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.mipmap.add_people,
            R.mipmap.icon_user
    };
    private TextView oweTextView, getTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        oweTextView = (TextView) findViewById(R.id.credit);
        getTextView = (TextView) findViewById(R.id.debit);
      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        Call<GroupsList> groups = RestServiceObject.getiRestServicesObject(getApplicationContext()).groups(PreferenceUtil.instance(getApplicationContext()).getIdFromSPreferences());
        groups.enqueue(new Callback<GroupsList>() {
            @Override
            public void onResponse(Call<GroupsList> call, Response<GroupsList> response) {
                GroupsList body = response.body();
                ResponseStatus responseStatus = body.getResponseStatus();
                if (responseStatus != null && responseStatus.getCode() == 200) {
                    List<GroupInfo> groupInfo = body.getGroupInfo();
                    Double debitAmount = 0.0, creditAmount = 0.0;
                    for (GroupInfo groupInfo1 : groupInfo) {
                        debitAmount += Double.parseDouble(AmountUtil.instance(getApplicationContext()).getDividedAmount(groupInfo1.getUsers(), groupInfo1.getAmount(), groupInfo1.getAdminId(), true));
                    }
                    oweTextView.setText(String.valueOf(debitAmount));
                    for (GroupInfo groupInfo1 : groupInfo) {
                        creditAmount += Double.parseDouble(AmountUtil.instance(getApplicationContext()).getDividedAmount(groupInfo1.getUsers(), groupInfo1.getAmount(), groupInfo1.getAdminId(), false));
                    }
                    getTextView.setText(String.valueOf(creditAmount));

                }
            }

            @Override
            public void onFailure(Call<GroupsList> call, Throwable t) {

            }
        });
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        // tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new GroupFragment(), "Groups");
        adapter.addFrag(new ProfileFragment(), "You");

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            PreferenceUtil.instance(getApplicationContext()).clearSPreferences();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
