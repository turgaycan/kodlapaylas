<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<sec:authorize access="isAnonymous()">
    <c:set var="userLoggedIn" value="false"/>
</sec:authorize>
<sec:authorize access="isFullyAuthenticated()">
    <c:set var="userLoggedIn" value="true"/>
</sec:authorize>
<c:set var="rootUrl" value="${pageContext.request.requestURL}"/>
<c:set var="rootUri" value="${pageContext.request.requestURI}"/>
<meta charset="utf-8">

<title>${seoMetaData.title}</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge">

<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="keywords" content="${seoMetaData.keywords}">
<meta name="description" content="${seoMetaData.description}">

<meta name="author" content="Turgay Can">

<%--<link rel="icon" href="http://www.mirchu.net/themes/BlogDesk/assets/icon/favicon.ico">--%>

<!-- Bootstrap core CSS -->
<spring:url value="/webjars/bootstrap/3.3.4/css/bootstrap.min.css" var="bootstrapCss"/>
<link rel="stylesheet" href="${bootstrapCss}" type='text/css'/>


<link rel="stylesheet" href="<spring:url value="/resources/static/css/style.css" />" type="text/css"/>

<spring:url value="/webjars/jquery/1.11.2/jquery.min.js" var="jQuery"/>
<script src="${jQuery}"></script>