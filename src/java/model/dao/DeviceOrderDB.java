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
import model.entity.DeviceOrder;

/**
 *
 * @author ThanhNhan
 */
public class DeviceOrderDB implements DBContext {

    public static ArrayList<DeviceOrder> getListDeviceOrder() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT DO.deviceOrderID, DO.userID, A.fullName, D.deviceID, DO.quantity, DO.typeID, T.typeName, D.deviceName, DO.orderDate FROM DEVICEORDER DO INNER JOIN ACCOUNT A ON A.userID = DO.userID\n"
                    + "INNER JOIN DEVICE D ON DO.deviceID = D.deviceID\n"
                    + "INNER JOIN TYPE T ON DO.typeID = T.typeID\n"
                    + "ORDER BY DO.deviceOrderID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<DeviceOrder> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                DeviceOrder a = new DeviceOrder(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8), (Date) f.parse(rs.getString(9)));
                list.add(a);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DeviceOrderDB.getListDeviceOrder()");
        }
        return null;
    }

    public static ArrayList<DeviceOrder> listDeviceOrder(Account a) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT DO.deviceOrderID, DO.userID, A.fullName, D.deviceID, DO.quantity, DO.typeID, T.typeName, D.deviceName, DO.orderDate FROM DEVICEORDER DO INNER JOIN ACCOUNT A ON A.userID = DO.userID\n"
                    + "INNER JOIN DEVICE D ON DO.deviceID = D.deviceID\n"
                    + "INNER JOIN TYPE T ON DO.typeID = T.typeID\n"
                    + "WHERE A.userID = ?\n"
                    + "ORDER BY DO.deviceOrderID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, a.getUserID());
            ResultSet rs = ps.executeQuery();
            ArrayList<DeviceOrder> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                DeviceOrder b = new DeviceOrder(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8), (Date) f.parse(rs.getString(9)));
                list.add(b);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DeviceOrderDB.getListDeviceOrder()");
        }
        return null;
    }

    public static DeviceOrder getDeviceOrder(int DeviceOrderID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT DO.deviceOrderID, DO.userID, A.fullName, D.deviceID, DO.quantity, DO.typeID, T.typeName, D.deviceName, DO.orderDate FROM DEVICEORDER DO INNER JOIN ACCOUNT A ON A.userID = DO.userID\n"
                    + "INNER JOIN DEVICE D ON DO.deviceID = D.deviceID\n"
                    + "INNER JOIN TYPE T ON DO.typeID = T.typeID\n"
                    + "WHERE DO.deviceOrderID =?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, DeviceOrderID);
            ResultSet rs = ps.executeQuery();
            DeviceOrder a = null;
            if (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                a = new DeviceOrder(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8), (Date) f.parse(rs.getString(9)));
            }
            conn.close();
            return a;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DeviceOrderDB.getListDeviceOrder()");
            throw new RuntimeException("Không tìm thấy đơn này!");
        }
    }

    public static void addOrder(DeviceOrder b) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO DEVICEORDER(userID, deviceID, quantity)\n"
                    + "VALUES(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, b.getUserID());
            ps.setInt(2, b.getDeviceID());
            ps.setInt(3, b.getQuantity());
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.createAccount()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void refure(DeviceOrder b) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE DEVICEORDER\n"
                    + "SET typeID = 3\n"
                    + "WHERE deviceOrderID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, b.getDeviceOrderID());
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.createAccount()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void delete(DeviceOrder b) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "DELETE DEVICEORDER\n"
                    + "WHERE deviceOrderID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, b.getDeviceOrderID());
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
//        for (DeviceOrder d : getListDeviceOrder()) {
//            System.out.println(d);
//        }

//        new DeviceOrder("ht", 1001, 2).add();
for(DeviceOrder d : listDeviceOrder(new Account("ht"))) {
            System.out.println(d.getQuantityDeviceReceipt());
        }
        
    }
}
