<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Alumni Login System</title>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/password.css" rel="stylesheet">

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
    </head>

    <body>

        <jsp:include page="headerNav.jsp" />

        <main role="main" class="container">



            <div class="row">
                <div class="col"></div>

                <div class="col-md-6">

                    <div class="card shadow">
                        <div class="card-body">
                            <h2>Register To Continue Booking</h2>
                            <br>
                            <form action="manageUserController?command=Register" method="post">

                       <label for="floatingPassword"><b>Name</b></label>
                                <div class="input-group mb-3">            
                                    <input type="text" class="form-control" placeholder="Full Name" name="name" required>
                                </div>

                                <div class="form-floating">
                                    <label for="floatingPassword"><b>Password</b></label>
                                    <input type="password" class="form-control" placeholder="Password" name="password" required>  
                                </div>

                                <div class="form-floating">
                                    <label for="floatingInput"><b>Email</b></label>
                                    <input type="text" class="form-control" id="floatingInput" placeholder="name@example.com" name="email">           
                                </div>

                                <div class="form-floating">
                                    <label for="floatingInput"><b>Phone No.</b></label>
                                    <input type="text" class="form-control" id="floatingInput" placeholder="0123456789" name="phoneNo">           
                                </div>

                               


                                <br>
                                <button type="submit" class="btn btn-primary">Register</button>   Already has an account? 
                                <a href="NavBarController?command=Login-Page"><button type="button" class="btn btn-danger" >Sign In</button></a>
                            </form>
                        </div>
                    </div>

                </div>
                <div class="col"></div>
            </div>

        </main>

        <footer class="container">
            <br />

        </footer>

        <script src="js/jquery-3.5.1.min.js"></script>
        <script src="js/popper.min.js"></script>      
        <script src="js/bootstrap.min.js"></script>
        <script src="js/password.min.js"></script>




    </body>   
</html>
