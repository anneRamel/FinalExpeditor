<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/theme/css/bootstrap.css" >
	<link rel="stylesheet" href="<%=request.getContextPath()%>/theme/css/bootstrap-theme.css" >
	<title>Suivi de commande</title>
</head>
<body>
	<div class="container-fluid">
		<h1>Bonjour M.Le Manager</h1>
		<div class="row">
			<div class="col-lg-8 panel-group">
				<div class="col-lg-12 panel panel-primary">
					<div class="row panel-heading">
						<div class="col-lg-2">
							<p>Numéro</p>
						</div>
						<div class="col-lg-3">
							<p>Client</p>
						</div>
						<div class="col-lg-3">
							<p>Statut</p>
						</div>
						<div class="col-lg-4">
							<p>Employé</p>
						</div>
					</div>
				
				<c:forEach var="commande" items="${requestScope.liste}">
					<div class="row panel-body">
					<div class="col-lg-2"><p>${commande.id}</p></div>
					<div class="col-lg-3"><p>${commande.societe.nom}</p></div>
					<div class="col-lg-3"><p>${commande.statut.toString()}</p></div>
					<div class="col-lg-4"><p>${commande.employe.prenom} ${commande.employe.nom }</p></div>
					</div>
				</c:forEach>
				</div>
			</div>
			<div class="col-lg-4 panel-group">
				<div class="col-lg-12 panel panel-primary">
					<div class="row panel-heading">
						<div class="col-lg-7"><p>Employé</p></div>
						<div class="col-lg-5"><p>Nombre de commandes</p></div>
					</div>
					<c:forEach var="employe" items="${requestScope.nbCommandes}">
					<div class="row panel-body">
						<div class="col-lg-7"><p>${employe.key.prenom}${employe.key.nom}</p></div>
						<div class="col-lg-5"><p>${employe.value}</p></div>
							
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
</html>