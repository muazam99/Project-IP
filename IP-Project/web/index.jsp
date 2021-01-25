<!doctype html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <meta name="description" content="">
      <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
      <meta name="generator" content="Jekyll v4.1.1">
      <title>Fixed top navbar Bootstrap template</title>

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
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons"    rel="stylesheet">
   </head>

   <body>

      <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

         <div class="container">
             <span class="material-icons">
                hotel
                </span>
             
             <a class="navbar-brand" href="#"><b>Marriott Hotel</b></a>
           
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
               <span class="navbar-toggler-icon"></span>
            </button>
            
            <div class="collapse navbar-collapse" id="navbarCollapse">


               <ul class="navbar-nav mr-auto">
                  <li class="nav-item active">
                    <a class="nav-link" href="javascript:;">Home</a>
                  </li>
                  <li class="nav-item active">
                    <a class="nav-link" href="javascript:;">Hotel List</a>
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

      <main role="main" class="container">

       

         <!-- Main jumbotron for a primary marketing message or call to action -->
         <div class="jumbotron">
            <div class="container">
               <h1 class="display-3">Welcome to Marriott</h1>
               <p>
                   Find deals on hotels, home and much more....
               </p>
              
               <form class="d-flex" action="#" method="post">     
                           
                            <input class="form-control me-2" type="search" placeholder="Where Are you going?" aria-label="Search">
                            <input class="form-control me-2" type="datetime-local" placeholder="Input your date" aria-label="Search">
                           <button class="btn btn-primary" type="submit">Search</button>    
                           
                  </form>
               
            </div>
         </div>

         <div class="row">
             <div class="row justify-content-around">
            <div class="col-md-2">
                            
              <div class="card" style="width: 18rem;">
                    <img src="https://upload.wikimedia.org/wikipedia/commons/f/fc/Moonrise_over_kuala_lumpur.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                      <h5 class="card-title">Kuala Lumpur</h5>
                      <p class="card-text">A happening city that never sleeps.</p>
                      <a href="#" class="btn btn-primary">Look hotels in Kuala Lumpur</a>
                    </div>
                  </div>
            </div>
             
            <div class="col-md-2">
             
             <div class="card" style="width: 18rem;">
                    <img src="https://media.tacdn.com/media/attractions-splice-spp-674x446/06/6f/40/7e.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                      <h5 class="card-title">Melaka</h5>
                      <p class="card-text">A historical city</p>
                      <a href="#" class="btn btn-primary">Look hotels in Melaka</a>
                    </div>
                  </div>
            </div>
                 
           <div class="col-md-2">  
             <div class="card" style="width: 18rem;">
                    <img src="https://cdn.theculturetrip.com/wp-content/uploads/2017/08/shutterstock_660444688.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                      <h5 class="card-title">Langkawi</h5>
                      <p class="card-text">A beauty you never seen before</p>
                      <a href="#" class="btn btn-primary">Look hotels in Langkawi</a>
                    </div>
                  </div>
            </div>
                 
             
             
             </div>
         </div>

      </main>

      <footer class="container">
       
      </footer>
      
      <script src="js/jquery-3.5.1.min.js"></script>
      <script src="js/popper.min.js"></script>      
      <script src="js/bootstrap.min.js"></script>
      <script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>
   </body>   
</html>
