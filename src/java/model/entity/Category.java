/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import model.dao.CategoryDB;

/**
 *
 * @author ThanhNhan
 */
public class Category {
    private String categoryID;
    private String categoryName;

    public Category() {
    }

    public Category(String categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }
    
    public Category(String categoryID){
        this(CategoryDB.getCategory(categoryID));
    }
    
    public Category(Category c){
        this(c.categoryID, c.categoryName);
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

    @Override
    public String toString() {
        return "Category{" + "categoryID=" + categoryID + ", categoryName=" + categoryName + '}';
    }    
    
    public void update(String id){
        CategoryDB.updateCategory(this, id);
    }
    
    public String add(){
        return CategoryDB.addCategory(this);
    }
    
    public int getCount(){
        return CategoryDB.count(this);
    }
    
    public int getCounts(){
        return CategoryDB.counts(this);
    }
}
