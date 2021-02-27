<%-- 
    Document   : headBarNav
    Created on : Feb 2, 2021, 2:45:46 PM
    Author     : Muaz Amir
--%>

       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--  <jsp:useBean id="user" type="Model.User" scope="session" />--%>

       <head>
                 <!-- Custom styles for this template -->
                <link href="css/navbar-top-fixed.css" rel="stylesheet">
                <link href="https://fonts.googleapis.com/icon?family=Material+Icons"    rel="stylesheet">
                <script src="https://kit.fontawesome.com/4962701fb8.js" crossorigin="anonymous"></script>
              <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
                <!-- Bootstrap core CSS -->
                <link href="css/bootstrap.min.css" rel="stylesheet">
               
       </head>      
       
 <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

         <div class="container">
             <span class="material-icons">
                hotel
                </span>
             
             <a class="navbar-brand" href="#"><b>Faiq Hotel</b></a>
           
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
               <span class="navbar-toggler-icon"></span>
            </button>
            
            <div class="collapse navbar-collapse" id="navbarCollapse">


               <ul class="navbar-nav mr-auto">
                  <li class="nav-item active">
                    <a class="nav-link" href="NavBarController">Home</a>
                  </li>
                  <li class="nav-item active">
                    <a class="nav-link" href="NavBarController?command=About-Us">About us</a>
                  </li>
                  
                
                  <c:choose>
                      <c:when test="${ADMIN != null}">
                          <li class="nav-item active">
                             <a class="nav-link" href="javascript:;">Manage Room</a>
                        </li>
                       
                      </c:when>
                        
                        <c:when test="${CLIENT  != null}">
       
                         </c:when>                   
                  </c:choose>
                  
                 
               </ul>
                  
                  

               <ul class="navbar-nav">
                  
                         
                    
                   
                  
                   
                    <c:choose>
                      <c:when test="${ADMIN != null}">
                         
                       <div class="offset-md-3"> 
                          <li>
                             <form action="ProfileController" method="POST">
                                 <input type="text" name="id" value="${ADMIN.getID()}" hidden="hidden">
                                 <input type="text" name="role" value="${ADMIN.getRole()}" hidden="hidden">
                                  <input type="text" name="option" value="view" hidden="hidden">
                                  <input type="submit" class="btn btn-success" value="My Profile"></button>
                             </form>
                           </li>
                        </div>  
                                  
                          <div class="offset-md-3">
                                <li class="nav-item">
                                  <a href="manageUserController?command=Logout" class="btn btn-danger" role="button" data-bs-toggle="button">Logout</a>
                               </li>  
                           </div>
  
                      </c:when>
                        
                        <c:when test="${CLIENT  != null}">
                         
                        <div class="offset-md-3"> 
                          <li>
                             <form action="ProfileController" method="POST">
                                <input type="text" name="id" value="${CLIENT.getID()}" hidden="hidden">
                                <input type="text" name="role" value="${CLIENT.getRole()}" hidden="hidden">
                                  <input type="text" name="option" value="view" hidden="hidden">
                                  <input type="submit" class="btn btn-success" value="My Profile"></button>
                             </form>
                           </li>
                        </div>  
                            
                         <div class="offset-md-3">
                                <li class="nav-item">
                                  <a href="manageUserController?command=Logout" class="btn btn-secondary" role="button" data-bs-toggle="button">Logout</a>
                               </li>  
                          </div>
                       </c:when>
                      
                        <c:otherwise>
                             <div class="offset-md-3">
                                    <li class="nav-item">
                                           <a href="NavBarController?command=Register-Page" class="btn btn-light" role="button" data-bs-toggle="button">Register</a>
                                    </li>  
                             </div>

                              <div class="offset-md-2">
                                    <li class="nav-item">
                                      <a href="NavBarController?command=Login-Page" class="btn btn-dark" role="button" data-bs-toggle="button">Login</a>
                                    </li>
                             </div>  
                        </c:otherwise>
                        
                  </c:choose>
          
               </ul>
                  
                  
            </div>
         </div>   
      </nav>   
