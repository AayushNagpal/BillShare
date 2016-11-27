package billshare.com.utils;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import billshare.com.responses.ResponseStatus;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "responseStatus",
        "groupInfo"
})
public class GroupsList {
    @JsonProperty("responseStatus")
    private ResponseStatus responseStatus;
    @JsonProperty("groupInfo")
    private List<GroupInfo> groupInfo = new ArrayList<GroupInfo>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The responseStatus
     */
    @JsonProperty("responseStatus")
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    /**
     * @param responseStatus The responseStatus
     */
    @JsonProperty("responseStatus")
    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    /**
     * @return The groupInfo
     */
    @JsonProperty("groupInfo")
    public List<GroupInfo> getGroupInfo() {
        return groupInfo;
    }

    /**
     * @param groupInfo The groupInfo
     */
    @JsonProperty("groupInfo")
    public void setGroupInfo(List<GroupInfo> groupInfo) {
        this.groupInfo = groupInfo;
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
