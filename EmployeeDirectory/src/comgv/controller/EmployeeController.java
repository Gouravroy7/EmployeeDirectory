package comgv.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comgv.dao.EmployeeDAO;
import comgv.dao.EmployeeDAOImpl;
import comgv.entity.Employee;


public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    EmployeeDAO employeeDAO=null;
    RequestDispatcher dispatcher=null;
    		
    public EmployeeController() {
        employeeDAO = new EmployeeDAOImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action==null)
		{
			action="LIST";
		}
		
		switch(action) {
		case "LIST" :
			listEmployees(request,response);
			break;
		case "EDIT" :
			getSingleEmployee(request,response);
			break;
		case "DELETE" :
			deleteEmployee(request,response);
			break;
		default:
			listEmployees(request,response);
			break;
		}
		
	}

	
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String name = request.getParameter("firstname");
		String dob = request.getParameter("dob");
		String department = request.getParameter("department");
		//System.out.println(id+" "+name+" "+dob+" "+department);
		Employee e=new Employee();
		e.setName(name);
		e.setDepartment(department);
		e.setDob(dob);
		if(id.isEmpty() || id==null)
		{
			if(employeeDAO.save(e)) {
				request.setAttribute("message","Saved Successfully");
			}
		}
		else
		{
			e.setId(Integer.parseInt(id));
			if(employeeDAO.update(e)) {
				request.setAttribute("message","Updated Successfully");
			}	
		}
		
		
		listEmployees(request,response);
		
	}

	
	
	 public void listEmployees(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		 List<Employee> list = employeeDAO.get();
			
			request.setAttribute("list", list);
			
			dispatcher = request.getRequestDispatcher("/views/employee-list.jsp");
			dispatcher.forward(request, response);
	 }
	 
	 public void getSingleEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String id = request.getParameter("id");
		 Employee employee = employeeDAO.get(Integer.parseInt(id));
		 request.setAttribute("employee",employee);
		 
		 dispatcher = request.getRequestDispatcher("/views/employee-add.jsp");
			dispatcher.forward(request, response);
	 }
	 
	 private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		 String id = request.getParameter("id");
		// System.out.println(id);
		 if(employeeDAO.delete(Integer.parseInt(id)))
		 {
			request.setAttribute("message", "Record Has Beeen Deleted"); 
		 }
		 listEmployees(request,response);
		}
}
