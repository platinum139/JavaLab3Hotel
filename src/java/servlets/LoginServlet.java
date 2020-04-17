package servlets;

import dao.UserDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;


public class LoginServlet extends HttpServlet {
    
    private UserDAO userDAO;
    
    @Override
    public void init() {
        String jdbcURL = "jdbc:mysql://localhost:3306/hotel";
        String jdbcUsername = "root";
        String jdbcPassword = "root";

        userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // get emails and password params
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        try {
            User user = userDAO.getUserByEmailAndPassword(email, password);
            
            // if account does not exists
            if ( user == null) {

                String errorMessage = "Invalid email or password. Please, check it carefully.";
                request.setAttribute("errorMessage", errorMessage);

                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // store logined user
            HttpSession session = request.getSession();  
            session.setAttribute("currentUserId", user.getId());  
        
            // if account exists
            if (user.getRole().equals("user"))
                    response.sendRedirect(request.getContextPath() + "/home");
            if (user.getRole().equals("admin"))
                response.sendRedirect(request.getContextPath() + "/requests");            
        }
        catch(SQLException e) {            
            request.setAttribute("javax.servlet.error.exception_class", e.getClass().toString());
            request.setAttribute("javax.servlet.error.message", e.getMessage());
            request.setAttribute("javax.servlet.error.status_code", e.getErrorCode());
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Login servlet";
    }
}
