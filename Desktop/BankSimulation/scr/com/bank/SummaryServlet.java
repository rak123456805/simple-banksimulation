package com.bank;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/summary")
public class SummaryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String accNo = request.getParameter("accNo");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (!AccountServlet.accounts.containsKey(accNo)) {
            out.println("<h3>Account not found!</h3>");
        } else {
            String name = AccountServlet.names.get(accNo);
            double balance = AccountServlet.accounts.get(accNo);

            out.println("<h2>Account Summary</h2>");
            out.println("<p>Account No: " + accNo + "</p>");
            out.println("<p>Name: " + name + "</p>");
            out.println("<p>Balance: ₹" + balance + "</p>");
        }

        out.println("<a href='home'>Back to Home</a>");
    }
}
