<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Absence </title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
</head>
<body>
<jsp:include page="barreDeNavigation.jsp" />
	<p> Fiche d'absence </p>
	<table>
		<th>DateDebut</th> <th>DateFin</th> <th>Supprimer</th>
	<c:forEach items="${ListAbsence }" var="abs">
		<tr>
			<td> <c:out value="${abs.dateHeureDebutAbsence }"></c:out> </td>
			<td> <c:out value="${abs.dateHeureFinAbsence }"></c:out>  </td>
			<td> <a href="../supprimerAbsence/${abs.idAbsence }" class="btn btn-danger">Supprimer</a> </td>
		</tr>
	
	</c:forEach>
	</table>
</body>
</html>