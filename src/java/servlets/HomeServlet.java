package servlets;

import dao.BillDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Bill;
import models.Request;
import dao.RequestDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;



public class HomeServlet extends HttpServlet {
    
    private RequestDAO requestDAO;
    private BillDAO billDAO;
    
    @Override
    public void init() {
        String jdbcURL = "jdbc:mysql://localhost:3306/hotel";
        String jdbcUsername = "root";
        String jdbcPassword = "root";

        requestDAO = new RequestDAO(jdbcURL, jdbcUsername, jdbcPassword);
        billDAO = new BillDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // get current user id
            HttpSession session = request.getSession(false);  
            int id = (int)session.getAttribute("currentUserId");

            // get all requests from db
            List<Request> requests = requestDAO.listUserRequests(id);
            
            // get all bills from db
            List<Bill> bills = billDAO.listUserBills(id);

            // set requests array to request
            request.setAttribute("requests", requests);
            
            // set bills array to request
            request.setAttribute("bills", bills);
        }
        catch(SQLException e) {
            request.setAttribute("javax.servlet.error.exception_class", e.getClass().toString());
            request.setAttribute("javax.servlet.error.message", e.getMessage());
            request.setAttribute("javax.servlet.error.status_code", e.getErrorCode());
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error");
            dispatcher.forward(request, response);
        }
        
        // redirect request
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // get billId parameter from hidden input
        int billId = Integer.parseInt(request.getParameter("billId"));

        // set billId as url param and redirect to bill page
        response.sendRedirect("pay?billId=" + billId);
    }

    @Override
    public String getServletInfo() {
        return "Home Servlet";
    }
}
