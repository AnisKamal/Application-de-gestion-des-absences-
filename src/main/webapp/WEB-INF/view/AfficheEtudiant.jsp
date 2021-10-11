<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Affichage Etudiant</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
</head>
<body>
<jsp:include page="barreDeNavigation.jsp" />
	<img alt="" src="<c:out value="${Etudiant.photo }"></c:out>" width="100" height="100">
	<c:out value="${Etudiant.nom }"></c:out>
	<c:out value="${Etudiant.prenom }"></c:out>
	<a href="obtenirAbsence/${Etudiant.idUtilisateur }" class="btn btn-primary">Obtenir la liste d' Absence</a>
	
</body>
</html>