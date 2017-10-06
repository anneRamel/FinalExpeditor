<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/bootstrap-theme.css">
<script src="${pageContext.request.contextPath}/theme/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/theme/js/bootstrap.min.js"></script>
<title>Bon de Livraison</title>
</head>


	<title>Bon de Livraison</title>
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
	<br>
	<br>
	<button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">Fin de la commande</button>
	
	<!-- Page modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
	 			<div class="modal-dialog" role="document">
	    			<div class="modal-content">
	      				<div class="modal-header">
	        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        				<h4 class="modal-title">Confirmation</h4>
	      				</div>
	      				<div class="modal-body">
	        				<p id="modal">Confirmez-vous la bonne impression du document?</p>
	      				</div>
	      				<div class="modal-footer">
	        				<button type="button" class="btn btn-default" data-dismiss="modal">Retour à l'impression</button>
 					        <a href="${pageContext.request.contextPath}/CommandePrioritaireServlet"><button type="button" class="btn btn-info">Commande Suivante</button></a>
	      				</div>
	    			</div><!-- /.modal-content -->
	  			</div><!-- /.modal-dialog -->
			</div><!-- /.modal -->

</body>

</html>