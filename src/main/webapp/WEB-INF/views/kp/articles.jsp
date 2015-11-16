<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 14/11/15
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/webjars/jquery/1.11.2/jquery.min.js" var="jQuery"/>
    <script src="${jQuery}"></script>
    <script src="<spring:url value="/resources/static/js/nicEdit.js" />"></script>
    <script src="<spring:url value="/resources/static/js/kp-app-admin.js" />"></script>
    <title></title>
</head>
<body>

<div id="sample">
    <script type="text/javascript">
        //<![CDATA[
        bkLib.onDomLoaded(function () {
            nicEditors.allTextAreas()
        });
        //]]>
    </script>
  <textarea name="area2" style="width: 100%;">
       Some Initial Content was in this textarea
</textarea><br/>
</div>

</body>
</html>
