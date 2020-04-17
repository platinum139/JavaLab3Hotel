<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" type="text/css" href="css/HomePage.css">
        <link rel="stylesheet" type="text/css" href="css/Header.css">
    </head>
    <body>
        <jsp:include page="logoutheader.jsp"></jsp:include>
        <h1>My personal account</h1>
        
        <h2>My hotel room requests</h2>
                
        <table>
            <tr>
                <th>ID</th>
                <th>Description</th>
            </tr>
            
            <c:forEach items="${requests}" var="request">
            <tr>
                <td>${request.id}</td>
                <td>${request.description}</td>
            </tr>
            </c:forEach>
        </table>

        <h2>My bills</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Description</th>
                <th>Price</th>
                <th></th>
            </tr>
            
            <c:forEach items="${bills}" var="bill">
            <tr>
                <td>${bill.id}</td>
                <td>${bill.description}</td>
                <td>${bill.price}</td>
                <td>
                    <form action="home" method="post">
                        <div class="button" align="center">
                            <input type="hidden" name="billId" value="${bill.id}">
                            <input type="submit" id="pay" value="Pay">
                        </div>
                    </form>
                </td>
            </tr>
            </c:forEach>

        </table>
        
        <div class="button" align="center">
            <a href="reserve" id="reserve">Make a reservation</a>
        </div>
        
    </body>
</html>
