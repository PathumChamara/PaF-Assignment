<%@ page import="model.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospitals Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/hospitals.js"></script>
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>HOSPITALS MANAGEMENT</h1>
				<form id="formHospital" name="formHospital" method="post" action="hospitals.jsp">  
					Hospital Type:  
					<input id="hType" name="hType" type="text" class="form-control form-control-sm">  
					<br> Hospital Name:  
					<input id="HosName" name="HosName" type="text" class="form-control form-control-sm">  
					<br> Telno:  
					<input id="HosTelNo" name="HosTelNo" type="text" class="form-control form-control-sm">  
					<br> Address:  
					<input id="HosAddress" name="HosAddress" type="text" class="form-control form-control-sm">  
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					<input type="hidden" id="hidHospitalIDSave" name="hidHospitalIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success">
					<%
						out.print(session.getAttribute("statusMsg"));
					%>
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divHospitalsGrid">
					<%
						Hospital hospitalObj = new Hospital();
						out.print(hospitalObj.readHospitals());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>

</body>
</html>