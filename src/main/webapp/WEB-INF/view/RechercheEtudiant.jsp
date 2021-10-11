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
	crossorigin="anonymous">
<style type="text/css">
form{
	margin-bottom: 60px;
	margin-top: 10px;
	padding: 50px;
	}
</style>
</head>
<body>
<div>
<jsp:include page="barreDeNavigation.jsp"/>
	<f:form action="rechercheEtudiant" >
		<input type="text" placeholder="Tapez l identifiant de l'etudiant" 
		name="recherche" pattern="[0-9]+" size="50"/> 
		<input type="submit" value="Rechercher" class="btn btn-primary" />
	</f:form>
</div>

</body>
</html>