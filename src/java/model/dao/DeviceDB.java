/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DBConnect.DBContext;
import model.entity.Device;
import model.entity.Devices;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

/**
 *
 * @author ThanhNhan
 */
public class DeviceDB implements DBContext {

    public static ArrayList<Device> getListDevice() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT D.deviceID, D.deviceName, D.subjectID, S.subjectName, D.deviceImg, D.quantity FROM DEVICE D INNER JOIN SUBJECT S ON D.subjectID = S.subjectID ORDER BY D.deviceID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Device> list = new ArrayList<>();
            while (rs.next()) {
                Device d = new Device(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                list.add(d);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.getListAccount()");
        }
        return null;
    }

    public static void addDevice(Device d) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO DEVICE(deviceName, subjectID, deviceImg, quantity) OUTPUT INSERTED.deviceID\n"
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, d.getDeviceName());
            ps.setInt(2, d.getSubjectID());
            ps.setString(3, d.getDeviceImg());
            ps.setInt(4, d.getQuantity());
            ResultSet rs = ps.executeQuery();
            int deviceID = -1;
            if (rs.next()) {
                deviceID = rs.getInt(1);
            }
            conn.commit();
            conn.close();
            DeviceDB.addDevices(deviceID, d.getQuantity());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Device getDevice(int deviceID) {
        Device d = null;
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT D.deviceID, D.deviceName, D.subjectID, S.subjectName, D.deviceImg, D.quantity FROM DEVICE D INNER JOIN SUBJECT S ON D.subjectID = S.subjectID WHERE D.deviceID = ? ORDER BY D.deviceID DESC";;
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, deviceID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                d = new Device(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6));
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at CategoriesDB.getCategory()");
        }
        if (d == null) {
            throw new RuntimeException("Không tìm thấy thiết bị này!");
        }
        return d;
    }

    public static int total(Device d) {
        int total = 0;
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT COUNT(deviceID) FROM DEVICES\n"
                    + "WHERE deviceID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, d.getDeviceID());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at DevicesBD.addDevice()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
        return total;
    }

    public static void addDevices(int deviceID, int n) {
        for (int i = 0; i < n; i++) {
            DeviceDB.devices(deviceID);
        }
    }

    private static void devices(int deviceID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO DEVICES(deviceID)\n"
                    + "VALUES(?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, deviceID);
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at DevicesBD.addDevices()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void borrow(Device d) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE DEVICE\n"
                    + "SET quantity = ?\n"
                    + "WHERE deviceID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, d.getQuantity());
            ps.setInt(2, d.getDeviceID());
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DevicesBD.update()");
            throw new RuntimeException("Không đủ thiết bị trong kho!");
        }
    }

    public static void borrows(Devices d, int deviceReceiptID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE DEVICES\n"
                    + "SET typeID = 1\n"
                    + "WHERE deviceCode = ?\n"
                    + "INSERT INTO DEVICERECEIPTDEVICES(deviceReceiptID, deviceCode)\n"
                    + "VALUES(?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, d.getDeviceCode());
            ps.setInt(2, deviceReceiptID);
            ps.setInt(3, d.getDeviceCode());
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DevicesBD.update()");
            throw new RuntimeException("Không đủ thiết bị trong kho!");
        }
    }

    public static void backs(Devices d) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE DEVICES\n"
                    + "SET typeID = 0\n"
                    + "WHERE deviceCode = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, d.getDeviceCode());
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DevicesBD.update()");
            throw new RuntimeException("Không đủ thiết bị trong kho!");
        }
    }

    public static void update(Device d) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE DEVICE\n"
                    + "SET deviceName = ?, subjectID = ?, deviceImg = ?, quantity = ?\n"
                    + "WHERE deviceID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, d.getDeviceName());
            ps.setInt(2, d.getSubjectID());
            ps.setString(3, d.getDeviceImg());
            ps.setInt(4, d.getQuantity());
            ps.setInt(5, d.getDeviceID());
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DevicesBD.update()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static ArrayList<Device> subjectDevice(int subjectID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT D.deviceID, D.deviceName, D.subjectID, S.subjectName, D.deviceImg, D.quantity FROM DEVICE D INNER JOIN SUBJECT S ON D.subjectID = S.subjectID\n"
                    + "WHERE S.subjectID = ?\n"
                    + "ORDER BY deviceID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, subjectID);
            ResultSet rs = ps.executeQuery();
            ArrayList<Device> list = new ArrayList<>();
            while (rs.next()) {
                Device d = new Device(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                list.add(d);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DevicesBD.categoryDevice()");
            throw new RuntimeException("Không tìm thấy thiết bị này!");
        }
    }

    public static ArrayList<Devices> getListDevices() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT DS.deviceID, DS.deviceCode, D.deviceName, D.subjectID, S.subjectName, DS.typeID, T.typeName\n"
                    + "FROM DEVICES DS INNER JOIN DEVICE D ON D.deviceID = DS.deviceID\n"
                    + "INNER JOIN SUBJECT S ON S.subjectID = D.subjectID\n"
                    + "INNER JOIN TYPE T ON DS.typeID = T.typeID\n"
                    + "ORDER BY D.deviceID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Devices> list = new ArrayList<>();
            while (rs.next()) {
                Devices d = new Devices(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7));
                list.add(d);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DevicesBD.getListDevice()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static Devices getDevices(int deviceCode) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT DS.deviceID, DS.deviceCode, D.deviceName, D.subjectID, S.subjectName, DS.typeID, T.typeName\n"
                    + "FROM DEVICES DS INNER JOIN DEVICE D ON D.deviceID = DS.deviceID\n"
                    + "INNER JOIN SUBJECT S ON S.subjectID = D.subjectID\n"
                    + "INNER JOIN TYPE T ON DS.typeID = T.typeID\n"
                    + "WHERE DS.deviceCode = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, deviceCode);
            ResultSet rs = ps.executeQuery();
            Devices d = null;
            if (rs.next()) {
                d = new Devices(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getString(7));
            }
            conn.close();
            return d;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DevicesBD.getListDevice()");
            throw new RuntimeException("Không tìm thấy thiết bị trong kho!");
        }
    }

    public static void delete(Devices d) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "DELETE DEVICES\n"
                    + "WHERE deviceCode = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, d.getDeviceCode());
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DevicesBD.update()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void deleteAll(Device d) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "DELETE DEVICERECEIPT\n"
                    + "WHERE deviceID = ?\n"
                    + "delete DEVICE\n"
                    + "where deviceID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, d.getDeviceID());
            ps.setInt(2, d.getDeviceID());
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DevicesBD.update()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static ArrayList<Devices> listDevices(int DeviceID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT DS.deviceID, DS.deviceCode, D.deviceName, D.subjectID, S.subjectName, DS.typeID, T.typeName\n"
                    + "FROM DEVICES DS INNER JOIN DEVICE D ON D.deviceID = DS.deviceID\n"
                    + "INNER JOIN SUBJECT S ON S.subjectID = D.subjectID\n"
                    + "INNER JOIN TYPE T ON DS.typeID = T.typeID\n"
                    + "WHERE DS.deviceID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, DeviceID);
            ResultSet rs = ps.executeQuery();
            ArrayList<Devices> list = new ArrayList<>();
            while (rs.next()) {
                Devices b = new Devices(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getString(7));
                list.add(b);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DevicesBD.getListDevice()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void statisticalDevice() {
        try {
            String file = "D:/workspace/java/DangDungLibrary/build/web/thietbi.xls";
            HSSFWorkbook wb2003 = new HSSFWorkbook();
            HSSFSheet sheet = (HSSFSheet) wb2003.createSheet();
            HSSFRow row = sheet.createRow(0);
            row.createCell(0, CellType.STRING).setCellValue("Tên thiết bị");
            row.createCell(1, CellType.STRING).setCellValue("Môn học");
            row.createCell(2, CellType.STRING).setCellValue("Mã thiết bị");
            row.createCell(3, CellType.STRING).setCellValue("Trạng thái");
            row.createCell(4, CellType.STRING).setCellValue("Số lượng tồn kho");
            row.createCell(5, CellType.STRING).setCellValue("Số lượng ban đầu");
            int index = 1;
            int i = 0;
            while (i < DeviceDB.getListDevice().size()) {
                Device b = DeviceDB.getListDevice().get(i);
                row = sheet.createRow(index);
                row.createCell(0, CellType.STRING).setCellValue(b.getDeviceName());
                row.createCell(1, CellType.STRING).setCellValue(b.getSubjectName());
                row.createCell(2, CellType.STRING).setCellValue(b.getDevices().get(0).getCode());
                row.createCell(3, CellType.STRING).setCellValue(b.getDevices().get(0).getTypeName());
                row.createCell(4, CellType.NUMERIC).setCellValue(b.getQuantity());
                row.createCell(5, CellType.NUMERIC).setCellValue(b.getTotal());
                int m = index + 1;
                int k = b.getDevices().size();
                for (int j = 1; j < k; j++) {
                    row = sheet.createRow(m);
                    row.createCell(2, CellType.STRING).setCellValue(b.getDevices().get(j).getCode());
                    row.createCell(3, CellType.STRING).setCellValue(b.getDevices().get(j).getTypeName());
                    m++;
                }
                i++;
                index = m;
            }
            row = sheet.createRow(index);
            row.createCell(0, CellType.STRING).setCellValue("Tổng cộng" + DeviceDB.getListDevice().size());
            int total = 0;
            int sum = 0;
            for (Device b : DeviceDB.getListDevice()) {
                total = total + b.getTotal();
                sum = sum + b.getQuantity();
            }
            row.createCell(0, CellType.STRING).setCellValue("Tổng cộng: " + DeviceDB.getListDevice().size());
            row.createCell(4, CellType.STRING).setCellValue(sum);
            row.createCell(5, CellType.STRING).setCellValue(total);
            for (int n = 0; n < 6; n++) {
                sheet.autoSizeColumn(n);
            }
            OutPutFile.createOutputFile(wb2003, file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static ArrayList<Devices> newDevices(int n) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT TOP (?) deviceCode FROM DEVICES\n"
                    + "ORDER BY deviceCode DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, n);
            ResultSet rs = ps.executeQuery();
            ArrayList<Devices> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Devices(rs.getInt(1)));
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BooksBD.getListBook()");
            throw new RuntimeException("Không tìm thấy thiết bị này!");
        }
    }
    
    public static void statisticalDevices(ArrayList<Devices> list) {
        try {
            String file = "D:/workspace/java/DangDungLibrary/build/web/mathietbi.xls";
            HSSFWorkbook wb2003 = new HSSFWorkbook();
            HSSFSheet sheet = (HSSFSheet) wb2003.createSheet();
            HSSFRow row = sheet.createRow(0);
            int index = 0;
            int i = 0;
            while (i < list.size()) {
                Devices b = list.get(i);
                row = sheet.createRow(index);
                row.createCell(0, CellType.STRING).setCellValue(b.getCode());
                i++;
                index = index + 2;
            }
            for (int n = 0; n < 1; n++) {
                sheet.autoSizeColumn(n);
            }
            OutPutFile.createOutputFile(wb2003, file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
//        new Devices(1107).delete();
//        new Device(new Devices(1107).getDeviceID()).delete();
//System.out.println(new Devices(1016));
//        Device d = new Device(1015);
//        d.setQuantity(55);
//        d.update();
//        for (Device d : DeviceDB.getListDevice()) {
//            DeviceDB.addDevices(d.getDeviceID(), d.getQuantity());
//        }
        for (Devices d : newDevices(5)) {
            System.out.println(d.getCode());
        }
    }
}
