package bill;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Servlet implementation class BillAPI
 */
@WebServlet("/BillAPI")
public class BillAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Billmodel itemObj = new Billmodel();
       
	  /* @see HttpServlet#HttpServlet()
	   */
	  public BillAPI() {
	    super();
	    // TODO Auto-generated constructor stub
	  }

	  /* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	   *      response)
	   */
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
	    // TODO Auto-generated method stub
	    // response.getWriter().append("Served at: ").append(request.getContextPath());
		  
		  Billmodel itemObj = new Billmodel();
	    
	    String output = "";
	    output = itemObj.readItems();
	    
	    response.getWriter().append(output);
	    
	  }

	  /**
	   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	   *      response)
	   */
	  protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {

	    String output = itemObj.insertItem(
	        request.getParameter("Customer_Name"),
	        request.getParameter("Customer_Account"),
	        request.getParameter("Date"),
	        request.getParameter("Units_Used"),
	        request.getParameter("Amount"));
	    response.getWriter().write(output);
	  }

	  protected void doPut(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
	    Map paras = getParasMap(request);
	    String output = "";
	    if (paras.get("hidBill_IDSave") != null) {
	    output = itemObj.updateItem(
	        paras.get("hidBill_IDSave").toString(),
	        paras.get("Customer_Name").toString(),
	        paras.get("Customer_Account").toString(),
	        paras.get("Date").toString(), 
	        paras.get("Units_Used").toString(), 
	        paras.get("Amount").toString());
	    }
	    else {
	      output = itemObj.updateItem(
	          request.getParameter("hidBill_IDSave"),
	          request.getParameter("Customer_Name"),
	          request.getParameter("Customer_Account"),
	          request.getParameter("Date"),
	          request.getParameter("Units_Used"), 
	          request.getParameter("Amount"));
	    }
	    response.getWriter().write(output);
	  }

	  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
	    Map paras = getParasMap(request);
	    String output = "";
	    
	    if (paras.get("Bill_ID") != null) {
	      output = itemObj.deleteItem(paras.get("Bill_ID").toString());
	    }
	    else {
	      
	      output = itemObj.deleteItem(request.getParameter("Bill_ID"));
	    }
	    System.out.println("Bill_ID: " + output);
	    response.getWriter().write(output);
	  }

	  private static Map getParasMap(HttpServletRequest request) {
	    Map<String, String> map = new HashMap<String, String>();
	    try {
	      Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	      String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
	      scanner.close();
	      String[] params = queryString.split("&");
	      for (String param : params) {

	        String[] p = param.split("=");
	        map.put(p[0], p[1]);
	      }
	    } catch (Exception e) {
	    }
	    return map;
	  }

	}