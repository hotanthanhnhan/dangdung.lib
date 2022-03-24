/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.sun.rowset.internal.Row;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import model.DBConnect.DBContext;
import model.entity.Account;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author ThanhNhan
 */
public class AccountDB implements DBContext {

    public static ArrayList<Account> getListAccount() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT A.userID, A.fullName, A.password, A.email, A.userTypeID, U.userTypeName, A.grade, A.class, A.lastAccess FROM ACCOUNT A INNER JOIN USERTYPE U ON A.userTypeID = U.userTypeID";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Account> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Account a = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getString(8), (Date) f.parse(rs.getString(9)));
                list.add(a);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.getListAccount()");
        }
        return null;
    }

    public static Account getAccount(String userID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT A.userID, A.fullName, A.password, A.email, A.userTypeID, U.userTypeName, A.grade, A.class, A.lastAccess FROM ACCOUNT A INNER JOIN USERTYPE U ON A.userTypeID = U.userTypeID\n"
                    + "WHERE userID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Account a = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getString(8), (Date) f.parse(rs.getString(9)));
                return a;
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.getAccount()");
        }
        throw new RuntimeException("Tài khoản không tồn tại!");
    }

    public static void doChangePass(Account a) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE ACCOUNT\n"
                    + "SET password = ?\n"
                    + "WHERE userID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, a.getPassword());
            ps.setString(2, a.getUserID());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at AccountDB.doChangePass()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void update(Account a) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE ACCOUNT\n"
                    + "SET email = ?\n"
                    + "WHERE userID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, a.getEmail());
            ps.setString(2, a.getUserID());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at AccountDB.update()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void change(Account a) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE ACCOUNT\n"
                    + "SET fullName = ?, email = ?, grade = ?, class = ?\n"
                    + "WHERE userID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, a.getFullName());
            ps.setString(2, a.getEmail());
            ps.setInt(3, a.getGrade());
            ps.setString(4, a.getClas());
            ps.setString(5, a.getUserID());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at AccountDB.update()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void login(Account a) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE ACCOUNT\n"
                    + "SET lastAccess = CURRENT_TIMESTAMP\n"
                    + "WHERE userID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, a.getUserID());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at AccountDB.update()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void remove(Account a) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "DELETE ACCOUNT\n"
                    + "WHERE userID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, a.getUserID());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at AccountDB.update()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void reset(Account a) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE ACCOUNT\n"
                    + "SET password = ?\n"
                    + "WHERE userID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3");
            ps.setString(2, a.getUserID());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at AccountDB.update()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static boolean createAccount(Account a) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO ACCOUNT(userID, fullName, password, email, userTypeID, grade, class)\n"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, a.getUserID().trim());
            ps.setString(2, a.getFullName());
            ps.setString(3, "A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3");
            ps.setString(4, a.getEmail());
            ps.setInt(5, a.getUserTypeID());
            ps.setInt(6, a.getGrade());
            ps.setString(7, a.getClas());
            ps.executeUpdate();
            conn.commit();
            conn.close();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.createAccount()");
        }
        return false;
    }

    public static void doCreateTeacher(String file) {
        try {
            File f = new File(file);
            InputStream is = new FileInputStream(f);
            HSSFWorkbook wb2003 = new HSSFWorkbook(is);
            HSSFSheet sheet = (HSSFSheet) wb2003.getSheetAt(0);
            int i = 1;
            while (true) {
                Account a = new Account();
                a.setUserTypeID(2);
                HSSFRow row = sheet.getRow(i);
                if (row.getCell(0) == null || row.getCell(0).getStringCellValue().trim().equals("")) {
                    return;
                }
                a.setUserID(row.getCell(0).getStringCellValue());
                a.setFullName(row.getCell(1).getStringCellValue());
                if (row.getCell(2) != null) {
                    a.setEmail(row.getCell(2).getStringCellValue());
                }
                i++;
                AccountDB.createAccount(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void doCreateStudent(String file) {
        try {
            File f = new File(file);
            InputStream is = new FileInputStream(f);
            HSSFWorkbook wb2003 = new HSSFWorkbook(is);
            HSSFSheet sheet = (HSSFSheet) wb2003.getSheetAt(0);
            int i = 1;
            while (true) {
                Account a = new Account();
                a.setUserTypeID(3);
                HSSFRow row = sheet.getRow(i);
                if (row.getCell(0) == null) {
                    return;
                }
                a.setUserID(String.valueOf((int) row.getCell(0).getNumericCellValue()));
                a.setFullName(row.getCell(1).getStringCellValue());
                if (row.getCell(2) != null) {
                    a.setEmail(row.getCell(2).getStringCellValue());
                }

                a.setGrade((int) row.getCell(3).getNumericCellValue());
                a.setClas(row.getCell(4).getStringCellValue());
                i++;
                AccountDB.createAccount(a);
//System.out.println(a);
            }
//System.out.println(sheet.getRow(0).getCell(0));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        
    }
}
