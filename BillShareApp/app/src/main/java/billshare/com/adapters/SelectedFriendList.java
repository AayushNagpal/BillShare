package billshare.com.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import billshare.com.activities.R;
import billshare.com.model.User;
import billshare.com.utils.PreferenceUtil;


public class SelectedFriendList extends BaseAdapter {
    private Context mContext;
    private List<User> mList;
    private LayoutInflater mLayoutInflater = null;
    private boolean isEdit;

    public SelectedFriendList(Context context, List<User> list, boolean isEdit) {
        mContext = context;
        mList = list;
        this.isEdit = isEdit;
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
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEdit==true) {

                    if (mList.get(mList.size() - 1).getId().equals(Integer.valueOf(PreferenceUtil.instance(mContext).getIdFromSPreferences()))) {
                        mList.remove(position);
                    } else {
                        Toast.makeText(mContext, "You don't have rights to remove user.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    mList.remove(position);
                }
            }
        });
        return v;
    }
}

class CompleteListViewHolder {
    public TextView name, email;
    ImageView imageView;

    public CompleteListViewHolder(View base) {
        name = (TextView) base.findViewById(R.id.name);
        email = (TextView) base.findViewById(R.id.email);
        imageView = (ImageView) base.findViewById(R.id.removeFried);
    }
}
