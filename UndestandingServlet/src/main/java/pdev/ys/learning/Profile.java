package pdev.ys.learning;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Profile
 */
@WebServlet(name="Profile",urlPatterns={"/checkProfile"})
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Inside doGet of Profile");
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("user") != null){
			//System.out.println("found user"+pCk.getValue());
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/profile.jsp");
			String uName = (String)session.getAttribute("user");
			request.setAttribute("userName",uName);
			rd.forward(request, response);
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
			PrintWriter out=response.getWriter();  
			out.print("<b>You are not logged-in, Please login and try again!</b>");  
			rd.include(request, response);
		}
		
		/*
		Cookie[] ck = request.getCookies();
		for(Cookie pCk : ck){
			System.out.println("cookie details:"+pCk.getValue() + pCk.getName());
			if(pCk.getName().equals("user") && !pCk.getValue().isEmpty()){
				System.out.println("found user"+pCk.getValue());
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/profile.jsp");
				request.setAttribute("userName",pCk.getValue());
				request.setAttribute("userName",uName);
				rd.forward(request, response);
			}else{
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
				PrintWriter out=response.getWriter();  
				out.print("<b>You are not logged-in, Please login and try again!</b>");  
				rd.include(request, response);
			}
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Inside doPost of Profile");
	}

}
