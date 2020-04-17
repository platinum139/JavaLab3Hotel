<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bill</title>
        <link rel="stylesheet" type="text/css" href="css/PayPage.css">
        <link rel="stylesheet" type="text/css" href="css/Header.css">
    </head>
    <body>
        <jsp:include page="logoutheader.jsp"></jsp:include>
        
        <h1>Bill ID ${billId}</h1>
        <h2>Do you want to pay?</h2>
        
        <form action="pay" method="post">
            <div class="button" align="center">
                <input type="hidden" name="billId" value="${billId}">
                <input type="submit" id="pay" value="Pay" >
            </div>
        </form>
    </body>
</html>
