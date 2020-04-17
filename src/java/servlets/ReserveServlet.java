package servlets;

import dao.RequestDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.BedsNumber;
import models.RoomType;
import models.Request;


public class ReserveServlet extends HttpServlet {
    
    private RequestDAO requestDAO;
    
    @Override
    public void init() {
        String jdbcURL = "jdbc:mysql://localhost:3306/hotel";
        String jdbcUsername = "root";
        String jdbcPassword = "root";

        requestDAO = new RequestDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/reserve.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // get current user id
        HttpSession session = request.getSession(false);  
        int userId = (int)session.getAttribute("currentUserId");

        // get params
        String roomTypeStr = request.getParameter("roomType");
        RoomType roomType = RoomType.valueOf(roomTypeStr);
        
        String bedsNumberStr = request.getParameter("bedsNumber");
        BedsNumber bedsNumber = BedsNumber.valueOf(bedsNumberStr);
        
        String stayTimeStr = request.getParameter("stayTime");
        int stayTime = Integer.parseInt(stayTimeStr);
        
        // create request
        Request clientRequest = new Request(bedsNumber, roomType, stayTime, userId);
        
        // write request to db
        try {
            requestDAO.insertRequest(clientRequest);
        }
        catch (SQLException e) {
            request.setAttribute("javax.servlet.error.exception_class", e.getClass().toString());
            request.setAttribute("javax.servlet.error.message", e.getMessage());
            request.setAttribute("javax.servlet.error.status_code", e.getErrorCode());
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error");
            dispatcher.forward(request, response);
        }
        
        // redirect to home page        
        response.sendRedirect(request.getContextPath() + "/home");
    }

    @Override
    public String getServletInfo() {
        return "Reserve Servlet";
    }
}
