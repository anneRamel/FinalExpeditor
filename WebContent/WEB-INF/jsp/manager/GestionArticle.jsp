<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/jsp/includes/header.jsp"/>

	<div class="container-fluid">
  		<div id="bonjour">
  			<h2>Bonjour ${sessionScope.user.prenom} ${sessionScope.user.nom}</h2>
  			
  			<b>${messageAjout}</b>
  		
  		
  		<h3>Menu Manager</h3>
			  <ul class="nav nav-tabs">
			    <li ><a href="${pageContext.request.contextPath}/suiviCommande">Gérer commande</a></li>
			    <li><a href="${pageContext.request.contextPath}/ManagerServlet">Gérer Employé</a></li>
			    <li class="active"><a href="#">Gérer Article</a></li>
			  </ul>
			  <br>
  		</div>
	  	<h3>Gérer les articles</h1>
	  		
		<form id="crud" action="<%=request.getContextPath()%>/ArticleServlet" method="post">
	  		<div class="well" id="listeArticles">
		  		<h1>Liste des articles :</h1><br/>
		  	
				<div class="list-group">
					<c:forEach var="article" items="${requestScope.listeArticles}">		
						<a onclick="obtenirId(${article.id})" name="${article.id}" class="list-group-item" title="${article.libelle}" data-toggle="popover" data-content="${article.poids} grammes - Il en reste ${article.quantite}">${article.libelle}</a>
					</c:forEach>
					<input type="hidden" id="toto" name="id">
				</div>
				
			</div>

			<button type="submit" name="modifier" class="btn col-md-1">Modifier</button> 
			<button type="submit" name="supprimer" onclick="recupId()" class="btn col md-2">Supprimer</button><br/>
			<br>
		</form>
		
		<script type="text/javascript">
			var id;
			
			function obtenirId(id){
				id=${article.id};
			};
		
			function recupId(){
				document.getElementById("toto").value=id;
			};
		</script>
		
		
		<form id="ajouter" action="<%=request.getContextPath()%>/ArticleServlet" method="post">
		
			<div class="well col-sm-12" id="articleDetaille">
	 			<h2>Article détaillé</h2>
				    <div class="form-group">
				      <label class="control-label col-sm-2" for="email">Libellé :</label>
				      <div class="col-sm-10">
				        <input type="text" class="form-control" id="libelle" placeholder="${libelle}" name="libelle">
				      </div>
				    </div>
				    <div class="form-group">
				      <label class="control-label col-sm-2" for="pwd">Poids :</label>
				      <div class="col-sm-10">          
				        <input type="text" class="form-control" id="poids" placeholder="${article.poids}" name="poids">
				      </div>
				    </div>
				    <div class="form-group">
				      <label class="control-label col-sm-2" for="pwd">Quantité :</label>
				      <div class="col-sm-10">          
				        <input type="text" class="form-control" id="quantite" placeholder="${article.quantite}" name="quantite">
				      </div>
				    </div>
				    <div class="form-group">        
				      <div class="col-sm-offset-2 col-sm-10">
				        <button type="submit" name="ajouter" class="btn btn-default">Enregistrer</button>
				      </div>
				    </div>
			</div>
		</form>
	</div>

<script>
$(document).ready(function(){
    $('[data-toggle="popover"]').popover();   
});

var outgoingLink;
var machin;

function clic(outgoingLink){
document.getElementById('contenu').innerHTML = "affichage du outgoingLink :"+outgoingLink+" ";
alert(outgoingLink);
}
</script>

<jsp:include page="/WEB-INF/jsp/includes/footer.jsp"/>

