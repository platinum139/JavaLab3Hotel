package servlets;

import dao.RequestDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Request;


public class RequestsServlet extends HttpServlet {

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
        
        try {
            // get all requests from db
            List<Request> requests = requestDAO.listAllRequests();
            
            // set requests array to request
            request.setAttribute("requests", requests);
        }
        catch(SQLException e) {
            request.setAttribute("javax.servlet.error.exception_class", e.getClass().toString());
            request.setAttribute("javax.servlet.error.message", e.getMessage());
            request.setAttribute("javax.servlet.error.status_code", e.getErrorCode());
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error");
            dispatcher.forward(request, response);
        }
        
        // redirect request
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/requests.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // get requestId parameter from hidden input
        int requestId = Integer.parseInt(request.getParameter("requestId"));

        // set requestId as url param and redirect to room page
        response.sendRedirect("rooms?requestId=" + requestId);
    }

    @Override
    public String getServletInfo() {
        return "Hotel room requests servlet";
    }
}
