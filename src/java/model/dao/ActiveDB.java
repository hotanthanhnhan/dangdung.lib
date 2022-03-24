/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.DBConnect.DBContext;
import model.entity.Active;

/**
 *
 * @author ThanhNhan
 */
public class ActiveDB implements DBContext {

    public static ArrayList<Active> getListActive() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT activeID, activeImg, name, appear, time FROM ACTIVE\n"
                    + "ORDER BY activeID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Active> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Active b = new Active(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getBoolean(4), (Date) f.parse(rs.getString(5)));
                list.add(b);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at ActivesBD.getListActive()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static Active getActive(int activeID) {
        Active b = null;
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT activeID, activeImg, name, appear, time FROM ACTIVE\n"
                    + "WHERE activeID = ?\n"
                    + "ORDER BY activeID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, activeID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                b = new Active(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getBoolean(4), (Date) f.parse(rs.getString(5)));
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at ActivesBD.getListActive()");
            throw new RuntimeException("Không tìm thấy hoạt động này!");
        }
        return b;
    }

    public static void add(Active a) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO ACTIVE(activeImg, name)\n"
                    + "VALUES(?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, a.getActiveImg());
            ps.setString(2, a.getName());            
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at Message.addMessage()");
        }
    }

    public static void update(Active a) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE ACTIVE\n"
                    + "SET appear = ?, activeImg = ?, name = ?\n"
                    + "WHERE activeID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, a.getAppear());
            ps.setString(2, a.getActiveImg());
            ps.setString(3, a.getName());  
            ps.setInt(4, a.getActiveID());
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at Message.addMessage()");
        }
    }
    
    public static void delete(Active i) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "DELETE Active\n"                    
                    + "WHERE activeID = ?";
            PreparedStatement ps = conn.prepareStatement(query);            
            ps.setInt(1, i.getActiveID());            
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at Message.addMessage()");
        }
    }
    
    public static void main(String[] args) {
        new Active(4).appear();
    }
}
