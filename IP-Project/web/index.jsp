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
                    <a class="nav-link" href="javascript:;">Home</a>
                  </li>
                  <li class="nav-item active">
                    <a class="nav-link" href="javascript:;">About us</a>
                  </li>
               
               </ul>

               <ul class="navbar-nav">
                   
                  <li class="nav-item">
                    <a href="#" class="btn btn-light" role="button" data-bs-toggle="button">Register</a>
                  </li>      
                  
                  <li class="nav-item">
                    <a href="#" class="btn btn-dark" role="button" data-bs-toggle="button">Login</a>
                  </li>
                                  
               </ul>
            </div>
         </div>   
      </nav>     

    

       

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
         
         </div>
         
         <main role="main" class="container">       

         <div class="row">
             <div class="col-md-4">
                 
             </div>
             
             <div class="col-md-3">
                 <div class="text-center">              
                     <div class="card shadow">
                          <div class="text-center">
                         <div class="card-body">     
                            <form class="d-flex" action="#" method="post">      
                               
                                     <div class="mb-6">
                                        <label for="exampleInputEmail1" class="form-label">Travel Date</label>
                                           <input type="date" class="form-control" id="date" name="traveldate">
                                           <br>
                                           <button type="submit" class="btn btn-primary">Search For Rooms</button>                  
                                      </div>
                                
                            </form>    
                            </div>      
                     </div>  
                 </div>  
               </div>
             </div>
             <div class="col-md-4">
                 
             </div>
             
         </div>

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
