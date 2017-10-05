<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/includes/header.jsp"/>

    <div class="loginmodal-container">
        <h3>Identifiez-vous</h3><br>
	    <form method="POST" action="<%=request.getContextPath()%>/EmployeServlet">
	      <div class="form-group">
					    <label for="login">Login:</label>
					    <input type="login" class="form-control" id="login" name="login" placeholder="Adresse mail ou identifiant">
					  </div>
					  
					  <div class="form-group">
					    <label for="password">Password:</label>
					    <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe">
					  </div>
					   <button type="submit" id="seconnecter" name="connexion" value="Connexion" class="btn btn-default">Submit</button>
	     	 <c:if test="${!empty error }">
		
				<div class="alert alert-warning">
		 				 <strong>${error}</strong> 
				</div>				
			
			</c:if>	
	    </form>
    </div>
<jsp:include page="/WEB-INF/jsp/includes/footer.jsp"/>