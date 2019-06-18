<div class="hasBorder bodyDiv">
	<div class="centerDiv" style="margin-top: 5%;">
		<form:form action="${pageContext.request.contextPath}/user/update"
			modelAttribute="userForm">
			<t:messagesPanel />
			<!-- ユーザID -->
			<div class="inputBoxDiv">
				<form:label path="userId" cssClass="lableStyle"><spring:message code="userForm.userId"/>：</form:label>
				<form:hidden path="userId" value="${f:h(userForm.userId)}"/>
				<span>${f:h(userForm.userId)}</span>
			</div>
			
			<!-- 名前 -->
			<div class="inputBoxDiv">
				<form:label path="username" cssErrorClass="lableStyle text-error" cssClass="lableStyle"><spring:message code="userForm.username"/>：</form:label>
				<form:input path="username" cssErrorClass="inputStyle input-error" cssClass="inputStyle" value="${f:h(userForm.username)}"/>				
				<form:errors path="username" cssClass="error-messages" />
			</div>
			
			<!-- 生年月日 -->
			<div class="inputBoxDiv">
				<form:label path="birthDay" cssErrorClass="lableStyle text-error" cssClass="lableStyle"><spring:message code="userForm.birthDay"/>：</form:label>
				<form:input path="birthDay" cssErrorClass="inputStyle input-error" cssClass="inputStyle" value="${f:h(userForm.birthDay)}"/>
				<span><spring:message code="userForm.label.birthDay"/>  </span>				
				<form:errors path="birthDay" cssClass="error-messages" />
			</div>
			
			<!-- 住所 -->
			<div class="inputBoxDiv">
				<form:label path="address" cssErrorClass="lableStyle text-error" cssClass="lableStyle"><spring:message code="userForm.address"/>：</form:label>
				<form:input path="address" cssErrorClass="inputStyle input-error" cssClass="inputStyle" value="${f:h(userForm.address)}"/>				
				<form:errors path="address" cssClass="error-messages" />
			</div>
			
			<!-- 電話番号 -->
			<div class="inputBoxDiv">
				<form:label path="tenNum" cssErrorClass="lableStyle text-error" cssClass="lableStyle"><spring:message code="userForm.tenNum"/>：</form:label>
				<form:input path="tenNum" cssErrorClass="inputStyle input-error" cssClass="inputStyle" value="${f:h(userForm.tenNum)}"/>				
				<form:errors path="tenNum" cssClass="error-messages" />
			</div>
			
			<!-- 権限 -->
			<div class="inputBoxDiv">
				<form:label path="roleList" cssErrorClass="lableStyle text-error" cssClass="lableStyle"><spring:message code="userForm.role"/>：</form:label>
				<form:checkboxes path="roleList" items="${CL_ROLES}" cssErrorClass="checkbox input-error" cssClass="checkbox" />			
				<form:errors path="roleList" cssClass="error-messages margin-left" />
			</div>
			
			<div class="inputBoxDiv">
				<form:label path="password" cssErrorClass="lableStyle text-error" cssClass="lableStyle"><spring:message code="userForm.password"/>：</form:label>
				<form:password path="password" cssErrorClass="inputStyle input-error" cssClass="inputStyle"/>				
				<form:errors path="password" cssClass="error-messages" />
			</div>

			<div class="inputBoxDiv">
				<form:label path="passwordConfirm" cssErrorClass="lableStyle text-error" cssClass="lableStyle"><spring:message code="userForm.passwordConfirm"/>：</form:label>
				<form:password path="passwordConfirm" cssErrorClass="inputStyle input-error" cssClass="inputStyle" />				
				<form:errors path="passwordConfirm" cssClass="error-messages" />
			</div>

			<!-- 更新 -->
			<div class="inputBoxDiv">
				<form:button style="margin-left: 125px;" name="confirm"><spring:message code="userForm.button.update"/></form:button>
			</div>
		</form:form>
	</div>
</div>