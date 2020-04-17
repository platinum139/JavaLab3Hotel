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


public class SignupServlet extends HttpServlet {

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
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signup.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // get params
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User(0, firstname, lastname, surname, email, password, "user");
        
        try {
            // write user to db
            userDAO.insertUser(user);
        }
        catch (SQLException e) {
            request.setAttribute("javax.servlet.error.exception_class", e.getClass().toString());
            request.setAttribute("javax.servlet.error.message", e.getMessage());
            request.setAttribute("javax.servlet.error.status_code", e.getErrorCode());
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error");
            dispatcher.forward(request, response);
        }

        // store logined user
        HttpSession session = request.getSession();  
        session.setAttribute("currentUserId", user.getId());  

        // redirect to home page
        response.sendRedirect(request.getContextPath() + "/home");
    }

    @Override
    public String getServletInfo() {
        return "Sign up servlet";
    }
}
