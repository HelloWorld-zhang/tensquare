package com.tensquare.friend.service;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FriendService {
    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    @Autowired
    private UserClient userClient;



    @Transactional
    public int addFriend(String userid,String friendid){
        userClient.incFollowcount(userid,1);
        userClient.incFanscount(friendid,1);
         if (friendDao.selectContent(userid,friendid) > 0){
             return 0;
         }
        Friend friend = new Friend();
         friend.setUserId(userid);
         friend.setFriendid(friendid);
         friend.setIslike("0");
         friendDao.save(friend);

         if (friendDao.selectContent(friendid,userid)>0){
             friendDao.updateLike(userid,friendid,"1");
             friendDao.updateLike(friendid,userid,"1");
         }
         return 1;
    }


    public void addNoFriend(String userid,String friendid){
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
    }
    @Transactional
    public void deleteFriend(String userid,String friendid){
        userClient.incFollowcount(friendid,-1);
        userClient.incFanscount(userid,-1);
        friendDao.deleteFriend(userid,friendid);
        friendDao.updateLike(friendid,userid,"0");
        addNoFriend(userid,friendid);
    }
}
