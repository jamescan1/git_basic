<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>exchange</title>
</head>
<body>
<%
	String temp = request.getParameter("won");
	int won = Integer.parseInt(temp);
	String currency = request.getParameter("currency");
	double er = 1200;
	if(currency.equals("wian")){
		er= 1300;
		
	} else if (currency.equals("euro")){
		er=1350;
	}
	double result = won/er;
	out.print(result);

%>
<br>
<a href ="exchange.html">환율페이지로</a>

</body>
</html>