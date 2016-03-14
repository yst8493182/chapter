package com.smart.services;

import com.smart.dao.LoginLogDao;
import com.smart.dao.UserDao;
import com.smart.daomain.LoginLog;
import com.smart.daomain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shutao.yang on 2016/3/14.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginLogDao loginLogDao;


    public boolean hasMatchUser(String userName, String password) {
        int matchCount = userDao.getMathCount(userName, password);
        return matchCount>0;
    }


    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    public void loginSuccess(User user) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        loginLogDao.insertLoginLog(loginLog);

    }
}
