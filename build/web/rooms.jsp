<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rooms</title>
        <link rel="stylesheet" type="text/css" href="css/RoomsPage.css">
        <link rel="stylesheet" type="text/css" href="css/Header.css">
    </head>
    <body>
        <jsp:include page="logoutheader.jsp"></jsp:include>
        
        <h1>Suitable rooms</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Room Type</th>
                <th>Beds Number</th>
                <th>Price for 1 night</th>
                <th>Is Available</th>
                <th></th>
            </tr>
            
            <c:forEach items="${suitRooms}" var="room">
            <tr>
                <td>${room.id}</td>
                <td>${room.roomType}</td>
                <td>${room.bedsNumber}</td>
                <td>${room.price}</td>
                <td>${room.isAvailable}</td>
                <td>
                    <form action="rooms" method="post">
                        <input type="hidden" name="requestId" value="${requestId}">
                        <input type="hidden" name="roomId" value="${room.id}">
                        <div class="button" align="center">
                            <input type="submit" id="choose" value="Choose" >
                        </div>
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table>
        
        <h1>All hotel rooms</h1>
        
        <table>
            <tr>
                <th>ID</th>
                <th>Room Type</th>
                <th>Beds Number</th>
                <th>Price for 1 night</th>
                <th>Is Available</th>
            </tr>
            
            <c:forEach items="${rooms}" var="room">
            <tr>
                <td>${room.id}</td>
                <td>${room.roomType}</td>
                <td>${room.bedsNumber}</td>
                <td>${room.price}</td>
                <td>${room.isAvailable}</td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
