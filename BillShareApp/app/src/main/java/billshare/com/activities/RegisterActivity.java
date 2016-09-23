package billshare.com.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import billshare.com.utils.CurrencyAndLanguageUtils;
import billshare.com.utils.TimeZoneUtils;

public class RegisterActivity extends AppCompatActivity {


    Spinner countries;

    Spinner currencies;

    Spinner languages;
    ArrayAdapter<String> countriesAdapter, currencyAdapter,languageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTimeZones();
        setCurrencies();
        setLanguages();
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
}
