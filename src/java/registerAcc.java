
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class registerAcc extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String n=request.getParameter("username");  
            String p=request.getParameter("userpass");  
            
        int count=0;
        Class.forName("com.mysql.jdbc.Driver");  
        Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/loginapp","root","");  
        Statement stmt=c.createStatement();  
        ResultSet rs=stmt.executeQuery("select userName from details");  
        
        while(rs.next())  {
        if(n.equals(rs.getString(1))){
                request.getRequestDispatcher("link.html").include(request, response);
                out.print("Username Exist, Please Choose Different User Name");
                count=1;
                break;
        }   
        }
        if(count!=0){
            request.getRequestDispatcher("link.html").include(request, response);
            java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/loginapp","root","");  
            Statement st=con.createStatement();
            st.executeUpdate("insert into details values ('"+n+"','"+p+"')");

            java.sql.Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
            Statement s=conn.createStatement();
            s.executeUpdate("insert into student_details (Name) values ('"+n+"')");
            out.print("You account is now Registered...");        
        }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }  
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
