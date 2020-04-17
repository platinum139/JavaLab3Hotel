package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ErrorServlet extends HttpServlet {

    final String EXC = "javax.servlet.error.exception_class";
    final String MSG = "javax.servlet.error.message";
    final String ST = "javax.servlet.error.status_code";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext context = getServletContext();
        
        String excClass = (String)request.getAttribute(EXC);
        String message = (String)request.getAttribute(MSG);
        Integer statusCode = (Integer)request.getAttribute(ST);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Error</title>");            
        out.println("</head>");
        
        out.println("<body style='text-align: center;'>");
        out.println("<h1>На жаль, сервер не може обробити Ваш запит.</h1>");
        
        out.println("<table align='center'>");
        out.println("<tr><td>Status Code: " + statusCode + "</td></tr>");
        out.println("<tr><td>Exception Type: " + excClass + "</td></tr>");
        out.println("<tr><td>Message: " + message + "</td></tr>");
        out.println("</table>");
        
        out.println("<h2>Будь-ласка, спробуйте знову...</h2>");
        out.println("</body>");
        out.println("</html>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}