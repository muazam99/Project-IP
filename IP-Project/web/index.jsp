
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
<!doctype html>
<html lang="en">
    
     
    
   <head>
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

   <body
     
        

         
         
         <main role="main" class="container">       

                 <jsp:include page="headerNav.jsp" />  
                 

        <c:choose>
            
            <c:when test="${CLIENT != null}">
            <%
                        Client client = (Client)session.getAttribute("CLIENT");
            %>         
                 
                          <div class="container pb-5">
                              <div class="row align-items-start justify-content-center"><h1>Welcome ${CLIENT.getName()}</h1></div>
                              <div class="row align-items-center pt-4">
                                  <div class="col">
                                      <div class="card p-3 mb-2 bg-light text-dark" style="width: 18rem;">
                                          <img src="image/cover.png" class="card-img-top" alt="...">
                                          <div class="card-body">
                                              <h5 class="card-title">Book A Room</h5>
                                              <p class="card-text">Search, and book for any available room</p>
                                              <a href="ClientController?command=Book-Room-Page" class="btn btn-primary stretched-link">Book Room</a>
                                          </div>
                                      </div>
                                  </div>
                                  <div class="col">
                                      <div class="card p-3 mb-2 bg-light text-dark" style="width: 18rem;">
                                          <img src="image/cover.png" class="card-img-top" alt="...">
                                          <div class="card-body">
                                              <h5 class="card-title">My Booking</h5>
                                                <p class="card-text">See if you have any upcoming booking</p>
                                                  <form  name="viewMyBooking" method="post" action="${pageContext.request.contextPath}/ClientController" >
                                                      <div class="d-grid gap-2 mx-auto pt-1">   
                                                          <input class=" btn btn-primary " type="submit" style="width:45%" id="command" name="command" value="My-Booking">                                            
                                                      </div>
                                                      <input type="hidden" name="clientID" value=<%=client.getID()%> >                                                
                                                  </form>                                                                                              
                                             </div>
                                      </div>
                                  </div>
                                  <div class="col">
                                      <div class="card p-3 mb-2 bg-light text-dark" style="width: 18rem;">
                                          <img src="image/cover.png" class="card-img-top" alt="...">
                                          <div class="card-body">
                                              <h5 class="card-title">Room Type</h5>
                                              <p class="card-text">View information about available room types in Faiq Hotel</p>
                                              <a href="ClientController?command=View-Room-Page" class="btn btn-primary stretched-link">View Room Type</a>
                                          </div>
                                      </div>
                                  </div>
                                  
                                  
                              </div>
                          </div>
                      
                
            </c:when>
            
            <c:when test="${ADMIN != null}">
                
                <%--untuk admin--%>           
            

                        <form action="${pageContext.request.contextPath}/ManageRoomController" method="post">
                            <div class="container pb-5">
                                <div class="row align-items-start justify-content-center"><h1>Welcome Admin ${ADMIN.getName()}</h1></div>
                                <div class="row align-items-start justify-content-center"><h3>Manage Room</h3></div>
                                <div class="row align-items-center pt-4">
                                  
                                    <div class="col">
                                        <div class="card p-1 mb-2 bg-light text-dark" style="width: 18rem;">
                                            <img src="image/cover.png" class="card-img-top" alt="...">
                                            <div class="card-body">
                                                <h5 class="card-title">Add New Room</h5>
                                                <p class="card-text">Add and create new upcoming event.</p>
                                                <input type="submit" class="btn btn-outline-primary btn-lg" id="command" name="command" value="Add-Room-Form" />
                                            </div>
                                        </div>
                                    </div>
                                  
                                    <div class="col">
                                        <div class="card p-1 mb-2 bg-light text-dark" style="width: 18rem;">
                                            <img src="image/cover.png" class="card-img-top" alt="...">
                                            <div class="card-body">
                                                <h5 class="card-title">Deleting Room</h5>
                                                <p class="card-text">Delete and remove any room.</p>
                                                <input type="submit" class="btn btn-outline-danger btn-lg" id="command" name="command" value="Delete-Room" />
                                            </div>
                                        </div>
                                    </div>
                                  
                                    <div class="col">
                                        <div class="card p-1 mb-2 bg-light text-dark" style="width: 18rem;">
                                            <img src="image/cover.png" class="card-img-top" alt="...">
                                            <div class="card-body">
                                                <h5 class="card-title">Updating Room</h5>
                                                <p class="card-text">Update or modify any room.</p>
                                                <input type="submit" class="btn btn-outline-success btn-lg" id="command" name="command" value="Update-Room" />
                                            </div>
                                        </div>
                                    </div>
                                  
                                    <div class ="col">
                                      <div class="row align-items-start justify-content-center">
                                        <div class="card p-1 mb-2 bg-light text-dark" style="width: 18rem;">
                                            <img src="image/cover.png" class="card-img-top" alt="...">                                        
                                            <div class="card-body">
                                                <h5 class="card-title">View Booked Room</h5>
                                                <p class="card-text">To view booking table, check in and check out customer.<p>
                                                <a href="ManageBookingController?command=View-Booking-Page" class="btn btn-primary stretched-link">View Booking Table</a>
                                            </div>
                                        </div>
                                     </div>
                                    </div>
                                    
                              </div>
                              
                          </div>
                      </form>                                                  


                
            </c:when>
            
            <c:otherwise>
                 <div class="jumbotron">
                        <div class="container">
                          <img src="image/cover.png" alt="Snow" style="width:100%;">
                          <div class="centered" style="color:white;"><h1 class="display-3" >
                                  <b>Welcome to Faiq Hotel</b></h1>
                                  <p>
                             Please Login to proceed
                                  </p>


                          </div>
                        </div>
                 </div>
            </c:otherwise>
            
        </c:choose>
        
      </main>
    
       
     
      <footer class="container">
       
      </footer>
      
      <script src="js/jquery-3.5.1.min.js"></script>
      <script src="js/popper.min.js"></script>      
      <script src="js/bootstrap.min.js"></script>
      <script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>

      <script>
         $('#traveldate').datepicker({
            uiLibrary: 'bootstrap4',
            format: 'dd-mm-yyyy'
         });

         $("form").submit(function(event){
            event.preventDefault();
            alert($("#traveldate").val());
            return false;
         });
      </script>    
      
   </body>   
</html>
