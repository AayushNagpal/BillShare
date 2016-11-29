package billshare.com.activities;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import billshare.com.adapters.SelectedFriendList;
import billshare.com.utils.AmountUtil;
import billshare.com.utils.GroupInfo;
import billshare.com.utils.StringConstants;

public class GroupActivity extends AppCompatActivity {
    private ListView friendList;
    private TextView getAmountTextView, oweAmountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        GroupInfo groupInfo = (GroupInfo) getIntent().getSerializableExtra(StringConstants.GROUP_INFO);
        friendList = (ListView) findViewById(R.id.friend_list);
        getAmountTextView = (TextView) findViewById(R.id.debit);
        oweAmountTextView = (TextView) findViewById(R.id.credit);
        setTitle(groupInfo.getName());
        oweAmountTextView.setText(AmountUtil.instance(getApplicationContext()).getDividedAmount(groupInfo.getUsers(), groupInfo.getAmount(), groupInfo.getAdminId(), true));
        getAmountTextView.setText(AmountUtil.instance(getApplicationContext()).getDividedAmount(groupInfo.getUsers(), groupInfo.getAmount(), groupInfo.getAdminId(), false));
        SelectedFriendList selectedFriendList = new SelectedFriendList(getApplicationContext(), groupInfo.getUsers(),false);
        friendList.setAdapter(selectedFriendList);
       // Toast.makeText(getApplicationContext(), groupInfo.getName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
