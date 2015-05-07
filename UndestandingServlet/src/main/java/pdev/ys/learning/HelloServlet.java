package pdev.ys.learning;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HelloServlet
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private ServletConfig config = null;
	
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Initialising the servlet");
		this.config = config;
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Destroying the servlet");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Inside Service");
	}*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Inside doGet");
		
		if(getServletConfig() == null){
			System.out.println("Servlet config is null");
		}else{
			System.out.println("Servlet config is not null");
		}
		
		PrintWriter out=response.getWriter();  
		out.print("<html><body>");  
		out.print("<b>hello generic servlet</b>");  
		out.print("</body></html>"); 
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Inside first doPost");
		
		RequestDispatcher rd = request.getRequestDispatcher("/LoginServlet");
		rd.include(request, response);
		if((Boolean)request.getAttribute("validate")){
			System.out.println("login true");
			rd = request.getRequestDispatcher("/jsp/welcome.jsp");
			String userName=request.getParameter("userName");
			if(userName != ""){
				HttpSession session = request.getSession(true);
				session.setAttribute("user", userName);
				//Cookie ck=new Cookie("user",userName);//creating cookie object  
				//response.addCookie(ck);//adding cookie in the response  
				request.setAttribute("userName",userName);
			}
			rd.forward(request, response);
		}else{
			System.out.println("login false");
			HttpSession session = request.getSession();
			session.invalidate();
			rd = request.getRequestDispatcher("/jsp/index.jsp");
			//Cookie ck=new Cookie("user","");//deleting value of cookie  
			//ck.setMaxAge(0);//changing the maximum age to 0 seconds  
			//response.addCookie(ck);//adding cookie in the response 
			rd.forward(request, response);
		}

	}

}
