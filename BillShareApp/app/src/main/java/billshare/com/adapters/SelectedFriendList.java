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


public class SelectedFriendList extends BaseAdapter {
    private Activity mContext;
    private List<User> mList;
    private LayoutInflater mLayoutInflater = null;

    public SelectedFriendList(Activity context, List<User> list) {
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
        CompleteListViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.selected_item, null);
            viewHolder = new CompleteListViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (CompleteListViewHolder) v.getTag();
        }
        viewHolder.name.setText(mList.get(position).getName());
        viewHolder.email.setText(mList.get(position).getEmailId());
        return v;
    }
}

class CompleteListViewHolder {
    public TextView name,email;

    public CompleteListViewHolder(View base) {
        name = (TextView) base.findViewById(R.id.name);
        email = (TextView) base.findViewById(R.id.email);
    }
}
