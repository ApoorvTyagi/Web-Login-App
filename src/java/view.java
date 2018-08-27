

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tyagi
 */
public class view extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            request.getRequestDispatcher("link.html").include(request, response); 
            HttpSession session=request.getSession(false);  
            if(session!=null){  
            String username=(String)session.getAttribute("name");  
            out.print("Hello, "+username+" Your Prfile Below:"); 
            Class.forName("com.mysql.jdbc.Driver");  
            
            java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
            Statement st=con.createStatement();
            //HAD GREAT PROBLEM TO FIND NEXT LINE name='"+username+"':)
            ResultSet rs=st.executeQuery("select Name,City,Email,Mobile from student_details where Name='"+username+"'");         
 

            while(rs.next()){
                out.println("<html>");
                out.println("<body>");
                out.println("<br>");
                out.println("<br>");
                out.println("</body></html>");  
                
                out.println("\nCity:"+rs.getString(1));
                out.println("<html>");
                out.println("<body>");
                out.println("<br>");
                out.println("<br>");
                out.println("</body></html>");  
                
                out.println("\nEmail:"+rs.getString(2));
                out.println("<html>");
                out.println("<body>");
                out.println("<br>");
                out.println("<br>");
                out.println("</body></html>");  
                
                out.println("\nMobile:"+rs.getString(3));  
            }
            }
            else{
            out.print("Please login first");  
            request.getRequestDispatcher("login.html").include(request, response);  
            }
        }catch(Exception e){
            out.print(e);
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
