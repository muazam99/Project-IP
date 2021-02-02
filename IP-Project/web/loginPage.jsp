<%-- 
    Document   : loginPage
    Created on : Jan 25, 2021, 3:16:31 PM
    Author     : Muaz Amir
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

    </head>
    <body>
        
        
                    <div class="wrapper fadeInDown">
              <div id="formContent">
                <!-- Tabs Titles -->

                <!-- Icon -->
                <div class="fadeIn first">
                  <img src="http://danielzawadzki.com/codepen/01/icon.svg" id="icon" alt="User Icon" />
                </div>

                <!-- Login Form -->
                <form>
                  <input type="text" id="login" class="fadeIn second" name="login" placeholder="login">
                  <input type="text" id="password" class="fadeIn third" name="login" placeholder="password">
                  <input type="submit" class="fadeIn fourth" value="Log In">
                </form>

                <!-- Remind Passowrd -->
                <div id="formFooter">
                  <a class="underlineHover" href="#">Forgot Password?</a>
                </div>

              </div>
            </div>
       
    </body>
</html>