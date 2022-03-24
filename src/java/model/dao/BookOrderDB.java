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
import model.entity.Account;
import model.entity.BookOrder;

/**
 *
 * @author ThanhNhan
 */
public class BookOrderDB implements DBContext {

    public static ArrayList<BookOrder> getListBookOrder() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT BO.bookOrderID, BO.userID, A.fullName, A.userTypeID, T1.userTypeName, A.class, B.bookID, T2.typeID, T2.typeName, B.bookName, BO.orderDate FROM BOOKORDER BO INNER JOIN ACCOUNT A ON BO.userID = A.userID\n"
                    + "INNER JOIN USERTYPE T1 ON A.userTypeID = T1.userTypeID\n"
                    + "INNER JOIN BOOK B ON BO.bookID = B.bookID\n"
                    + "INNER JOIN TYPE T2 ON BO.typeID = T2.typeID\n"
                    + "ORDER BY BO.bookOrderID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<BookOrder> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                BookOrder a = new BookOrder(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10), (Date) f.parse(rs.getString(11)));
                list.add(a);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BookOrderDB.getListBookOrder()");
        }
        return null;
    }

    public static ArrayList<BookOrder> listBookOrder(Account a) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT BO.bookOrderID, BO.userID, A.fullName, A.userTypeID, T1.userTypeName, A.class, B.bookID, T2.typeID, T2.typeName, B.bookName, BO.orderDate FROM BOOKORDER BO INNER JOIN ACCOUNT A ON BO.userID = A.userID\n"
                    + "INNER JOIN USERTYPE T1 ON A.userTypeID = T1.userTypeID\n"
                    + "INNER JOIN BOOK B ON BO.bookID = B.bookID\n"
                    + "INNER JOIN TYPE T2 ON BO.typeID = T2.typeID\n"
                    + "WHERE A.userID = ?\n"
                    + "ORDER BY BO.bookOrderID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, a.getUserID());
            ResultSet rs = ps.executeQuery();
            ArrayList<BookOrder> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                BookOrder b = new BookOrder(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10), (Date) f.parse(rs.getString(11)));
                list.add(b);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BookOrderDB.getListBookOrder()");
        }
        return null;
    }

    public static BookOrder getBookOrder(int bookOrderID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT BO.bookOrderID, BO.userID, A.fullName, A.userTypeID, T1.userTypeName, A.class, B.bookID, T2.typeID, T2.typeName, B.bookName, BO.orderDate FROM BOOKORDER BO INNER JOIN ACCOUNT A ON BO.userID = A.userID\n"
                    + "INNER JOIN USERTYPE T1 ON A.userTypeID = T1.userTypeID\n"
                    + "INNER JOIN BOOK B ON BO.bookID = B.bookID\n"
                    + "INNER JOIN TYPE T2 ON BO.typeID = T2.typeID\n"
                    + "WHERE BO.bookOrderID = ?\n";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, bookOrderID);
            ResultSet rs = ps.executeQuery();
            BookOrder a = null;
            if (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                a = new BookOrder(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10), (Date) f.parse(rs.getString(11)));
            }
            conn.close();
            return a;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BookOrderDB.getListBookOrder()");
            throw new RuntimeException("Không tìm thấy đơn này!");
        }
    }

    public static void addOrder(BookOrder b) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO BOOKORDER(userID, bookID)\n"
                    + "VALUES(?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, b.getUserID());
            ps.setInt(2, b.getBookID());
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.createAccount()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }
    
    public static void delete(BookOrder b) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "DELETE BOOKORDER\n"                    
                    + "WHERE bookOrderID = ?";
            PreparedStatement ps = conn.prepareStatement(query);            
            ps.setInt(1, b.getBookOrderID());
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.createAccount()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void main(String[] args) {

    }
}
