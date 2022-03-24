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
import model.entity.Book;
import model.entity.Category;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

/**
 *
 * @author ThanhNhan
 */
public class CategoryDB implements DBContext {

    public static ArrayList<Category> getListCategory() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT categoryID, categoryName\n"
                    + "FROM CATEGORY\n"
                    + "ORDER BY categoryName";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Category> list = new ArrayList<>();
            while (rs.next()) {
                Category c = new Category(rs.getString(1), rs.getString(2));
                list.add(c);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at CategoriesDB.getListCategory()");
        }
        return null;
    }

    public static String addCategory(Category c) {
        String categoryID = "";
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO CATEGORY(categoryID, categoryName)\n"
                    + "OUTPUT inserted.categoryID\n"
                    + "VALUES(?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, c.getCategoryID().toUpperCase().trim());
            ps.setString(2, c.getCategoryName().trim());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                categoryID = rs.getString(1);
            }
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at CategoriesDB.addCategory()");
            throw new RuntimeException("Mã thể loại đã tồn tại!");
        }
        return categoryID;
    }

    public static ArrayList<Category> searchCategory(String search) {
        ArrayList<Category> list = CategoryDB.getListCategory();
        ArrayList<Category> result = new ArrayList<>();
        for (Category c : list) {
            if (c.getCategoryName().toUpperCase().contains(search.toUpperCase())) {
                result.add(c);
            }
        }
        return result;
    }

    public static void updateCategory(Category c, String id) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE CATEGORY\n"
                    + "SET categoryID = ?, categoryName = ?\n"
                    + "WHERE categoryID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, c.getCategoryID().trim().toUpperCase());
            ps.setString(2, c.getCategoryName().trim());
            ps.setString(3, id);
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at CategoriesDB.updateCategory()");
            throw new RuntimeException("Mã thể loại đã tồn tại!");
        }
    }

    public static Category getCategory(String categoryID) {
        Category c = null;
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT categoryID, categoryName\n"
                    + "FROM CATEGORY\n"
                    + "WHERE categoryID = ?";;
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, categoryID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Category(rs.getString(1), rs.getString(2));
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at CategoriesDB.getCategory()");
        }
        if (c == null) {
            throw new RuntimeException("Không tìm thấy thể loại sách này!");
        }
        return c;
    }

    public static int count(Category a) {
        int count = 0;
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT COUNT(categoryID) FROM BOOK\n"
                    + "WHERE categoryID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, a.getCategoryID());
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

    public static int counts(Category a) {
        int count = 0;
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT COUNT(B.categoryID) FROM BOOKS BS INNER JOIN BOOK B ON BS.bookID = B.bookID\n"
                    + "WHERE B.categoryID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, a.getCategoryID());
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

    public static void statisticalCategory() {
        try {
            String file = "D:/workspace/java/DangDungLibrary/build/web/theloai.xls";
            HSSFWorkbook wb2003 = new HSSFWorkbook();
            HSSFSheet sheet = (HSSFSheet) wb2003.createSheet();
            HSSFRow row = sheet.createRow(0);
            row.createCell(0, CellType.STRING).setCellValue("Số thứ tự");
            row.createCell(1, CellType.STRING).setCellValue("Mã thể loại");
            row.createCell(2, CellType.STRING).setCellValue("Tên thể loại");
            row.createCell(3, CellType.STRING).setCellValue("Số lượng đầu sách");
            row.createCell(4, CellType.STRING).setCellValue("Số lượng sách trong kho");
            int index = 1;
            int i = 0;
            while (i < CategoryDB.getListCategory().size()) {
                Category c = CategoryDB.getListCategory().get(i);
                row = sheet.createRow(index);
                row.createCell(0, CellType.STRING).setCellValue(i + 1);
                row.createCell(1, CellType.STRING).setCellValue(c.getCategoryID());
                row.createCell(2, CellType.STRING).setCellValue(c.getCategoryName());
                row.createCell(3, CellType.STRING).setCellValue(c.getCount());
                row.createCell(4, CellType.STRING).setCellValue(c.getCounts());
                i++;
                index++;
                System.out.println(c);
            }
            row = sheet.createRow(index);
            row.createCell(2, CellType.STRING).setCellValue("Tổng cộng");
            int total = 0;
            for (Category c : CategoryDB.getListCategory()) {
                total = total + c.getCount();
            }
            row.createCell(3, CellType.STRING).setCellValue(total);
            int sum = 0;
            for (Category c : CategoryDB.getListCategory()) {
                sum = sum + c.getCounts();
            }
            row.createCell(3, CellType.STRING).setCellValue(total);
            row.createCell(4, CellType.STRING).setCellValue(sum);
            for (int n = 0; n < 5; n++) {
                sheet.autoSizeColumn(n);
            }
            OutPutFile.createOutputFile(wb2003, file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        CategoryDB.statisticalCategory();
    }
}
