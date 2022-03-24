/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.dao.BookDB;
import model.dao.BookOrderDB;
import model.dao.BookReceiptDB;

/**
 *
 * @author ThanhNhan
 */
public class BookOrder {
    private int bookOrderID;
    private String userID;
    private String fullName;
    private int typeID;
    private String typeName;
    private String clas;
    private int bookID;
    private int statusID;
    private String status;
    private String bookName;
    private Date orderDate;

    public BookOrder() {
    }

    public BookOrder(String userID, int bookID) {        
        this.userID = userID;
        this.bookID = bookID;
    }
    
    public BookOrder(int bookOrderID){
        this(BookOrderDB.getBookOrder(bookOrderID));
    }
    
    public BookOrder(BookOrder b){
        this(b.bookOrderID, b.userID, b.fullName, b.typeID, b.typeName, b.clas, b.bookID, b.statusID, b.status, b.bookName, b.orderDate);
    }

    public BookOrder(int bookOrderID, String userID, String fullName, int typeID, String typeName, String clas, int bookID, int statusID, String status, String bookName, Date orderDate) {
        this.bookOrderID = bookOrderID;
        this.userID = userID;
        this.fullName = fullName;
        this.typeID = typeID;
        this.typeName = typeName;
        this.clas = clas;
        this.bookID = bookID;
        this.statusID = statusID;
        this.status = status;
        this.bookName = bookName;
        this.orderDate = orderDate;
    }

    public int getBookOrderID() {
        return bookOrderID;
    }

    public void setBookOrderID(int bookOrderID) {
        this.bookOrderID = bookOrderID;
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

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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
        return "BookOrder{" + "bookOrderID=" + bookOrderID + ", userID=" + userID + ", fullName=" + fullName + ", typeID=" + typeID + ", typeName=" + typeName + ", clas=" + clas + ", bookID=" + bookID + ", statusID=" + statusID + ", status=" + status + ", bookName=" + bookName + ", orderDate=" + orderDate + '}';
    }

    public void add(){
        BookOrderDB.addOrder(this);
    }
    
    public void delete(){
        BookOrderDB.delete(this);
    }
    
    public int getQuantityBookReceipt(){
        return new Account(this.getUserID()).getQuantityBookReceipt();
    }
    
    public ArrayList<Books> getListBooks(){
        ArrayList<Books> list = new ArrayList<>();
        for(Books b : BookDB.listBooks(this.getBookID())){
            if(b.getTypeID() == 0){
                list.add(b);
            }
        }
        return list;
    }
    
    public static boolean check(int bookID){
        for(BookOrder b : BookOrderDB.getListBookOrder()){
            if(b.getBookID() == bookID && b.getStatusID() == 2) return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        for(BookOrder b : BookOrderDB.getListBookOrder()){
            System.out.println(b);
        }
    }
}
