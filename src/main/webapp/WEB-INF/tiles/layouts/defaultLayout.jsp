<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><tiles:getAsString name="title" /></title>
<link rel="stylesheet"
	href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">



</head>


<body>

	<header id="header" class="navbar-inverse" role="banner">
		<tiles:insertAttribute name="header" />
	</header>


	<div class="jumbotron">
		<div class="container">
			<div class="col-xs-12 col-md-4">
				<section id="importfile">
					<tiles:insertAttribute name="fileupload" />
				</section>
			</div>

			<div class="col-xs-12 col-md-8">
				<section id="site-content">
					<tiles:insertAttribute name="body" />
				</section>
			</div>

			<div class="col-xs-12 col-md-12">
				<section id="table">
					<tiles:insertAttribute name="table" />
				</section>
			</div>

		</div>
	</div>

	<div class="footer">
		<div class="container">
			<footer class="footer">
				<tiles:insertAttribute name="footer" />
			</footer>
		</div>
	</div>
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>

	<script type="text/javascript">
		if (${sqlresult}){
			var sql = ${sqlresult} ; 
			
		}
		
		window.alert = function() {};
			$(document).ready(function() {
			    var table = $('#dataTable').DataTable( {
			        "aaData": sql,
			        "scrollY": 270,
			        	"aoColumns": [
								         { 'mDataProp': 'id' },				         
								         { 'mDataProp': 'active' },	
								         { 'mDataProp': 'amount' },	
								         { 'mDataProp': 'amountPeriod' },	
								         { 'mDataProp': 'amountType' },	
								         { 'mDataProp': 'authorizationPercent' },	
								         { 'mDataProp': 'fromDate' },	
								         { 'mDataProp': 'orderNumber' },	
								         { 'mDataProp': 'request' },	
								         { 'mDataProp': 'toDate' },	
								         { 'mDataProp': 'systemId' }, 	
								         ],
								        
			    } );
			} );
			
			
	</script>
	<script type="text/javascript">
		var importFile = ${importFileResult};
		if (importFile == true) {
			document.getElementById("isResult").innerHTML = "File import done - OK";
         }      
         else {
        	 document.getElementById("isResult").innerHTML = "Bad file or empty system list - ERROR";
         }
			
		
	</script>
	<script type="text/javascript">
		var importSQL = ${importSQLResult};
		if (importSQL == true) {
			document.getElementById("isResult").innerHTML = "SQL import done - OK";
         }      
         else {
        	 document.getElementById("isResult").innerHTML = "Empty database - ERROR";
         }
		
	</script>
	<script type="text/javascript">
		var fileOrSQL = ${fileOrSQL};
		if (fileOrSQL == true) {
			document.getElementById("fileOrSQL").innerHTML = "Importing Contracts from File";
         }      
         else {
        	 document.getElementById("fileOrSQL").innerHTML = "Importing Contracts from SQL database";
         }
		
	</script>
</body>
</html>
