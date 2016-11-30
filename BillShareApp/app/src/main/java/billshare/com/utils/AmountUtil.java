package billshare.com.utils;

import android.content.Context;

import java.util.List;

import billshare.com.model.User;


public class AmountUtil {
    private static AmountUtil amountUtil;
    private Context context;

    private AmountUtil(Context context) {
        this.context = context;
    }

    public static AmountUtil instance(Context context) {
        if (amountUtil == null) {
            amountUtil = new AmountUtil(context);
        }
        return amountUtil;

    }

    public String getDividedAmount(List<User> users, Double amount, Integer groupAdminId, boolean isDebit) {
        final String idFromSPreferences = PreferenceUtil.instance(context).getIdFromSPreferences();
        for (User user : users) {
            if (user.getId().equals(Integer.valueOf(idFromSPreferences)) && groupAdminId.equals(user.getId())) {
                return String.valueOf(getAmount(users.size(), amount, true, isDebit));
            } else if (user.getId().equals(Integer.valueOf(idFromSPreferences))) {
                return String.valueOf(getAmount(users.size(), amount, false, isDebit));
            }
        }
        return "0.0";
    }

    private Double getAmount(int noOfUsers, Double amount, boolean isAdmin, boolean isDebit) {
        Double calculatedAmount = 0.0;

        if (!isDebit && isAdmin) {
            calculatedAmount = amount - (amount / noOfUsers);
        } else if (!isAdmin&&isDebit == true) {
            calculatedAmount = amount / noOfUsers;
        }
        return Math.round(calculatedAmount * 100.00) / 100.00;
    }
}
