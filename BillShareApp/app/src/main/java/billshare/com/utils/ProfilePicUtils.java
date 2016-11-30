package billshare.com.utils;


public class ProfilePicUtils {
    private final static ProfilePicUtils profilePicUtils= new ProfilePicUtils();
    private static String profilePicByteString;

    private ProfilePicUtils() {

    }

    public static ProfilePicUtils instance() {

        return profilePicUtils;
    }

    public String getProfilePicByteString() {
        return profilePicByteString;
    }

    public void setProfilePicByteString(String profilePicByteString) {
        this.profilePicByteString = profilePicByteString;
    }
}
