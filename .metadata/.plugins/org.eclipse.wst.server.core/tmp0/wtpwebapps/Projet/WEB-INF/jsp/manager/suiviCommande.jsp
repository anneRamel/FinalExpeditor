<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Suivi de commande</title>
</head>
<body>
	<h1>Bonjour M.Le Manager</h1>
	<table>
	<c:forEach var="commande" items="${requestScope.liste}">
		<tr>
			<td>${commande.id}</td>
			<td>${commande.societe.nom}</td>
			<td>${commande.statut.toString()}</td>
			<td>${commande.employe.nom } ${commande.employe.prenom }</td>
		</tr>
	
	</c:forEach>
		
	</table>
</body>
</html>