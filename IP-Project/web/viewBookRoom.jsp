<%@page import="java.util.Base64"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Controller.*"%>
<%@page import="Model.*"%>
<%@page import="java.sql.ResultSet"%>
<%@page language="java" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Booking Room</title>
        
        <link rel='stylesheet' href='style.css'/>
        
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/main.css">
        <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
        
        <style>
            .custom-shadow {
                box-shadow: rgba(10, 37, 64, 0.35) 0px -2px 6px 0px inset;
            }
            .center {
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .container-custom {
                display: block;
                margin-right: auto;
                margin-left: auto;
                width: 95%;
                padding: 10px 0 10px 200px;
            }
            .header-home {
                text-align: center;
            }
            @media only screen and (max-width: 600px) {.container-custom {padding-left: 10px; padding-top: 110px;}}
        </style>
        
    </head>
    <body>
        
         <jsp:include page="headerNav.jsp" />  
        
        <%
            Booking booking = new Booking();
            ArrayList<Booking> bookingList = (ArrayList<Booking>)session.getAttribute("bookingList");
        %>

        <div class="container-custom">
            
            <nav class="navbar navbar-dark bg-dark">
                <div style="height:36px">
                    <p class="navbar-brand">Booking List</p>
                </div>
            </nav>
        
            <div class="jumbotron" style="padding-top:25px;padding-bottom: 10px;">
                <table class="table table-hover table-info table-striped">
                        <thead>
                            <tr>
                                <td><b>Booking ID</b></td>
                                <td><b>Client ID</b></td>
                                <td><b>Room ID</b></td>
                                <td><b>Booking Date In</b></td>
                                <td><b>Booking Date Out</b></td>
                                <td><b>Status </b></td>
                                <td><b>Action</b></td>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for(int i=0; i<bookingList.size(); i++){
                                    
                                
                            %>
                            <tr>
                                <td> <%= ((Booking)bookingList.get(i)).getBookingID()      %> </td>
                                <td> <%= ((Booking)bookingList.get(i)).getClientID()       %> </td>   
                                <td> <%= ((Booking)bookingList.get(i)).getRoomID()         %> </td>
                                <td> <%= ((Booking)bookingList.get(i)).getBookingDateIn()  %> </td>
                                <td> <%= ((Booking)bookingList.get(i)).getBookingDateOut() %> </td>
                                <td> <%= ((Booking)bookingList.get(i)).getStatus()         %> </td>
                                
                                <% int bookingID =((Booking)bookingList.get(i)).getBookingID();%>
                                <td>
                                    <form  name="viewBookRoom" action="${pageContext.request.contextPath}/ManageBookingController" method="post">
                                        <label>       
                                            <input type="hidden" name="bookingID" value=<%=bookingID %> >
                                            <input class="btn btn-primary btn-sm" type="submit" name="command" id="command" value="Check-In">
                                        </label> 
                                        <label>
                                            <input type="hidden" name="bookingID" value=<%=bookingID %> >
                                            <input class="btn btn-danger btn-sm" type="submit" name="command" id="command" value="Check-Out">
                                        </label>
                                    </form>
                                </td>
                            </tr>
                            
                            <% 
                                }
                            %>
                        </tbody>
                </table>
            </div>
        
        </div>    
    </body>
</html>
