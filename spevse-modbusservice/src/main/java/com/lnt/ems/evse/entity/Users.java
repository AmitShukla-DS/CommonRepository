/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the _users database table.
 */
@Entity
@Table(name = "lntds_users")
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "activation_date")
    private Date activationDate;

    @Column(length = 100)
    private String address1;

    @Column(length = 100)
    private String address2;

    @Column(length = 50)
    private String city;

    @Column(length = 50)
    private String country;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "disable_date")
    private Date disableDate;

    @Column(name = "email_address", length = 100)
    private String emailAddress;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "enable_date")
    private Date enableDate;

    @Column(name = "external_user", length = 1)
    private String externalUser;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(length = 1)
    private String isactive;

    @Column(length = 1)
    private String isenabled;

    @Column(name = "language", length = 100)
    private String language;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "login_id", nullable = false, length = 50)
    private String loginId;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "mobile_number", length = 16)
    private String mobileNumber;

    @Column(name = "phone_number", length = 16)
    private String phoneNumber;

    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "requires_password", length = 1)
    private String requiresPassword;

    @Column(name = "timezone", length = 100)
    private String timezone;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "emailid")
    private String emailId;

    @Column(name = "username")
    private String username;

    //	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(length = 10)
    private String zip;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "users")
    private List<UserPassword> userPassword;

    @Column(name = "token")
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_active_time")
    private Date lastActiveTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getEnableDate() {
        return enableDate;
    }

    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }

    public String getExternalUser() {
        return externalUser;
    }

    public void setExternalUser(String externalUser) {
        this.externalUser = externalUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getIsenabled() {
        return isenabled;
    }

    public void setIsenabled(String isenabled) {
        this.isenabled = isenabled;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getRequiresPassword() {
        return requiresPassword;
    }

    public void setRequiresPassword(String requiresPassword) {
        this.requiresPassword = requiresPassword;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public List<UserPassword> getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(List<UserPassword> userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(Date lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }
}