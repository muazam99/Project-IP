<%-- 
    Document   : AddRoomInterface
    Created on : Feb 3, 2021, 9:36:29 PM
    Author     : Muhammad Faiq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Room</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel='stylesheet' href='style.css'/>
        
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/main.css">
        <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
    </head>
    <body>
      
         <jsp:include page="headerNav.jsp" /> 
        
        <form  name="AddRoomform" method="post" action="ManageRoomController" enctype="multipart/form-data">
            
            <div class="container pb-5">
            <div class="row align-items-start justify-content-center"></div>
            <div class="row align-items-center pt-4">
                <div class="col">
                    <div class="card mb-2 bg-light text-dark" style="width: auto;">
                        <div class="card-body">
                            <h4 class="card-title" style="text-align: center;"><b>Add New Room</b></h4>
                            <div class="row g-2 p-2">
                                <div class="col">
                                  <div class="form-floating">
                                    <label for="floatingInput">Room Number</label>
                                    <input type="text" class="form-control" id="roomNo" placeholder="Room Number" name="roomNo" size="100" required>                                    
                                  </div>
                                </div>
                                <div class="col-md">
                                  <div class="form-floating">
                                    <label for="floatingInput">Room Type</label>
                                    <select class="form-control" id="roomType" placeholder="Room Type" name="roomType" required>
                                        <option></option>
                                        <option value="Single">Single</option>
                                        <option value="Double">Double</option>
                                        <option value="Triple">Triple</option>
                                        <option value="Quad">Quad</option>
                                    </select>
                                  </div>
                                </div>
                            </div>
                            <div class="row g-2 p-2">
                                <div class="col">
                                  <div class="form-floating">
                                    <label for="floatingInput">Room Fee</label>
                                    <input type="text" class="form-control" id="roomPrice" placeholder="Room Fee" name="roomPrice" size="100" required>                                    
                                  </div>
                                </div>
                                <div class="col-md">
                                  <div class="form-floating">
                                    <label for="floatingSelect">Room Image</label>
                                    <input type="file" class="form-control" id="roomImage" placeholder="Image of the Room" name="roomImage" size="100" required>                                    
                                  </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="d-grid gap-2 col-3 mx-auto pt-1">
                        <input class="btn btn-warning" type="reset" id="buttonreset" name="buttonreset" value="Reset Form">
                        <input class=" btn btn-primary" type="submit" id="command" name="command" value="Add-Room">                                            
                    </div>
                    <div class="d-grid gap-2 col-2 mx-auto pt-2">
                        <a class="btn btn-success" href="index.jsp" role="button">Room Menu</a>                                           
                    </div>
                </div>
            </div>
            <div class="row align-items-end"></div>
        </div>
            
    </form>
        
        
        
    </body>
</html>
