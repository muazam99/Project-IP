<%-- 
    Document   : ChooseRoom
    Created on : Feb 3, 2021, 7:34:49 PM
    Author     : Muhammad Faiq
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Base64"%>
<%@page import="Model.*"%>
<%@page import="Controller.*"%>
<%@page import="java.sql.ResultSet"%>
<%@page language="java" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! Room room = new Room(); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <jsp:include page="headerNav.jsp" /> 
        
        <%ArrayList<Room> roomAvailable = (ArrayList<Room>)session.getAttribute("roomAvailable");%>
        
        
    </body>
</html>
