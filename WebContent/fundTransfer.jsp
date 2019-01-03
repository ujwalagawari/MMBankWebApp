<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="fundTransferInSA.mm">
		<label>Enter Sender Account Number : </label> 
		<input type="number" name="senderAccountNumber" /> 
		<br>
		
		<label>Enter Receiver Account Number : </label> 
		<input type="number" name="receiverAccountNumber" /> 
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