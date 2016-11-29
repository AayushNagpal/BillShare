package billshare.com.activities;

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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Picasso;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;

import billshare.com.model.User;
import billshare.com.responses.ResponseStatus;
import billshare.com.restservice.RestServiceObject;
import billshare.com.services.MyInstanceIDListenerService;
import billshare.com.testcases.NameNotFoundException;
import billshare.com.utils.CurrencyAndLanguageUtils;
import billshare.com.utils.ImageUtils;
import billshare.com.utils.NetWork;
import billshare.com.utils.TimeZoneUtils;
import billshare.com.utils.ValidationUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.Matchers.hasProperty;

public class RegisterActivity extends AppCompatActivity {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    Spinner countries;
    NetWork inetrService;
    Spinner currencies;
    Context mContext;
    View parentView;
    ImageView imageView;
    TextView fileNameView;
    String imagePath;
    Spinner languages;
    ArrayAdapter<String> countriesAdapter, currencyAdapter, languageAdapter;
    Button uploadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTimeZones();
        setCurrencies();
        setLanguages();
        mContext = getApplicationContext();
        parentView = findViewById(R.id.registerView);
        fileNameView = (TextView) findViewById(R.id.fileNameView);
        uploadButton = (Button) findViewById(R.id.uploadButton);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImagePopup(v);
            }
        });
        findViewById(R.id.registerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    register();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void register() throws NameNotFoundException {
        User user = new User();
        boolean cancel = false;
        View focusView = null;
        EditText nameEditText = (EditText) findViewById(R.id.fullname);
        EditText emailEditText = (EditText) findViewById(R.id.email);
        EditText passwordEditText = (EditText) findViewById(R.id.password);
        EditText phonenumberEditText = (EditText) findViewById(R.id.phone_number);
        nameEditText.setError(null);
        emailEditText.setError(null);
        passwordEditText.setError(null);
        phonenumberEditText.setError(null);

        String name = nameEditText.getText().toString();
      /*  if ("".equals(name)) {
            throw new NameNotFoundException(666, "Name is empty!");
        }*/
        if (TextUtils.isEmpty(name)) {
            nameEditText.setError(getString(R.string.error_field_required));
            focusView = nameEditText;
            cancel = true;
        }

        user.setName(name);


        String phoneNumber = phonenumberEditText.getText().toString();
        if (!cancel && TextUtils.isEmpty(phoneNumber)) {
            phonenumberEditText.setError(getString(R.string.error_field_required));
            focusView = phonenumberEditText;
            cancel = true;
        }

        user.setMobileNo(phoneNumber);
        String email = emailEditText.getText().toString();
        if (!cancel && TextUtils.isEmpty(email)) {
            emailEditText.setError(getString(R.string.error_field_required));
            focusView = emailEditText;
            cancel = true;
        } else if (!cancel && !ValidationUtil.instance().isEmailValid(email)) {
            emailEditText.setError(getString(R.string.error_invalid_email));
            focusView = emailEditText;
            cancel = true;
        }
        user.setEmailId(emailEditText.getText().toString());
        String password = passwordEditText.getText().toString();
        if (!cancel && TextUtils.isEmpty(password)) {
            passwordEditText.setError(getString(R.string.error_field_required));
            focusView = passwordEditText;
            cancel = true;
        }
        if (!cancel && !TextUtils.isEmpty(password) && !ValidationUtil.instance().isPasswordValid(password)) {
            passwordEditText.setError(getString(R.string.error_invalid_password));
            focusView = passwordEditText;
            cancel = true;
        }
        user.setPassword(password);
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            user.setProfilePic(ImageUtils.instance().getBinarySting(bitmap));
            Spinner timeZoneSpinner = (Spinner) findViewById(R.id.time_zone);
            user.setTimeZone(timeZoneSpinner.getSelectedItem().toString());
            Spinner currencySpinner = (Spinner) findViewById(R.id.currency);
            user.setCurrency(currencySpinner.getSelectedItem().toString());
            Spinner languagesSpinner = (Spinner) findViewById(R.id.languages);
            user.setLangugeCode(languagesSpinner.getSelectedItem().toString());
            Log.d("Token", "Refreshed token: " + FirebaseInstanceId.getInstance().getToken());
            user.setDeviceId(FirebaseInstanceId.getInstance().getToken());
            Call<ResponseStatus> call = RestServiceObject.getiRestServicesObject(getApplicationContext()).register(user);
            call.enqueue(new Callback<ResponseStatus>() {
                @Override
                public void onResponse(Call<ResponseStatus> call, Response<ResponseStatus> response) {
                    if (response != null) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<ResponseStatus> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void setTimeZones() {
        countries = (Spinner) findViewById(R.id.time_zone);
        countriesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, TimeZoneUtils.instance().getTimeZoneList());
        countries.setAdapter(countriesAdapter);
    }

    private void setCurrencies() {
        currencies = (Spinner) findViewById(R.id.currency);
        currencyAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, CurrencyAndLanguageUtils.instance().getCurrencyList());
        currencies.setAdapter(currencyAdapter);
    }

    private void setLanguages() {
        languages = (Spinner) findViewById(R.id.languages);
        languageAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, CurrencyAndLanguageUtils.instance().getLanguageList());
        languages.setAdapter(languageAdapter);
    }

    @Test
    public void testNameNotFoundException() throws NameNotFoundException {

        //test type
        thrown.expect(NameNotFoundException.class);

        //test message
        thrown.expectMessage(is("Name is empty!"));

        //test detail
        // thrown.expect(hasProperty("errCode"));  //make sure getters n setters are defined.
        //thrown.expect(hasProperty("errCode", is(666)));


    }

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
            mContext = getApplicationContext();
            Uri selectedImageUri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);
                fileNameView.setText(new File(imagePath).getName());
                mContext = getApplicationContext();
              /*  Picasso.with(mContext).load(new File(imagePath)).resize(200, 200).centerCrop()
                        .into(imageView);

                Snackbar.make(parentView, R.string.string_reselect, Snackbar.LENGTH_LONG).show();
                cursor.close();*/

                //   textView.setVisibility(View.GONE);
                // imageView.setVisibility(View.VISIBLE);
            } else {
                //  textView.setVisibility(View.VISIBLE);
               /* imageView.setVisibility(View.GONE);
                Snackbar.make(parentView, R.string.string_unable_to_load_image, Snackbar.LENGTH_LONG).show();*/
            }
        }
    }
}
