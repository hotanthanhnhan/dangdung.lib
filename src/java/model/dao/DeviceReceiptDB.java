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
import model.entity.Device;
import model.entity.DeviceOrder;
import model.entity.DeviceReceipt;
import model.entity.Devices;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

/**
 *
 * @author ThanhNhan
 */
public class DeviceReceiptDB {

    public static ArrayList<DeviceReceipt> getListDeviceReceipt() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT DR.deviceReceiptID, DR.userID, A.fullName, D.deviceID, D.deviceName, DR.quantity, T.typeID, T.typeName, DR.borrowDate, DR.payDate FROM DEVICERECEIPT DR INNER JOIN ACCOUNT A ON DR.userID = A.userID\n"
                    + "INNER JOIN DEVICE D ON D.deviceID = DR.deviceID\n"
                    + "INNER JOIN TYPE T ON T.typeID = DR.typeID\n"
                    + "ORDER BY DR.deviceReceiptID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<DeviceReceipt> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date payDate = null;
                if (rs.getString(10) != null) {
                    payDate = (Date) f.parse(rs.getString(10));
                }
                DeviceReceipt a = new DeviceReceipt(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), (Date) f.parse(rs.getString(9)), payDate);
                list.add(a);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DeviceReceiptDB.getListDeviceReceipt()");
        }
        return null;
    }

    public static ArrayList<DeviceReceipt> listDeviceReceipt(Account b) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT DR.deviceReceiptID, DR.userID, A.fullName, D.deviceID, D.deviceName, DR.quantity, T.typeID, T.typeName, DR.borrowDate, DR.payDate FROM DEVICERECEIPT DR INNER JOIN ACCOUNT A ON DR.userID = A.userID\n"
                    + "INNER JOIN DEVICE D ON D.deviceID = DR.deviceID\n"
                    + "INNER JOIN TYPE T ON T.typeID = DR.typeID\n"
                    + "WHERE DR.userID = ?\n"
                    + "ORDER BY DR.deviceReceiptID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, b.getUserID());
            ResultSet rs = ps.executeQuery();
            ArrayList<DeviceReceipt> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date payDate = null;
                if (rs.getString(10) != null) {
                    payDate = (Date) f.parse(rs.getString(10));
                }
                DeviceReceipt a = new DeviceReceipt(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), (Date) f.parse(rs.getString(9)), payDate);
                list.add(a);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DeviceReceiptDB.getListDeviceReceipt()");
        }
        return null;
    }

    public static DeviceReceipt getDeviceReceipt(int DeviceReceiptID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT DR.deviceReceiptID, DR.userID, A.fullName, D.deviceID, D.deviceName, DR.quantity, T.typeID, T.typeName, DR.borrowDate, DR.payDate FROM DEVICERECEIPT DR INNER JOIN ACCOUNT A ON DR.userID = A.userID\n"
                    + "INNER JOIN DEVICE D ON D.deviceID = DR.deviceID\n"
                    + "INNER JOIN TYPE T ON T.typeID = DR.typeID\n"
                    + "WHERE DR.deviceReceiptID = ?\n"
                    + "ORDER BY DR.deviceReceiptID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, DeviceReceiptID);
            ResultSet rs = ps.executeQuery();
            DeviceReceipt a = null;
            if (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date payDate = null;
                if (rs.getString(10) != null) {
                    payDate = (Date) f.parse(rs.getString(13));
                }
                a = new DeviceReceipt(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), (Date) f.parse(rs.getString(9)), payDate);
            }
            conn.close();
            return a;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DeviceReceiptDB.getListDeviceReceipt()");
            throw new RuntimeException("Không tìm thấy đơn này!");
        }
    }

    public static int add(DeviceReceipt b) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO DEVICERECEIPT(userID, deviceID, quantity) OUTPUT inserted.deviceReceiptID\n"
                    + "VALUES(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, b.getUserID());
            ps.setInt(2, b.getDeviceID());
            ps.setInt(3, b.getQuantity());
            ResultSet rs = ps.executeQuery();
            int deviceReceiptID = -1;
            if (rs.next()) {
                deviceReceiptID = rs.getInt(1);
            }
            conn.close();
            return deviceReceiptID;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DeviceReceiptDB.getListDeviceReceipt()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static String getBorrower(int DeviceCode) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT DR.userID from DEVICERECEIPTDEVICES DD INNER JOIN DEVICERECEIPT DR ON DD.deviceReceiptID = DR.deviceReceiptID\n"
                    + "WHERE DD.deviceCode = ? AND typeID = 4";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, DeviceCode);
            ResultSet rs = ps.executeQuery();
            String borrower = "";
            if (rs.next()) {
                borrower = rs.getString(1);
            }
            conn.close();
            return borrower;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DeviceReceiptDB.getListDeviceReceipt()");
            throw new RuntimeException("Không tìm thấy đơn này!");
        }
    }

    public static ArrayList<Devices> listDevicesBorrow(int deviceReceiptID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT deviceCode from DEVICERECEIPTDEVICES\n"
                    + "WHERE deviceReceiptID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, deviceReceiptID);
            ResultSet rs = ps.executeQuery();
            ArrayList<Devices> list = new ArrayList<>();
            while (rs.next()) {
                Devices a = new Devices(rs.getInt(1));
                list.add(a);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DeviceReceiptDB.getListDeviceReceipt()");
        }
        return null;
    }
    
    public static void back(DeviceReceipt b) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE DEVICERECEIPT\n"
                    + "SET typeID = 5, payDate = GETDATE()\n"
                    + "WHERE deviceReceiptID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, b.getDeviceReceiptID());
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BookReceiptDB.getListBookReceipt()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }
    
    public static void statistical(ArrayList<DeviceReceipt> list) {
        try {
            String file = "D:/workspace/java/DangDungLibrary/build/web/giaovienmuonthietbi.xls";
            HSSFWorkbook wb2003 = new HSSFWorkbook();
            HSSFSheet sheet = (HSSFSheet) wb2003.createSheet();
            HSSFRow row = sheet.createRow(0);
            row.createCell(0, CellType.STRING).setCellValue("Họ và tên");
            row.createCell(1, CellType.STRING).setCellValue("Thiết bị mượn");
            row.createCell(2, CellType.STRING).setCellValue("Số lượng");
            row.createCell(3, CellType.STRING).setCellValue("Mã thiết bị");
            row.createCell(4, CellType.STRING).setCellValue("Trạng thái");
            row.createCell(5, CellType.STRING).setCellValue("Ngày mượn");
            row.createCell(6, CellType.STRING).setCellValue("Ngày trả");
            int index = 1;
            int i = 0;
            while (i < list.size()) {
                DeviceReceipt b = list.get(i);
                row = sheet.createRow(index);
                row.createCell(0, CellType.STRING).setCellValue(b.getFullName());
                row.createCell(1, CellType.STRING).setCellValue(b.getDeviceName());
                row.createCell(2, CellType.STRING).setCellValue(b.getQuantity());
                row.createCell(3, CellType.STRING).setCellValue(b.getCode().get(0).getCode());
                row.createCell(4, CellType.STRING).setCellValue(b.getCode().get(0).getTypeName());
                row.createCell(5, CellType.NUMERIC).setCellValue(b.getBorrowDate());
                row.createCell(6, CellType.NUMERIC).setCellValue(b.getPayDate());
                int m = index + 1;
                int k = b.getCode().size();
                for (int j = 1; j < k; j++) {
                    row = sheet.createRow(m);
                    row.createCell(3, CellType.STRING).setCellValue(b.getCode().get(j).getCode());
                    row.createCell(4, CellType.STRING).setCellValue(b.getCode().get(j).getTypeName());
                    m++;
                }
                i++;
                index = m;
            }
            row = sheet.createRow(index);
            row.createCell(1, CellType.STRING).setCellValue("Tổng cộng");
            int total = 0;            
            for (DeviceReceipt d : list) {
                total = total + d.getQuantity();                
            }
            row.createCell(2, CellType.STRING).setCellValue(total);            
            for (int n = 0; n < 7; n++) {
                sheet.autoSizeColumn(n);
            }
            OutPutFile.createOutputFile(wb2003, file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
//        int deviceReceiptID = new DeviceReceipt("ht", 1011, 1).add();
//        new DeviceReceipt(deviceReceiptID).borrow(1);

        for(DeviceReceipt d : getListDeviceReceipt()){
            System.out.println(d);
        }
    }
}
