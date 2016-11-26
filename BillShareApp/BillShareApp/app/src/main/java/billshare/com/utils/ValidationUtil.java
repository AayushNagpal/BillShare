package billshare.com.utils;



public class ValidationUtil {
    private static ValidationUtil validationUtil;
    private ValidationUtil(){

    }
    public  static ValidationUtil instance(){
        if(validationUtil==null){
            validationUtil=new ValidationUtil();
        }
        return validationUtil;
    }
    public boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    public boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

}
