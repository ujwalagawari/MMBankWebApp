<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="addNew.mm">
		<div id="accountNo">
		    <label>Account Number: </label> 
			<input type="text" name="accountNumber" id="accountNumber" value="${requestScope.account.bankAccount.accountNumber}" readonly="readonly" />
			<br>
		</div>
		
		<label>Enter Account Holder Name: </label> 
		<input type="text" name="txtAccHN" id="txtAccHN" value="${requestScope.account.bankAccount.accountHolderName }" />
		<br />
		
		<label>Enter Account Balance: </label>
		<input type="number" name="txtBalance" id="txtBalance" value="${requestScope.account.bankAccount.accountBalance}" />
		<br />
		
		<input type="hidden" id="salary" value="${requestScope.account.salary}">
		
		<label>Salaried?: </label>
		<input type="radio" name="rdSalary" id="yes" value="Yes" /> Yes
		<input type="radio" name="rdSalary" id="no" value="No" /> No 
		<br />
		
		<input type="submit" id="submit" value="Submit" /> 
		<input type="submit" id="update" value="Update" /> 
		<input type="reset" value="Clear" />
	</form>
	<div>
		<jsp:include page="homeLink.html"></jsp:include>
	</div>
	
	<script type="text/javascript">
		$(function(){
			if($("#accountNumber").val()!=""){
				$("#submit").hide();
				//$("#txtAccHN").attr('readonly', true);
				$("#txtBalance").attr('readonly', true);
				if($("#salary").val()=="true"){
					 $("#yes").attr('checked', 'checked');
				}else{
					 $("#no").attr('checked', 'checked');
				}
			}else if($("#accountNumber").val()==""){
				$("#accountNo").hide();
				$("#update").hide();
			}
		});
	</script>
</body>
</html>










