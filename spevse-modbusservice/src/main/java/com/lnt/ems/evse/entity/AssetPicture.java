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
import java.io.Serializable;

@Entity
@Table(name = "lntds_asset_picture")
public class AssetPicture implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private Integer pictureId;


    @Lob
    @Column(name = "picture_file")
    @Basic(fetch = FetchType.LAZY)
    private byte[] pictureFile;

    @Column(name = "picture_title")
    private String pictureTitle;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "asset_ID")
    private Assets assets;

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public byte[] getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(byte[] pictureFile) {
        this.pictureFile = pictureFile;
    }

    public String getPictureTitle() {
        return pictureTitle;
    }

    public void setPictureTitle(String pictureTitle) {
        this.pictureTitle = pictureTitle;
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
