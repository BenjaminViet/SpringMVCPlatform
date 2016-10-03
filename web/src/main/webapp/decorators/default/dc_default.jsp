<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html>
<head>
    <title><decorator:title default="SiteMesh Integration"/></title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>

    <c:choose>
        <c:when test="${instanceType == null || (instanceType != null && instanceType == 'DEV')}">
            <link rel="stylesheet" type="text/css" href="<c:url value="/themes/white-black/clientstyle/css/dev/style.css" />" />
        </c:when>
        <c:otherwise>
            <link rel="stylesheet" type="text/css" href="<c:url value="/themes/white-black/clientstyle/css/pro/style.min.css" />" />
        </c:otherwise>
    </c:choose>
</head>

<decorator:head/>
<body>

<div id="content">
    <jsp:include page="dc_header.jsp" />
    <div id="wrapper">
        <decorator:body/>
    </div>
    <jsp:include page="dc_footer.jsp" />
</div>

</body>
</html>