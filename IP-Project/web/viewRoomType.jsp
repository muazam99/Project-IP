

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
         <  <jsp:include page="headerNav.jsp" /> 
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
            ArrayList<Room> roomSingleAvailable = (ArrayList<Room>)session.getAttribute("roomlistSingle");
            ArrayList<Room> roomDoubleAvailable = (ArrayList<Room>)session.getAttribute("roomlistDouble");
            ArrayList<Room> roomTripleAvailable = (ArrayList<Room>)session.getAttribute("roomlistTriple");
            ArrayList<Room> roomQuadAvailable = (ArrayList<Room>)session.getAttribute("roomlistQuad");
        %>
        <div class="container pb-5">
            <div class="row align-items-start justify-content-center"><h1>Welcome ${CLIENT.getName()}</h1></div>
            <div class="row align-items-center pt-4">
               
                    <div class="col">
                        <div class="card p-3 mb-2 bg-light text-dark" style="width: 18rem;">
                            <%
                                byte roomImage[] =((Room)roomSingleAvailable.get(i)).getRoomImage();
                                String imgDataBase64=new String(Base64.getEncoder().encode(roomImage));
                            %>
                            <img src="data:image/gif;base64,<%= imgDataBase64 %>" class="card-img-top" alt="..."/>
                            <div class="card-body">
                                <h5 class="card-title">Single Room</h5>
                                <p class="card-text">RM20.00/night</p>
                                
                            </div>
                        </div>
                    </div>
              
                
                    <div class="col">
                        <div class="card p-3 mb-2 bg-light text-dark" style="width: 18rem;">
                            <%
                                roomImage =((Room)roomDoubleAvailable.get(i)).getRoomImage();
                                imgDataBase64=new String(Base64.getEncoder().encode(roomImage));
                            %>
                            <img src="data:image/gif;base64,<%= imgDataBase64 %>" class="card-img-top" alt="..."/>
                            <div class="card-body">
                                <h5 class="card-title">Double Room</h5>
                                <p class="card-text">RM40.00/night</p>
                                
                            </div>
                        </div>
                    </div>
               
               
                    <div class="col">
                        <div class="card p-3 mb-2 bg-light text-dark" style="width: 18rem;">
                            <%
                                roomImage =((Room)roomTripleAvailable.get(i)).getRoomImage();
                                imgDataBase64=new String(Base64.getEncoder().encode(roomImage));
                            %>
                            <img src="data:image/gif;base64,<%= imgDataBase64 %>" class="card-img-top" alt="..."/>
                            <div class="card-body">
                                <h5 class="card-title">Triple Room</h5>
                                <p class="card-text">RM60.00/night</p>
                               
                            </div>
                        </div>
                    </div>
               
                
                    <div class="col">
                        <div class="card p-3 mb-2 bg-light text-dark" style="width: 18rem;">
                             <%
                                roomImage =((Room) roomQuadAvailable.get(i)).getRoomImage();
                                imgDataBase64=new String(Base64.getEncoder().encode(roomImage));
                            %>
                            <img src="data:image/gif;base64,<%= imgDataBase64 %>" class="card-img-top" alt="..."/>
                            <div class="card-body">
                                <h5 class="card-title">Quad Room</h5>
                                <p class="card-text">RM80.00/night</p>
                               
                            </div>
                        </div>
                    </div>
                


            </div>
        </div>
    </body>
</html>