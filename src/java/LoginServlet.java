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
import javax.servlet.http.HttpSession;  
public class LoginServlet extends HttpServlet {  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                    throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        request.getRequestDispatcher("link.html").include(request, response);  
          
        String name=request.getParameter("name");  
        String password=request.getParameter("password");  
        try{   
        HttpSession session=request.getSession(); 
        int count=0;
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/loginapp","root","");  
        Statement stmt=con.createStatement();  
        ResultSet rs=stmt.executeQuery("select userName,password from details");  
        while(rs.next())  {
        if(name.equals(rs.getString(1)) && password.equals(rs.getString(2)))
        {
        count=1;    
        out.print("Welcome, "+name);       
        session.setAttribute("name",name);  
        break;
        }  
        }
        if(count!=1){  
            out.print("Sorry, username or password error!");  
            request.getRequestDispatcher("login.html").include(request, response);  
        }
                      
        out.close(); 
        }catch(Exception e){
            out.print(e);
        }
    }  
}  