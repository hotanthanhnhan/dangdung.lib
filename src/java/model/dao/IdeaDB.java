/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.DBConnect.DBContext;
import model.entity.Idea;

/**
 *
 * @author ThanhNhan
 */
public class IdeaDB implements DBContext {

    public static ArrayList<Idea> getListIdea() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT I.ideaID, I.userID, A.fullName, A.class, I.bookID, B.bookName, I.content, I.appear, I.time FROM IDEA I INNER JOIN ACCOUNT A ON A.userID = I.userID\n"
                    + "INNER JOIN BOOK B ON I.bookID = B.bookID\n"
                    + "ORDER BY I.ideaID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Idea> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Idea b = new Idea(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6),
                        rs.getString(7), rs.getBoolean(8), (Date) f.parse(rs.getString(9)));
                list.add(b);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at IdeasBD.getListIdea()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static ArrayList<Idea> getIdeaBook(int bookID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT I.ideaID, I.userID, A.fullName, A.class, I.bookID, B.bookName, I.content, I.appear, I.time FROM IDEA I INNER JOIN ACCOUNT A ON A.userID = I.userID\n"
                    + "INNER JOIN BOOK B ON I.bookID = B.bookID\n"
                    + "WHERE I.bookID = ?\n"
                    + "ORDER BY I.ideaID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, bookID);
            ResultSet rs = ps.executeQuery();
            ArrayList<Idea> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Idea b = new Idea(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6),
                        rs.getString(7), rs.getBoolean(8), (Date) f.parse(rs.getString(9)));
                list.add(b);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at IdeasBD.getListIdea()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static Idea getIdea(int ideaID) {
        Idea b = null;
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT I.ideaID, I.userID, A.fullName, A.class, I.bookID, B.bookName, I.content, I.appear, I.time FROM IDEA I INNER JOIN ACCOUNT A ON A.userID = I.userID\n"
                    + "INNER JOIN BOOK B ON I.bookID = B.bookID\n"
                    + "WHERE I.ideaID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, ideaID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                b = new Idea(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6),
                        rs.getString(7), rs.getBoolean(8), (Date) f.parse(rs.getString(9)));
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at IdeasBD.getListIdea()");
            throw new RuntimeException("Không tìm thấy bài viết cảm nghĩ này!");
        }
        return b;
    }

    public static void add(Idea i) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO IDEA(userID, bookID, content)\n"
                    + "VALUES(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, i.getUserID());
            ps.setInt(2, i.getBookID());
            ps.setString(3, i.getContent());
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at Message.addMessage()");
        }
    }

    public static void update(Idea i) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE IDEA\n"
                    + "SET appear = ?\n"
                    + "WHERE ideaID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, i.getAppear());
            ps.setInt(2, i.getIdeaID());            
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at Message.addMessage()");
        }
    }
    
    public static void delete(Idea i) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "DELETE IDEA\n"                    
                    + "WHERE ideaID = ?";
            PreparedStatement ps = conn.prepareStatement(query);            
            ps.setInt(1, i.getIdeaID());            
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at Message.addMessage()");
        }
    }

    public static void main(String[] args) {
//        new Idea("hs", 1001, "Đây là cuốn sách hay").add();
//        for (Idea i : getListIdea()) {
//            System.out.println(i);
//        }
new Idea(3).update();
    }
}
