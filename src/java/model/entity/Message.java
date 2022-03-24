/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ThanhNhan
 */
public class Message {
    private int messageID;
    private boolean typeID;
    private String userID;    
    private String fullName;
    private String message;
    private Date time;
    private String job;
    private String clas;

    public Message() {
    }

    public Message(boolean typeID, String userID, String message) {        
        this.typeID = typeID;
        this.userID = userID;        
        this.message = message;
    }

    public Message(int messageID, boolean typeID, String userID, String fullName, String message, Date time, String job, String clas) {
        this.messageID = messageID;
        this.typeID = typeID;
        this.userID = userID;
        this.fullName = fullName;
        this.message = message;
        this.time = time;
        this.job = job;
        this.clas = clas;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public boolean isTypeID() {
        return typeID;
    }

    public void setTypeID(boolean typeID) {
        this.typeID = typeID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        return f.format(time);
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    @Override
    public String toString() {
        return "Message{" + "messageID=" + messageID + ", typeID=" + typeID + ", userID=" + userID + ", fullName=" + fullName + ", message=" + message + ", time=" + time + ", job=" + job + ", clas=" + clas + '}';
    }
}


