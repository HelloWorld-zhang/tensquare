package com.tensquare.friend.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name = "tb_friend")
@IdClass(Friend.class)
public class Friend implements Serializable {
    @Id
    private String userId;

    @Id
    private String friendid;
    private String islike;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendid() {
        return friendid;
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }

    public void setIslike(String islike) {
        this.islike = islike;
    }

    public String getIslike() {
        return islike;
    }
}
