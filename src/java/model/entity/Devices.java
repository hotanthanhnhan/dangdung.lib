/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import model.dao.DeviceDB;
import model.dao.DeviceReceiptDB;

/**
 *
 * @author ThanhNhan
 */
public class Devices {
    private int deviceID;
    private int deviceCode;
    private String deviceName;
    private int subjectID;
    private String subjectName;
    private int typeID;
    private String typeName;
    private String code;

    public Devices() {
    }
    
    public Devices(Devices d){
        this(d.deviceID, d.deviceCode, d.deviceName, d.subjectID, d.subjectName, d.typeID, d.typeName);
    }
    
    public Devices(int deviceCode){
        this(DeviceDB.getDevices(deviceCode));
    }

    public Devices(int deviceID, int deviceCode) {
        this.deviceID = deviceID;
        this.deviceCode = deviceCode;        
    }

    public Devices(int deviceID, int deviceCode, String deviceName, int subjectID,
            String subjectName, int typeID, String typeName) {
        this.deviceID = deviceID;
        this.deviceCode = deviceCode;
        this.deviceName = deviceName;
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.typeID = typeID;
        this.typeName = typeName; 
        this.setCode();
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public int getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(int deviceCode) {
        this.deviceCode = deviceCode;
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

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCode() {
        return code;
    }

    public final void setCode() {
        this.code = "TB-" + this.getDeviceID() + "-" + this.getDeviceCode();
    }
    
    public String getCodeSearch(){
        return "TB" + this.getDeviceID() + "" + this.getDeviceCode();
    }

    @Override
    public String toString() {
        return "Devices{" + "deviceID=" + deviceID + ", deviceCode=" + deviceCode + ", deviceName=" + deviceName + ", subjectID=" + subjectID + ", subjectName=" + subjectName + ", typeID=" + typeID + ", typeName=" + typeName + ", code=" + code + '}';
    }
    
    public void delete(){
        DeviceDB.delete(this);
    }
    
    public String getBorrower(){
        if(this.getTypeID() == 0) return "-";
        else return new Account(DeviceReceiptDB.getBorrower(deviceCode)).getFullName();
    }
    
    public void borrow(int deviceReceiptID){
        DeviceDB.borrows(this, deviceReceiptID);
    }
    
    public void back(){
        DeviceDB.backs(this);
    }
}
