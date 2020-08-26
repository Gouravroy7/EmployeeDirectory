package comgv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comgv.entity.Employee;
import comgv.util.DBConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	Connection connection =null;
	Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement =  null;
	
	@Override
	public List<Employee> get() {
		 
		List<Employee> list = null;
		Employee employee = null;
		
		try
		{
		list=new ArrayList<Employee>();
		
		String sql="select * from tbl_employee";
		
		connection =DBConnectionUtil.openConnection();
		
		statement = connection.createStatement();
		
		resultSet = statement.executeQuery(sql);
		
		
		
		while(resultSet.next()) {
			employee =new Employee();
			employee.setId(resultSet.getInt("id"));
			employee.setName(resultSet.getString("name"));
			employee.setDepartment(resultSet.getString("department"));
			employee.setDob(resultSet.getString("dob"));
			
			list.add(employee);
		}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	@Override
	public boolean save(Employee e) {
		boolean flag=false;
		
		try {
			String sql="INSERT INTO tbl_employee(name,dob,department) values('"+e.getName()+"', '"+e.getDob()+"', '"+e.getDepartment()+"')";
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag=true;
		}
		catch(Exception e1){
			e1.printStackTrace();
		}
		return flag;
	}

	@Override
	public Employee get(int id) {
		Employee employee = null;
		try {
			employee=new Employee();
			
			String sql="select * from tbl_employee where id="+id;
			 Connection connection = DBConnectionUtil.openConnection();
			 statement = connection.createStatement();
			 resultSet= statement.executeQuery(sql);
			 
			 while(resultSet.next()) {
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setDepartment(resultSet.getString("department"));
				employee.setDob(resultSet.getString("dob"));
				
			 }
			 
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//System.out.println(employee.getId()+" "+employee.getName()+" "+employee.getDob()+" "+employee.getDepartment());
		 return employee;
	}

	@Override
	public boolean update(Employee e) {
	 boolean flag=false;
	 
	 try
	 {
		String sql="update tbl_employee set name='"+e.getName()+"', dob='"+e.getDob()+"', department='"+e.getDepartment()+"' where id="+e.getId();
		 connection = DBConnectionUtil.openConnection();
		 preparedStatement = connection.prepareStatement(sql);
		 preparedStatement.executeUpdate();
		 flag=true;
	 }
	 catch(Exception ex){
		 ex.printStackTrace();
	 }
	return flag;	
		
	}

	@Override
	public boolean delete(int id) {
		boolean flag=false;
		
		try
		{
			String sql="delete from tbl_employee where id="+id;
			connection=DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag=true;
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}

}
