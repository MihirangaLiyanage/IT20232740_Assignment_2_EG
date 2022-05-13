<%@page import="bill.Billmodel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Bill Management</h1>
<form id="formItem" name="formItem">
 Customer Name:
 <input id="Customer_Name" name="Customer_Name" type="text"
 class="form-control form-control-sm">
 <br> Customer Account No:
 <input id="Customer_Account" name="Customer_Account" type="text"
 class="form-control form-control-sm">
 <br> Bill Date:
 <input id="Date" name="Date" type="text"
 class="form-control form-control-sm">
  <br> Units Used:
 <input id="Units_Used" name="Units_Used" type="text"
 class="form-control form-control-sm">
 <br> Amount:
 <input id="Amount" name="Amount" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidBill_IDSave"
 name="hidBill_IDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Billmodel itemObj = new Billmodel();
 out.print(itemObj.readItems());
 %>
</div>
</div> </div> </div>
</body>
</html>