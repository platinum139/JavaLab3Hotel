package servlets;

import dao.BillDAO;
import dao.RequestDAO;
import dao.RoomDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Bill;
import models.Request;
import models.Room;


public class RoomServlet extends HttpServlet {
    
    private RoomDAO roomDAO;
    private RequestDAO requestDAO;
    private BillDAO billDAO;
    
    @Override
    public void init() {
        String jdbcURL = "jdbc:mysql://localhost:3306/hotel";
        String jdbcUsername = "root";
        String jdbcPassword = "root";

        roomDAO = new RoomDAO(jdbcURL, jdbcUsername, jdbcPassword);
        requestDAO = new RequestDAO(jdbcURL, jdbcUsername, jdbcPassword);
        billDAO = new BillDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        try {
            // get requestId attribute
            int requestId = Integer.parseInt(request.getParameter("requestId"));
        
            // get request by id
            Request clientRequest = requestDAO.getRequest(requestId);
        
            // get all rooms from db
            List<Room> rooms = roomDAO.listAllRooms();
            
            // get suitable rooms for the client request
            List<Room> suitableRooms = roomDAO.getSuitableRooms(clientRequest);
            
            // set rooms array as attribute
            request.setAttribute("rooms", rooms);
            
            // set suitable room array as attribute
            request.setAttribute("suitRooms", suitableRooms);
            
            // set requestId param as attribute
            request.setAttribute("requestId", requestId);
        }
        catch(SQLException e) {
            request.setAttribute("javax.servlet.error.exception_class", e.getClass().toString());
            request.setAttribute("javax.servlet.error.message", e.getMessage());
            request.setAttribute("javax.servlet.error.status_code", e.getErrorCode());
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error");
            dispatcher.forward(request, response);
        }
        
        // redirect request
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/rooms.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // get requestId parameter from hidden input
            int requestId = Integer.parseInt(request.getParameter("requestId"));
            
            // get request from db by id
            Request clientRequest = requestDAO.getRequest(requestId);
            
            // get userId
            int userId = clientRequest.getUserId();
            
            // get roomId parameter from hidden input
            int roomId = Integer.parseInt(request.getParameter("roomId"));
            
            // get room from db by id
            Room room = roomDAO.getRoom(roomId);
                        
            // make room unavailable in db
            roomDAO.updateRoom(roomId, false, userId);
                    
            // delete a request
            requestDAO.deleteRequest(requestId);
            
            // create a bill
            int price = room.getPrice() * clientRequest.getStayTime();
            Bill bill = new Bill(userId, roomId, price , clientRequest.getDescription());
            
            // insert bill to db
            billDAO.insertBill(bill);
        }
        catch(SQLException e) {
            request.setAttribute("javax.servlet.error.exception_class", e.getClass().toString());
            request.setAttribute("javax.servlet.error.message", e.getMessage());
            request.setAttribute("javax.servlet.error.status_code", e.getErrorCode());
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error");
            dispatcher.forward(request, response);
        }
        
        // redirect to requests page
        response.sendRedirect("requests");
    }

    @Override
    public String getServletInfo() {
        return "Room servlet";
    }
}
