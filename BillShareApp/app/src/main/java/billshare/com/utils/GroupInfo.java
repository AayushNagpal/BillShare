package billshare.com.utils;


import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import billshare.com.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "adminId",
        "amount",
        "users",
        "groupId",
        "limitAmount",
        "image"
})
public class GroupInfo implements Serializable {
    public GroupInfo() {

    }

    @JsonProperty("limitAmount")
    private Double limitAmount = 0.0;
    @JsonProperty("name")
    private String name;
    @JsonProperty("adminId")
    private Integer adminId;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("users")
    private List<User> users = new ArrayList<User>();
    @JsonProperty("groupId")
    private Integer groupId;
    @JsonProperty("image")
    private String image;

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("limitAmount")
    public Double getLimitAmount() {
        return limitAmount;
    }

    @JsonProperty("limitAmount")
    public void setLimitAmount(Double limitAmount) {
        this.limitAmount = limitAmount;
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
     * @return The adminId
     */
    @JsonProperty("adminId")
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * @param adminId The adminId
     */
    @JsonProperty("adminId")
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * @return The amount
     */
    @JsonProperty("amount")
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount The amount
     */
    @JsonProperty("amount")
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return The users
     */
    @JsonProperty("users")
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users The users
     */
    @JsonProperty("users")
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * @return The groupId
     */
    @JsonProperty("groupId")
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * @param groupId The groupId
     */
    @JsonProperty("groupId")
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(adminId);
        dest.writeDouble(amount);
        dest.writeString(name);
        dest.writeTypedList(users);
        dest.writeInt(groupId);
    }

    // Creator
    public static final Parcelable.Creator<GroupInfo> CREATOR
            = new Parcelable.Creator<GroupInfo>() {
        public GroupInfo createFromParcel(Parcel in) {
            return new GroupInfo(in);
        }

        public GroupInfo[] newArray(int size) {
            return new GroupInfo[size];
        }
    };

    // "De-parcel object
    public GroupInfo(Parcel in) {
        name = in.readString();
        amount = in.readInt();
        adminId = in.readInt();
        groupId = in.readInt();
        in.readTypedList(users, User.CREATOR);
        //  in.readList(users, GroupInfo.class.getClassLoader());
    }*/
}
