<%--
  Created by IntelliJ IDEA.
  User: turgaycan
  Date: 9/30/15
  Time: 12:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url var="firstUrl" value="${pageUrl}/${pagingDTO.first}"/>
<c:url var="beginUrl" value="${pageUrl}/${pagingDTO.begin}"/>
<c:url var="endUrl" value="${pageUrl}/${pagingDTO.end}"/>
<c:url var="prevUrl" value="${pageUrl}/${pagingDTO.prev}"/>
<c:url var="nextUrl" value="${pageUrl}/${pagingDTO.next}"/>
<c:url var="lastUrl" value="${pageUrl}/${pagingDTO.totalPages}"/>

<div class="pagination-wrap">
    <ul class="pagination">
        <c:choose>
            <c:when test="${pagingDTO.current == pagingDTO.first || pagingDTO.current == 0}">
                <li class="disabled"><a aria-label="Previous" href="#">&lt;&lt;</a></li>
                <li class="disabled"><a aria-label="Previous" href="#">Geri</a></li>
            </c:when>
            <c:otherwise>
                <li><a title="${firstUrl}" href="${firstUrl}">&lt;&lt;&lt;</a></li>
                <li><a title="${beginUrl}" href="${beginUrl}">&lt;&lt;</a></li>
                <li><a title="${prevUrl}" href="${prevUrl}">Geri</a></li>
            </c:otherwise>
        </c:choose>
        <c:forEach var="i" begin="${pagingDTO.begin}" end="${pagingDTO.end}">
            <c:url var="url" value="${pageUrl}/${i}"/>
            <c:choose>
                <c:when test="${i == pagingDTO.current}">
                    <li class="active"><a title="${url}" href="${url}"><c:out value="${i}"/></a></li>
                </c:when>
                <c:otherwise>
                    <li><a title="${url}" href="${url}"><c:out value="${i}"/></a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${pagingDTO.current >= pagingDTO.totalPages || pagingDTO.next == 1}">
                <li class="disabled"><a href="#">İleri</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a title="${nextUrl}" href="${nextUrl}">İleri</a></li>
                <li><a title="${endUrl}" href="${endUrl}">&gt;&gt;</a></li>
                <li><a title="${lastUrl}" href="${lastUrl}">&gt;&gt;&gt;</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>