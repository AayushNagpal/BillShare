
package billshare.com.responses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import billshare.com.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
    "status",
    "code",
    "domainId",
    "uid",
    "message",
    "user"
})
public class ResponseStatus {

    @JsonProperty("status")
    private String status;
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("domainId")
    private String domainId;
    @JsonProperty("uid")
    private String uid;
    @JsonProperty("message")
    private String message;
    @JsonProperty("user")
    private User user;
    @JsonProperty("users")
    private List<User> users;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonProperty("users")
    public List<User> getUsers() {
        return users;
    }
    @JsonProperty("users")
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * 
     * @return
     *     The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The code
     */
    @JsonProperty("code")
    public Integer getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The code
     */
    @JsonProperty("code")
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 
     * @return
     *     The domainId
     */
    @JsonProperty("domainId")
    public String getDomainId() {
        return domainId;
    }

    /**
     * 
     * @param domainId
     *     The domainId
     */
    @JsonProperty("domainId")
    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    /**
     * 
     * @return
     *     The uid
     */
    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    /**
     * 
     * @param uid
     *     The uid
     */
    @JsonProperty("uid")
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 
     * @return
     *     The message
     */
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 
     * @return
     *     The user
     */
    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    /**
     * 
     * @param user
     *     The user
     */
    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
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
