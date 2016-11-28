package billshare.com.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import billshare.com.activities.R;
import billshare.com.model.User;
import billshare.com.utils.GroupInfo;
import billshare.com.utils.PreferenceUtil;


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
    public View getView(int position, View convertView, ViewGroup parent) {
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
        viewHolder.adminTextView.setText("Admin "+users.get(users.size() - 1).getName());

        return v;
    }
}

class ViewHolder {
    public TextView name, amountTextVeiw, groupSize, adminTextView;

    public ViewHolder(View base) {
        name = (TextView) base.findViewById(R.id.name);
        amountTextVeiw = (TextView) base.findViewById(R.id.amountTextView);
        groupSize = (TextView) base.findViewById(R.id.groupSize);
        adminTextView = (TextView) base.findViewById(R.id.adminTextView);
    }

}