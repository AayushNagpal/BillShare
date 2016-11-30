package billshare.com.activities;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import billshare.com.adapters.SelectedFriendList;
import billshare.com.utils.AmountUtil;
import billshare.com.utils.GroupInfo;
import billshare.com.utils.ImageUtils;
import billshare.com.utils.StringConstants;

public class GroupActivity extends AppCompatActivity {
    private ListView friendList;
    private TextView getAmountTextView, oweAmountTextView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        GroupInfo groupInfo = ImageUtils.instance().getGroupInfo();
        friendList = (ListView) findViewById(R.id.friend_list);
        getAmountTextView = (TextView) findViewById(R.id.debit);
        oweAmountTextView = (TextView) findViewById(R.id.credit);
        imageView = (ImageView) findViewById(R.id.imageView);
        setTitle(groupInfo.getName());
        oweAmountTextView.setText(AmountUtil.instance(getApplicationContext()).getDividedAmount(groupInfo.getUsers(), groupInfo.getAmount(), groupInfo.getAdminId(), true));
        getAmountTextView.setText(AmountUtil.instance(getApplicationContext()).getDividedAmount(groupInfo.getUsers(), groupInfo.getAmount(), groupInfo.getAdminId(), false));
        SelectedFriendList selectedFriendList = new SelectedFriendList(getApplicationContext(), groupInfo.getUsers(), false,true,false);
        friendList.setAdapter(selectedFriendList);
        if (groupInfo.getImage() != null)
            imageView.setImageBitmap(ImageUtils.instance().getBitmapFromByteArray(groupInfo.getImage()));
        else
            imageView.setVisibility(View.GONE);
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
