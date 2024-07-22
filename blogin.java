package pack2;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class blogin
 */
@WebServlet("/blogin")
public class blogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public blogin() {
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
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","12345");
			PreparedStatement pt=con.prepareStatement("select acc_no,pw from bank");
			ResultSet rs=pt.executeQuery();
			int c=0;
			int a=Integer.parseInt(s1);
			int b=Integer.parseInt(s2);
			while(rs.next())
			{
				if((rs.getInt(1)==a)&&(rs.getInt(2)==b))
				{
					c++;
					RequestDispatcher rds=request.getRequestDispatcher("boptions.html");
					rds.forward(request, response);
					break;
				}
			}
			if(c==0)
			{
				PrintWriter pw=response.getWriter();
				pw.print("Credetians are Wrong...please enter valid details");
				RequestDispatcher rd=request.getRequestDispatcher("eindex.html");
				rd.include(request,response);
			}
			
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
