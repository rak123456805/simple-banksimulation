package com.bank;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String accNo = request.getParameter("accNo");
        String type = request.getParameter("type");
        double amount = Double.parseDouble(request.getParameter("amount"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (!AccountServlet.accounts.containsKey(accNo)) {
            out.println("<h3>Account does not exist!</h3>");
        } else {
            double currentBalance = AccountServlet.accounts.get(accNo);

            if (type.equals("deposit")) {
                currentBalance += amount;
                AccountServlet.accounts.put(accNo, currentBalance);
                out.println("<h3>₹" + amount + " Deposited Successfully.</h3>");
            } else if (type.equals("withdraw")) {
                if (currentBalance >= amount) {
                    currentBalance -= amount;
                    AccountServlet.accounts.put(accNo, currentBalance);
                    out.println("<h3>₹" + amount + " Withdrawn Successfully.</h3>");
                } else {
                    out.println("<h3>Insufficient Balance!</h3>");
                }
            }
            out.println("<p>Current Balance: ₹" + currentBalance + "</p>");
        }

        out.println("<a href='home'>Back to Home</a>");
    }
}
