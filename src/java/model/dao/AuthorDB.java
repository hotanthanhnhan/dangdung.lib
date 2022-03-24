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
import model.entity.Author;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

/**
 *
 * @author ThanhNhan
 */
public class AuthorDB implements DBContext {

    public static ArrayList<Author> getListAuthor() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT authorID, authorName\n"
                    + "FROM AUTHOR\n"
                    + "ORDER BY authorName";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Author> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Author(rs.getInt(1), rs.getString(2)));
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println("Error at AuthorsDB.getListAuthor()");
        }
        return null;
    }

    public static ArrayList<Author> searchAuthor(String search) {
        ArrayList<Author> list = AuthorDB.getListAuthor();
        ArrayList<Author> result = new ArrayList<>();
        for (Author a : list) {
            if (a.getAuthorName().toUpperCase().contains(search.toUpperCase())) {
                result.add(a);
            }
        }
        return result;
    }

    public static int addAuthor(Author a) {
        int authorID = -1;
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO AUTHOR(authorName)\n"
                    + "OUTPUT inserted.authorID\n"
                    + "VALUES(?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, a.getAuthorName().trim());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                authorID = rs.getInt(1);
            }
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at AuthorsDB.addAuthor()");
        }
        return authorID;
    }

    public static int count(Author a) {
        int count = 0;
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT COUNT(authorID) FROM BOOKAUTHOR\n"
                    + "WHERE authorID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, a.getAuthorID());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at AuthorsDB.addAuthor()");
        }
        return count;
    }

    public static int counts(Author a) {
        int count = 0;
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT COUNT(authorID) FROM BOOKAUTHOR BA INNER JOIN BOOKS BS ON BA.bookID = BS.bookID\n"
                    + "WHERE authorID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, a.getAuthorID());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at AuthorsDB.addAuthor()");
        }
        return count;
    }

    public static void updateAuthor(Author a) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE AUTHOR\n"
                    + "SET authorName = ?\n"
                    + "WHERE authorID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, a.getAuthorName().trim());
            ps.setInt(2, a.getAuthorID());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at AuthorsDB.updateAuhor()");
        }
    }

    public static Author getAuthor(int authorID) {
        Author a = null;
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT authorID, authorName\n"
                    + "FROM AUTHOR\n"
                    + "WHERE authorID = ?\n";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, authorID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Author(rs.getInt(1), rs.getString(2));
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error at AuthorsDB.getAuthor()");
        }
        if (a == null) {
            throw new RuntimeException("Không tìm thấy tác giả!");
        }
        return a;
    }

    public static ArrayList<Author> getAuthors(int bookID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT A.authorID, A.authorName\n"
                    + "FROM AUTHOR A INNER JOIN BOOKAUTHOR BA ON A.authorID = BA.authorID\n"
                    + "INNER JOIN BOOK B ON B.bookID = BA.bookID\n"
                    + "WHERE B.bookID = ?\n"
                    + "ORDER BY authorName";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, bookID);
            ResultSet rs = ps.executeQuery();
            ArrayList<Author> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Author(rs.getInt(1), rs.getString(2)));
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AuthorsDB.getListAuthor()");
        }
        return null;
    }

    public static void statisticalAuthor() {
        try {
            String file = "D:/workspace/java/DangDungLibrary/build/web/tacgia.xls";
            HSSFWorkbook wb2003 = new HSSFWorkbook();
            HSSFSheet sheet = (HSSFSheet) wb2003.createSheet();
            HSSFRow row = sheet.createRow(0);
            row.createCell(0, CellType.STRING).setCellValue("Số thứ tự");
            row.createCell(1, CellType.STRING).setCellValue("Tên tác giả");
            row.createCell(2, CellType.STRING).setCellValue("Số lượng đầu sách");
            row.createCell(3, CellType.STRING).setCellValue("Số lượng sách trong kho");
            int index = 1;
            int i = 0;
            while (i < AuthorDB.getListAuthor().size()) {
                Author c = AuthorDB.getListAuthor().get(i);
                row = sheet.createRow(index);
                row.createCell(0, CellType.STRING).setCellValue(i + 1);
                row.createCell(1, CellType.STRING).setCellValue(c.getAuthorName());
                row.createCell(2, CellType.STRING).setCellValue(c.getCount());
                row.createCell(3, CellType.STRING).setCellValue(c.getCounts());
                i++;
                index++;
            }            
            for (int n = 0; n < 4; n++) {
                sheet.autoSizeColumn(n);
            }
            OutPutFile.createOutputFile(wb2003, file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        for (Author a : AuthorDB.getAuthors(100011)) {
            System.out.println(a);
        }
    }
}
