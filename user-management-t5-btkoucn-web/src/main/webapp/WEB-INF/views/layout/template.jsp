<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript">
    
</script>
<c:set var="titleKey">
    <tiles:insertAttribute name="title" ignore="true" />
</c:set>
<title><spring:message code="${titleKey}"/></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/bootstrap.css"
    type="text/css" media="screen, projection">
<script src="${pageContext.request.contextPath}/resources/app/js/common/jquery.js"></script>
</head>
<body>
    <div>
        <tiles:insertAttribute name="header"/>
        <tiles:insertAttribute name="body" />
        <div class="hasBorder">
        <p style="text-align: right;">terasoluna5 勉強</p>
        </div>
    </div>
</body>
</html>