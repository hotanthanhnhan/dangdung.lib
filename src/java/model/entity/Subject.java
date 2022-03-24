/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import model.dao.SubjectDB;

/**
 *
 * @author ThanhNhan
 */
public class Subject {
    private int subjectID;
    private String subjectName;

    public Subject() {
    }

    public Subject(int subjectID, String subjectName) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
    }
    
    public Subject(Subject s) {
        this(s.subjectID, s.subjectName);
    }
    
    public Subject(int subjectID) {
        this(SubjectDB.getSubject(subjectID));
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

    @Override
    public String toString() {
        return "Subject{" + "subjectID=" + subjectID + ", subjectName=" + subjectName + '}';
    }   
    
    public int getCount(){
        return SubjectDB.count(this);
    }
    
    public int getCounts(){
        return SubjectDB.counts(this);
    }
}
