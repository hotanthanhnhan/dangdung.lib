/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.dao.DeviceDB;
import model.dao.DeviceOrderDB;
import model.dao.DeviceReceiptDB;

/**
 *
 * @author ThanhNhan
 */
public class DeviceOrder {
    private int deviceOrderID;
    private String userID;
    private String fullName;
    private int deviceID;
    private int quantity;
    private int statusID;
    private String status;
    private String deviceName;
    private Date orderDate;

    public DeviceOrder() {
    }

    public DeviceOrder(String userID, int deviceID, int quantity) {        
        this.userID = userID;
        this.deviceID = deviceID;
        this.quantity = quantity;
    }

    public DeviceOrder(int deviceOrderID, String userID, String fullName, int deviceID, int quantity, int statusID, String status, String deviceName, Date orderDate) {
        this.deviceOrderID = deviceOrderID;
        this.userID = userID;
        this.fullName = fullName;
        this.deviceID = deviceID;
        this.quantity = quantity;
        this.statusID = statusID;
        this.status = status;
        this.deviceName = deviceName;
        this.orderDate = orderDate;
    }
    
    public DeviceOrder(DeviceOrder d) {
        this(d.deviceOrderID, d.userID, d.fullName, d.deviceID, d.quantity, d.statusID, d.status, d.deviceName, d.orderDate);
    }
    
    public DeviceOrder(int deviceOrderID) {
        this(DeviceOrderDB.getDeviceOrder(deviceOrderID));
    }

    public int getDeviceOrderID() {
        return deviceOrderID;
    }

    public void setDeviceOrderID(int deviceOrderID) {
        this.deviceOrderID = deviceOrderID;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getOrderDate() {
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        return f.format(orderDate);
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "DeviceOrder{" + "deviceOrderID=" + deviceOrderID + ", userID=" + userID + ", fullName=" + fullName + ", deviceID=" + deviceID + ", quantity=" + quantity + ", statusID=" + statusID + ", status=" + status + ", deviceName=" + deviceName + ", orderDate=" + orderDate + '}';
    }
    
    public void add(){
        DeviceOrderDB.addOrder(this);
    }
    
    public void delete(){
        DeviceOrderDB.delete(this);
    }
    
    public int getQuantityDeviceReceipt(){
        int sum = 0;
        for(DeviceReceipt d : DeviceReceiptDB.listDeviceReceipt(new Account(this.getUserID()))){
            if(d.getStatusID() == 4){
                sum = sum + d.getQuantity();
            }            
        }
        return sum;
    }
    
    public ArrayList<Devices> getListDevices(){
        ArrayList<Devices> list = new ArrayList<>();
        for(Devices b : DeviceDB.listDevices(this.getDeviceID())){
            if(b.getTypeID() == 0){
                list.add(b);
            }
        }
        return list;
    }
    
    public static boolean check(int deviceID){
        for(DeviceOrder b : DeviceOrderDB.getListDeviceOrder()){
            if(b.getDeviceID() == deviceID && b.getStatusID() == 2) return false;
        }
        return true;
    }
}
