<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel</title>
        <link rel="stylesheet" type="text/css" href="css/BookingPage.css">
        <link rel="stylesheet" type="text/css" href="css/Header.css">
    </head>
    <body>
        <jsp:include page="logoutheader.jsp"></jsp:include>
        
        <h1>Making a reservation</h1>
        <h2>Please, fill up the form and enjoy your room!</h2>
        
        <form action="reserve" method="post">
            
            <table>
                <tr>
                    <td>Type of Room</td>
                    <td class="fields">
                        <select size="1" name="roomType">
                            <option value="STANDART" selected>Standart</option>
                            <option value="DELUX">De lux</option>
                            <option value="STUDIO">Studio</option>
                            <option value="SUPERIOR">Superior</option>
                            <option value="APPARTMENT">Appartment</option>
                            <option value="SUITE_MINI">Suite mini</option>
                            <option value="PRESIDENTAL_SUITE">Presidental suite</option>
                        </select>
                    </td>
                </tr>
                
                <tr>
                    <td>Number of beds</td>
                    <td class="fields">
                        <select size="1" name="bedsNumber">
                            <option value="ONE" selected>1</option>
                            <option value="TWO">2</option>
                            <option value="THREE">3</option>
                            <option value="FOUR">4</option>
                        </select>
                    </td>
                </tr>
                
                <tr>
                    <td>Stay time (in days)</td>
                    <td class="fields">
                        <input type="number" min="1" max="30" name="stayTime">
                    </td>
                </tr>
            </table>
            
            <div class="button" align="center">
                <input type="submit" id="admit" value="Admit">
            </div>
            
        </form>
    </body>
</html>
