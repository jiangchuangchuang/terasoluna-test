<script src="${pageContext.request.contextPath}/resources/app/js/user/user.js"></script>
<div class="hasBorder bodyDiv">
	<div class="centerDiv" style="margin-top: 2%; margin-left: 5%;">
		<c:if test="${page != null && page.totalPages != 0}">
			<t:pagination page="${page}" outerElementClass="pagination"
				maxDisplayCount="5" outerElement="div" pathTmpl="" />
			<div style="margin-left: 75%;">
				${f:h(page.number + 1) } / ${f:h(page.totalPages)}
				<spring:message code="Pages" />
				(
				<fmt:formatNumber value="${page.totalElements}" />
				<spring:message code="results" />
				)
			</div>
			<div class="commontable">
				<table>
					<thead>
						<tr>
							<th width="50" class=""><spring:message
									code="userForm.isSelected" /></th>
							<th width="100" class=""><spring:message
									code="userForm.userId" /></th>
							<th width="200" class=""><spring:message
									code="userForm.username" /></th>
							<th width="100" class=""><spring:message
									code="userForm.birthDay" /></th>
							<th width="200" class=""><spring:message
									code="userForm.address" /></th>
							<th width="150" class=""><spring:message
									code="userForm.tenNum" /></th>
							<th width="100" class=""><spring:message
									code="userForm.role" /></th>
							<th width="100" class=""><spring:message
									code="userForm.loginStatus" /></th>
						</tr>
					</thead>

					<c:forEach var="item" items="${page.content}" varStatus="rowStatus">
						<tr>
							<td width="50">
								<c:if test="${item.loginStatus != 'DEL'}">
									<input id="${rowStatus.index}" name="selectRow" type="radio"
										value="${f:h(item.userId)}" />
								</c:if>
							</td>
							<td width="100">${f:h(item.userId)}</td>
							<td width="200">${f:h(item.username)}</td>
							<td width="100">${f:h(item.birthDay)}</td>
							<td width="200">${f:h(item.address)}</td>
							<td width="150">${f:h(item.tenNum)}</td>
							<td width="100">
								<c:forEach var="roleItem"
									items="${item.roles}">
									${f:h(roleItem.role)}
									<br />
								</c:forEach></td>
							<td width="100">
								<c:forEach var="loginStatus"
									items="${CL_LOGINSTATUS}">
									<c:if test="${item.loginStatus == loginStatus.key}">
									${f:h(loginStatus.value)}
									</c:if>
								</c:forEach>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div style="margin-left: 40%; margin-top: 20px;">
				<div style="margin-right: 50px; display: inline-block;">
					<form:form action="${pageContext.request.contextPath}/user/search"
						modelAttribute="userForm" method="get">
						<input type="submit" name="back"
							value="<spring:message code="userForm.button.back" />" />
					</form:form>
				</div>

				<sec:authorize access="hasRole('ADMIN')">
					<div style="margin-right: 50px; display: inline-block;">
						<form:form action="${pageContext.request.contextPath}/user/update"
							method="get" modelAttribute="userForm" id="updateForm">
							<input type="hidden" id="updateUserId" path="userId" name="userId" value=""/>
							<input type="button" id="updateButton"
								value="<spring:message code="userForm.button.update" />" />
						</form:form>
					</div>
					<div style="display: inline-block;">
						<form:form action="${pageContext.request.contextPath}/user/delete"
							method="get" modelAttribute="userForm" id="deleteForm">
							<input type="hidden" id="deleteUserId" path="userId" name="userId" value="" />
							<input type="button" id="deleteButton"
								value="<spring:message code="userForm.button.delete" />" />
						</form:form>
					</div>
				</sec:authorize>
				
			</div>
		</c:if>
	</div>
</div>