/**
 * PPC is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.hmi.alerts.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lnt.hmi.alerts.dto.CustomUserDetails;
import com.lnt.hmi.alerts.entity.Users;
import com.lnt.hmi.alerts.repository.UserDao;

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
