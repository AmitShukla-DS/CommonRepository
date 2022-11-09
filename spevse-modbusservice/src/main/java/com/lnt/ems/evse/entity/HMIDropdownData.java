/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.entity;

import javax.persistence.*;

/**
 * The persistent class for the _users database table.
 */
@Entity
@Table(name = "lntds_hmi_dropdown_data")
public class HMIDropdownData {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "dropdown_value")
    private String dropdownValue;

    @Column(name = "jsonfile_value")
    private String jsonfileValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDropdownValue() {
        return dropdownValue;
    }

    public void setDropdownValue(String dropdownValue) {
        this.dropdownValue = dropdownValue;
    }

    public String getJsonfileValue() {
        return jsonfileValue;
    }

    public void setJsonfileValue(String jsonfileValue) {
        this.jsonfileValue = jsonfileValue;
    }

}

