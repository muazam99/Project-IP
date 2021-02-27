<%-- 
    Document   : login
    Created on : Jan 12, 2021, 7:30:24 AM
    Author     : Muaz Amir
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
       <title>Alumni Login System</title>

      <!-- Bootstrap core CSS -->
      <link href="css/bootstrap.min.css" rel="stylesheet">

      <style>
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
      
     <!-- JQUERY -->
      <script
         src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
         crossorigin="anonymous"></script>
        <script type="text/javascript"
                src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js">
                    
        </script>
      
   </head>

   <body>
       
       <jsp:include page="headerNav.jsp" />
       
       <main role="main" class="container">  
            <br><br>
                <div class="row row-cols-1 row-cols-md-3 g-4">
           <div class="col ">
             <div class="card h-100">
                 <img src="image/faiq.jpg" class="card-img-top" alt="..." height="500" width="200">
               <div class="card-body">
                 <h5 class="card-title">Muhammad Faiq Bin Roslan</h5>
                 <p class="card-text">Project Director</p>
               </div>
      
             </div>
           </div>
           <div class="col ">
             <div class="card h-100">
               <img src="image/bob.jpg" class="card-img-top" alt="..." height="500" width="200">
               <div class="card-body">
                 <h5 class="card-title">Muhammad Burhanuddin Bin Ibrahim</h5>
                 <p class="card-text">Group Leader</p>
               </div>
 
             </div>
           </div>
           <div class="col">
             <div class="card h-100">
               <img src="image/muaz.jpg" class="card-img-top" alt="..." height="500" width="200">
               <div class="card-body">
                 <h5 class="card-title">Muaz Bin Amir Nur Rashid</h5>
                 <p class="card-text">UI Designer</p>
               </div>

             </div>
           </div>
         </div>
           <br><br>
        <div class="row row-cols-1 row-cols-md-3 g-4">
           <div class="col">
             <div class="card h-100">
               <img src="image/danial.jpg" class="card-img-top" alt="..." height="500" width="200">
               <div class="card-body">
                 <h5 class="card-title">Muhammad Danial Hakimi </h5>
                 <p class="card-text">Back End Programmer 2</p>
               </div>
 
             </div>
           </div>
           <div class="col">
             <div class="card h-100">
               <img src="image/pijo.jpg" class="card-img-top" alt="..." height="500" width="200">
               <div class="card-body">
                 <h5 class="card-title">Muhammad Hafizul Bin Shukran</h5>
                 <p class="card-text">Back End Programmer</p>
               </div>
     
             </div>
           </div>
           <div class="col">
             <div class="card h-100">
               <img src="image/fikri.jpg" class="card-img-top" alt="..." height="500" width="200">
               <div class="card-body">
                 <h5 class="card-title">Muhammad Fikri Bin Hamidan</h5>
                 <p class="card-text">Project Manager</p>
               </div>
    
             </div>
           </div>
         </div>


       </container>
        <br><br><br><br><br><br><br><br>
        <br><br><br><br><br><br>
       
   </body>   
   

   
   
</html>

