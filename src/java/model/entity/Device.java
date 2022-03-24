/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.util.ArrayList;
import model.dao.DeviceDB;

/**
 *
 * @author ThanhNhan
 */
public class Device {

    private int deviceID;
    private String deviceName;
    private int subjectID;
    private String subjectName;
    private String deviceImg;
    private int quantity;
    private int total;

    public Device() {
    }

    public Device(Device d) {
        this(d.deviceID, d.deviceName, d.subjectID, d.subjectName, d.deviceImg, d.quantity);
    }

    public Device(int deviceID) {
        this(DeviceDB.getDevice(deviceID));
    }

    public Device(int deviceID, String deviceName, int subjectID, String deviceImg, int quantity) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.subjectID = subjectID;
        this.deviceImg = deviceImg;
        this.quantity = quantity;
    }

    public Device(int deviceID, String deviceName, int subjectID, String subjectName, String deviceImg, int quantity) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.deviceImg = deviceImg;
        this.quantity = quantity;
        this.setTotal();
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

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDeviceImg() {
        return deviceImg;
    }

    public void setDeviceImg(String deviceImg) {
        this.deviceImg = deviceImg;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public final void setTotal() {
        this.total = DeviceDB.total(this);
    }

    @Override
    public String toString() {
        return "Device{" + "deviceID=" + deviceID + ", deviceName=" + deviceName + ", subjectID=" + subjectID + ", subjectName=" + subjectName + ", deviceImg=" + deviceImg + ", quantity=" + quantity + '}';
    }

    public void update() {
        DeviceDB.update(this);
    }

    public void add() {
        DeviceDB.addDevice(this);
    }

    public void borrow(int quantity) {
        this.quantity = this.quantity - quantity;
        if (this.getQuantity() < 0) {
            throw new RuntimeException("Không đủ thiết bị trong kho!");
        }
        DeviceDB.borrow(this);
    }

    public void back(int quantity) {
        this.quantity = this.quantity + quantity;
        DeviceDB.borrow(this);
    }
    
    public void delete(){
        this.quantity--;
        DeviceDB.update(this);
    }
    
    public ArrayList<Devices> getDevices(){
        return DeviceDB.listDevices(deviceID);
    }
    
    public boolean getCheck(){
        return this.getQuantity() == this.getTotal() && DeviceOrder.check(deviceID);
    }
    
    public void deleteAll(){
        DeviceDB.deleteAll(this);
    }
}
