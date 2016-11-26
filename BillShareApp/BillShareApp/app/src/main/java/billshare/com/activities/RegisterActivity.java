package billshare.com.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import billshare.com.model.User;
import billshare.com.responses.ResponseStatus;
import billshare.com.restservice.RestServiceObject;
import billshare.com.testcases.NameNotFoundException;
import billshare.com.utils.Constants;
import billshare.com.utils.CurrencyAndLanguageUtils;
import billshare.com.utils.TimeZoneUtils;
import billshare.com.utils.ValidationUtil;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.Matchers.hasProperty;

public class RegisterActivity extends AppCompatActivity implements Constants {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    Spinner countries;

    Spinner currencies;

    Spinner languages;
    ArrayAdapter<String> countriesAdapter, currencyAdapter, languageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTimeZones();
        setCurrencies();
        setLanguages();
        findViewById(R.id.registerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    register();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error in RegisterActivity.findViewByID");
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
        if ( TextUtils.isEmpty(name)) {
            nameEditText.setError(getString(R.string.error_field_required));
            focusView = nameEditText;
            cancel = true;
            Log.w(TAG, "Name field is empty.");
        }

        user.setName(name);




        String phoneNumber=phonenumberEditText.getText().toString();
        if (!cancel &&TextUtils.isEmpty(phoneNumber)) {
            phonenumberEditText.setError(getString(R.string.error_field_required));
            focusView = phonenumberEditText;
            cancel = true;
            Log.w(TAG, "Phone number field is empty.");
        }

        user.setMobileNo(phoneNumber);
        String email = emailEditText.getText().toString();
        if (!cancel &&TextUtils.isEmpty(email)) {
            emailEditText.setError(getString(R.string.error_field_required));
            focusView = emailEditText;
            cancel = true;
            Log.w(TAG, "Email field is empty.");
        } else if (!cancel &&!ValidationUtil.instance().isEmailValid(email)) {
            emailEditText.setError(getString(R.string.error_invalid_email));
            focusView = emailEditText;
            cancel = true;
            Log.w(TAG, "Invalid email entered.");
        }
        user.setEmailId(emailEditText.getText().toString());
        String password = passwordEditText.getText().toString();
        if (!cancel &&TextUtils.isEmpty(password)) {
            passwordEditText.setError(getString(R.string.error_field_required));
            focusView = passwordEditText;
            cancel = true;
            Log.w(TAG, "password field is empty.");
        }
        if (!cancel &&!TextUtils.isEmpty(password) && !ValidationUtil.instance().isPasswordValid(password)) {
            passwordEditText.setError(getString(R.string.error_invalid_password));
            focusView = passwordEditText;
            cancel = true;
            Log.w(TAG, "Invalid password");
        }
        user.setPassword(password);
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {


            Spinner timeZoneSpinner = (Spinner) findViewById(R.id.time_zone);
            user.setTimeZone(timeZoneSpinner.getSelectedItem().toString());
            Spinner currencySpinner = (Spinner) findViewById(R.id.currency);
            user.setCurrency(currencySpinner.getSelectedItem().toString());
            Spinner languagesSpinner = (Spinner) findViewById(R.id.languages);
            user.setLangugeCode(languagesSpinner.getSelectedItem().toString());

            Call<ResponseStatus> call = RestServiceObject.getiRestServicesObject(getApplicationContext()).register(user);
            call.enqueue(new Callback<ResponseStatus>() {
                @Override
                public void onResponse(Response<ResponseStatus> response, Retrofit retrofit) {
                    if (response != null) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        Log.i(TAG, "starting login.");
                    }
                }

                @Override
                public void onFailure(Throwable t) {
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
        Log.i(TAG, "setting time zone");
    }

    private void setCurrencies() {
        currencies = (Spinner) findViewById(R.id.currency);
        currencyAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, CurrencyAndLanguageUtils.instance().getCurrencyList());
        currencies.setAdapter(currencyAdapter);
        Log.i(TAG, "setting currency");
    }

    private void setLanguages() {
        languages = (Spinner) findViewById(R.id.languages);
        languageAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, CurrencyAndLanguageUtils.instance().getLanguageList());
        languages.setAdapter(languageAdapter);
        Log.i(TAG, "setting language");
    }

    @Test
    public void testNameNotFoundException() throws NameNotFoundException {

        //test type
        thrown.expect(NameNotFoundException.class);

        //test message
        thrown.expectMessage(is("Name is empty!"));
        Log.e(TAG, "Testing: name is empty");

        //test detail
       // thrown.expect(hasProperty("errCode"));  //make sure getters n setters are defined.
        //thrown.expect(hasProperty("errCode", is(666)));


    }

}
