/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "lntds_asset_notes")
public class AssetNotes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "asset_note")
    private String assetNote;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "asset_id")
    private Assets assets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssetNote() {
        return assetNote;
    }

    public void setAssetNote(String assetNote) {
        this.assetNote = assetNote;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    @JsonIgnore
    public Assets getAssets() {
        return assets;
    }

    @JsonProperty
    public void setAssets(Assets assets) {
        this.assets = assets;
    }
}
