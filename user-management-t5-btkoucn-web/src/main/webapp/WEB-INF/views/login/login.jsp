<div id="wrapper" class="hasBorder bodyDiv">
	<div class="centerDiv">
		<t:messagesPanel disableHtmlEscape="true"/>
		<form:form action="${pageContext.request.contextPath}/login/dologin"
			method="post" modelAttribute="loginForm">
			<div class="inputBoxDiv">
				<form:label path="userId" cssErrorClass="lableStyle text-error" cssClass="lableStyle"><spring:message code="loginForm.userId"/>：</form:label>
				<form:input path="userId" id="userId" name="userId"
					cssErrorClass="inputStyle input-error" cssClass="inputStyle"/>
				<form:errors path="userId" cssClass="error-messages" />
			</div>

			<div class="inputBoxDiv">
				<form:label path="password" cssErrorClass="lableStyle text-error" cssClass="lableStyle"><spring:message code="loginForm.password"/>：</form:label>
				<form:password path="password" id="password" name="password"
					cssErrorClass="inputStyle input-error" cssClass="inputStyle"/>
				<form:errors path="password" cssClass="error-messages" />
			</div>

			<div class="inputBoxDiv">
				<form:button style="margin-left: 125px;width:125px;"><spring:message code="login.button.doLogin"/></form:button>
			</div>

		</form:form>
	</div>
</div>
