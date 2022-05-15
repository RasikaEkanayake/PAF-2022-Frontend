<%@page import="com.payment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    // SAVE
    	if (request.getParameter("pAccNo") != null) {	
    		
    		payment item = new payment();
    		String statusMsg = "";
    		
    		String pAccNo = request.getParameter("pAccNo");
    		String pCus = request.getParameter("pCus");
    		String pAmount = request.getParameter("pAmount");
    		String pDate = request.getParameter("pDate");
    		
    		if (request.getParameter("hidItemIDSave") == "") {
    	statusMsg = item.insertpayment(pAccNo, pCus, pAmount, pDate);
    		} else {
    	statusMsg = item.updatepayment(request.getParameter("hidItemIDSave"),pAccNo, pCus, pAmount, pDate);
    		}
    		
    		session.setAttribute("statusMsg", statusMsg);
    	}

    	// DELETE
    	if (request.getParameter("hidItemIDDelete") != null) {
    		payment item = new payment();
    		String statusMsg = item.deletepayment(request.getParameter("hidItemIDDelete"));
    		session.setAttribute("statusMsg", statusMsg);
    	}
    %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.2.1.min.js"></script>
        <script src="Components/paymanage.js"></script>
		<title>Payments Management</title>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col">
					<h1>Payment Management</h1>
					<form id="formItem" name="formItem" method="POST" action="paymanage.jsp">
						Customer pAccNo: 
						<input 
							id="pAccNo" 
							name="pAccNo" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						Customer name: 
						<input 
							id="pCus"
							name="pCus" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						Total Amount: 
						<input 
							id="pAmount" 
							name="pAmount" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						Date: 
						<input 
							id="pDate" 
							name="pDate" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						<input 
							id="btnSave" 
							name="btnSave" 
							type="button" 
							value="Save" 
							class="btn btn-primary"
						>

						<input type="hidden" name="hidItemIDSave" id="hidItemIDSave" value="">
					</form>
				
					<br>
					<div id="alertSuccess" class="alert alert-success">
						<%
						out.print(session.getAttribute("statusMsg"));
						%>
					</div>
					<div id="alertError" class="alert alert-danger"></div>
					<br>
					<%
					payment item = new payment(); 
									out.print(item.readpayment());
					%>
				</div>
			</div>
		</div>
	</body>
</html>