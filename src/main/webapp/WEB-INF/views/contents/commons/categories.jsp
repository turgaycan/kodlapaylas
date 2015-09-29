<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: turgaycan
  Date: 9/28/15
  Time: 1:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-sm-6 col-md-12 col-lg-12">

    <div class="panel panel-default theme-panel">

        <div class="panel-heading">Kategoriler</div>

        <div class="panel-body nopadding">

            <div class="list-group">

                <c:forEach items="${categories}" var="category">
                    <a href="<c:url value="${fn:toLowerCase(category.name)}" /> " class="list-group-item">${category.name}</a>
                </c:forEach>
            </div>

        </div>

    </div>

</div>
