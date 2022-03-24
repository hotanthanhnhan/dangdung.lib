/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;
import model.dao.AccountDB;
import model.dao.BookDB;
import model.dao.BookOrderDB;
import model.dao.BookReceiptDB;
import model.dao.DeviceOrderDB;
import model.dao.DeviceReceiptDB;

/**
 *
 * @author ThanhNhan
 */
public class Account {

    private String userID;
    private String fullName;
    private String password;
    private String email;
    private int userTypeID;
    private String userTypeName;
    private int grade;
    private String clas;
    private Date lastAccess;

    public Account() {
    }

    public Account(String userID, String fullName, String email, int userTypeID, int grade, String clas) {
        this.userID = userID;
        this.fullName = fullName;        
        this.email = email;
        this.userTypeID = userTypeID;        
        this.grade = grade;
        this.clas = clas;        
    }

    public Account(String userID, String fullName, String password, String email, int userTypeID, String userTypeName, int grade, String clas, Date lastAccess) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.userTypeID = userTypeID;
        this.userTypeName = userTypeName;
        this.grade = grade;
        this.clas = clas;
        this.lastAccess = lastAccess;
    }

    public Account(Account a) {
        this(a.userID, a.fullName, a.password, a.email, a.userTypeID, a.userTypeName, a.grade, a.clas, a.lastAccess);
    }

    public Account(String userID) {
        this(AccountDB.getAccount(userID));
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserTypeID() {
        return userTypeID;
    }

    public void setUserTypeID(int userTypeID) {
        this.userTypeID = userTypeID;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getLastAccess() {
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        return f.format(lastAccess);
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    @Override
    public String toString() {
        return "Account{" + "userID=" + userID + ", fullName=" + fullName + ", password=" + password + ", email=" + email + ", userTypeID=" + userTypeID + ", userTypeName=" + userTypeName + ", grade=" + grade + ", clas=" + clas + ", lastAccess=" + lastAccess + '}';
    }

    public static String pass(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // replay MD5, SHA-512
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            password = DatatypeConverter.printHexBinary(hash);
            return password;
        } catch (NoSuchAlgorithmException e) {
        }
        return password;
    }

    public static Account login(String userID, String password) {
        Account a = AccountDB.getAccount(userID);
        if (a.getPassword().equals(Account.pass(password))) {
            AccountDB.login(a);
            return a;
        } else {
            throw new RuntimeException("Mật khẩu sai!");
        }
    }

    public void changePass(String oldPass, String newPass) {
        if (this.password.equals(Account.pass(oldPass))) {
            this.setPassword(Account.pass(newPass));
            AccountDB.doChangePass(this);
        } else {
            throw new RuntimeException("Mật khẩu sai!");
        }
    }

    public boolean checkPass(String password) {
        if (this.password.equals(Account.pass(password))) {
            return true;
        }
        throw new RuntimeException("Mật khẩu sai!");
    }

    public void update() {
        AccountDB.update(this);
    }
    
    public void change() {
        AccountDB.change(this);
    }
    
    public boolean checkBook(int bookID){
        for(BookOrder b : BookOrderDB.listBookOrder(this)){
            if(b.getBookID() == bookID && b.getStatusID() == 2){
                throw new RuntimeException("Bạn chỉ được mượn 1 cuốn sách này!");
            }
        }
        for(BookReceipt b : BookReceiptDB.listBookReceipt(this)){
            if(b.getBookID() == bookID && b.getStatusID() == 4){
                throw new RuntimeException("Bạn chỉ được mượn 1 cuốn sách này!");
            }
        }
        return true;
    }
    
    public int getQuantityBookOrder(){
        return BookOrderDB.listBookOrder(this).size();
    }
    
    public int getQuantityDeviceOrder(){
        return DeviceOrderDB.listDeviceOrder(this).size();
    }

    public int getQuantityBookReceipt(){
        int sum = 0;
        for(BookReceipt b : BookReceiptDB.listBookReceipt(this)){
            if(b.getStatusID() == 4) sum = sum + 1;
        }
        return sum;
    }
    
    public int getQuantityDeviceReceipt(){
        int sum = 0;
        for(DeviceReceipt b : DeviceReceiptDB.listDeviceReceipt(this)){
            if(b.getStatusID() == 4) sum = sum + b.getQuantity();
        }
        return sum;
    }
    
    public void remove(){
        AccountDB.remove(this);
    }
    
    public void reset(){
        AccountDB.reset(this);
    }
}
