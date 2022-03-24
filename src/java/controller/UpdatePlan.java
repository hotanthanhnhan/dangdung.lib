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
import model.entity.Account;
import model.entity.Plan;

/**
 *
 * @author ThanhNhan
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 200,
        maxFileSize = 1024 * 1024 * 200,
        maxRequestSize = 1024 * 1024 * 200)
public class UpdatePlan extends HttpServlet {

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
            out.println("<title>Servlet UpdatePlan</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePlan at " + request.getContextPath() + "</h1>");
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
                            response.sendRedirect("plan");
                        } else {
                            int planID = -1;
                            try {
                                planID = Integer.parseInt(id);
                            } catch (Exception e) {
                                throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
                            }
                            request.setAttribute("p", new Plan(planID));
                            request.getRequestDispatcher("UpdatePlan.jsp").include(request, response);
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
        int planID = Integer.parseInt(request.getParameter("id"));
        Plan p = new Plan(planID);
        Part img = request.getPart("planImg");
        String realPath = "D:\\workspace\\java\\DangDungLibrary\\web\\assets\\covers\\";
        String fileName = Paths.get(img.getSubmittedFileName()).getFileName().toString();
        p.setTitle(request.getParameter("title"));
        p.setDescription(request.getParameter("description"));
        if (fileName != null && !fileName.trim().equals("")) {
            Random rd = new Random();
            int n = rd.nextInt();
            if (n < 0) {
                n = -n;
            }
            img.write(realPath + n + fileName);
            img.write(n + fileName);
            realPath = request.getServletContext().getRealPath("./assets/covers/");
            img.write(realPath + n + fileName);
            String planImg = "./assets/covers/" + n + fileName;
            p.setPlanImg(planImg);
        }
        p.update();
        response.sendRedirect("plan");
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
