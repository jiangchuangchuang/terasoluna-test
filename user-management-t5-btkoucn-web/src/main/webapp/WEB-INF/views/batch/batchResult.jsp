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
							<th width="100" class=""><spring:message
									code="batch.jobSeq" /></th>
							<th width="300" class=""><spring:message
									code="batch.jobName" /></th>
							<th width="100" class=""><spring:message
									code="batch.returnValue" /></th>
							<th width="100" class=""><spring:message
									code="batch.jobType" /></th>
							<th width="200" class=""><spring:message
									code="batch.addTime" /></th>
							<th width="200" class=""><spring:message
									code="batch.updateTime" /></th>
						</tr>
					</thead>

					<c:forEach var="item" items="${page.content}" varStatus="rowStatus">
						<tr>
							<td width="100">${f:h(item.jobSeq)}</td>
							<td width="300">${f:h(item.jobName)}</td>
							<td width="100">${f:h(item.returnValue)}</td>
							<td width="100">${f:h(item.jobType)}</td>
							<td width="200">${f:h(item.addTime)}</td>
							<td width="200">${f:h(item.updateTime)}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</div>
</div>