<div class="hasBorder" style="height: 40px;">
	<h3 class="display-inline float-left" style="margin-top: 8px;margin-right: 20px;">
		<spring:message code="system.title" />
	</h3>
	<sec:authorize access="isAuthenticated()">
		<form:form action="${pageContext.request.contextPath}/home" method="get">
			<input type="submit" class="display-inline float-left" style="margin-top: 3px;"
				value="<spring:message code="header.button.topPage" />" />
		</form:form>
		<form:form method="post" action="${pageContext.request.contextPath}/logout">
			<input type="submit" class="display-inline float-right" style="margin-top: 3px;"
				value="<spring:message code="header.button.logout" />" />
		</form:form>
		<sec:authentication property="principal" var="userDetails" />
		<span class="display-inline float-right" style="margin-top: 8px;margin-right: 20px;"
		>${f:h(userDetails.account.username)}様 <spring:message
				code="header.loginStatus" /></span>
	</sec:authorize>
	
	<c:if test="${logoutOnly != null}">
		<form:form method="post" action="${pageContext.request.contextPath}/logout">
			<input type="submit" class="display-inline float-right" style="margin-top: 3px;"
				value="<spring:message code="header.button.logout" />" />
		</form:form>
	</c:if>
</div>