<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up</title>
        <link rel="stylesheet" type="text/css" href="css/SignUpPage.css">
        <link rel="stylesheet" type="text/css" href="css/Header.css">
    </head>
    <body>
        <jsp:include page="loginheader.jsp"></jsp:include>
        <h1>Sign up</h1>
        
        <form action="" method="post">
            
            <table>
                <tr>
                    <td>Firstname</td>
                    <td class="fields">
                        <input type="text" name="firstname" placeholder="Enter your firstname..." value="">
                    </td>
                </tr>
                
                <tr>
                    <td>Lastname</td>
                    <td class="fields">
                        <input type="text" name="lastname" placeholder="Enter your lastname..." value="">
                    </td>
                </tr>
                
                <tr>
                    <td>Surname</td>
                    <td class="fields">
                        <input type="text" name="surname" placeholder="Enter your surname..." value="">
                    </td>
                </tr>
                
                <tr>
                    <td>Email</td>
                    <td class="fields">
                        <input type="email" name="email" placeholder="Enter your email..." value="">
                    </td>
                </tr>
                
                <tr>
                    <td>Password</td>
                    <td class="fields">
                        <input type="password" name="password" placeholder="Enter your password..." value="">
                    </td>
                </tr>

            </table>
            
            <div class="button" align="center">
                <input type="submit" id="signup" value="Sign up">
            </div>
            
        </form>
    </body>
</html>
