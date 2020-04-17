package servlets;

import dao.BillDAO;
import dao.RoomDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Bill;
 

public class BillServlet extends HttpServlet {

    private BillDAO billDAO;
    private RoomDAO roomDAO;
    
    @Override
    public void init() {
        String jdbcURL = "jdbc:mysql://localhost:3306/hotel";
        String jdbcUsername = "root";
        String jdbcPassword = "root";

        billDAO = new BillDAO(jdbcURL, jdbcUsername, jdbcPassword);
        roomDAO = new RoomDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // get billId attribute
        int billId = Integer.parseInt(request.getParameter("billId"));
        
        // set billId as attribute
        request.setAttribute("billId", billId);
        
        // redirect request
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bill.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // get billId parameter from hidden input
            int billId = Integer.parseInt(request.getParameter("billId"));

            // get bill from db by id
            Bill bill = billDAO.getBill(billId);

            // get roomId
            int roomId = bill.getRoomId();

            // make room available
            // we suppose if user pays his bill, he checks out and room will become available
            roomDAO.updateRoom(roomId, true, 0);

            // delete bill from db
            billDAO.deleteBill(billId);
        }
        catch(SQLException e) {
            request.setAttribute("javax.servlet.error.exception_class", e.getClass().toString());
            request.setAttribute("javax.servlet.error.message", e.getMessage());
            request.setAttribute("javax.servlet.error.status_code", e.getErrorCode());
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error");
            dispatcher.forward(request, response);
        }
        
        // redirect to home page
        response.sendRedirect("home");
    }

    @Override
    public String getServletInfo() {
        return "Bill servlet";
    }
}
