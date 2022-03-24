/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import model.dao.AuthorDB;

/**
 *
 * @author ThanhNhan
 */
public class Author {
    private int authorID;
    private String authorName;

    public Author() {
    }
    
    public Author(Author a){
        this(a.authorID, a.authorName);
    }
    
    public Author(int authorID){
        this(AuthorDB.getAuthor(authorID));
    }

    public Author(String authorName) {        
        this.authorName = authorName;
    }

    public Author(int authorID, String authorName) {
        this.authorID = authorID;
        this.authorName = authorName;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "Author{" + "authorID=" + authorID + ", authorName=" + authorName + '}';
    }    
    
    public void update(){
        AuthorDB.updateAuthor(this);
    }
    
    public int add(){
        return AuthorDB.addAuthor(this);
    }
    
    public int getCount(){
        return AuthorDB.count(this);
    }
    
    public int getCounts(){
        return AuthorDB.counts(this);
    }    
}
