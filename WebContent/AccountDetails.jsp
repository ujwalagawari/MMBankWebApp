<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th><a href="sortByNumber.mm">Account Number</a></th>
			<th><a href="sortByName.mm">Holder Name</a></th>
			<th><a href="sortByBalance.mm">Account Balance</a></th>
			<th><a href="sortBySalary.mm">Salary</a></th>
			<th>Over Draft Limit</th>
			<th>Type Of Account</th>
		</tr>
		<jstl:if test="${requestScope.account!=null}">
			<tr>
				<td>${requestScope.account.bankAccount.accountNumber}</td>
				<td>${requestScope.account.bankAccount.accountHolderName }</td>
				<td>${requestScope.account.bankAccount.accountBalance}</td>
				<td>${requestScope.account.salary==true?"Yes":"No"}</td>
				<td>${"N/A"}</td>
				<td>${"Savings"}</td>
			</tr>
		</jstl:if>
		<jstl:if test="${requestScope.accounts!=null}">
			<jstl:forEach var="account" items="${requestScope.accounts}">
				<tr>
					<td>${account.bankAccount.accountNumber}</td>
					<td>${account.bankAccount.accountHolderName }</td>
					<td>${account.bankAccount.accountBalance}</td>
					<td>${account.salary==true?"Yes":"No"}</td>
					<td>${"N/A"}</td>
					<td>${"Savings"}</td>
				</tr>
			</jstl:forEach>
		</jstl:if>
	</table>
	<div>
		<jsp:include page="homeLink.html"></jsp:include>
	</div>
	
	<script type="text/javascript">
	$(function(){
		$("#nameDesc").hide();
	});
	
	$("#nameAsc").click(function(){
		alert("ascending");
		$("#nameAsc").hide();
		$("#nameDesc").show();
	});
	
	$("#nameDesc").click(function(){
		alert("descending");
		$("#nameAsc").show();
		$("#nameDesc").hide();
	});
	</script>
</body>
</html>







