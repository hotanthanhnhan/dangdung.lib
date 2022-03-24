/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import model.dao.PlanDB;

/**
 *
 * @author ThanhNhan
 */
public class Plan {

    private int planID;
    private String title;
    private String description;
    private String planImg;
    private boolean appear;
    private Date time;

    public Plan() {
    }

    public Plan(int planID) {
        this(PlanDB.getPlan(planID));
    }

    public Plan(Plan p) {
        this(p.planID, p.title, p.description, p.planImg, p.appear, p.time);
    }

    public Plan(String title, String description, String planImg) {
        this.title = title;
        this.description = description;
        this.planImg = planImg;
    }

    public Plan(int planID, String title, String description, String planImg, boolean appear, Date time) {
        this.planID = planID;
        this.title = title;
        this.description = description;
        this.planImg = planImg;
        this.appear = appear;
        this.time = time;
    }

    public int getPlanID() {
        return planID;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlanImg() {
        return planImg;
    }

    public void setPlanImg(String planImg) {
        this.planImg = planImg;
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
        return "Plan{" + "planID=" + planID + ", title=" + title + ", description=" + description + ", planImg=" + planImg + ", appear=" + appear + ", time=" + time + '}';
    }

    public String getTitleView() {
        String text = this.getTitle();
        while (text.contains("\n")) {
            text = text.replaceAll("\n", "<br>");
        }
        return text;
    }
    
    public String getDescriptionView() {
        String text = this.getDescription();
        while (text.contains("\n")) {
            text = text.replaceAll("\n", "<br>");
        }
        return text;
    }
    
    public void appear(){
        if(this.appear){
            this.setAppear(false);
        } else this.setAppear(true);
        PlanDB.update(this);
    }
    
    public void update(){
        PlanDB.update(this);
    }
    
    public void add(){
        PlanDB.add(this);
    }
    
    public void delete(){
        PlanDB.delete(this);
    }
}
