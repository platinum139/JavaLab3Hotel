<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Requests</title>
        <link rel="stylesheet" type="text/css" href="css/RequestsPage.css">
        <link rel="stylesheet" type="text/css" href="css/Header.css">
    </head>
    <body>
        <jsp:include page="logoutheader.jsp"></jsp:include>
        
        <h1>All hotel room requests</h1>
        
        <table>
            <tr>
                <th>ID</th>
                <th>Room Type</th>
                <th>Beds Number</th>
                <th>Stay Time</th>
                <th></th>
            </tr>
            
            <c:forEach items="${requests}" var="request">
            <tr>
                <td>${request.id}</td>
                <td>${request.roomType}</td>
                <td>${request.bedsNumber}</td>
                <td>${request.stayTime}</td>
                <td>
                    <form action="requests" method="post">
                        <div class="button" align="center">
                            <input type="hidden" name="requestId" value="${request.id}" >
                            <input type="submit" id="chooseRoom" value="Choose room" >
                        </div>
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
