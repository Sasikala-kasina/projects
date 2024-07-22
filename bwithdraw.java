package pack2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class bwithdraw
 */
@WebServlet("/bwithdraw")
public class bwithdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bwithdraw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String a=request.getParameter("n1");
		String b=request.getParameter("n2");
		int c=Integer.parseInt(a);
		float d=Float.parseFloat(b);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","12345");
			PreparedStatement ps1=con.prepareStatement("select balance from bank where acc_no=?");
			ps1.setInt(1, c);
			ResultSet rs=ps1.executeQuery();
			float e=0;
			while(rs.next())
			{ 
				e=rs.getFloat(1);
			}
			float f=e-d;
			if(f>=0)
			{
				PreparedStatement ps2=con.prepareStatement("update bank set balance=? where acc_no=?");
				ps2.setFloat(1, f);
				ps2.setInt(2, c);
				ps2.executeUpdate();
				PrintWriter pw=response.getWriter();
				pw.println("withdraw successfull");
				pw.print("Balance amount is :"+f);
				
			}
			else
			{
				PrintWriter pw1=response.getWriter();
				pw1.println("You have less amount...Please check the balance");
			InvalidFunds obj=new InvalidFunds("You have less amount...Please check the balance");
				throw obj;
				
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
