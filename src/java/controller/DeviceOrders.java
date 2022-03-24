/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.DeviceOrderDB;
import model.dao.MessageDB;
import model.entity.Account;
import model.entity.DeviceOrder;
import model.entity.DeviceReceipt;
import model.entity.Devices;
import model.entity.Message;

/**
 *
 * @author ThanhNhan
 */
public class DeviceOrders extends HttpServlet {

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
            out.println("<title>Servlet DeviceOrders</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeviceOrders at " + request.getContextPath() + "</h1>");
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
                        response.setContentType("text/html;charset=UTF-8");
                        request.setAttribute("list", DeviceOrderDB.getListDeviceOrder());
                        request.getRequestDispatcher("DeviceOrder.jsp").include(request, response);
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
        String type = request.getParameter("type");
        int deviceOrderID = Integer.parseInt(request.getParameter("o"));
        DeviceOrder b = new DeviceOrder(deviceOrderID);
        switch (type) {
            case "1":
                PrintWriter out = response.getWriter();
                int[] deviceCode = new int[b.getQuantity()];
                for (int i = 0; i < b.getQuantity(); i++) {
                    deviceCode[i] = Integer.parseInt(request.getParameter("deviceCode" + (i + 1)));
                }
                boolean check = true;
                for (int i = 0; i < b.getQuantity(); i++) {
                    for (int j = i + 1; j < b.getQuantity(); j++) {
                        if (deviceCode[i] == deviceCode[j]) {
                            check = false;
                            throw new RuntimeException("Vui lòng chọn các mã thiết bị khác nhau!");
                        }
                    }
                }
                if (check) {
                    try {
                        String time = request.getParameter("time");
                        time = time.substring(11, time.length()) + " " + time.substring(8, 10) + "-" + time.substring(5, 7) + "-" + time.substring(0, 4);
                        SimpleDateFormat f = new SimpleDateFormat("HH:mm dd-MM-yyyy");
                        Date d = f.parse(time);
                        if (d.getTime() < new java.util.Date().getTime()) {
                            throw new RuntimeException("Vui lòng không chọn thời gian trong quá khứ!");
                        } else {
                            int deviceReceiptID = new DeviceReceipt(b.getUserID(), b.getDeviceID(), b.getQuantity()).add();
                            new DeviceReceipt(deviceReceiptID).borrow(b.getQuantity());
                            for (int i = 0; i < b.getQuantity(); i++) {
                                new Devices(deviceCode[i]).borrow(deviceReceiptID);
                            }
                            MessageDB.addMessage(new Message(false, b.getUserID(), "<b>Thiết bị bạn mượn:</b> " + b.getDeviceName() + " <br><b style='color: blue'>ĐƯỢC ĐỒNG Ý!</b><br>Vui lòng ghé thư viện vào lúc: " + time + " để nhận thiết bị!"));
                            b.delete();
                            response.sendRedirect("deviceorder");
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(DeviceOrders.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "2":
                String reason = request.getParameter("reason");
                MessageDB.addMessage(new Message(false, b.getUserID(), "<b>Thiết bị bạn mượn:</b> " + b.getDeviceName() + " <br><b style='color: red'>BỊ TỪ CHỐI!</b><br><b>Lý do:</b> " + reason));
                b.delete();
                response.sendRedirect("deviceorder");
                break;
        }
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
