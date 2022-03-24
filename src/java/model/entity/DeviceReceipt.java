/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.dao.DeviceReceiptDB;

/**
 *
 * @author ThanhNhan
 */
public class DeviceReceipt {
    private int deviceReceiptID;
    private String userID;
    private String fullName;    
    private int deviceID;
    private String deviceName;  
    private int quantity;
    private int statusID;
    private String status;
    private Date borrowDate;
    private Date payDate;

    public DeviceReceipt() {
    }
    
    public DeviceReceipt(int deviceReceiptID) {
        this(DeviceReceiptDB.getDeviceReceipt(deviceReceiptID));
    }
    
    public DeviceReceipt(DeviceReceipt d) {
        this(d.deviceReceiptID, d.userID, d.fullName, d.deviceID, d.deviceName, d.quantity, d.statusID, d.status, d.borrowDate, d.payDate);
    }

    public DeviceReceipt(String userID, int deviceID, int quantity) {
        this.userID = userID;
        this.deviceID = deviceID;
        this.quantity = quantity;
    }

    public DeviceReceipt(int deviceReceiptID, String userID, String fullName, int deviceID, String deviceName, int quantity, int statusID, String status, Date borrowDate, Date payDate) {
        this.deviceReceiptID = deviceReceiptID;
        this.userID = userID;
        this.fullName = fullName;
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.quantity = quantity;
        this.statusID = statusID;
        this.status = status;
        this.borrowDate = borrowDate;
        this.payDate = payDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    

    public int getDeviceReceiptID() {
        return deviceReceiptID;
    }

    public void setDeviceReceiptID(int deviceReceiptID) {
        this.deviceReceiptID = deviceReceiptID;
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

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }       
            
    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBorrowDate() {
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        return f.format(borrowDate);
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getPayDate() {
        if(payDate == null) return "Chưa trả";
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        return f.format(payDate);
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    @Override
    public String toString() {
        return "DeviceReceipt{" + "deviceReceiptID=" + deviceReceiptID + ", userID=" + userID + ", fullName=" + fullName + ", deviceID=" + deviceID + ", deviceName=" + deviceName + ", quantity=" + quantity + ", statusID=" + statusID + ", status=" + status + ", borrowDate=" + borrowDate + ", payDate=" + payDate + '}';
    }
    
    public ArrayList<Devices> getCode(){
        return DeviceReceiptDB.listDevicesBorrow(deviceReceiptID);
    }
    
    public int add(){
        return DeviceReceiptDB.add(this);
    }
    
    public void back(){
        DeviceReceiptDB.back(this);
        new Device(deviceID).back(this.getQuantity());
        for(Devices d : this.getCode()){
            d.back();
        }
    }
    
    public void borrow(int quantity){
        new Device(deviceID).borrow(quantity);
    }
}
