package pack2;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class bregister
 */
@WebServlet("/bregister")
public class bregister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bregister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String s1=request.getParameter("n1");
		String s2=request.getParameter("n2");
		String s3=request.getParameter("n3");
		String s4=request.getParameter("n4");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","12345");
			PreparedStatement pt=con.prepareStatement("insert into bank values(?,?,?,?)");
			int a=Integer.parseInt(s2);
			int b=Integer.parseInt(s3);
			int c=Integer.parseInt(s4);
			pt.setString(1, s1);
			pt.setInt(2, a);
			pt.setInt(3, b);
			pt.setInt(4, c);
			pt.executeQuery();
			PrintWriter pw=response.getWriter();
			pw.println("***Registration successfull***");
			RequestDispatcher rd=request.getRequestDispatcher("blogin.html");
			rd.include(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
