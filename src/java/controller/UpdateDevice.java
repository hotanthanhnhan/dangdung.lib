/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.dao.DeviceDB;
import model.dao.SubjectDB;
import model.entity.Account;
import model.entity.Device;

/**
 *
 * @author ThanhNhan
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 200,
        maxFileSize = 1024 * 1024 * 200,
        maxRequestSize = 1024 * 1024 * 200)
public class UpdateDevice extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateDevice</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateDevice at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Account a = (Account) request.getSession().getAttribute("acc");
            if (a != null) {
                int type = a.getUserTypeID();
                switch (type) {
                    case 3:
                    case 2:
                    case 0:
                        response.sendRedirect("library");
                        break;
                    case 1:
                        String id = request.getParameter("id");
                        if (id == null || id.trim().equals("")) {
                            response.sendRedirect("listdevice");
                        } else {
                            int deviceID = -1;
                            try {
                                deviceID = Integer.parseInt(id);
                            } catch (Exception e) {
                                throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
                            }
                            request.setAttribute("d", new Device(deviceID));
                            request.setAttribute("su", SubjectDB.getListSubject());
                            request.getRequestDispatcher("UpdateDevice.jsp").include(request, response);
                        }
                        break;
                }
            } else {
                response.sendRedirect("login");
            }
        } catch (IOException | ServletException e) {
            response.sendRedirect("login");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        int deviceID = -1;
        try {
            deviceID = Integer.parseInt(request.getParameter("deviceID"));
        } catch (Exception e) {
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
        Device d = new Device(deviceID);
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String deviceName = request.getParameter("deviceName");
        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        if (quantity < d.getTotal()) {
            throw new RuntimeException("Vui lòng nhập số lượng ít nhất bằng tổng số lượng cũ, hoặc vào tất cả thiết bị trong kho để xóa!");
        } else {
            Random rd = new Random();
            int n = rd.nextInt();
            if(n < 0) n = -n;
            Part img = request.getPart("deviceImg");
            String realPath = "D:\\workspace\\java\\DangDungLibrary\\web\\assets\\covers\\";
            String fileName = Paths.get(img.getSubmittedFileName()).getFileName().toString();
            if (fileName != null && !fileName.trim().equals("")) {
                img.write(realPath + n + fileName);
                img.write(n + fileName);
                realPath = request.getServletContext().getRealPath("./assets/covers/");
                img.write(realPath + n + fileName);
                String deviceImg = "./assets/covers/" + n + fileName;
                new Device(deviceID, deviceName, subjectID, deviceImg, d.getQuantity() + quantity - d.getTotal()).update();
                DeviceDB.addDevices(deviceID, quantity - d.getTotal());
            } else{
                new Device(deviceID, deviceName, subjectID, d.getDeviceImg(), d.getQuantity() + quantity - d.getTotal()).update();
                DeviceDB.addDevices(deviceID, quantity - d.getTotal());
            }
        }
        response.sendRedirect("listdevice");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
