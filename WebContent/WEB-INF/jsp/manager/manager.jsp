<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/includes/header.jsp"/>

<div class="row head" align="center">
   <h1> 
        <span class="titre-accueil">Bienvenue ${user.nom} ${user.prenom}</span>
   </h1> 
    
</div>
<h3>Menu Manager</h3>
  <ul class="nav nav-tabs">
    <li><a href="${pageContext.request.contextPath}/suiviCommande">Gérer commande</a></li>
    <li class="active"><a href="#">Gérer Employé</a></li>
    <li><a href="${pageContext.request.contextPath}/ArticleServlet">Gérer Article</a></li>
  </ul>
  <br>
<div class="row" > 
  
<div class="col-lg-6 panel-group">
  <h2>Liste des employés:</h2>
  
  <table class="table table-bordered table-hover" id="listeEmploye">
  
    
									  						
			<c:forEach var="employes" items="${requestScope.listeEmploye}" varStatus="count">  
		      <tr onclick="Choix(${count.count})" >
		        <td>${employes.nom}  ${employes.prenom}</td>
		        <input type="hidden" name="nom" value=${employes.nom} id="nom_${count.count}">
		        <input type="hidden" name="prenom" value=${employes.prenom} id="prenom_${count.count}">
		        <input type="hidden" name="email" value=${employes.email} id="email_${count.count}">
		        <input type="hidden" name="password" value=${employes.password} id="password_${count.count}">
		      </tr>   
		    </c:forEach>  												
															

      
  
  </table>
  <input onclick="Modifier()" type="submit" class="btn btn-default" name="modifier" value="Modifier"/>
  <input onclick="Supprimer()" type="submit" class="btn btn-default" name="supprimer" value="Supprimer"/> 

</div>

			<div class="col-lg-6 panel-group" >
					<div class="form-group">
					  <label for="usr">Nom:</label>
					  <input type="text" class="form-control" id="nomAremplir">
					</div>
					<div class="form-group">
					  <label for="pwd">Prénom:</label>
					  <input type="text" class="form-control" id="prenomAremplir">
					</div>
					<div class="form-group">
					  <label for="usr">Email:</label>
					  <input type="text" class="form-control" id="emailAremplir">
					</div>
					<div class="form-group">
					  <label for="pwd">Mot de passe:</label>
					  <input type="text" class="form-control" id="pwdAremplir">
					</div>	
					<input onclick="Creer()" type="submit" class="btn btn-default" name="Creer" value="Créer"/>	
					<form method="POST" action="<%=request.getContextPath()%>/WEB-INF/jsp/manager/manager.jsp">
					 <input onclick="Valider()" type="submit" class="btn btn-default" id="valider" name="Valider" value="Valider"/>
    				</form>
								
			</div>
	
</div>
							
					     	 
					     	
	
		
		<script>
		   
        
		function Choix(num) {
		    document.getElementById("nomAremplir").value = document.getElementById("nom_"+num).value;
		    document.getElementById("prenomAremplir").value = document.getElementById("prenom_"+num).value;
		    document.getElementById("emailAremplir").value = document.getElementById("email_"+num).value;
		    document.getElementById("pwdAremplir").value = document.getElementById("password_"+num).value;
		}
		</script>
		
		<script>
		   
        
		function Creer() {
		    document.getElementById("nomAremplir").value = null;
		    document.getElementById("prenomAremplir").value = null;
		    document.getElementById("emailAremplir").value = null;
		    document.getElementById("pwdAremplir").value = null;
		}
		function Valider() {
			
			
		    <!--= document.getElementById("nomAremplir").value;
		    = document.getElementById("prenomAremplir").value;
		    = document.getElementById("emailAremplir").value;
		    = document.getElementById("pwdAremplir").value;-->
		}
		</script>
   
<jsp:include page="/WEB-INF/jsp/includes/footer.jsp"/>