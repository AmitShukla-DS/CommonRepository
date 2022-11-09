package com.lnt.ems.evse.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lnt.ems.evse.dao.UserDao;
import com.lnt.ems.evse.dto.CustomUserDetails;
import com.lnt.ems.evse.entity.Users;

import java.util.Date;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Users user = userDao.findByLoginId(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User not found !!");
        } else {
            return new CustomUserDetails(user);
        }

    }

    public Users updateUserActivetime(String userName) {
        Users user = userDao.findByLoginId(userName);
        user.setLastActiveTime(new Date());
        return userDao.save(user);

    }

    public Boolean verifyCurrentToken(String userName, String token) {

        Users user = userDao.findByLoginId(userName);

        return user.getIsenabled().equals("N") && user.getToken().equals(token);

    }

}
