<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<jsp:include page="/WEB-INF/jsp/includes/header.jsp"/>

    <div class="loginmodal-container">
        <h1>Identifiez-vous</h1><br>
	    <form method="POST" action="<%=request.getContextPath()%>/EmployeServlet">
	      <input type="text" name="login" id="login" placeholder="Adresse mail ou identifiant"/>
	      <input type="password" name="password" id="password" placeholder="Mot de passe"/> 
	      <c:if test="${error != null}">
            
            </c:if>
	      <input type="submit" name="connexion" class="login loginmodal-submit" value="Connexion">
	    </form>
    </div>
<jsp:include page="/WEB-INF/jsp/includes/footer.jsp"/>