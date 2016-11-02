package billshare.com.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import billshare.com.adapters.UserAdapter;
import billshare.com.model.User;
import billshare.com.responses.ResponseStatus;
import billshare.com.restservice.RestServiceObject;
import billshare.com.utils.PreferenceUtil;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class AddGroupActivity extends AppCompatActivity {
    private AutoCompleteTextView userAutoCompleteTextView;
    private UserAdapter userAdapter, selectedFriendsAdapter;
    private List<User> userList;
    private ListView friendsList;
    private List<User> selectedList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        friendsList = (ListView) findViewById(R.id.friend_list);

        userAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.searchFriends);
        userAutoCompleteTextView.setThreshold(1);


        final Call<ResponseStatus> usersCall = RestServiceObject.getiRestServicesObject(getApplicationContext()).users(PreferenceUtil.instance(getApplicationContext()).getIdFromSPreferences());

        usersCall.enqueue(new Callback<ResponseStatus>() {
            @Override
            public void onResponse(Response<ResponseStatus> response, Retrofit retrofit) {
                if (response.body() != null) {
                    userList = response.body().getUsers();
                    userAdapter = new UserAdapter(AddGroupActivity.this, R.layout.user_list_item, userList);
                    userAdapter.notifyDataSetChanged();
                    userAutoCompleteTextView.setAdapter(userAdapter);
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        if (userAdapter != null) {
            userAdapter.notifyDataSetChanged();
            userAutoCompleteTextView.setAdapter(userAdapter);
        }
        selectFriend();
        selectedFriendsAdapter = new UserAdapter(AddGroupActivity.this, R.layout.user_list_item, getFriends());
        selectedFriendsAdapter.notifyDataSetChanged();
        friendsList.setAdapter(selectedFriendsAdapter);
    }

    private void addFriends(User user) {
        selectedList.add(user);
    }

    private List<User> getFriends() {
        return selectedList;
    }

    private void selectFriend() {
        userAutoCompleteTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_group_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {

        }
        return super.onOptionsItemSelected(item);
    }
}
