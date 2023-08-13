package cscorner;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection	("jdbc:mysql://localhost:3306/sunita", "root", "Danang2018@");
			String n = request.getParameter("textName");
			String p = request.getParameter("textPwd");
			PreparedStatement ps =con.prepareStatement("select uname from login where uname =? and password=?");
			ps.setString(1, n);
			ps.setString(2, p);
			ResultSet rs=ps.executeQuery();
				if (rs.next())
				{
				RequestDispatcher rd =request.getRequestDispatcher("/welcome.jsp");
				rd.forward(request, response);
				}
			else {
				out.println("<font color=red size=18>Login Failed!!<br>");
				out.println("<a href=login.jsp>Try AGAIN!! </a>");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}catch (SQLException e) {
				
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
	}

}
