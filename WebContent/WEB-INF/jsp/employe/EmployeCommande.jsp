<!DOCTYPE html>
<html lang="fr">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<title>Saisie de la commande</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/theme/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/theme/js/bootstrap.min.js"></script>
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
	
	<div class="container">
	<h1>Bonjour ${sessionScope.user.getPrenom()} ${sessionScope.user.getNom()}</h1>
	<h2>COMMANDE A TRAITER : N°${sessionScope.commandeEnCours.id}</h2>
		<p>Veuillez saisir les quantités des articles de la commande :</p>
		<form id="imprimer" action="${pageContext.request.contextPath}/CommandePrioritaireServlet" method="post">
			<table class="table table-hover">
				<div class="form-group">
					<label for="usr"></label> 
					<input type="text" class="form-control"
						value=" ${sessionScope.commandeEnCours.societe.nom},${sessionScope.commandeEnCours.societe.adresse}" id="usr">
				</div>
				<thead>
					<tr>
						<th>Articles</th>
						<th>Quantités Commandées</th>
						<th>Quantités Saisies</th>
						<th>Poids</th>
					</tr>
				<c:forEach var="article" items="${sessionScope.commandeEnCours.articlesCommandes}" varStatus="count" >
					<tr>
						<th><input type="text" id="articleLibelle" value="${article.key.libelle}" name="libelleArticle" type="text" disabled ></th>
						<th><input type="text" value="${article.value}" name="quantiteCommande" type="text" disabled></th>
						<th><input type="text" id="quantiteSaisie_${count.count}" name="quantiteSaisie" onBlur="poids()"></th>
						<th><input type="text" id="poidsCalcule_${count.count}" name="poidsCalcule" ></th>
						<th><input type="hidden" id="poidsArticle_${count.count}" value="${article.key.poids}"></th> 
					</tr>
				</c:forEach>
				</thead>
				
			</table>
			<table>
				<tr>
					<label for="poidsTotals">Poids Total : </label>
					<input type="text" name="poidsTotals" id="poidsTotals">
				</tr>
			</table>
			<br/>
			<button><a href="${pageContext.request.contextPath}/pdfServlet" target="blank">Imprimer</a></button>
			<input type="submit"  name="Imprimer" id="print" value="Fin de commande" id="imprimer">
			<br/>
			<br/>
			<input type="button" name="reinitialiser" value="Reset!" onclick="resetFields()"  />
			<br/>
		</form>
	</div>
		
	<script>
	
	var poidsCarton=300;
	var nbreLigneCommande="${fn:length(sessionScope.commandeEnCours.articlesCommandes)}";
	var poidsTotal = document.getElementById("poidsTotals");
 	function poids()
 	{
 		var poidstotalCommande=poidsCarton;
 		for (var i = 1; i <= nbreLigneCommande; i++) 
 		{
		if(document.getElementById("quantiteSaisie_"+i).value != "")
			{
	    var ttligne = parseFloat(document.getElementById("quantiteSaisie_"+i).value) * parseFloat(document.getElementById("poidsArticle_"+i).value);
	   document.getElementById("poidsCalcule_"+i).value= ttligne ;
	   poidstotalCommande += ttligne;
 			}
		}
	   poidsTotal.value = poidstotalCommande;
	}
 	
 	
 	var reset = document.getElementsByName("poidsCalcule");
 	var reset1 = document.getElementsByName("quantiteSaisie");
 	
 	function resetFields(){
 		poidsTotal.value = "";
 		for(var i=0; i<reset.length;i++){
 			reset[i].value ="";
		}
 		for(var i=0; i<reset1.length;i++){
 			reset1[i].value ="";
 		}
 	}
 	function myFunction() {
 	    var txt;
 	    if (confirm("Press a button!") == true) {
 	        txt = "OK!";
 	    } else {
 	        txt = "Cancel!";
 	       alert("non")
 	    }
 	    document.getElementById("print").innerHTML = txt;
 	}
 	</script>
 	
 			
	
 	
</body>
</html>
