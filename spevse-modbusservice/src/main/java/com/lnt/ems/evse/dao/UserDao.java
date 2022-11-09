/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lnt.ems.evse.entity.Users;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface UserDao extends CrudRepository<Users, Integer> {

    // @Query("select a from Users a")
    public List<Users> findAll();

    @Modifying
    @Query("update Users m set m.isenabled=:isEnabled, m.disableDate=:disableDate, m.enableDate=:enableDate where m.userId=:userId")
    public void enableUser(@Param("userId") Integer userId, @Param("disableDate") Timestamp disableDate,
                           @Param("isEnabled") String string, @Param("enableDate") Timestamp enableDate);

    @Modifying
    @Query("update Users m set m.isactive=:isActive , m.activationDate=:activationDate where m.userId=:userId")
    public void activateUser(@Param("userId") Integer userId, @Param("activationDate") Timestamp activationDate,
                             @Param("isActive") String isActive);

    public Users findByLoginId(String loginId);

    public Users findByUserId(Integer userId);

    @Modifying
    @Query("update Users m set m.language=:language where m.loginId=:loginId")
    public void saveLanguage(@Param("language") String language, @Param("loginId") String loginId);

    @Query("select a from Users a where a.isactive='Y' and a.isenabled='N'")
    public List<Users> findAllActiveAndEnableUser();

    @Query("select a.emailAddress from Users a where a.loginId=:loginId")
    public String findEmailByUserId(@Param("loginId") String loginId);
}
