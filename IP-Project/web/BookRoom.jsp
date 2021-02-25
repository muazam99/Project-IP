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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book A Room</title>
           <!-- Bootstrap core CSS -->
      <link href="css/bootstrap.min.css" rel="stylesheet">
    </head>
    
      <%  
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = df.format(new Date());
        %>
    
    <body>
        
        <jsp:include page="headerNav.jsp" />  
        
        
        <main role="main" class="container">  
        <div class="row">
                        <div class="col-md-12">
                            <div class="text-center">              
                                <div class="card shadow">
                                    <div class="text-center">
                                       <div class="card-body">     
                                           <form class="d-flex" name="Search Room" action="${pageContext.request.contextPath}/ManageBookingController" method="post">  
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
                                                                <input type="date" class="form-control" id="date_in" name="traveldateIn" min="<%=formattedDate%>" required>
                                                            </td>
                                                            <td>
                                                                <input type="date" class="form-control" id="date_out" name="traveldateOut" min="<%=formattedDate%>" required>
                                                            </td>
                                                            <td>
                                                                <input type="number" class="form-control" id="guest_adult" name="guest_adult" min="1" max="4" value="1">
                                                            </td>
                                                            <td>
                                                                <input type="submit" class="btn btn-primary" name="command" id="command" value="Search For Rooms">
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
        </main>
    </body>
    
</html>
