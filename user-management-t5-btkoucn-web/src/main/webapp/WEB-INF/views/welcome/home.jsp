<div id="wrapper" class="hasBorder bodyDiv">
	<div class="centerDiv">
		<sec:authorize access="isAuthenticated()">
			<sec:authorize access="hasRole('ADMIN')">
				<div
					style="margin-top: 10px; margin-right: 100px; margin-bottom: 50px; display: inline-block;">
					<form:form action="${pageContext.request.contextPath}/user/insert"
						method="get">
						<input type="submit" style="width: 150px;" name="form"
							value="<spring:message code="home.button.insert"/>" />
					</form:form>
				</div>
			</sec:authorize>
			<div style="display: inline-block;">
				<form:form action="${pageContext.request.contextPath}/user/search"
					method="get">
					<input type="submit" style="width: 150px;" name="form"
						value="<spring:message code="home.button.search"/>" />
				</form:form>
			</div>
			<sec:authorize access="hasRole('ADMIN')">
				<div>
					<div style="margin-right: 100px; display: inline-block;">
						<form:form
							action="${pageContext.request.contextPath}/file/upload"
							method="get">
							<input type="submit" style="width: 150px;"
								value="<spring:message code="home.button.insertAll"/>" />
						</form:form>
					</div>
					<div style="display: inline-block;">
						<form:form
							action="${pageContext.request.contextPath}/batch/batchResult"
							method="get">
							<input type="submit" style="width: 150px;"
								value="<spring:message code="home.button.insertAllResult"/>" />
						</form:form>
					</div>
				</div>
			</sec:authorize>
		</sec:authorize>
	</div>
</div>
