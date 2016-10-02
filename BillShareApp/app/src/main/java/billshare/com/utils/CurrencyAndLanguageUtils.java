package billshare.com.utils;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;


public class CurrencyAndLanguageUtils {
    private static CurrencyAndLanguageUtils currencyAndLanguageUtils;
    private List<String> currencyList, languageList;

    private CurrencyAndLanguageUtils() {

    }

    public static CurrencyAndLanguageUtils instance() {
        if (currencyAndLanguageUtils == null) {
            currencyAndLanguageUtils = new CurrencyAndLanguageUtils();
            currencyAndLanguageUtils.setCurrencyList();
            currencyAndLanguageUtils.setLanguageList();
        }
        return currencyAndLanguageUtils;
    }

    public List<String> getLanguageList() {
        return languageList;
    }

    private void setLanguageList() {
        languageList = new ArrayList<String>();
        final String[] isoLanguages = Locale.getISOLanguages();
        for (String language : isoLanguages) {
            languageList.add(language);
        }

    }

    private void setCurrencyList() {

        currencyList = new ArrayList<String>();


        Locale[] locs = Locale.getAvailableLocales();

        for (Locale loc : locs) {
            try {
                currencyList.add(Currency.getInstance(loc).getCurrencyCode());
            } catch (Exception exc) {
                // Locale not found
            }
        }

    }

    public List<String> getCurrencyList() {
        return currencyList;
    }
}
