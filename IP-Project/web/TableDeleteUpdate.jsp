<%-- 
    Document   : TableDeleteUpdate
    Created on : Feb 4, 2021, 4:20:06 AM
    Author     : Muhammad Faiq
--%>

<%@page import="java.util.Base64"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Controller.*"%>
<%@page import="Model.*"%>
<%@page import="java.sql.ResultSet"%>
<%@page language="java" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!Room room=new Room();%>
<!DOCTYPE html>
<html>
    <head>
         <jsp:include page="headerNav.jsp" /> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update & Delete Room</title>
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
        
        <%
            Room room=new Room();
            ArrayList<Room> roomlistSingle = (ArrayList<Room>)session.getAttribute("roomlistSingle");
            ArrayList<Room> roomlistDouble = (ArrayList<Room>)session.getAttribute("roomlistDouble");
            ArrayList<Room> roomlistTriple = (ArrayList<Room>)session.getAttribute("roomlistTriple");
            ArrayList<Room> roomlistQuad = (ArrayList<Room>)session.getAttribute("roomlistQuad");
        %>
        
        <div class="container-custom">
            
            <nav class="navbar navbar-dark bg-dark">
                <div style="height:36px">
                    <p class="navbar-brand">Single Room</p>
                </div>
            </nav>
            
            <div class="jumbotron" style="padding-top:25px;padding-bottom: 10px;">
                <div class="table-responsive">
                    <table class="table table-hover table-info table-striped">
                        <thead>
                            <tr>
                                <td><b>Number</b></td>
                                <td><b>Type</b></td>
                                <td><b>Image</b></td>
                                <td><b>Price</b></td>
                                <td><b>Action</b></td>
                            </tr>
                        </thead>
                        <tbody>
                    <%
                        for(int i=0; i<roomlistSingle.size();i++)
                        {
                    %>
                            <tr>
                                <td><%=((Room)roomlistSingle.get(i)).getRoomNo()%></td>
                                <td><%=((Room)roomlistSingle.get(i)).getRoomType()%></td>
                                <td>
                                    <%
                                        byte roomImage[] =((Room)roomlistSingle.get(i)).getRoomImage();
                                        String imgDataBase64=new String(Base64.getEncoder().encode(roomImage));
                                    %>
                                    <img src="data:image/gif;base64,<%= imgDataBase64 %>" alt="No images" width="100px" height="100px"/>
                                </td>
                                <td><%=((Room)roomlistSingle.get(i)).getRoomPrice()%></td>

                                <% int roomID =((Room)roomlistSingle.get(i)).getRoomID();%>
                                <td> 
                                    <form  name="DeleteUpdateForm" action="ManageRoomController" method="post" enctype="multipart/form-data">
                                        <label>       
                                            <input type="hidden" name="roomID" value=<%=roomID %> >
                                            <input class="btn btn-primary btn-sm" type="submit" name="command" id="command" value="Update">
                                        </label> 
                                        <label>
                                            <input type="hidden" name="roomID" value=<%=roomID %> >
                                            <input class="btn btn-danger btn-sm" type="submit" name="command" id="command" value="Delete">
                                        </label>
                                    </form>
                                </td>    
                            </tr>
                        <%  } %>
                        </tbody>            
                    </table> 
                </div>

                <table border="0" align="center" style="margin-top:10px;">
                    <tr>
                        <td>
                            <a href="index.jsp" class="btn btn-success">Main Menu</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        
        <div class="container-custom">
            
            <nav class="navbar navbar-dark bg-dark">
                <div style="height:36px">
                    <p class="navbar-brand">Double Room</p>
                </div>
            </nav>
            
            <div class="jumbotron" style="padding-top:25px;padding-bottom: 10px;">
                <div class="table-responsive">
                    <table class="table table-hover table-info table-striped">
                        <thead>
                            <tr>
                                <td><b>Number</b></td>
                                <td><b>Type</b></td>
                                <td><b>Image</b></td>
                                <td><b>Price</b></td>
                                <td><b>Action</b></td>
                            </tr>
                        </thead>
                        <tbody>
                    <%
                        for(int i=0; i<roomlistDouble.size();i++)
                        {
                    %>
                            <tr>
                                <td><%=((Room)roomlistDouble.get(i)).getRoomNo()%></td>
                                <td><%=((Room)roomlistDouble.get(i)).getRoomType()%></td>
                                <td>
                                    <%
                                        byte roomImage[] =((Room)roomlistDouble.get(i)).getRoomImage();
                                        String imgDataBase64=new String(Base64.getEncoder().encode(roomImage));
                                    %>
                                    <img src="data:image/gif;base64,<%= imgDataBase64 %>" alt="No images" width="100px" height="100px"/>
                                </td>
                                <td><%=((Room)roomlistDouble.get(i)).getRoomPrice()%></td>

                                <% int roomID =((Room)roomlistDouble.get(i)).getRoomID();%>
                                <td> 
                                    <form  name="DeleteUpdateForm" action="${pageContext.request.contextPath}/ManageRoomController" method="post">
                                        <label>       
                                            <input type="hidden" name="roomID" value=<%=roomID %> >
                                            <input class="btn btn-primary btn-sm" type="submit" name="command" id="command" value="Update">
                                        </label> 
                                        <label>
                                            <input type="hidden" name="roomID" value=<%=roomID %> >
                                            <input class="btn btn-danger btn-sm" type="submit" name="command" id="command" value="Delete">
                                        </label>
                                    </form>
                                </td>    
                            </tr>
                        <%  } %>
                        </tbody>            
                    </table> 
                </div>

                <table border="0" align="center" style="margin-top:10px;">
                    <tr>
                        <td>
                            <a href="index.jsp" class="btn btn-success">Main Menu</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        
        <div class="container-custom">
            
            <nav class="navbar navbar-dark bg-dark">
                <div style="height:36px">
                    <p class="navbar-brand">Triple Room</p>
                </div>
            </nav>
            
            <div class="jumbotron" style="padding-top:25px;padding-bottom: 10px;">
                <div class="table-responsive">
                    <table class="table table-hover table-info table-striped">
                        <thead>
                            <tr>
                                <td><b>Number</b></td>
                                <td><b>Type</b></td>
                                <td><b>Image</b></td>
                                <td><b>Price</b></td>
                                <td><b>Action</b></td>
                            </tr>
                        </thead>
                        <tbody>
                    <%
                        for(int i=0; i<roomlistTriple.size();i++)
                        {
                    %>
                            <tr>
                                <td><%=((Room)roomlistTriple.get(i)).getRoomNo()%></td>
                                <td><%=((Room)roomlistTriple.get(i)).getRoomType()%></td>
                                <td>
                                    <%
                                        byte roomImage[] =((Room)roomlistTriple.get(i)).getRoomImage();
                                        String imgDataBase64=new String(Base64.getEncoder().encode(roomImage));
                                    %>
                                    <img src="data:image/gif;base64,<%= imgDataBase64 %>" alt="No images" width="100px" height="100px"/>
                                </td>
                                <td><%=((Room)roomlistTriple.get(i)).getRoomPrice()%></td>

                                <% int roomID =((Room)roomlistTriple.get(i)).getRoomID();%>
                                <td> 
                                    <form  name="DeleteUpdateForm" action="${pageContext.request.contextPath}/ManageRoomController" method="post">
                                        <label>       
                                            <input type="hidden" name="roomID" value=<%=roomID %> >
                                            <input class="btn btn-primary btn-sm" type="submit" name="command" id="command" value="Update">
                                        </label> 
                                        <label>
                                            <input type="hidden" name="roomID" value=<%=roomID %> >
                                            <input class="btn btn-danger btn-sm" type="submit" name="command" id="command" value="Delete">
                                        </label>
                                    </form>
                                </td>    
                            </tr>
                        <%  } %>
                        </tbody>            
                    </table> 
                </div>

                <table border="0" align="center" style="margin-top:10px;">
                    <tr>
                        <td>
                            <a href="index.jsp" class="btn btn-success">Main Menu</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        
        <div class="container-custom">
            
            <nav class="navbar navbar-dark bg-dark">
                <div style="height:36px">
                    <p class="navbar-brand">Quad Room</p>
                </div>
            </nav>
            
            <div class="jumbotron" style="padding-top:25px;padding-bottom: 10px;">
                <div class="table-responsive">
                    <table class="table table-hover table-info table-striped">
                        <thead>
                            <tr>
                                <td><b>Number</b></td>
                                <td><b>Type</b></td>
                                <td><b>Image</b></td>
                                <td><b>Price</b></td>
                                <td><b>Action</b></td>
                            </tr>
                        </thead>
                        <tbody>
                    <%
                        for(int i=0; i<roomlistQuad.size();i++)
                        {
                    %>
                            <tr>
                                <td><%=((Room)roomlistQuad.get(i)).getRoomNo()%></td>
                                <td><%=((Room)roomlistQuad.get(i)).getRoomType()%></td>
                                <td>
                                    <%
                                        byte roomImage[] =((Room)roomlistQuad.get(i)).getRoomImage();
                                        String imgDataBase64=new String(Base64.getEncoder().encode(roomImage));
                                    %>
                                    <img src="data:image/gif;base64,<%= imgDataBase64 %>" alt="No images" width="100px" height="100px"/>
                                </td>
                                <td><%=((Room)roomlistQuad.get(i)).getRoomPrice()%></td>

                                <% int roomID =((Room)roomlistQuad.get(i)).getRoomID();%>
                                <td> 
                                    <form  name="DeleteUpdateForm" action="${pageContext.request.contextPath}/ManageRoomController" method="post">
                                        <label>       
                                            <input type="hidden" name="roomID" value=<%=roomID %> >
                                            <input class="btn btn-primary btn-sm" type="submit" name="command" id="command" value="Update">
                                        </label> 
                                        <label>
                                            <input type="hidden" name="roomID" value=<%=roomID %> >
                                            <input class="btn btn-danger btn-sm" type="submit" name="command" id="command" value="Delete">
                                        </label>
                                    </form>
                                </td>    
                            </tr>
                        <%  } %>
                        </tbody>            
                    </table> 
                </div>

                <table border="0" align="center" style="margin-top:10px;">
                    <tr>
                        <td>
                            <a href="index.jsp" class="btn btn-success">Main Menu</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>

