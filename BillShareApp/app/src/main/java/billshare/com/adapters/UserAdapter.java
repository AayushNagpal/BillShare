package billshare.com.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;

import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import billshare.com.activities.R;
import billshare.com.model.User;

public class UserAdapter extends ArrayAdapter<User> {
    private final Context mContext;
    private final List<User> users;
    private final List<User> usersAll;
    private final List<User> userSuggestions;
    private final int layoutResourceId;

    public UserAdapter(Context context, int layoutResourceId, List<User> users) {
        super(context, layoutResourceId, users);
        this.mContext = context;
        this.users = new ArrayList<User>(users);
        this.usersAll = new ArrayList<User>(users);
        this.userSuggestions = new ArrayList<User>(users);
        this.layoutResourceId = layoutResourceId;

    }

    public int getCount() {
        return users.size();
    }

    public User getItem(int position) {
        return users.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((AppCompatActivity) mContext).getLayoutInflater();
                convertView = inflater.inflate(layoutResourceId, parent, false);
                final User user = getItem(position);
                TextView name = (TextView) convertView.findViewById(R.id.name);
                TextView email = (TextView) convertView.findViewById(R.id.email);

                name.setText(user.getName());
                email.setText(user.getEmailId());
               /* convertView.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, user.getName(), Toast.LENGTH_SHORT).show();

                    }
                });*/

                return convertView;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            public String convertResultToString(Object resultValue) {
                if (resultValue == null) {
                    return users.get(0).getName();
                } else {
                    return ((User) resultValue).getName();
                }

            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    userSuggestions.clear();
                    for (User user : usersAll) {
                        if (user.getName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            userSuggestions.add(user);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = userSuggestions;
                    filterResults.count = userSuggestions.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                users.clear();
                if (results != null && results.count > 0) {

                    List<?> result = (List<?>) results.values;
                    for (Object object : result) {
                        if (object instanceof User) {
                            users.add((User) object);
                        }
                    }
                    notifyDataSetChanged();
                } else if (constraint == null) {

                    users.addAll(usersAll);
                    notifyDataSetInvalidated();
                }


            }
        };
    }
}
