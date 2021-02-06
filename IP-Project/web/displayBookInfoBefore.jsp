<%-- 
    Document   : displayBookInfoBefore
    Created on : Feb 6, 2021, 11:41:06 PM
    Author     : Muhammad Faiq
--%>

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
         <jsp:include page="headerNav.jsp" /> 
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <meta name="description" content="">
      <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
      <meta name="generator" content="Jekyll v4.1.1">
      <title>Faiq Hotel</title>

      <!-- Bootstrap core CSS -->
      <link href="css/bootstrap.min.css" rel="stylesheet">

            <style>
                .container {
                position: relative;
                text-align: center;
                
              }

              /* Bottom left text */
              .bottom-left {
                position: absolute;
                bottom: 8px;
                left: 16px;
              }

              /* Top left text */
              .top-left {
                position: absolute;
                top: 8px;
                left: 16px;
              }

              /* Top right text */
              .top-right {
                position: absolute;
                top: 8px;
                right: 16px;
              }

              /* Bottom right text */
              .bottom-right {
                position: absolute;
                bottom: 8px;
                right: 16px;
              }

              /* Centered text */
              .centered {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
              }
        
          
         .bd-placeholder-img {
           font-size: 1.125rem;
           text-anchor: middle;
           -webkit-user-select: none;
           -moz-user-select: none;
           -ms-user-select: none;
           user-select: none;
         }

         @media (min-width: 768px) {
           .bd-placeholder-img-lg {
             font-size: 3.5rem;
           }
         }
      </style>

      <!-- Custom styles for this template -->
      <link href="css/navbar-top-fixed.css" rel="stylesheet">
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons"    rel="stylesheet">
      <script src="https://kit.fontawesome.com/4962701fb8.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
   
   </head>
        <%
            int i=0;
            Room room = new Room();
            byte roomImage[] = (byte[])session.getAttribute("roomImage");
            double pricepernight = (Double)session.getAttribute("pricepernight");
            double totalPrice = (Double)session.getAttribute("totalPrice");
            String roomType = (String)session.getAttribute("roomType");
            String traveldateIn=(String)session.getAttribute("traveldateIn");
            String traveldateOut=(String)session.getAttribute("traveldateOut");
            long datediff = (Long)session.getAttribute("datediff");
        %>
    <body>
        <form  name="confirmationPage" action="${pageContext.request.contextPath}/ManageBookingController" method="post">
        <div class="container pb-5">
            <div class="row align-items-center pt-4">
                <div class="col">
                    <div class="card p-3 mb-2 bg-light text-dark" style="width: 25rem;">
                        <%
                            String imgDataBase64=new String(Base64.getEncoder().encode(roomImage));
                        %>
                        <img src="data:image/gif;base64,<%= imgDataBase64 %>" class="card-img-top" alt="..."/>
                    </div>
                </div>
                <div class="col">
                    <div class="card p-3 mb-2 bg-light text-dark" style="width: 40rem;">
                        <div class="card-body">
                            <h5 class="card-title" style="color: blue"><b>Booking Info Confirmation</b></h5>
                            <p class="card-text"><b>Check In: </b> <%=traveldateIn%></p>
                            <p class="card-text"><b>Check Out: </b> <%=traveldateOut%></p>
                            <p class="card-text"><b>Type: </b> <%=roomType%></p>
                            <p class="card-text"><b>Price/Night: RM</b> <%=pricepernight%></p>
                            <p class="card-text"><b>Total Night: </b> <%=datediff%></p>
                            <p class="card-text"><b>Total Price: RM</b> <%=totalPrice%></p>
                            <input type="hidden" name="traveldateIn" value=<%=traveldateIn %> >
                            <input type="hidden" name="traveldateOut" value=<%=traveldateOut%> >
                            <input type="hidden" name="roomType" value=<%=roomType %> >
                            <input type="submit" class="btn btn-outline-primary" name="command" id="command" value="Book Room Confirm">
                            
                        </div>
                    </div>
                </div>
            </div>
            <div class="row align-items-end">
                <div class="d-grid gap-2 col-2 mx-auto pt-2">
                    <form action="${pageContext.request.contextPath}/ManageBookingController" method="post">
                        <input type="submit" class="btn btn-success btn-lg" name="button" value="Main Menu" />
                    </form>                                         
                </div>
            </div>
        </div>
    </form>
    </body>
</html>
