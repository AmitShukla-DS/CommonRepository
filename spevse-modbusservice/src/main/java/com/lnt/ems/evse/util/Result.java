package com.lnt.ems.evse.util;

import java.io.Serializable;

/**
 * Object class to send response message
 */
public class Result implements Serializable {

    /**
     * Default value
     */
    private static final long serialVersionUID = 1L;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
