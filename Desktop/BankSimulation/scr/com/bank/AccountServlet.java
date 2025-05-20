package com.bank;

import java.io.*;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    // In-memory account store (accountNo -> Balance)
    public static HashMap<String, Double> accounts = new HashMap<>();
    public static HashMap<String, String> names = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String accNo = request.getParameter("accNo");
        String name = request.getParameter("name");
        double balance = Double.parseDouble(request.getParameter("balance"));

        accounts.put(accNo, balance);
        names.put(accNo, name);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>Account Created Successfully!</h3>");
        out.println("<p>Account No: " + accNo + "</p>");
        out.println("<p>Name: " + name + "</p>");
        out.println("<p>Balance: ₹" + balance + "</p>");
        out.println("<a href='home'>Back to Home</a>");
    }
}
