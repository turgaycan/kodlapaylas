<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 31/01/16
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-sm-6 col-md-12 col-lg-12">
    <div class="panel panel-default theme-panel">
        <div class="panel-heading"><a href="<c:url value="/arsiv" /> ">Arşiv</a></div>
        <div class="panel-body nopadding">
            <div class="list-group">
                <c:forEach var="eachYear" items="${years}">
                    <a href="<c:url value="/arsiv/${eachYear}" />"
                       class="list-group-item">Arşiv ${eachYear}</a>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
