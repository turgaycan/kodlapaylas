<%--
  Created by IntelliJ IDEA.
  User: turgaycan
  Date: 9/28/15
  Time: 1:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-sm-6 col-md-12 col-lg-12">

    <div class="panel panel-default theme-panel">

        <div class="panel-heading">Etiketler</div>

        <div class="panel-body">

            <ul class="list-inline tags">
                <c:forEach items="${tags}" var="tag">
                    <c:url value="${tag.tagUrl()}" var="tagUrl"/>
                    <c:choose>
                        <c:when test="${tag.count >= 10}">
                            <li class="big"><a href="<c:out value='${tagUrl}'/>">${tag.name}</a></li>
                        </c:when>
                        <c:when test="${tag.count > 5 && tag.count <10}">
                            <li><a href="<c:out value='${tagUrl}'/>">${tag.name}</a></li>
                        </c:when>
                        <c:otherwise>

                            <li class="small"><a href="<c:out value='${tagUrl}'/>">${tag.name}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>

        </div>

    </div>

</div>
