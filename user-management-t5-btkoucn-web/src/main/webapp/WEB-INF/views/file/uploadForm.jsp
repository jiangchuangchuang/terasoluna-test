<script type="text/javascript">
    var timer1 = setInterval(refeshPage,1000);

    function refeshPage(){
        if(getCookie("updateStatus")=="success"){
	    clearInterval(timer1);
	    delCookie("updateStatus");
	    location.reload(true);
	 }
    }
</script>
<div id="wrapper" class="hasBorder bodyDiv">
	<div class="inputBoxDiv">
		<form:form
			action="${pageContext.request.contextPath}/batch/batchResult"
			method="get">
			<input type="submit" style="width: 150px;"
				value="<spring:message code="home.button.insertAllResult"/>" />
		</form:form>
	</div>
	<div class="centerDiv" style="margin-left: 15%;">
		<t:messagesPanel disableHtmlEscape="true" />
		<spring:nestedPath path="fileUploadForm">
			<form:errors path="*" element="div" cssClass="error-message-list" cssStyle="width:600px;margin-left: 200px;"/>
		</spring:nestedPath>
		<form:form action="${pageContext.request.contextPath}/file/upload"
			method="post" modelAttribute="fileUploadForm"
			enctype="multipart/form-data">
			<div class="inputBoxDiv">
				<form:label path="file" cssStyle="width: 200px;"
					cssErrorClass="lableStyle text-error" cssClass="lableStyle">
					<spring:message code="file.upload.fileName" />：</form:label>
				<form:input path="file" type="file" name="file"
					cssStyle="width: 600px;" cssErrorClass="inputStyle input-error"
					cssClass="inputStyle" />
			</div>

			<br />
			<br />
			<div class="inputBoxDiv">
				<form:button style="margin-left: 125px;width:125px;">
					<spring:message code="file.upload.button.insert" />
				</form:button>
			</div>
		</form:form>
	</div>
</div>
