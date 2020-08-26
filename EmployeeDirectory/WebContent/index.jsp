<html>
 <head>
  <title></title>
 </head>
 <link href="https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
 <body>
  <%
  
 String email =  (String)session.getAttribute("email");
  
  if(email!=null)
  {
	  response.sendRedirect("EmployeeController?action=LIST");
  }
   String status = request.getParameter("status");
  if(status!=null)
  {
  if(status.equals("false"))
  {
	out.print("Bad Credentials");  
  }
  else if(status.equals("error"))
  {
	  out.print("Some error occured.");
  } 
  }
  %>
   <div class="container">
   <form action="loginprocess" method="POST">
     <div class="card">
       <div class="card-header">
        Login
       </div>
       <div class="card-body">
         <div class="form-group">
          <input type="text" name="email" class="form-control" placeholder="Enter Email"><br>
         </div>
         <div class="form-group">
          <input type="password" name="password" class="form-control"  placeholder="Enter Password"><br>
         </div>
       </div>
       <div class="card-footer">
         <input type="submit" value="Login" class="btn btn-primary">
       </div>
      </div>
   </form>
   </div>
 </body>
</html>