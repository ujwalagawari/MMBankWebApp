<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="withdrawFromSa.mm">
		<label>Enter Account Number : </label> 
		<input type="number" name="accountNumber" /> 
		<br>
		<label>Enter Amount : </label> 
		<input type="number" name="amount" />
		<br>
		<button type="submit">Submit</button>
	</form>
	<div>
		<jsp:include page="homeLink.html"></jsp:include>
	</div>
</body>
</html>