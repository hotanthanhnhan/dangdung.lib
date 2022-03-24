/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import model.dao.BookDB;
import model.dao.BookReceiptDB;

/**
 *
 * @author ThanhNhan
 */
public class Books {
    private int bookID;
    private int bookCode;
    private String bookName;
    private String categoryID;
    private String categoryName;
    private int subjectID;
    private String subjectName;
    private int typeID;
    private String typeName;    

    public Books() {
    }

    public Books(int bookID, int bookCode) {
        this.bookID = bookID;
        this.bookCode = bookCode;        
    }

    public Books(int bookCode) {
        this(BookDB.getBooks(bookCode));
    }
    
    private Books(Books b){
        this(b.bookID, b.bookCode, b.bookName, b.categoryID, b.categoryName, b.subjectID, b.subjectName, b.typeID, b.typeName);
    }

    public Books(int bookID, int bookCode, String bookName, String categoryID, String categoryName, int subjectID, String subjectName, int typeID, String typeName) {
        this.bookID = bookID;
        this.bookCode = bookCode;
        this.bookName = bookName;
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.typeID = typeID;
        this.typeName = typeName;        
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getBookCode() {
        return bookCode;
    }

    public void setBookCode(int bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
        return this.getCategoryID() + "-" + this.getSubjectID() + "-" + this.getBookID() + "-" + this.getBookCode();
    }
    
    public String getCodeSearch(){
        return this.getCategoryID() + "" + this.getSubjectID() + "" + this.getBookID() + "" + this.getBookCode();
    }
    
    public String getBorrower(){
        if(this.getTypeID() == 0) return "-";
        else return new Account(BookReceiptDB.getBorrower(bookCode)).getFullName();
    }

    @Override
    public String toString() {
        return "Books{" + "bookID=" + bookID + ", bookCode=" + bookCode + ", bookName=" + bookName + ", categoryID=" + categoryID + ", categoryName=" + categoryName + ", subjectID=" + subjectID + ", subjectName=" + subjectName + ", typeID=" + typeID + ", typeName=" + typeName + '}';
    }    
    
    public void delete(){
        BookDB.delete(this);
    }
}
