package billshare.com.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import billshare.com.utils.CurrencyAndLanguageUtils;
import billshare.com.utils.TimeZoneUtils;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.Matchers.hasProperty;

public class RegisterActivity extends AppCompatActivity {

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
                }

            }
        });

    }

    private void register() throws NameNotFoundException {
        User user = new User();
        EditText nameEditText = (EditText) findViewById(R.id.fullname);
        String name = nameEditText.getText().toString();
        if ("".equals(name)) {
            throw new NameNotFoundException(666, "Name is empty!");
        }
        user.setName(name);
        EditText emailEditText = (EditText) findViewById(R.id.email);
        user.setEmailId(emailEditText.getText().toString());
        EditText passwordEditText = (EditText) findViewById(R.id.password);
        user.setPassword(passwordEditText.getText().toString());
        EditText phonenumberEditText = (EditText) findViewById(R.id.phone_number);
        user.setMobileNo(phonenumberEditText.getText().toString());
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
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
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
     //   thrown.expect(NameNotFoundException.class);

        //test message
       // thrown.expectMessage(is("Name is empty!"));

        //test detail
        //thrown.expect(hasProperty("errCode"));  //make sure getters n setters are defined.
        //thrown.expect(hasProperty("errCode", is(666)));


    }

}
