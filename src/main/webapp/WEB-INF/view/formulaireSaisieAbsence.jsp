<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Insertion Absence </title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<style >
	form{
	margin-bottom: 60px;
	margin-top: 10px;
	padding: 50px;
	}
	select{
		width: 200px;
	}
</style>
</head>
<body>
<jsp:include page="barreDeNavigation.jsp"/>
	<div>
		<f:form action="listEtudiant" type="POST" >
		<select name="seance">
		<option value="">-- selctionner le type de seance -- </option>
		<c:forEach items="${typeSeanceList }" var="t">
			<option value="<c:out value="${t.idTypeSeance }"> </c:out>"> <c:out value="${t.alias }"> </c:out></option>
		</c:forEach> 
		</select>
			 <select name="niveau">
			 <option value="">-- selectionner le niveau --</option>
				<c:forEach items="${niveauList }" var="n">
					<option value="<c:out value="${n.idNiveau }"> </c:out>" > <c:out value="${n.alias }"> </c:out></option>
				</c:forEach> 
			</select>
			
			 <br> <br> <br>
			 
			Date Debut : <input type="datetime-local" name="dateDebut" >
			
			Date Fin : <input type="datetime-local" name="dateFin">

			<br> <br> 
			
			<input type="submit" value="valider"  class="btn btn-primary" />
			
		</f:form>
	</div>
</body>
</html>