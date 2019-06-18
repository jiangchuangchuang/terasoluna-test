<div class="hasBorder bodyDiv">
	<div class="centerDiv" style="margin-top: 5%;">
		<form:form action="${pageContext.request.contextPath}/user/search"
			modelAttribute="userForm">
			<t:messagesPanel />
			<!-- ユーザID -->
			<div class="inputBoxDiv">
				<form:label path="userId" cssErrorClass="lableStyle text-error" cssClass="lableStyle"><spring:message code="userForm.userId"/>：</form:label>
				<form:input path="userId" cssErrorClass="inputStyle input-error" cssClass="inputStyle"/>				
				<form:errors path="userId" cssClass="error-messages" />
			</div>
			
			<!-- 名前 -->
			<div class="inputBoxDiv">
				<form:label path="username" cssErrorClass="lableStyle text-error" cssClass="lableStyle"><spring:message code="userForm.username"/>：</form:label>
				<form:input path="username" cssErrorClass="inputStyle input-error" cssClass="inputStyle"/>				
				<form:errors path="username" cssClass="error-messages" />
			</div>
			
			<!-- 生年月日 -->
			<div class="inputBoxDiv">
				<form:label path="birthDay" cssErrorClass="lableStyle text-error" cssClass="lableStyle"><spring:message code="userForm.birthDay"/>：</form:label>
				<form:input path="birthDay" cssErrorClass="inputStyle input-error" cssClass="inputStyle"/>
				<span><spring:message code="userForm.label.birthDay"/>  </span>				
				<form:errors path="birthDay" cssClass="error-messages" />
			</div>
			
			<!-- 住所 -->
			<div class="inputBoxDiv">
				<form:label path="address" cssErrorClass="lableStyle text-error" cssClass="lableStyle"><spring:message code="userForm.address"/>：</form:label>
				<form:input path="address" cssErrorClass="inputStyle input-error" cssClass="inputStyle"/>				
				<form:errors path="address" cssClass="error-messages" />
			</div>
			
			<!-- 電話番号 -->
			<div class="inputBoxDiv">
				<form:label path="tenNum" cssErrorClass="lableStyle text-error" cssClass="lableStyle"><spring:message code="userForm.tenNum"/>：</form:label>
				<form:input path="tenNum" cssErrorClass="inputStyle input-error" cssClass="inputStyle"/>				
				<form:errors path="tenNum" cssClass="error-messages" />
			</div>
			
			<!-- 権限 -->
			<div class="inputBoxDiv">
				<form:label path="role" cssErrorClass="lableStyle text-error" cssClass="lableStyle"><spring:message code="userForm.role"/>：</form:label>
				<form:select path="role" cssErrorClass="inputStyle input-error" cssClass="inputStyle">
					<form:option value="" label="--Select--" />
					<form:options items="${CL_ROLES}" />
				</form:select>				
				<form:errors path="role" cssClass="error-messages" />
			</div>
			
			<!-- 状態 -->
			<div class="inputBoxDiv">
				<form:label path="loginStatus" cssErrorClass="lableStyle text-error" cssClass="lableStyle"><spring:message code="userForm.loginStatus"/>：</form:label>
				<form:select path="loginStatus" cssErrorClass="inputStyle input-error" cssClass="inputStyle">
					<form:option value="" label="--Select--" />
					<form:options items="${CL_LOGINSTATUS}" />
				</form:select>				
				<form:errors path="loginStatus" cssClass="error-messages" />
			</div>

			<!-- 検索 -->
			<div class="inputBoxDiv">
				<form:button style="margin-left: 125px;" name="search"><spring:message code="userForm.button.search"/></form:button>
			</div>
		</form:form>
	</div>
</div>