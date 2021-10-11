<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous" >

</head>
<body>
<jsp:include page="barreDeNavigation.jsp"/>
	<h2 style="color: green;"> Liste des messages </h2>
	<table>
	<th> Message </th> <th> Reponse </th>
		<c:forEach items="${ListMessage }" var="msg">
			<tr>
				<td>
				<c:out value="${msg.dateHeure}"></c:out> <br>
				 <c:out value="${msg.texte }" ></c:out> </td>
				<td> <a href="reponse/1/${msg.idMessage }" class="btn btn-success">  Oui </a> </td>
				<td> <a href="reponse/0/${msg.idMessage }"  class="btn btn-warning"> Non </a> </td>
				
			 </tr>
			 
		</c:forEach>
	</table>
</body>
</html>