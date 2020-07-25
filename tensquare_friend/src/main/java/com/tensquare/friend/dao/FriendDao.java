package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend,String> {

    @Query("select count(f) from Friend f where f.userId=?1 and f.friendid=?2")
    public int selectContent(String userid,String friendid);

    @Modifying
    @Query("update  Friend  f set f.islike=?3 where f.userId=?1 and f.friendid=?2")
    public void updateLike(String userid,String friendid,String islike);

    @Modifying
    @Query("delete  from  Friend  f where  f.userId=?1 and f.friendid=?2")
    public void deleteFriend(String userid,String friendid);
}
