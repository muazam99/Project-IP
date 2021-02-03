
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

   <body>

       <jsp:include page="headerNav.jsp" />

         <!-- Main jumbotron for a primary marketing message or call to action -->
         <div class="jumbotron">
                <div class="container">
                  <img src="image/cover.png" alt="Snow" style="width:100%;">
                  <div class="centered" style="color:white;"><h1 class="display-3" >
                          <b>Welcome to Faiq Hotel</b></h1>
                          <p>
                     Find deals on hotels, home and much more....
                          </p>


                  </div>
                </div>

            </div>
         
         
         <main role="main" class="container">       

             
        <%  
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = df.format(new Date());
        %>
         <div class="row">
             
             <div class="col-md-12">
                 <div class="text-center">              
                     <div class="card shadow">
                         <div class="text-center">
                            <div class="card-body">     
                                <form class="d-flex" action="${pageContext.request.contextPath}/ManageBookingController" method="post">  
                                    
                                            <div class="offset-md-2">
                                            <table>
                                                    <tr>
                                                        <th>
                                                            <label for="exampleInputEmail1" class="form-label">Check in</label>
                                                        </th>
                                                        <th>
                                                            <label for="exampleInputEmail1" class="form-label">Check out</label>
                                                        </th>
                                                        <th colspan="2">
                                                            <label for="exampleInputEmail1" class="form-label">Guest</label>
                                                        </th>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <input type="date" class="form-control" id="date_in" name="traveldateIn" min="<%=formattedDate%>" >
                                                        </td>
                                                        <td>
                                                            <input type="date" class="form-control" id="date_out" name="traveldateOut" min="<%=formattedDate%>">
                                                        </td>
                                                        <td>
                                                            <input type="number" class="form-control" id="guest_adult" name="guest_adult" min="1" max="5" value="2">
                                                        </td>
                                                        <td>
                                                            <input type="number" class="form-control" id="guest_child" name="guest_child" min="0" max="5" value="0">
                                                        </td>
                                                        <td>
                                                            <input type="submit" name="command" id="Search For Rooms" value="Search For Rooms">
                                                        </td>
                                                    </tr>
                                                 </table> 
                                              </div>                                  
                              </form>    
                         </div>      
                       </div>  
                    </div>  
                  </div>
                </div>
             
         </div>
             
        <div class="row">
            
        </div>

      </main>
    
       <%--untuk admin--%>                                                    
      <form action="${pageContext.request.contextPath}/ManageRoomController" method="post">
        <div class="container pb-5">
            <div class="row align-items-start justify-content-center"><h1>Manage Room</h1></div>
            <div class="row align-items-center pt-4">
                <div class="col">
                    <div class="card p-3 mb-2 bg-light text-dark" style="width: 18rem;">
                        <img src="image/cover.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Add New Room</h5>
                            <p class="card-text">Add and create new upcoming event.</p>
                            <input type="submit" class="btn btn-outline-primary btn-lg" name="command" value="Add Room Form" />
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card p-3 mb-2 bg-light text-dark" style="width: 18rem;">
                        <img src="image/cover.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Deleting Room</h5>
                            <p class="card-text">Delete and remove any room.</p>
                            <input type="submit" class="btn btn-outline-danger btn-lg" name="command" value="Delete Room" />
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card p-3 mb-2 bg-light text-dark" style="width: 18rem;">
                        <img src="image/cover.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Updating Room</h5>
                            <p class="card-text">Update or modify any room.</p>
                            <input type="submit" class="btn btn-outline-success btn-lg" name="command" value="Update Room" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="row align-items-end">
                <div class="d-grid gap-2 col-2 mx-auto pt-2">
                    <a class="btn btn-success btn-lg" href="MainStaffInterface.jsp" role="button">Main Menu</a>                                           
                </div>
            </div>
        </div>
    </form>                                                  
                                                        

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
