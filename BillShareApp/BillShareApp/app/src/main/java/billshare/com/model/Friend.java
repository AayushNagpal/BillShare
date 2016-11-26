package billshare.com.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import billshare.com.utils.Status;

/*
status

P=pending
R=Reject
A=Accepted
 */
public class Friend {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("userId")
    private Integer userId;

    @JsonProperty("friendStatus")
    private Status friendStatus;
    @JsonProperty("groupId")
    private Integer groupId;

    @JsonProperty("userId")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty("groupId")
    public Integer getGroupId() {
        return groupId;
    }

    @JsonProperty("groupId")
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("friendStatus")
    public Status getStatus() {
        return friendStatus;
    }

    @JsonProperty("friendStatus")
    public void setStatus(Status friendStatus) {
        this.friendStatus = friendStatus;
    }
}
