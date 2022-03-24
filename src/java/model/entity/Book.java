/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.util.ArrayList;
import model.dao.AuthorDB;
import model.dao.BookDB;

/**
 *
 * @author ThanhNhan
 */
public class Book {

    private int bookID;
    private String bookName;
    private String categoryID;
    private String categoryName;
    private int subjectID;
    private String subjectName;
    private String content;
    private String bookImg;
    private int quantity;
    private int viewCounter;
    private int total;
    private String pdfLink;

    public Book() {
    }

    public Book(String bookName, String categoryID, int subjectID, String content, String bookImg, int quantity, String pdfLink) {
        this.bookName = bookName;        
        this.categoryID = categoryID;
        this.subjectID = subjectID;
        this.content = content;
        this.bookImg = bookImg;
        this.quantity = quantity;
        this.pdfLink = pdfLink;
    }

    public Book(int bookID, String bookName, String categoryID, int subjectID, String content, String bookImg, int quantity, String pdfLink) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.categoryID = categoryID;
        this.subjectID = subjectID;
        this.content = content;
        this.bookImg = bookImg;
        this.quantity = quantity;
        this.pdfLink = pdfLink;
    }
    
    public Book(int bookID, String bookName, String categoryID, String categoryName, int subjectID, String subjectName, String content, String bookImg, int quantity, int viewCounter, String pdfLink) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.content = content;
        this.bookImg = bookImg;
        this.quantity = quantity;
        this.viewCounter = viewCounter;
        this.pdfLink = pdfLink;
        this.setTotal();
    }

    public Book(int bookID) {
        this(BookDB.getBook(bookID));
    }

    public Book(Book b) {
        this(b.bookID, b.bookName, b.categoryID, b.categoryName, b.subjectID, b.subjectName, b.content, b.bookImg, b.quantity, b.viewCounter, b.pdfLink);
    }

    public int getBookID() {
        return bookID;
    }

    public int getTotal() {
        return total;
    }

    public final void setTotal() {
        this.total = BookDB.total(this);
    }
    
    public void setBookID(int bookID) {
        this.bookID = bookID;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getViewCounter() {
        return viewCounter;
    }

    public void setViewCounter(int viewCounter) {
        this.viewCounter = viewCounter;
    }

    public String getPdfLink() {
        return pdfLink;
    }

    public void setPdfLink(String pdfLink) {
        this.pdfLink = pdfLink;
    }
    
    

    @Override
    public String toString() {
        return "Book{" + "bookID=" + bookID + ", bookName=" + bookName + ", categoryID=" + categoryID + ", categoryName=" + categoryName + ", subjectID=" + subjectID + ", subjectName=" + subjectName + ", content=" + content + ", bookImg=" + bookImg + ", quantity=" + quantity + ", viewCounter=" + viewCounter + ", total=" + total + '}';
    }    
    
    public void update() {
        BookDB.update(this);
    }

    public void upViewCounter() {
        viewCounter++;
        BookDB.upViewCounter(this);
    }

    public int add() {
        return BookDB.addBook(this);
    }

    public void borrow(int bookCode) {
        this.quantity--;
        if(this.getQuantity() < 0){
            throw new RuntimeException("Không đủ sách trong kho!");
        }
        BookDB.borrow(this, bookCode);
    }

    public void back(int bookCode) {
        this.quantity++;
        BookDB.back(this, bookCode);        
    }
    
    public ArrayList<Author> getAuthors(){
        return AuthorDB.getAuthors(bookID);
    }
    
    public int getAuthor1(){
        return this.getAuthors().get(0).getAuthorID();
    }
    
    public int getAuthor2(){
        try {
            return this.getAuthors().get(1).getAuthorID();
        } catch (Exception e) {
            return -1;
        }        
    }
    
    public int getAuthor3(){
        try {
            return this.getAuthors().get(2).getAuthorID();
        } catch (Exception e) {
            return -1;
        } 
    }
    
    public int getAuthor4(){
        try {
            return this.getAuthors().get(3).getAuthorID();
        } catch (Exception e) {
            return -1;
        } 
    }
    
    public int getAuthor5(){
        try {
            return this.getAuthors().get(4).getAuthorID();
        } catch (Exception e) {
            return -1;
        } 
    }
    
    public void delete(){
        this.quantity--;
        BookDB.update(this);
    }
    
    public String getContentView() {
        String text = this.getContent();
        while (text.contains("\n")) {
            text = text.replaceAll("\n", "<br>");
        }
        return text;
    }
    
    public ArrayList<Books> getBooks(){
        return BookDB.listBooks(bookID);
    }
    
    public boolean getCheck(){
        return this.getQuantity() == this.getTotal() && BookOrder.check(bookID);
    }
    
    public void deleteAll(){
        BookDB.deleteAll(this);
    }
}


