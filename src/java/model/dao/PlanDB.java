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
import model.entity.Plan;

/**
 *
 * @author ThanhNhan
 */
public class PlanDB implements DBContext {

    public static ArrayList<Plan> getListPlan() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT planID, title, description, planImg, appear, time FROM [PLAN]\n"
                    + "ORDER BY planID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Plan> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Plan b = new Plan(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getBoolean(5), (Date) f.parse(rs.getString(6)));
                list.add(b);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at PlansBD.getListPlan()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static Plan getPlan(int PlanID) {
        Plan b = null;
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT planID, title, description, planImg, appear, time FROM [PLAN]\n"
                    + "WHERE planID = ?\n"
                    + "ORDER BY planID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, PlanID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                b = new Plan(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getBoolean(5), (Date) f.parse(rs.getString(6)));
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at PlansBD.getListPlan()");
            throw new RuntimeException("Không tìm thấy bài viết cảm nghĩ này!");
        }
        return b;
    }

    public static void add(Plan i) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO [PLAN](title, description, planImg)\n"
                    + "VALUES(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, i.getTitle());
            ps.setString(2, i.getDescription());
            ps.setString(3, i.getPlanImg());
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at Message.addMessage()");
        }
    }

    public static void update(Plan i) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE [PLAN]\n"
                    + "SET title = ?, description = ?, planImg = ?, appear = ?\n"
                    + "WHERE planID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, i.getTitle());
            ps.setString(2, i.getDescription());
            ps.setString(3, i.getPlanImg());
            ps.setBoolean(4, i.getAppear());
            ps.setInt(5, i.getPlanID());
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at Message.addMessage()");
        }
    }

    public static void delete(Plan i) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "DELETE [Plan]\n"
                    + "WHERE PlanID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, i.getPlanID());
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at Message.addMessage()");
        }
    }
    
    public static void main(String[] args) {
//        new Plan("abc", "abc", null).add();
//        System.out.println(new Plan(1));
new Plan(1).appear();
    }
}
