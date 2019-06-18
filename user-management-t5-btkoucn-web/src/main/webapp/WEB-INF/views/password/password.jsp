<div class="hasBorder bodyDiv">

	<div class="centerDiv">
		<form:form action="${pageContext.request.contextPath}/password/update"
			method="post" modelAttribute="passwordForm">
			<t:messagesPanel />
			<sec:authentication property="principal" var="userDetails" />
			<form:hidden path="userId" value="${f:h(userDetails.account.userId)}" />
			
			<div class="inputBoxDiv">
				<form:label path="password" cssErrorClass="lableStyle text-error" cssClass="lableStyle"><spring:message code="passwordForm.password"/>：</form:label>
				<form:password path="password" cssErrorClass="inputStyle input-error" cssClass="inputStyle"/>				
				<form:errors path="password" cssClass="error-messages" />
			</div>

			<div class="inputBoxDiv">
				<form:label path="passwordConfirm" cssErrorClass="lableStyle text-error" cssClass="lableStyle"><spring:message code="passwordForm.passwordConfirm"/>：</form:label>
				<form:password path="passwordConfirm" cssErrorClass="inputStyle input-error" cssClass="inputStyle" />				
				<form:errors path="passwordConfirm" cssClass="error-messages" />
			</div>

			<div class="inputBoxDiv">
				<form:button style="margin-left: 125px;"><spring:message code="password.button.changed"/></form:button>
			</div>
		</form:form>
	</div>
</div>