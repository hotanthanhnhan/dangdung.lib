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
import model.entity.Subject;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

/**
 *
 * @Author ThanhNhan
 */
public class SubjectDB implements DBContext {

    public static ArrayList<Subject> getListSubject() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT subjectID, subjectName FROM SUBJECT";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Subject> list = new ArrayList<>();
            while (rs.next()) {
                Subject c = new Subject(rs.getInt(1), rs.getString(2));
                list.add(c);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at SubjectDB.getListSubject()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static Subject getSubject(int subjectID) {
        Subject s = null;
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT subjectID, subjectName FROM SUBJECT WHERE subjectID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, subjectID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s = new Subject(rs.getInt(1), rs.getString(2));
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at SubjectDB.getListSubject()");
        }
        if (s == null) {
            throw new RuntimeException("Không tìm thấy môn học này!");
        }
        return s;
    }

    public static int count(Subject a) {
        int count = 0;
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT COUNT(subjectID) FROM BOOK\n"
                    + "WHERE subjectID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, a.getSubjectID());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at SubjectsDB.addSubject()");
        }
        return count;
    }

    public static int counts(Subject a) {
        int count = 0;
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT COUNT(subjectID) FROM BOOKS BS INNER JOIN BOOK B ON B.bookID = BS.bookID\n"
                    + "WHERE subjectID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, a.getSubjectID());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at SubjectsDB.addSubject()");
        }
        return count;
    }

    public static void statisticalSubject() {
        try {
            String file = "D:/workspace/java/DangDungLibrary/build/web/monhoc.xls";
            HSSFWorkbook wb2003 = new HSSFWorkbook();
            HSSFSheet sheet = (HSSFSheet) wb2003.createSheet();
            HSSFRow row = sheet.createRow(0);
            row.createCell(0, CellType.STRING).setCellValue("Số thứ tự");
            row.createCell(1, CellType.STRING).setCellValue("Tên môn học");
            row.createCell(2, CellType.STRING).setCellValue("Số lượng sách");
            int index = 1;
            int i = 0;
            while (i < SubjectDB.getListSubject().size()) {
                Subject c = SubjectDB.getListSubject().get(i);
                row = sheet.createRow(index);
                row.createCell(0, CellType.STRING).setCellValue(i + 1);
                row.createCell(1, CellType.STRING).setCellValue(c.getSubjectName());
                row.createCell(2, CellType.STRING).setCellValue(c.getCount());
                i++;
                index++;
            }
            row = sheet.createRow(index);
            row.createCell(1, CellType.STRING).setCellValue("Tổng cộng");
            int total = 0;
            for (Subject c : SubjectDB.getListSubject()) {
                total = total + c.getCount();
            }
            row.createCell(2, CellType.STRING).setCellValue(total);
            for (int n = 0; n < 3; n++) {
                sheet.autoSizeColumn(n);
            }
            OutPutFile.createOutputFile(wb2003, file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
//        for(Subject s : SubjectDB.getListSubject()){
//            System.out.println(s);
//        }

        System.out.println(new Subject(1000));
    }
}
