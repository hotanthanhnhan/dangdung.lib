/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.function.Predicate;
import javafx.scene.control.Cell;
import model.DBConnect.DBContext;
import model.entity.Author;
import model.entity.Book;
import model.entity.Books;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author ThanhNhan
 */
public class BookDB implements DBContext {

    public static ArrayList<Book> getListBook() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT B.bookID, B.bookName,\n"
                    + "B.categoryID, C.categoryName, B.subjectID, S.subjectName, B.content, B.bookImg, B.quantity, B.viewCounter, B.pdfLink\n"
                    + "FROM BOOK B INNER JOIN CATEGORY C ON B.categoryID = C.categoryID\n"
                    + "INNER JOIN SUBJECT S ON B.subjectID = S.subjectID\n"
                    + "ORDER BY B.bookID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Book> list = new ArrayList<>();
            while (rs.next()) {
                Book b = new Book(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11));
                list.add(b);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BooksBD.getListBook()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static ArrayList<Book> getListNewBook() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT TOP 10 B.bookID, B.bookName,\n"
                    + "B.categoryID, C.categoryName, B.subjectID, S.subjectName, B.content, B.bookImg, B.quantity, B.viewCounter, B.pdfLink\n"
                    + "FROM BOOK B INNER JOIN CATEGORY C ON B.categoryID = C.categoryID\n"
                    + "INNER JOIN SUBJECT S ON B.subjectID = S.subjectID\n"
                    + "ORDER BY B.bookID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Book> list = new ArrayList<>();
            while (rs.next()) {
                Book b = new Book(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11));
                list.add(b);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BooksBD.getListNEWBook()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static ArrayList<Book> topBook() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT TOP 10 B.bookID, B.bookName,\n"
                    + "B.categoryID, C.categoryName, B.subjectID, S.subjectName, B.content, B.bookImg, B.quantity, B.viewCounter, B.pdfLink\n"
                    + "FROM BOOK B INNER JOIN CATEGORY C ON B.categoryID = C.categoryID\n"
                    + "INNER JOIN SUBJECT S ON B.subjectID = S.subjectID\n"
                    + "ORDER BY B.viewCounter DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Book> list = new ArrayList<>();
            while (rs.next()) {
                Book b = new Book(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11));
                list.add(b);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BooksBD.topBook()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static ArrayList<Book> authorBook(int authorID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT B.bookID, B.bookName,\n"
                    + "B.categoryID, C.categoryName, B.subjectID, S.subjectName, B.content, B.bookImg, B.quantity, B.viewCounter, B.pdfLink\n"
                    + "FROM BOOK B INNER JOIN BOOKAUTHOR BA ON BA.bookID = B.bookID\n"
                    + "INNER JOIN CATEGORY C ON B.categoryID = C.categoryID\n"
                    + "INNER JOIN SUBJECT S ON B.subjectID = S.subjectID\n"
                    + "WHERE BA.authorID = ?\n"
                    + "ORDER BY bookID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, authorID);
            ResultSet rs = ps.executeQuery();
            ArrayList<Book> list = new ArrayList<>();
            while (rs.next()) {
                Book b = new Book(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11));
                list.add(b);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BooksBD.authorBook()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static ArrayList<Book> categoryBook(String categoryID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT B.bookID, B.bookName,\n"
                    + "B.categoryID, C.categoryName, B.subjectID, S.subjectName, B.content, B.bookImg, B.quantity, B.viewCounter, B.pdfLink\n"
                    + "FROM BOOK B INNER JOIN CATEGORY C ON B.categoryID = C.categoryID\n"
                    + "INNER JOIN SUBJECT S ON B.subjectID = S.subjectID\n"
                    + "WHERE B.categoryID = ?\n"
                    + "ORDER BY bookID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, categoryID);
            ResultSet rs = ps.executeQuery();
            ArrayList<Book> list = new ArrayList<>();
            while (rs.next()) {
                Book b = new Book(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11));
                list.add(b);
            }
            conn.close();
            if (list.isEmpty()) {
                throw new RuntimeException("Không tìm thấy thể loại sách này!");
            }
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BooksBD.categoryBook()");
            throw new RuntimeException("Không tìm thấy thể loại sách này!");
        }

    }

    public static ArrayList<Book> subjectBook(int subjectID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT B.bookID, B.bookName,\n"
                    + "B.categoryID, C.categoryName, B.subjectID, S.subjectName, B.content, B.bookImg, B.quantity, B.viewCounter, B.pdfLink\n"
                    + "FROM BOOK B INNER JOIN CATEGORY C ON B.categoryID = C.categoryID\n"
                    + "INNER JOIN SUBJECT S ON B.subjectID = S.subjectID\n"
                    + "WHERE S.subjectID = ?\n"
                    + "ORDER BY bookID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, subjectID);
            ResultSet rs = ps.executeQuery();
            ArrayList<Book> list = new ArrayList<>();
            while (rs.next()) {
                Book b = new Book(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11));
                list.add(b);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BooksBD.categoryBook()");
            throw new RuntimeException("Không tìm thấy thể loại sách này!");
        }
    }

    public static Book getBook(int bookID) {
        Book b = null;
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT B.bookID, B.bookName,\n"
                    + "B.categoryID, C.categoryName, B.subjectID, S.subjectName, B.content, B.bookImg, B.quantity, B.viewCounter, B.pdfLink\n"
                    + "FROM BOOK B INNER JOIN CATEGORY C ON B.categoryID = C.categoryID\n"
                    + "INNER JOIN SUBJECT S ON B.subjectID = S.subjectID\n"
                    + "WHERE bookID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, bookID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                b = new Book(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11));
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BooksBD.getBook()");
            throw new RuntimeException("Không tìm thấy sách này!");
        }
        if (b == null) {
            throw new RuntimeException("Không tìm thấy sách này!");
        }
        return b;
    }

    public static void upViewCounter(Book b) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE BOOK\n"
                    + "SET viewCounter = ?\n"
                    + "WHERE bookID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, b.getViewCounter());
            ps.setInt(2, b.getBookID());
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BooksBD.upViewCounter()");
        }
    }

    public static void update(Book b) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE BOOK\n"
                    + "SET bookName = ?, categoryID = ?, subjectID = ?, bookImg = ?, content = ?, quantity = ?, pdfLink = ?\n"
                    + "WHERE bookID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, b.getBookName());
            ps.setString(2, b.getCategoryID());
            ps.setInt(3, b.getSubjectID());
            ps.setString(4, b.getBookImg());
            ps.setString(5, b.getContent());
            ps.setInt(6, b.getQuantity());
            ps.setString(7, b.getPdfLink());
            ps.setInt(8, b.getBookID());
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BooksBD.update()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void deleteAuthorBook(Book b) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "DELETE BOOKAUTHOR\n"
                    + "WHERE bookID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, b.getBookID());
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BooksBD.update()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static ArrayList<Book> search(Predicate<Book> s) {
        ArrayList<Book> list = BookDB.getListBook();
        ArrayList<Book> result = new ArrayList<>();
        for (Book b : list) {
            if (s.test(b)) {
                result.add(b);
            }
        }
        return result;
    }

    public static int addBook(Book b) {
        int bookID = -1;
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO BOOK(bookName, categoryID, subjectID, content, bookImg, quantity, pdfLink) OUTPUT inserted.bookID\n"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, b.getBookName().trim());
            ps.setString(2, b.getCategoryID());
            ps.setInt(3, b.getSubjectID());
            ps.setString(4, b.getContent());
            ps.setString(5, b.getBookImg());
            ps.setInt(6, b.getQuantity());
            ps.setString(7, b.getPdfLink());
            ResultSet rs = ps.executeQuery();
            bookID = -1;
            if (rs.next()) {
                bookID = rs.getInt(1);
            }
            conn.commit();
            conn.close();
            BookDB.addBooks(bookID, b.getQuantity());
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at BooksBD.addBook()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
        return bookID;
    }

    public static int total(Book b) {
        int total = 0;
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT COUNT(bookID) FROM BOOKS\n"
                    + "WHERE bookID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, b.getBookID());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at BooksBD.addBook()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
        return total;
    }

    public static void addBooks(int bookID, int n) {
        for (int i = 0; i < n; i++) {
            BookDB.books(bookID);
        }
    }

    private static void books(int bookID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO BOOKS(bookID)\n"
                    + "VALUES(?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, bookID);
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at BooksBD.addBooks()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void borrow(Book b, int bookCode) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE BOOK\n"
                    + "SET quantity = ?\n"
                    + "WHERE bookID = ?\n"
                    + "UPDATE BOOKS\n"
                    + "SET typeID = 1\n"
                    + "WHERE bookCode = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, b.getQuantity());
            ps.setInt(2, b.getBookID());
            ps.setInt(3, bookCode);
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BooksBD.update()");
            throw new RuntimeException("Không đủ sách trong kho!");
        }
    }

    public static void back(Book b, int bookCode) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE BOOK\n"
                    + "SET quantity = ?\n"
                    + "WHERE bookID = ?\n"
                    + "UPDATE BOOKS\n"
                    + "SET typeID = 0\n"
                    + "WHERE bookCode = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, b.getQuantity());
            ps.setInt(2, b.getBookID());
            ps.setInt(3, bookCode);
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BooksBD.update()");
            throw new RuntimeException("Không đủ sách trong kho!");
        }
    }

    public static ArrayList<Books> getListBooks() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT B.bookID, BS.bookCode, B.bookName, B.categoryID, C.categoryName, B.subjectID, S.subjectName, BS.typeID, T.typeName FROM BOOKS BS INNER JOIN BOOK B ON BS.bookID = B.bookID\n"
                    + "INNER JOIN TYPE T ON BS.typeID = T.typeID\n"
                    + "INNER JOIN CATEGORY C ON B.categoryID = C.categoryID\n"
                    + "INNER JOIN SUBJECT S ON B.subjectID = S.subjectID\n"
                    + "ORDER BY B.bookID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Books> list = new ArrayList<>();
            while (rs.next()) {
                Books b = new Books(rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6),
                        rs.getString(7), rs.getInt(8), rs.getString(9));
                list.add(b);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BooksBD.getListBook()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static ArrayList<Books> listBooks(int bookID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT B.bookID, BS.bookCode, B.bookName, B.categoryID, C.categoryName, B.subjectID, S.subjectName, BS.typeID, T.typeName FROM BOOKS BS INNER JOIN BOOK B ON BS.bookID = B.bookID\n"
                    + "INNER JOIN TYPE T ON BS.typeID = T.typeID\n"
                    + "INNER JOIN CATEGORY C ON B.categoryID = C.categoryID\n"
                    + "INNER JOIN SUBJECT S ON B.subjectID = S.subjectID\n"
                    + "WHERE B.bookID = ?\n"
                    + "ORDER BY B.bookID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, bookID);
            ResultSet rs = ps.executeQuery();
            ArrayList<Books> list = new ArrayList<>();
            while (rs.next()) {
                Books b = new Books(rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6),
                        rs.getString(7), rs.getInt(8), rs.getString(9));
                list.add(b);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BooksBD.getListBook()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static Books getBooks(int bookCode) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT B.bookID, BS.bookCode, B.bookName, B.categoryID, C.categoryName, B.subjectID, S.subjectName, BS.typeID, T.typeName FROM BOOKS BS INNER JOIN BOOK B ON BS.bookID = B.bookID\n"
                    + "INNER JOIN TYPE T ON BS.typeID = T.typeID\n"
                    + "INNER JOIN CATEGORY C ON B.categoryID = C.categoryID\n"
                    + "INNER JOIN SUBJECT S ON B.subjectID = S.subjectID\n"
                    + "WHERE BS.bookCode = ?\n"
                    + "ORDER BY B.bookID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, bookCode);
            ResultSet rs = ps.executeQuery();
            Books b = null;
            if (rs.next()) {
                b = new Books(rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6),
                        rs.getString(7), rs.getInt(8), rs.getString(9));
            }
            conn.close();
            return b;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BooksBD.getListBook()");
            throw new RuntimeException("Không tìm thấy sách này!");
        }
    }

    public static void delete(Books b) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "DELETE BOOKS\n"
                    + "WHERE bookCode = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, b.getBookCode());
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DevicesBD.update()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void deleteAll(Book b) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "DELETE BOOK\n"
                    + "WHERE bookID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, b.getBookID());
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DevicesBD.update()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void borrows(Books b) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE BOOKS\n"
                    + "SET typeID = 1\n"
                    + "WHERE bookCode = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, b.getBookCode());
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at DevicesBD.update()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void addAuthorBook(Book b, Author a) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO BOOKAUTHOR(bookID, authorID)\n"
                    + "VALUES(?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, b.getBookID());
            ps.setInt(2, a.getAuthorID());
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at BooksBD.addBook()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void statisticalBook() {
        try {
            String file = "D:/workspace/java/DangDungLibrary/build/web/sach.xls";
            HSSFWorkbook wb2003 = new HSSFWorkbook();
            HSSFSheet sheet = (HSSFSheet) wb2003.createSheet();
            HSSFRow row = sheet.createRow(0);
            row.createCell(0, CellType.STRING).setCellValue("Tên sách");
            row.createCell(1, CellType.STRING).setCellValue("Tác giả");
            row.createCell(2, CellType.STRING).setCellValue("Thể loại");
            row.createCell(3, CellType.STRING).setCellValue("Môn học");
            row.createCell(4, CellType.STRING).setCellValue("Mã sách");
            row.createCell(5, CellType.STRING).setCellValue("Trạng thái");
            row.createCell(6, CellType.STRING).setCellValue("Số lượng tồn kho");
            row.createCell(7, CellType.STRING).setCellValue("Số lượng ban đầu");
            int index = 1;
            int i = 0;
            while (i < BookDB.getListBook().size()) {
                Book b = BookDB.getListBook().get(i);
                row = sheet.createRow(index);
                row.createCell(0, CellType.STRING).setCellValue(b.getBookName());
                row.createCell(1, CellType.STRING).setCellValue(b.getAuthors().get(0).getAuthorName());
                row.createCell(2, CellType.STRING).setCellValue(b.getCategoryName());
                row.createCell(3, CellType.STRING).setCellValue(b.getSubjectName());
                row.createCell(4, CellType.STRING).setCellValue(b.getBooks().get(0).getCode());
                row.createCell(5, CellType.STRING).setCellValue(b.getBooks().get(0).getTypeName());
                row.createCell(6, CellType.NUMERIC).setCellValue(b.getQuantity());
                row.createCell(7, CellType.NUMERIC).setCellValue(b.getTotal());
                System.out.println(b.getBookName());
                int m = index + 1;
                int k = b.getBooks().size();
                if (b.getAuthors().size() > b.getBooks().size()) {
                    k = b.getAuthors().size();
                }
                for (int j = 1; j < k; j++) {
                    row = sheet.createRow(m);
                    if (j < b.getAuthors().size()) {
                        row.createCell(1, CellType.STRING).setCellValue(b.getAuthors().get(j).getAuthorName());
                    }
                    if (j < b.getBooks().size()) {
                        row.createCell(4, CellType.STRING).setCellValue(b.getBooks().get(j).getCode());
                        row.createCell(5, CellType.STRING).setCellValue(b.getBooks().get(j).getTypeName());
                    }
                    m++;
                }
                i++;
                index = m;
            }
            row = sheet.createRow(index);
            row.createCell(0, CellType.STRING).setCellValue("Tổng cộng" + BookDB.getListBook().size());
            int total = 0;
            int sum = 0;
            for (Book b : BookDB.getListBook()) {
                total = total + b.getTotal();
                sum = sum + b.getQuantity();
            }
            row.createCell(0, CellType.STRING).setCellValue("Tổng cộng: " + BookDB.getListBook().size());
            row.createCell(6, CellType.STRING).setCellValue(sum);
            row.createCell(7, CellType.STRING).setCellValue(total);
            for (int n = 0; n < 8; n++) {
                sheet.autoSizeColumn(n);
            }
            OutPutFile.createOutputFile(wb2003, file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void statisticalBooks(ArrayList<Books> list) {
        try {
            String file = "D:/workspace/java/DangDungLibrary/build/web/masach.xls";
            HSSFWorkbook wb2003 = new HSSFWorkbook();
            HSSFSheet sheet = (HSSFSheet) wb2003.createSheet();
            HSSFRow row = sheet.createRow(0);
            int index = 0;
            int i = 0;
            while (i < list.size()) {
                Books b = list.get(i);
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

    public static ArrayList<Books> newBooks(int n) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT TOP (?) bookCode FROM BOOKS\n"
                    + "ORDER BY bookCode DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, n);
            ResultSet rs = ps.executeQuery();
            ArrayList<Books> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Books(rs.getInt(1)));
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at BooksBD.getListBook()");
            throw new RuntimeException("Không tìm thấy sách này!");
        }
    }

    public static void main(String[] args) {
        BookDB.statisticalBook();
    }

}
