<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 26/12/15
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-sm-12 col-md-12 col-lg-12">

    <div class="panel panel-default theme-panel">

        <div class="panel-heading"><a href="<c:url value="/arsiv" />">Arşiv</a></div>

        <div class="panel-body nopadding">

            <div class="list-group">
                <c:forEach var="month" items="${months}">
                    <a href="<c:url value="${pageUrl}/${month.monthValue}" />" class="list-group-item">${month.monthName}</a>
                </c:forEach>
            </div>

        </div>

    </div>

</div>