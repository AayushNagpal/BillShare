package billshare.com.model;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import billshare.com.responses.ResponseStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "adminId",
        "friends",
        "responseStatus",
        "amount",
        "name"
})
public class Group {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("adminId")
    private Integer adminId;
    @JsonProperty("friends")
    private List<Friend> friends;
    @JsonProperty("responseStatus")
    private ResponseStatus responseStatus;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("amount")
    public BigDecimal getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @JsonProperty("responseStatus")
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    @JsonProperty("responseStatus")
    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("adminId")
    public Integer getAdminId() {
        return adminId;
    }

    @JsonProperty("adminId")
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @JsonProperty("friends")
    public List<Friend> getFriendsIds() {
        return friends;
    }

    @JsonProperty("friends")
    public void setFriendsIds(List<Friend> friends) {
        this.friends = friends;
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