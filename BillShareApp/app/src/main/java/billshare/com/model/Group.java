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
        "name",
        "image"
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
    @JsonProperty("amountLimit")
    private BigDecimal amountLimit;
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

    @JsonProperty("friends")
    public List<Friend> getFriends() {
        return friends;
    }

    @JsonProperty("friends")
    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    @JsonProperty("name")

    private String name;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("amountLimit")
    public BigDecimal getAmountLimit() {
        return amountLimit;
    }
    @JsonProperty("amountLimit")
    public void setAmountLimit(BigDecimal amountLimit) {
        this.amountLimit = amountLimit;
    }

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


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
