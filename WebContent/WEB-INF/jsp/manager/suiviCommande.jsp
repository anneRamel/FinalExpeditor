<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/theme/css/bootstrap.css" >
	<link rel="stylesheet" href="<%=request.getContextPath()%>/theme/css/bootstrap-theme.css" >
	<script src="${pageContext.request.contextPath}/theme/js/Chart.js"></script>
	<title>Suivi de commande</title>
</head>
<body>
	 <header>
     	<nav class="navbar navbar-default navbar-static-top">
      		<div class="container">
        		<div class="navbar-header"></div>
       			<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
            			<li><a href="<%=request.getContextPath()%>/Deconnexion">Home</a></li>
          			</ul>
        		</div>
      		</div>
    	</nav>
	</header>
<div class="container-fluid">

		<h1>Bonjour ${sessionScope.user.prenom} ${sessionScope.user.nom}</h1>
		<h3>Menu Manager</h3>
		  <ul class="nav nav-tabs">
		    <li class="active"><a href="#">Gérer commande</a></li>
		    <li><a href="${pageContext.request.contextPath}/ManagerServlet">Gérer Employé</a></li>
		    <li><a href="${pageContext.request.contextPath}/ArticleServlet">Gérer Article</a></li>
		  </ul>
		  <br>
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
					<div class="row panel-body">
						<div class="col-lg-12">
							<c:forEach var="employe" items="${requestScope.nbCommandes}">
							<div class="col-lg-7"><p>${employe.key.prenom} ${employe.key.nom}</p></div>
							<div class="col-lg-5"><p>${employe.value}</p></div>
							</c:forEach>
						</div>
						<div id="chart-holder">
							<canvas id="myChart"></canvas>
						</div>
				</div>
			</div>
		
</div>	

<script>
	var data = '${requestScope.data}';
	var label = '${requestScope.label}';
	function toObject(arr) {
		  var rv = {};
		  for (var i = 0; i < arr.length; ++i)
		    rv[i] = arr[i];
		  return rv;
		}
	var dataObjet = toObject(data);
	var labelObjet = toObject(label);
	console.log(dataObjet);
	var ctx = document.getElementById("myChart").getContext('2d');
	var myPieChart = new Chart(ctx,{
	    type: 'pie',
	    data: {
	        datasets: [{
	            data: 'dataObjet',
	            backgroundColor : ['rgb(22, 184, 78)','rgb(247, 35, 12)']
	        }],
	
    	     labels: 'labelObjet'
	        }
	});
	</script>
 <%@include file="/WEB-INF/jsp/includes/footer.jsp" %>	
