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
import model.entity.Account;
import model.entity.Message;

/**
 *
 * @author ThanhNhan
 */
public class MessageDB implements DBContext {

    public static ArrayList<Message> getMessage(String userID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT M.messageID, M.typeID, M.userID, A.fullName, M.message, M.time, U.userTypeName, A.class\n"
                    + "FROM MESSAGE M INNER JOIN ACCOUNT A ON M.userID = A.userID\n"
                    + "INNER JOIN USERTYPE U ON A.userTypeID = U.userTypeID\n"
                    + "WHERE M.userID = ?\n"
                    + "ORDER BY messageID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            ArrayList<Message> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Message m = new Message(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5), (Date) f.parse(rs.getString(6)), rs.getString(7), rs.getString(8));
                list.add(m);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at MessageBD.getMessage()");
        }
        return null;
    }

    public static ArrayList<Message> getListSender() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT C2.messageID, C2.typeID, C1.userID, C3.fullName, C2.message, C2.time, U.userTypeName, C3.class FROM\n"
                    + "(SELECT A.userID, max(messageID) as ms\n"
                    + "FROM MESSAGE M INNER JOIN ACCOUNT A ON M.userID = A.userID\n"
                    + "GROUP BY A.userID\n"
                    + ") AS C1\n"
                    + "INNER JOIN \n"
                    + "MESSAGE C2 \n"
                    + "ON C1.ms = C2.messageID\n"
                    + "INNER JOIN ACCOUNT C3\n"
                    + "ON C2.userID = C3.userID\n"
                    + "INNER JOIN USERTYPE U ON C3.userTypeID = U.userTypeID\n"
                    + "ORDER BY C1.ms DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Message> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Message m = new Message(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5), (Date) f.parse(rs.getString(6)), rs.getString(7), rs.getString(8));
                list.add(m);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at MessageBD.getListSender()");
        }
        return null;
    }

    public static void addMessage(Message m) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO MESSAGE(typeID, userID, message)\n"
                    + "VALUES(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, m.isTypeID());
            ps.setString(2, m.getUserID());
            ps.setString(3, m.getMessage());
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at Message.addMessage()");
        }
    }

    public static void main(String[] args) {
        
    }
}
