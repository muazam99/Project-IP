<%-- 
    Document   : paymentAfterBook
    Created on : Feb 7, 2021, 3:46:46 AM
    Author     : Muhammad Faiq
--%>

<%@page import="Model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                width: 80%;
                padding: 10px 0 10px 150px;
            }
            .header-home {
                text-align: center;
            }
            @media only screen and (max-width: 600px) {.container-custom {padding-left: 10px; padding-top: 110px;}}
        </style>
    </head>
    <body>
        
        <div class="container-custom">
            
            <nav class="navbar navbar-dark bg-dark">
                <div style="height:36px">
                    <p class="navbar-brand">JOIN EVENT</p>
                </div>
            </nav>
            
            <div class="jumbotron" style="padding-top:25px;padding-bottom: 10px;">
                
    
    <% 
        String roomType = (String)session.getAttribute("roomType");
        String traveldateIn=(String)session.getAttribute("traveldateIn");
        String traveldateOut=(String)session.getAttribute("traveldateOut");
        double totalPrice = (Double)session.getAttribute("totalPrice");
        Client client = (Client)session.getAttribute("CLIENT");
    %>
    
    
    <form  name="AddParticipantform" method="post" action="${pageContext.request.contextPath}/ManageBookingController" enctype="multipart/form-data">
            <div class="container pb-5">
            <div class="row align-items-start justify-content-center"></div>
            <div class="row align-items-center pt-4">
                <div class="col">
                    <div class="card mb-2 bg-light text-dark" style="width: auto;">
                        <div class="card-body">
                            <div class="row g-2 p-2">
                                <div class="col">
                                  <div class="form-floating">
                                    <label for="floatingInput">Name</label>
                                    <input type="text" class="form-control-plaintext" id="floatingInput" placeholder="Name" name="clientName" value="<%=client.getName()%>" size="100" readonly required>                                    
                                  </div>
                                </div>
                                <div class="col-md">
                                  <div class="form-floating">
                                    <label for="floatingInput">Total Price</label>
                                    <input type="text" class="form-control-plaintext" id="floatingInput" placeholder="Total Price " name="totalPrice" value="<%=totalPrice %>" size="100" readonly required>                                    
                                  </div>
                                </div>
                            </div>
                            <div class="row g-2 p-2">
                                <div class="col">
                                  <div class="form-floating">
                                    <label for="floatingInput">Email Address</label>
                                    <input type="text" class="form-control" id="floatingInput" placeholder="Email Address " name="clientEmail" value="<%=client.getEmail()%>" size="100" required>                                    
                                  </div>
                                </div>
                                <div class="col-md">
                                  <div class="form-floating">
                                    <label for="floatingInput">Phone Number</label>
                                    <input type="text" class="form-control" id="floatingInput" placeholder="Phone Number " name="clientPhoneNo" value="<%=client.getPhoneNo()%>" size="100" required>                                    
                                  </div>
                                </div>
                            </div>
                            <div class="row g-2 p-2">
                                <div class="col">
                                  <div class="form-floating">
                                    <label for="floatingInput">Proof of Payment</label>
                                    <input type="file" class="form-control" style="height:41px;" id="floatingInput" placeholder="Upload Proof of Payment" name="bookImgProofImage" size="100" required>                                    
                                  </div>
                                </div>
                            </div>
                            <input type="hidden" name="traveldateIn" value=<%=traveldateIn %> >
                            <input type="hidden" name="traveldateOut" value=<%=traveldateOut%> >
                            <input type="hidden" name="roomType" value=<%=roomType %> >
                            <input type="hidden" name="totalPrice" value=<%=totalPrice%> >
                            <input type="hidden" name="clientID" value=<%=client.getID()%> >
                        </div>
                    </div>
                    <div class="d-grid gap-2 mx-auto pt-1" style="width:250px;display: block;margin-left: auto;margin-right: auto;">
                        <input class="btn btn-warning" type="reset" style="width:45%" id="button2" name="buttonreset" value="Reset Form">
                        <input class=" btn btn-primary" type="submit" style="width:45%" id="command" name="command" value="Payment">                                            
                    </div>
                </div>
            </div>
            </div>
    </form>
  
    <form  name="menubutton" method="post" action="${pageContext.request.contextPath}/ManageBookingController" enctype="multipart/form-data">
        <div class="d-grid gap-2 col-1 mx-auto pt-1"> 
            <input type="hidden" name="clientID" value=<%=client.getID()%> >
            <input class=" btn btn-success" type="submit" id="button1" name="command" value="Back"> 
        </div>
    </form>
  </div>
</div>

    </body>
</html>
