<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gugudan</title>
</head>
<body>
<% 	
	String temp = request.getParameter("dan");
	int dan= Integer.parseInt(temp);
	for(int i=0;i<9;i++){
		out.println(dan+"x"+(i+1)+"="+(dan*(i+1))+"<br>");
	
	}
%>

</body>
</html>