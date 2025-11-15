package com.example.servlet;

import com.example.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

public class SubmitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String hashPassword(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(byte b : digest) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch(Exception e) { return password; }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String dob = request.getParameter("dob");
        String mobile = request.getParameter("mobile");
        String country = request.getParameter("country");
        String gender = request.getParameter("gender");

        if(!password.equals(confirmPassword)) {
            response.getWriter().println("<h3>Password and Confirm Password do not match.</h3>");
            return;
        }

        try(Connection conn = DBUtil.getConnection()) {
            // Check if email already exists
            PreparedStatement check = conn.prepareStatement("SELECT id FROM users WHERE email=?");
            check.setString(1, email);
            ResultSet rs = check.executeQuery();
            if(rs.next()) {
                response.getWriter().println("<h3>Try to contact the owner for info, form can only fill once.</h3>");
                return;
            }

            // Insert new user
            PreparedStatement insert = conn.prepareStatement(
                    "INSERT INTO users (name,email,password,dob,mobile,country,gender) VALUES (?,?,?,?,?,?,?)"
            );
            insert.setString(1, name);
            insert.setString(2, email);
//            insert.setString(3, hashPassword(password));
            insert.setString(3, password);
            if(dob==null || dob.trim().isEmpty()) insert.setNull(4, Types.DATE);
            else insert.setDate(4, Date.valueOf(dob));
            insert.setString(5, mobile);
            insert.setString(6, country);
            insert.setString(7, gender);
            insert.executeUpdate();

            response.getWriter().println("<h3>Thanks for visiting our company.</h3>");

        } catch(SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Server error: "+e.getMessage()+"</h3>");
        }
    }
}
