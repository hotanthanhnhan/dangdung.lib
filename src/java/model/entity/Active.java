/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import model.dao.ActiveDB;

/**
 *
 * @author ThanhNhan
 */
public class Active {
    private int activeID;
    private String activeImg;
    private String name;    
    private boolean appear;
    private Date time;

    public Active() {
    }
    
    public Active(int activeID) {
        this(ActiveDB.getActive(activeID));
    }
    
    public Active(Active a) {
        this(a.activeID, a.activeImg, a.name, a.appear, a.time);
    }

    public Active(String activeImg, String name) {        
        this.activeImg = activeImg;
        this.name = name;        
    }

    public Active(int activeID, String activeImg, String name, boolean appear, Date time) {
        this.activeID = activeID;
        this.activeImg = activeImg;
        this.name = name;        
        this.appear = appear;
        this.time = time;
    }

    public int getActiveID() {
        return activeID;
    }

    public void setActiveID(int activeID) {
        this.activeID = activeID;
    }

    public String getActiveImg() {
        return activeImg;
    }

    public void setActiveImg(String activeImg) {
        this.activeImg = activeImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Active{" + "activeID=" + activeID + ", activeImg=" + activeImg + ", name=" + name + ", appear=" + appear + ", time=" + time + '}';
    }
    
    public void add(){
        ActiveDB.add(this);
    }
    
    public void appear(){
        if(this.appear){
            this.setAppear(false);
        } else this.setAppear(true);
        ActiveDB.update(this);
    }
    
    public void update(){
        ActiveDB.update(this);
    }
    
    public String getNameView() {
        String text = this.getName();
        while (text.contains("\n")) {
            text = text.replaceAll("\n", "<br>");
        }
        return text;
    }
    
    public void delete(){
        ActiveDB.delete(this);
    }
}
