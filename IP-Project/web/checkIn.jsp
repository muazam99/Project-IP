<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page import="java.util.*" %>
 <%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@page import="Model.*"%>
<%@page import="Controller.*"%>
<%@page import="java.sql.ResultSet"%>
<%@page language="java" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang ="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check In</title>
        
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

      <!-- Bootstrap core CSS -->
      <link href="css/bootstrap.min.css" rel="stylesheet">      
        <link href="css/navbar-top-fixed.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"    rel="stylesheet">
        <script src="https://kit.fontawesome.com/4962701fb8.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
 
    </head>
    <body>
        <!-- include adminHeaderNav.jsp -->    
        <c:choose>
            <c:when test="${CLIENT != null}">
                <jsp:include page="headerNav.jsp" />  
            </c:when>
                        
            <c:otherwise>                
            </c:otherwise>
        </c:choose>  

        ${ADMIN.getName()}

        <h2>Check In</h2>
        <br>
        
        <form action ="manageBookingController?command=CheckIn" method ="post">
            <label>Please enter customer name.</label>
            <input type="text"  placeholder="Full Name" name="name" required>
            <br>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>
        
        
        
    </body>
</html>
