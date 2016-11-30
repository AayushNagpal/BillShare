package billshare.com.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
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
import billshare.com.utils.GroupInfo;
import billshare.com.utils.ImageUtils;
import billshare.com.utils.PreferenceUtil;
import billshare.com.utils.Status;
import billshare.com.utils.StringConstants;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddGroupActivity extends AppCompatActivity {
    private AutoCompleteTextView userAutoCompleteTextView;
    private UserAdapter userAdapter;
    private SelectedFriendList selectedFriendsAdapter;
    private List<User> userList;
    private ListView friendsList;
    private List<User> selectedList = new ArrayList<User>();
    private List<Friend> friends = new ArrayList<Friend>();
    private EditText groupNameEditText, amountEditText, limitEditText;
    Context mContext;
    View parentView;
    ImageView imageView;
    TextView textView;
    String imagePath;
    private Button uploadBill;
    boolean isEdit;
    String groupName, amount, limitAmount;
    GroupInfo groupInfo;
    private boolean isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        friendsList = (ListView) findViewById(R.id.friend_list);
        groupNameEditText = (EditText) findViewById(R.id.group_name);
        amountEditText = (EditText) findViewById(R.id.amountEditText);
        userAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.searchFriends);
        userAutoCompleteTextView.setThreshold(1);
        uploadBill = (Button) findViewById(R.id.uploadBill);
        imageView = (ImageView) findViewById(R.id.imageView);
        parentView = findViewById(R.id.activity_group);
        limitEditText = (EditText) findViewById(R.id.limit);
        //  mContext = getApplicationContext();
        isEdit = getIntent().getBooleanExtra(StringConstants.IS_EDIT, false);
        if (isEdit) {
            groupInfo = ImageUtils.instance().getGroupInfo();
            groupNameEditText.setText(groupInfo.getName());
            amountEditText.setText(String.valueOf(groupInfo.getAmount().toString()));

            if (groupInfo.getLimitAmount() != null) {
                limitEditText.setText(String.valueOf(groupInfo.getLimitAmount().toString()));
                limitEditText.setEnabled(false);
            }

            selectedList = groupInfo.getUsers();
            if (selectedList.get(selectedList.size() - 1).getId().equals(Integer.valueOf(PreferenceUtil.instance(mContext).getIdFromSPreferences()))) {
                isAdmin = true;
            } else {
                isAdmin = false;
            }
            selectedList.remove(selectedList.size() - 1);
            imageView.setImageBitmap(ImageUtils.instance().getBitmapFromByteArray(groupInfo.getImage()));
           /* Picasso.with(mContext).load(new File()).resize(200, 200).centerCrop()
                    .into(imageView);*/
            // imageView.setVisibility(View.GONE);
            setUserAdapter();

        }
        final Call<ResponseStatus> usersCall = RestServiceObject.getiRestServicesObject(getApplicationContext()).users(PreferenceUtil.instance(getApplicationContext()).getIdFromSPreferences());
        usersCall.enqueue(new Callback<ResponseStatus>() {
            @Override
            public void onResponse(Call<ResponseStatus> call, Response<ResponseStatus> response) {
                if (response.body() != null) {
                    userList = response.body().getUsers();
                    userAdapter = new UserAdapter(AddGroupActivity.this, R.layout.user_list_item, userList);
                    userAdapter.notifyDataSetChanged();
                    userAutoCompleteTextView.setAdapter(userAdapter);
                    setUserAdapter();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatus> call, Throwable t) {

            }
        });

        /*if (userAdapter != null) {
            userAdapter.notifyDataSetChanged();
            userAutoCompleteTextView.setAdapter(userAdapter);
        }*/


        selectFriend();
        uploadBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImagePopup(imageView);
            }
        });
    }

    private void setUserAdapter() {
        selectedFriendsAdapter = new SelectedFriendList(AddGroupActivity.this, selectedList, isEdit, false, isAdmin);
        selectedFriendsAdapter.notifyDataSetChanged();
        friendsList.setAdapter(selectedFriendsAdapter);
    }

    public void addFriends(User user) {
        selectedList.add(user);
    }

    private List<User> getFriends() {
        return selectedList;
    }

    private void selectFriend() {
        setUserAdapter();
        userAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object user = parent.getItemAtPosition(position);
                if (user instanceof User) {
                    if (isSelectedUser((User) user)) {
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

    private boolean isSelectedUser(User user) {
        for (User user1 : selectedList) {
            if (user1.getId().equals(user.getId())) {
                return true;
            }
        }
        return false;
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
            groupName = groupNameEditText.getText().toString();
            amount = amountEditText.getText().toString();
            limitAmount = limitEditText.getText().toString();
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
            if (TextUtils.isEmpty(limitAmount)) {
                limitEditText.setError(getString(R.string.error_field_required));
                focusView = limitEditText;
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
                if (!isEdit)
                    saveGroup();
                else {
                    updateGroup();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateGroup() {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(AddGroupActivity.this);
        progressDialog.setMessage("Updating.. Wait for a while...");
        progressDialog.show();
        Group group = new Group();
        group.setId(groupInfo.getGroupId());
        group.setAdminId(groupInfo.getAdminId());
        group.setName(groupInfo.getName());
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            group.setImage(ImageUtils.instance().getBinarySting(bitmap));

        } else {
            group.setImage(groupInfo.getImage());
        }
        group.setFriends(friends);
        group.setAmountLimit(BigDecimal.valueOf(groupInfo.getLimitAmount()));
        group.setAmount(BigDecimal.valueOf(Double.parseDouble(amount)));
        Call<Group> call = RestServiceObject.getiRestServicesObject(getApplicationContext()).update(group);
        call.enqueue(new Callback<Group>() {
            @Override
            public void onResponse(Call<Group> call, Response<Group> response) {
                progressDialog.dismiss();
                Group body = response.body();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
                ResponseStatus responseStatus = body.getResponseStatus();
                if (responseStatus != null) {
                    if (responseStatus.getCode() == 200) {
                        Toast.makeText(getApplicationContext(), "Group updated successfully.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Group> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }

    private void saveGroup() {
        Group group = new Group();
        group.setAdminId(Integer.parseInt(PreferenceUtil.instance(getApplicationContext()).getIdFromSPreferences()));
        group.setFriends(friends);
        group.setAmount(BigDecimal.valueOf(Double.parseDouble(amount)));
        group.setName(groupName);
        group.setAmountLimit(BigDecimal.valueOf(Double.parseDouble(limitAmount)));
        /**
         * Progressbar to Display if you need
         */

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(AddGroupActivity.this);
        progressDialog.setMessage(getString(R.string.string_title_upload_progressbar_));
        progressDialog.show();
        //File file = new File(imagePath);
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        group.setImage(ImageUtils.instance().getBinarySting(bitmap));
        Call<Group> call = RestServiceObject.getiRestServicesObject(getApplicationContext()).saveGroup(group);
        call.enqueue(new Callback<Group>() {
            @Override
            public void onResponse(Call<Group> call, Response<Group> response) {
                progressDialog.dismiss();
                Group body = response.body();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
                ResponseStatus responseStatus = body.getResponseStatus();
                if (responseStatus != null) {
                    if (responseStatus.getCode() == 200) {
                        Toast.makeText(getApplicationContext(), "Group saved successfully.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Group> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

        //Create Upload Server Client

        //File creating from selected URL


      /*  // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("uploaded_file", file.getName(), requestFile);

        Call<Group> groupCall = RestServiceObject.getiRestServicesObject(mContext).saveGroup(body);
        groupCall.enqueue(new Callback<Group>() {
            @Override
            public void onResponse(Call<Group> call, Response<Group> response) {
                progressDialog.dismiss();
                // Response Success or Fail

                if (response.body() != null && response.body().getResponseStatus().getCode() == 200) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
*//*                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();*//*
                    *//*if (response.body().getResult().equals("success"))
                        Snackbar.make(parentView, R.string.string_upload_success, Snackbar.LENGTH_LONG).show();
                    else
                        Snackbar.make(parentView, R.string.string_upload_fail, Snackbar.LENGTH_LONG).show();*//*

                } else {
                    Snackbar.make(parentView, R.string.string_upload_fail, Snackbar.LENGTH_LONG).show();
                }

                *//**
         * Update Views
         *//*
                // imagePath = "";
                //textView.setVisibility(View.VISIBLE);
                //  imageView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<Group> call, Throwable t) {
                progressDialog.dismiss();
            }


        });*/
    }

    /**
     * Showing Image Picker
     */

    public void showImagePopup(View view) {

        // File System.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_PICK);

        // Chooser of file system options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, getString(R.string.string_choose_image));
        startActivityForResult(chooserIntent, 1010);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1010) {
            if (data == null) {
                Snackbar.make(parentView, R.string.string_unable_to_pick_image, Snackbar.LENGTH_INDEFINITE).show();
                return;
            }
            Uri selectedImageUri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);
                mContext = getApplicationContext();
                Picasso.with(mContext).load(new File(imagePath)).resize(200, 200).centerCrop()
                        .into(imageView);

                Snackbar.make(parentView, R.string.string_reselect, Snackbar.LENGTH_LONG).show();
                cursor.close();

                //   textView.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
            } else {
                //  textView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
                Snackbar.make(parentView, R.string.string_unable_to_load_image, Snackbar.LENGTH_LONG).show();
            }
        }
    }

}
