/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Client;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;
import jdbc.JDBCutility;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 *
 * @author hafizul
 */
@WebServlet(name = "editProfileController", urlPatterns = {"/editProfileController"})
public class ProfileController extends HttpServlet {
    
    private JDBCutility jdbcUtility;   
    private Connection con;
    
    private static final long serialVersionUID = 1L;
     
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "image";
 
    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB 
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    public void init() throws ServletException{
        
         String driver = "com.mysql.jdbc.Driver"; 
        String dbName = "faiqhoteldb";
        String url = "jdbc:mysql://localhost:3306/" + dbName + "?";
        String userName = "root";
        String password = "";

        jdbcUtility = new JDBCutility(driver,
                                      url,
                                      userName,
                                      password);

        jdbcUtility.jdbcConnect();
        
        //get JDC connection
        con = jdbcUtility.jdbcGetConnection();
        
        //prepare the statement once only
        //for the entire servlet lifecycle
        jdbcUtility.prepareSQLStatementRegister();
             
    }
    
    @Override
    
    public void destroy(){
        jdbcUtility.jdbcConClose();
    }
    
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }
     
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String option = request.getParameter("option");
        
        if(option==null){
            option="";
        }
        
        try{
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0);
            
            switch(option){
                
                case "view":
                    view(request , response);
                     break;
                
                case "edit":
                    edit(request , response);
                    break;
                    
                default :
                   request.getRequestDispatcher("index.jsp").forward(request, response);
                   break;
            }
        }catch (Exception exc) {
            throw new ServletException(exc);
        }
        
    }
    
    public void view(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        HttpSession session = request.getSession();
        
                PreparedStatement preparedStatement = null;
                String status = "";
                try{
                    
                    String queryClient = "SELECT * FROM user"; 
                    preparedStatement = con.prepareStatement(queryClient);
                    
                    ResultSet rs = preparedStatement.executeQuery();
                    while(rs.next()){
                        Client c = new Client();
                        c.setName(rs.getString("name"));
                        c.setEmail(rs.getString("email"));
                        session.setAttribute("client", c);
                        status = "SUCCESS";
                    }
                    
                    
                }catch(SQLException e){

                }
                if(status.equals("SUCCESS"))   //On success, you can display a message to user on Home page
                {
                    request.getRequestDispatcher("/profile.jsp").forward(request, response);
                }
                else
                {
                    request.setAttribute("errMessage", status);
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
        
    }
    
    public void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
                // checks if the request actually contains upload file
            if (!ServletFileUpload.isMultipartContent(request)) {
                // if not, we stop here
                PrintWriter writer = response.getWriter();
                writer.println("Error: Form must has enctype=multipart/form-data.");
                writer.flush();
                return;
            }

            // configures upload settings
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // sets memory threshold - beyond which files are stored in disk
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            // sets temporary location to store files
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);

            // sets maximum size of upload file
            upload.setFileSizeMax(MAX_FILE_SIZE);

            // sets maximum size of request (include file + form data)
            upload.setSizeMax(MAX_REQUEST_SIZE);

            // constructs the directory path to store upload file
            // this path is relative to application's directory
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;        

            // creates the directory if it does not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String fileName = "";   
            String extension = "";
            String fullname = "";
            try {
                // parses the request's content to extract file data
                @SuppressWarnings("unchecked")
                List<FileItem> formItems = upload.parseRequest(request);

                if (formItems != null && formItems.size() > 0) {
                    // iterates over form's fields
                    for (FileItem item : formItems) {
                        // processes file form field
                        if (!item.isFormField()) {
                            fileName = new File(item.getName()).getName();
                            String filePath = uploadPath + File.separator + fileName;
                            File storeFile = new File(filePath);

                            int index = fileName.lastIndexOf(".");                        
                            if(index > 0){
                                extension = fileName.substring(index+1);
                                extension = extension.toLowerCase();
                            }

                            // saves the file on disk
                            item.write(storeFile);
                        } else {
                            //process text form field
                            String field = item.getFieldName();

                            if (field.equals("fullname")) {
                                fullname = item.getString();
                            }
                        }
                    }
                }

            } 
            catch (Exception ex) {
            }
            String name = "John";
            String email = "gmail.com";
            try{
                PreparedStatement ps = null;

                Client c = new Client();
                c.setPicture(fileName);
                c.setName(name);
                int uploadStatus = 0;

                    String query = "INSERT INTO client (name) VALUES (?) ";
                    ps = con.prepareStatement(query);
                    ps.setString(1, c.getName());
    //                ps.setString(2, c.getPicture());

                    uploadStatus = ps.executeUpdate();
                    PrintWriter out = response.getWriter();
                    if(uploadStatus == 1){
                        out.println("<script>");
                        out.println("    alert('User Registration Success');");
                        out.println("    window.location = '/IP-Project/profile.jsp'");
                        out.println("</script>");
                    }
                }
                catch(SQLException ex){
                    while (ex != null) {
                        System.out.println ("SQLState: " + ex.getSQLState ());
                        System.out.println ("Message:  " + ex.getMessage ());
                        System.out.println ("Vendor:   " + ex.getErrorCode ());
                        ex = ex.getNextException ();
                        System.out.println ("");
                    }

                    PrintWriter out = response.getWriter();

                    out.println("<script>");
                    out.println("    alert('User Registration Failed');");
                    out.println("    window.location = '/IP-Project/editProfile.jsp'");
                    out.println("</script>");            
                }
                catch (java.lang.Exception ex){
                    ex.printStackTrace ();

                    PrintWriter out = response.getWriter();

                    out.println("<script>");
                    out.println("    alert('User Registration Failed 2');");
                    out.println("    window.location = '/IP-Project/editProfile.jsp'");
                    out.println("</script>");
                }
            
            }
        }

