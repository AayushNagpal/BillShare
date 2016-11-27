package billshare.com.utils;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
        "groupId"
})
public class GroupInfo {


    @JsonProperty("name")
    private String name;
    @JsonProperty("adminId")
    private Integer adminId;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("users")
    private List<User> users = new ArrayList<User>();
    @JsonProperty("groupId")
    private Integer groupId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The adminId
     */
    @JsonProperty("adminId")
    public Integer getAdminId() {
        return adminId;
    }

    /**
     *
     * @param adminId
     * The adminId
     */
    @JsonProperty("adminId")
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     *
     * @return
     * The amount
     */
    @JsonProperty("amount")
    public Integer getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     * The amount
     */
    @JsonProperty("amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     * The users
     */
    @JsonProperty("users")
    public List<User> getUsers() {
        return users;
    }

    /**
     *
     * @param users
     * The users
     */
    @JsonProperty("users")
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     *
     * @return
     * The groupId
     */
    @JsonProperty("groupId")
    public Integer getGroupId() {
        return groupId;
    }

    /**
     *
     * @param groupId
     * The groupId
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
}
