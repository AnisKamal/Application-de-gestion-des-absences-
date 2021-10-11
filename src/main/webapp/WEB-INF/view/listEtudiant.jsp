<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste des etudiants</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<style>
form{
	margin-bottom: 60px;
	margin-top: 10px;
	padding: 50px;
	}
</style>
</head>
<body>
<jsp:include page="barreDeNavigation.jsp" />
	<div>
	<f:form action="ajouterAbsence" type="POST">
		<table>
			<th> Photo </th> <th> Nom </th> <th>Prenom</th> <th>Cocher l'absence </th>
			
	<c:forEach items="${etudiantList }" var="et">
		<tr>
			<td width="25%"> <img alt="" src="<c:out value="${et.photo }"></c:out>" width="100" height="100"> </td>
			<td width="25%"><c:out value="${et.nom }"></c:out></td>
			<td width="25%"><c:out value="${et.prenom }"></c:out></td>
			<td width="25%"> <input type="checkbox" value="${et.idUtilisateur }" name="absent"/> </td>
		</tr>
	</c:forEach> 
		</table>
		<br> <br>
		<input type="submit" value="valider" class="btn btn-primary"/>
		</f:form>
	</div> 
		<%-- <c:forEach items="${etudiantList }" var="et">
			<c:out value="${ et.idInscription}"></c:out>
		</c:forEach> --%>
	
</body>
</html>