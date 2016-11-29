package billshare.com.utils;


public class ProfilePicUtils {
    private static ProfilePicUtils profilePicUtils;
    private String profilePicByteString;

    private ProfilePicUtils() {

    }

    public static ProfilePicUtils instance() {
        if (profilePicUtils == null) {
            profilePicUtils = new ProfilePicUtils();
        }
        return profilePicUtils;
    }

    public String getProfilePicByteString() {
        return profilePicByteString;
    }

    public void setProfilePicByteString(String profilePicByteString) {
        this.profilePicByteString = profilePicByteString;
    }
}
