
package billshare.com.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
        "id",
        "name",
        "mobileNo",
        "emailId",
        "currency",
        "timeZone",
        "langugeCode",
        "deviceId",
        "profilePic"
})
public class User implements Serializable {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("mobileNo")
    private String mobileNo;
    @JsonProperty("emailId")
    private String emailId;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("timeZone")
    private String timeZone;
    @JsonProperty("langugeCode")
    private String langugeCode;
    @JsonProperty("deviceId")
    private String deviceId;
    @JsonProperty("profilePic")
    private String profilePic;
    @JsonProperty("profilePic1")
    private String profilePic1;
    @JsonProperty("profilePic1")
    public String getProfilePic1() {
        return profilePic1;
    }
    @JsonProperty("profilePic1")
    public void setProfilePic1(String profilePic1) {
        this.profilePic1 = profilePic1;
    }

    @JsonProperty("profilePic")
    public String getProfilePic() {
        return profilePic;
    }

    @JsonProperty("profilePic")
    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    private String password;
    private boolean isSelected;

    public User() {

    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The mobileNo
     */
    @JsonProperty("mobileNo")
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo The mobileNo
     */
    @JsonProperty("mobileNo")
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * @return The emailId
     */
    @JsonProperty("emailId")
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId The emailId
     */
    @JsonProperty("emailId")
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return The currency
     */
    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency The currency
     */
    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return The timeZone
     */
    @JsonProperty("timeZone")
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * @param timeZone The timeZone
     */
    @JsonProperty("timeZone")
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * @return The langugeCode
     */
    @JsonProperty("langugeCode")
    public String getLangugeCode() {
        return langugeCode;
    }

    /**
     * @param langugeCode The langugeCode
     */
    @JsonProperty("langugeCode")
    public void setLangugeCode(String langugeCode) {
        this.langugeCode = langugeCode;
    }

    /**
     * @return The deviceId
     */
    @JsonProperty("deviceId")
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId The deviceId
     */
    @JsonProperty("deviceId")
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

  /*  @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(emailId);
        dest.writeString(mobileNo);
    }

    // Creator
    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    // "De-parcel object
    public User(Parcel in) {
        name = in.readString();
        emailId = in.readString();
        id = in.readInt();
        mobileNo = in.readString();
    }*/
}
