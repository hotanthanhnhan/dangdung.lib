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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.DBConnect.DBContext;
import model.entity.Account;
import model.entity.Book;
import model.entity.BookReceipt;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

/**
 *
 * @author ThanhNhan
 */
public class BookReceiptDB implements DBContext {

    public static ArrayList<BookReceipt> getListBookReceipt() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT BR.bookReceiptID, BR.userID, A.fullName, A.userTypeID, T1.userTypeName, A.class, B.bookID, B.bookName, BS.bookCode, BR.typeID, T2.typeName, BR.borrowDate, BR.payDate FROM BOOKRECEIPT BR INNER JOIN ACCOUNT A ON BR.userID = A.userID\n"
                    + "INNER JOIN USERTYPE T1 ON T1.userTypeID = A.userTypeID\n"
                    + "INNER JOIN BOOKS BS ON BS.bookCode = BR.bookCode\n"
                    + "INNER JOIN BOOK B ON B.bookID = BS.bookID\n"
                    + "INNER JOIN TYPE T2 ON BR.typeID = T2.typeID\n"
                    + "ORDER BY BR.bookReceiptID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<BookReceipt> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date payDate = null;
                if (rs.getString(13) != null) {
                    payDate = (Date) f.parse(rs.getString(13));
                }
                BookReceipt a = new BookReceipt(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11), (Date) f.parse(rs.getString(12)), payDate);
                list.add(a);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BookReceiptDB.getListBookReceipt()");
        }
        return null;
    }

    public static ArrayList<BookReceipt> listBookReceipt(Account b) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT BR.bookReceiptID, BR.userID, A.fullName, A.userTypeID, T1.userTypeName, A.class, B.bookID, B.bookName, BS.bookCode, BR.typeID, T2.typeName, BR.borrowDate, BR.payDate FROM BOOKRECEIPT BR INNER JOIN ACCOUNT A ON BR.userID = A.userID\n"
                    + "INNER JOIN USERTYPE T1 ON T1.userTypeID = A.userTypeID\n"
                    + "INNER JOIN BOOKS BS ON BS.bookCode = BR.bookCode\n"
                    + "INNER JOIN BOOK B ON B.bookID = BS.bookID\n"
                    + "INNER JOIN TYPE T2 ON BR.typeID = T2.typeID\n"
                    + "WHERE BR.userID = ?\n"
                    + "ORDER BY BR.bookReceiptID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, b.getUserID());
            ResultSet rs = ps.executeQuery();
            ArrayList<BookReceipt> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date payDate = null;
                if (rs.getString(13) != null) {
                    payDate = (Date) f.parse(rs.getString(13));
                }
                BookReceipt a = new BookReceipt(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11), (Date) f.parse(rs.getString(12)), payDate);
                list.add(a);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BookReceiptDB.getListBookReceipt()");
        }
        return null;
    }
    
    public static ArrayList<BookReceipt> listBookReceiptGrade(int grade) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT BR.bookReceiptID, BR.userID, A.fullName, A.userTypeID, T1.userTypeName, A.class, B.bookID, B.bookName, BS.bookCode, BR.typeID, T2.typeName, BR.borrowDate, BR.payDate FROM BOOKRECEIPT BR INNER JOIN ACCOUNT A ON BR.userID = A.userID\n"
                    + "INNER JOIN USERTYPE T1 ON T1.userTypeID = A.userTypeID\n"
                    + "INNER JOIN BOOKS BS ON BS.bookCode = BR.bookCode\n"
                    + "INNER JOIN BOOK B ON B.bookID = BS.bookID\n"
                    + "INNER JOIN TYPE T2 ON BR.typeID = T2.typeID\n"
                    + "WHERE A.grade = ?\n"
                    + "ORDER BY BR.bookReceiptID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, grade);
            ResultSet rs = ps.executeQuery();
            ArrayList<BookReceipt> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date payDate = null;
                if (rs.getString(13) != null) {
                    payDate = (Date) f.parse(rs.getString(13));
                }
                BookReceipt a = new BookReceipt(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11), (Date) f.parse(rs.getString(12)), payDate);
                list.add(a);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BookReceiptDB.getListBookReceipt()");
        }
        return null;
    }
    
    public static ArrayList<BookReceipt> listBookReceiptAllStudent() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT BR.bookReceiptID, BR.userID, A.fullName, A.userTypeID, T1.userTypeName, A.class, B.bookID, B.bookName, BS.bookCode, BR.typeID, T2.typeName, BR.borrowDate, BR.payDate FROM BOOKRECEIPT BR INNER JOIN ACCOUNT A ON BR.userID = A.userID\n"
                    + "INNER JOIN USERTYPE T1 ON T1.userTypeID = A.userTypeID\n"
                    + "INNER JOIN BOOKS BS ON BS.bookCode = BR.bookCode\n"
                    + "INNER JOIN BOOK B ON B.bookID = BS.bookID\n"
                    + "INNER JOIN TYPE T2 ON BR.typeID = T2.typeID\n"
                    + "WHERE T1.userTypeID = 3\n"
                    + "ORDER BY BR.bookReceiptID DESC";
            PreparedStatement ps = conn.prepareStatement(query);            
            ResultSet rs = ps.executeQuery();
            ArrayList<BookReceipt> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date payDate = null;
                if (rs.getString(13) != null) {
                    payDate = (Date) f.parse(rs.getString(13));
                }
                BookReceipt a = new BookReceipt(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11), (Date) f.parse(rs.getString(12)), payDate);
                list.add(a);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BookReceiptDB.getListBookReceipt()");
        }
        return null;
    }
    
    public static ArrayList<BookReceipt> listBookReceiptAllTeacher() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT BR.bookReceiptID, BR.userID, A.fullName, A.userTypeID, T1.userTypeName, A.class, B.bookID, B.bookName, BS.bookCode, BR.typeID, T2.typeName, BR.borrowDate, BR.payDate FROM BOOKRECEIPT BR INNER JOIN ACCOUNT A ON BR.userID = A.userID\n"
                    + "INNER JOIN USERTYPE T1 ON T1.userTypeID = A.userTypeID\n"
                    + "INNER JOIN BOOKS BS ON BS.bookCode = BR.bookCode\n"
                    + "INNER JOIN BOOK B ON B.bookID = BS.bookID\n"
                    + "INNER JOIN TYPE T2 ON BR.typeID = T2.typeID\n"
                    + "WHERE T1.userTypeID != 3\n"
                    + "ORDER BY BR.bookReceiptID DESC";
            PreparedStatement ps = conn.prepareStatement(query);            
            ResultSet rs = ps.executeQuery();
            ArrayList<BookReceipt> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date payDate = null;
                if (rs.getString(13) != null) {
                    payDate = (Date) f.parse(rs.getString(13));
                }
                BookReceipt a = new BookReceipt(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11), (Date) f.parse(rs.getString(12)), payDate);
                list.add(a);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BookReceiptDB.getListBookReceipt()");
        }
        return null;
    }

    public static BookReceipt getBookReceipt(int BookReceiptID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT BR.bookReceiptID, BR.userID, A.fullName, A.userTypeID, T1.userTypeName, A.class, B.bookID, B.bookName, BS.bookCode, BR.typeID, T2.typeName, BR.borrowDate, BR.payDate FROM BOOKRECEIPT BR INNER JOIN ACCOUNT A ON BR.userID = A.userID\n"
                    + "INNER JOIN USERTYPE T1 ON T1.userTypeID = A.userTypeID\n"
                    + "INNER JOIN BOOKS BS ON BS.bookCode = BR.bookCode\n"
                    + "INNER JOIN BOOK B ON B.bookID = BS.bookID\n"
                    + "INNER JOIN TYPE T2 ON BR.typeID = T2.typeID\n"
                    + "WHERE BR.bookReceiptID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, BookReceiptID);
            ResultSet rs = ps.executeQuery();
            BookReceipt a = null;
            if (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date payDate = null;
                if (rs.getString(13) != null) {
                    payDate = (Date) f.parse(rs.getString(13));
                }
                a = new BookReceipt(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11), (Date) f.parse(rs.getString(12)), payDate);
            }
            conn.close();
            return a;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BookReceiptDB.getListBookReceipt()");
            throw new RuntimeException("Không tìm thấy đơn này!");
        }
    }

    public static int add(BookReceipt b) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO BOOKRECEIPT(userID, bookCode)\n"
                    + "OUTPUT inserted.bookReceiptID\n"
                    + "VALUES(?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, b.getUserID());
            ps.setInt(2, b.getBookCode());
            ResultSet rs = ps.executeQuery();
            int bookReceiptID = -1;
            if (rs.next()) {
                bookReceiptID = rs.getInt(1);
            }
            conn.close();
            return bookReceiptID;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BookReceiptDB.getListBookReceipt()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void back(BookReceipt b) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE BOOKRECEIPT\n"
                    + "SET typeID = 5, payDate = GETDATE()\n"
                    + "WHERE bookReceiptID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, b.getBookReceiptID());
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BookReceiptDB.getListBookReceipt()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static String getBorrower(int bookCode) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT userID from BOOKRECEIPT\n"
                    + "WHERE bookCode = ? AND typeID = 4";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, bookCode);
            ResultSet rs = ps.executeQuery();
            String borrower = "";
            if (rs.next()) {
                borrower = rs.getString(1);
            }
            conn.close();
            return borrower;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BookReceiptDB.getListBookReceipt()");
            throw new RuntimeException("Không tìm thấy đơn này!");
        }
    }
    
    public static void statistical(ArrayList<BookReceipt> list) {
        try {
            String file = "D:/workspace/java/DangDungLibrary/build/web/danhsachmuonsach.xls";
            HSSFWorkbook wb2003 = new HSSFWorkbook();
            HSSFSheet sheet = (HSSFSheet) wb2003.createSheet();
            HSSFRow row = sheet.createRow(0);                        
            row.createCell(0, CellType.STRING).setCellValue("Họ và tên");
            row.createCell(1, CellType.STRING).setCellValue("Sách mượn");
            row.createCell(2, CellType.STRING).setCellValue("Mã sách");
            row.createCell(3, CellType.STRING).setCellValue("Trạng thái");
            row.createCell(4, CellType.STRING).setCellValue("Ngày mượn");
            row.createCell(5, CellType.STRING).setCellValue("Ngày trả");            
            int index = 1;
            int i = 0;
            while (i < list.size()) {
                BookReceipt b = list.get(i);
                row = sheet.createRow(index);
                row.createCell(0, CellType.STRING).setCellValue(b.getFullName());
                row.createCell(1, CellType.STRING).setCellValue(b.getBookName());
                row.createCell(2, CellType.STRING).setCellValue(b.getCode());
                row.createCell(3, CellType.STRING).setCellValue(b.getStatus());
                row.createCell(4, CellType.STRING).setCellValue(b.getBorrowDate());
                row.createCell(5, CellType.STRING).setCellValue(b.getPayDate());               
                i++;
                index++;
            }
            row = sheet.createRow(index);
            row.createCell(1, CellType.STRING).setCellValue("Tổng cộng:");
            row.createCell(2, CellType.STRING).setCellValue(list.size());            
            for (int n = 0; n < 6; n++) {
                sheet.autoSizeColumn(n);
            }
            OutPutFile.createOutputFile(wb2003, file);
        } catch (Exception e) {
            System.out.println(e);
        }
    } 

    public static void main(String[] args) throws ParseException {
        
    }
}

