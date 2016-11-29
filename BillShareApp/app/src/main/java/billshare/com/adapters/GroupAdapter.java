package billshare.com.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import billshare.com.activities.AddGroupActivity;
import billshare.com.activities.R;
import billshare.com.model.User;
import billshare.com.utils.GroupInfo;
import billshare.com.utils.ImageUtils;
import billshare.com.utils.PreferenceUtil;
import billshare.com.utils.StringConstants;

/**
 * Created by venu on 27/11/16.
 */

public class GroupAdapter extends BaseAdapter {
    private Activity mContext;
    private List<GroupInfo> mList;
    private LayoutInflater mLayoutInflater = null;

    public GroupAdapter(Activity context, List<GroupInfo> list) {
        mContext = context;
        mList = list;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int pos) {
        return mList.get(pos);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.group_item, null);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.name.setText(mList.get(position).getName());
        viewHolder.amountTextVeiw.setText(PreferenceUtil.instance(mContext).getCurrencyFromSPreferences() + " " + mList.get(position).getAmount().toString());
        int size = mList.get(position).getUsers().size();
        viewHolder.groupSize.setText((size == 1) ? size + " Member" : size + " Members");
        List<User> users = mList.get(position).getUsers();
        viewHolder.adminTextView.setText("Admin " + users.get(users.size() - 1).getName());
        viewHolder.editGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddGroupActivity.class);
                ImageUtils.instance().setGroupInfo(mList.get(position));
               // intent.putExtra(StringConstants.GROUP_INFO, mList.get(position));
                intent.putExtra(StringConstants.IS_EDIT, true);
                mContext.startActivity(intent);
                // Toast.makeText(mContext, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}

class ViewHolder {
    public TextView name, amountTextVeiw, groupSize, adminTextView;
    ImageView editGroup;

    public ViewHolder(View base) {
        name = (TextView) base.findViewById(R.id.name);
        amountTextVeiw = (TextView) base.findViewById(R.id.amountTextView);
        groupSize = (TextView) base.findViewById(R.id.groupSize);
        adminTextView = (TextView) base.findViewById(R.id.adminTextView);
        editGroup = (ImageView) base.findViewById(R.id.editGroup);
    }

}