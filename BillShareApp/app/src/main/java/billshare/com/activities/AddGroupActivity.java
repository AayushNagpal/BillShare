package billshare.com.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import billshare.com.adapters.SelectedFriendList;
import billshare.com.adapters.UserAdapter;
import billshare.com.model.Friend;
import billshare.com.model.Group;
import billshare.com.model.User;
import billshare.com.responses.ResponseStatus;
import billshare.com.restservice.RestServiceObject;
import billshare.com.utils.PreferenceUtil;
import billshare.com.utils.Status;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class AddGroupActivity extends AppCompatActivity {
    private AutoCompleteTextView userAutoCompleteTextView;
    private UserAdapter userAdapter;
    private SelectedFriendList selectedFriendsAdapter;
    private List<User> userList;
    private ListView friendsList;
    private List<User> selectedList = new ArrayList<User>();
    private List<Friend> friends = new ArrayList<Friend>();
    private EditText groupNameEditText, amountEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        friendsList = (ListView) findViewById(R.id.friend_list);
        groupNameEditText = (EditText) findViewById(R.id.group_name);
        amountEditText = (EditText) findViewById(R.id.amountEditText);
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

        /*if (userAdapter != null) {
            userAdapter.notifyDataSetChanged();
            userAutoCompleteTextView.setAdapter(userAdapter);
        }*/


        selectFriend();
    }

    public void addFriends(User user) {
        selectedList.add(user);
    }

    private List<User> getFriends() {
        return selectedList;
    }

    private void selectFriend() {
        selectedFriendsAdapter = new SelectedFriendList(AddGroupActivity.this, selectedList);

        friendsList.setAdapter(selectedFriendsAdapter);
        userAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object user = parent.getItemAtPosition(position);
                if (user instanceof User) {
                    if (((User) user).isSelected()) {
                        Toast.makeText(getApplicationContext(), "User is already selected.", Toast.LENGTH_SHORT).show();
                    } else {
                        selectedList.add((User) user);
                        ((User) user).setSelected(true);
                        selectedFriendsAdapter.notifyDataSetChanged();
                        userAutoCompleteTextView.setText("");

                    }
                    // addFriends((User) user);

                    //
                }
            }
        });


       /* userAutoCompleteTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
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
            boolean cancel = false;
            View focusView = null;
            String groupName = groupNameEditText.getText().toString();
            String amount = amountEditText.getText().toString();
            if (TextUtils.isEmpty(groupName)) {
                groupNameEditText.setError(getString(R.string.error_field_required));
                focusView = groupNameEditText;
                cancel = true;
            } else if (selectedList.size() == 0) {
                userAutoCompleteTextView.setError("Please select at least one user.");
                focusView = userAutoCompleteTextView;
                cancel = true;
            }
            if (TextUtils.isEmpty(amount)) {
                amountEditText.setError(getString(R.string.error_field_required));
                focusView = amountEditText;
                cancel = true;
            }
            if (cancel) {
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView.requestFocus();
            } else {
                for (User user : selectedList) {
                    Friend friend = new Friend();
                    friend.setUserId(user.getId());
                    //friend.setStatus(Status.PENDING);
                    friends.add(friend);
                }
                Group group = new Group();
                group.setAdminId(Integer.parseInt(PreferenceUtil.instance(getApplicationContext()).getIdFromSPreferences()));
                group.setFriendsIds(friends);
                group.setAmount(BigDecimal.valueOf(Double.parseDouble(amount)));
                group.setName(groupName);
                Call<Group> call = RestServiceObject.getiRestServicesObject(getApplicationContext()).saveGroup(group);
                call.enqueue(new Callback<Group>() {
                    @Override
                    public void onResponse(Response<Group> response, Retrofit retrofit) {
                        Group body = response.body();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        finish();
                        ResponseStatus responseStatus = body.getResponseStatus();
                        if(responseStatus!=null){
                            if(responseStatus.getCode()==200){
                                Toast.makeText(getApplicationContext(),"Group saved successfully.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
