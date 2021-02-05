/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Client;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdbc.JDBCutility;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author hafizul
 */
@WebServlet(name = "profileControl", urlPatterns = {"/profileControl"})
public class profileControl extends HttpServlet {
    
    private JDBCutility jdbcUtility;   
    private Connection con;
    
    private static final long serialVersionUID = 1L;
     
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "image";
 
    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB 
    
    
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
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String option = request.getParameter("option");
        
        if(option == null){
            option = "";
        }
        
        switch(option){
            case "view" : 
                viewProfile(request , response);
                break;
                
            case "edit" :
                editProfile(request , response);
                break;
                
            case "upload" :
                uploadProfile(request , response);
                break;
                
            default :
                viewProfile(request , response);
                break;
        }
        
    }
            
    public void viewProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        String status = "";
        HttpSession session = request.getSession();
        
        try{
            String viewProfile = "SELECT * FROM user WHERE userID = 1 ";
                PreparedStatement preparedStatement = con.prepareStatement(viewProfile);

                ResultSet rs = preparedStatement.executeQuery();

                Client c = new Client();

                while(rs.next()){
                    String userID = String.valueOf(rs.getInt("userID"));
                    String name = rs.getString("name");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    String phone = rs.getString("phoneNo");
                    String role = rs.getString("role");
                    
                    c.setID(userID);
                    c.setName(name);
                    c.setPassword(password);
                    c.setEmail(email);
                    c.setPhoneNo(phone);
                    c.setRole(role);

                    session.setAttribute("user", c);
                    status = "SUCCESS";
                }
            }catch(SQLException e){
                status = "INVALID ACCOUNT";
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
          
    public void editProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        Client c = new Client();
        String status = "";
        PreparedStatement psEdit = null;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        
        String userID = c.getID();
        String editProfile = "UPDATE user SET password = ?, email = ?, phoneNo = ? WHERE userID = 1";
        
        try{
            
             psEdit = con.prepareStatement(editProfile);
            
            psEdit.setString(1, password);
            psEdit.setString(2, email);
            psEdit.setString(3, phone);
            
            
            int statusSQL = psEdit.executeUpdate();
            psEdit.close();
            
            if(statusSQL == 1){
                request.getRequestDispatcher("/profileControl?option=view'").forward(request, response);
//                try(PrintWriter out = response.getWriter()){
//                      out.println("<script>");
//                    out.println("  alert('Edit Success');");
//                    out.println("    window.location = '" + request.getContextPath() + "/profileControl?option=view'");
//                    out.println("</script>");
//                 }
            }else{
                request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
//                try(PrintWriter out = response.getWriter()){
//                      out.println("<script>");
//                    out.println("  alert('Edit Fail');");
//                    out.println("    window.location = '" + request.getContextPath() + "editProfile.jsp");
//                    out.println("</script>");
//                }
            }
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
                status = "INVALID ACCOUNT";
                request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        
    }

    public void uploadProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
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
                
                request.getRequestDispatcher("/profileControl?option=view'").forward(request, response);

            } 
            catch (Exception ex) {
            }
    }
}
