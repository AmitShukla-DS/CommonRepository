/**
 * EVSE is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.ems.evse.dto;

import java.util.List;

public class ResultModel {

    private volatile String message;

    private Object data;

    private List<String> messageList;

    public synchronized String getMessage() {
        return message;
    }

    public synchronized void setMessage(String message) {
        this.message = message;
    }

    public synchronized Object getData() {
        return data;
    }

    public synchronized void setData(Object data) {
        this.data = data;
    }

    public synchronized List<String> getMessageList() {
        return messageList;
    }

    public synchronized void setMessageList(List<String> messageList) {
        this.messageList = messageList;
    }
}
