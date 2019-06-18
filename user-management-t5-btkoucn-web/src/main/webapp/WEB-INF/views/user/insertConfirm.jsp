<div class="hasBorder bodyDiv">
	<div class="centerDiv" style="margin-top: 5%;">
		<t:messagesPanel />
		<!-- ユーザID -->
		<div class="inputBoxDiv">
			<span class="lableStyle">
				<spring:message code="userForm.userId" />：</span>
			<span>${f:h(userForm.userId)}</span>
		</div>

		<!-- 名前 -->
		<div class="inputBoxDiv">
			<span class="lableStyle">
				<spring:message code="userForm.username" />：</span>
			<span>${f:h(userForm.username)}</span>
		</div>

		<!-- 生年月日 -->
		<div class="inputBoxDiv">
			<span class="lableStyle">
				<spring:message code="userForm.birthDay" />：</span>
			<span>${f:h(userForm.birthDay)}</span>
		</div>

		<!-- 住所 -->
		<div class="inputBoxDiv">
			<span class="lableStyle">
				<spring:message code="userForm.address" />：</span>
			<span>${f:h(userForm.address)}</span>
		</div>

		<!-- 電話番号 -->
		<div class="inputBoxDiv">
			<span class="lableStyle">
				<spring:message code="userForm.tenNum" />：</span>
			<span>${f:h(userForm.tenNum)}</span>
		</div>

		<!-- 権限 -->
		<div class="inputBoxDiv">
			<span class="lableStyle">
				<spring:message code="userForm.role" />：</span>
			<c:forEach var="role" varStatus="status" items="${userForm.roleList}">
				<span style="display: inline-block;">${f:h(role)}</span>
				<c:if test="${!status.last}">
					<span style="display: inline-block;">,</span>
				</c:if>
			</c:forEach>
		</div>

		<div class="inputBoxDiv" style="display: inline-block;margin-right: 50px;">
			<form:form action="${pageContext.request.contextPath}/user/insert"
				modelAttribute="userForm" method="post">
				<!-- 確認 -->
				<form:button style="margin-left: 50px;width:100px;">
					<spring:message code="userForm.button.confirm" />
				</form:button>
			</form:form>
		</div>

		<div class="inputBoxDiv" style="display: inline-block;">
			<form:form action="${pageContext.request.contextPath}/user/insert"
				modelAttribute="userForm" method="post">
				<!-- やり直し -->
				<form:button style="width:100px;" name="redo">
					<spring:message code="userForm.button.redo" />
				</form:button>
			</form:form>
		</div>
	</div>
</div>