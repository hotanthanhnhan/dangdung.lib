/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import model.dao.IdeaDB;

/**
 *
 * @author ThanhNhan
 */
public class Idea {
    private int ideaID;
    private String userID;
    private String fullName;
    private String clas;
    private int bookID;
    private String bookName;
    private String content;
    private boolean appear;
    private Date time;

    public Idea() {
    }
    
    public Idea(Idea i) {
        this(i.ideaID, i.userID, i.fullName, i.clas, i.bookID, i.bookName, i.content, i.appear, i.time);
    }
    
    public Idea(int ideaID) {
        this(IdeaDB.getIdea(ideaID));
    }

    public Idea(String userID, int bookID, String content) {        
        this.userID = userID;                
        this.bookID = bookID;        
        this.content = content;        
    }

    public Idea(int ideaID, String userID, String fullName, String clas, int bookID, String bookName, String content, boolean appear, Date time) {
        this.ideaID = ideaID;
        this.userID = userID;
        this.fullName = fullName;
        this.clas = clas;
        this.bookID = bookID;
        this.bookName = bookName;
        this.content = content;
        this.appear = appear;
        this.time = time;
    }

    public int getIdeaID() {
        return ideaID;
    }

    public void setIdeaID(int ideaID) {
        this.ideaID = ideaID;
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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getAppear() {
        return appear;
    }

    public void setAppear(boolean appear) {
        this.appear = appear;
    }

    public String getTime() {
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        return f.format(time);
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Idea{" + "ideaID=" + ideaID + ", userID=" + userID + ", fullName=" + fullName + ", clas=" + clas + ", bookID=" + bookID + ", bookName=" + bookName + ", content=" + content + ", appear=" + appear + ", time=" + time + '}';
    }
    
    public void add(){
        IdeaDB.add(this);
    }

    public void update(){
        if(this.appear){
            this.setAppear(false);
        } else this.setAppear(true);
        IdeaDB.update(this);
    }
    
    public String getContentView() {
        String text = this.getContent();
        while (text.contains("\n")) {
            text = text.replaceAll("\n", "<br>");
        }
        return text;
    }
    
    public void delete(){
        IdeaDB.delete(this);
    }
}
