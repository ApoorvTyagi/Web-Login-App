import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
public class ProfileServlet extends HttpServlet {  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                      throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
          
        HttpSession session=request.getSession(false);  
        if(session!=null){  
        String name=(String)session.getAttribute("name");  
        request.getRequestDispatcher("link.html").include(request, response);   
        out.print("Hello, "+name+" Welcome to Your Profile\n");  
        out.println("<html>");
        out.println("<body>");
        out.println("<br><br><a href=\"update.html\">Update Your Info</a>");
        out.println("<br><br><a href=\"view\">View Your Info</a>");
        out.println("</body></html>");  
        }  
        else{  
            out.print("Please login first");  
            request.getRequestDispatcher("login.html").include(request, response);  
        }  
        out.close();  
    }  
}  