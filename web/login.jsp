<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css/SignUpPage.css">
        <link rel="stylesheet" type="text/css" href="css/Header.css">
    </head>
    <body>
        <jsp:include page="loginheader.jsp"></jsp:include>

        <h1>Welcome!</h1>
        <h2>Log in your account to make a reservation for a room.</h2>

        <p id="error">${errorMessage}</p>
        
        <form action="${pageContext.request.contextPath}/login" method="post">
            
            <table>             
                <tr>
                    <td>Email</td>
                    <td class="fields">
                        <input type="email" id="email" placeholder="Enter your email..." name="email">
                    </td>
                </tr>
                
                <tr>
                    <td>Password</td>
                    <td class="fields">
                        <input type="password" id="password" placeholder="Enter your password..." name="password">
                    </td>
                </tr>
            </table>
            
            <div class="button" align="center">
                <input type="submit" id="login" value="Login">
            </div>
            
            <div class="button" align="center">
                <a href="signup" id="signupLink">Sign up</a>
            </div>
            
            <input type="hidden" name="redirectId" value="${param.redirectId}">
            
        </form>
    </body>
</html>
