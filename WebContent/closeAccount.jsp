<!DOCTYPE html>
<html lang="en">
<head>
    <title>Banking Application</title>
    <link rel="stylesheet" type="text/css" href="resources/css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="resources/css/formStyle.css">
    <link rel="stylesheet" type="text/css" href="resources/css/footerStyle.css">
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

        <h1 align="center">Close Account</h1>
                 <hr>
         <div align="center">
			<form action="closeSaAccount.mm">
				<label>Enter Account Number : </label> 
				<input type="number" name="accountNumber" /> 
				<br>
				<button type="submit">Submit</button>
			</form>
		 </div>
  <footer>Copyscape � 2018 Money Money Bank. All rights reserved. Terms of Use.</footer>
    <!-- <object  type="text/html" data="resources/include/footer.html"></object> -->
    
</body>
</html>