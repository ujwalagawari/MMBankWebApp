<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
    <title>Banking Application</title>
    <link rel="stylesheet" type="text/css" href="resources/css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="resources/css/formStyle.css">
    <link rel="stylesheet" type="text/css" href="resources/css/footerStyle.css">
    <link rel="stylesheet" type="text/css" href="resources/css/tableStyle.css">
</head>

<body>
        <div class="header">
            <h1>Money Money Bank </h1>
            <div class="details">
                    <p>Contac us - 22450 57111<p>
                            <p>Email - <i style="color: blue">mm_bank@gmail.com</i></p>
            </div>
        </div>
        <nav id="menu">
            <ul>
            	<jsp:include page="homeLink.html"></jsp:include>
			</ul>
        </nav>

        <h1 align="center">Fund Transfer</h1>
                 <hr>
	<table style="width:100%">
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
  <footer>Copyscape � 2018 Money Money Bank. All rights reserved. Terms of Use.</footer>
    <!-- <object  type="text/html" data="resources/include/footer.html"></object> -->
    
	
</body>
</html>







