<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 04/10/15
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="comment-list">
    <c:forEach items="${commentBaseModel.commentUIModels}" var="eachModel">
        <div class="media">
            <div class="media-left">
                <a href="#">
                    <img class="media-object"
                         src="http://www.mirchu.net/themes/BlogDesk/assets/images/comment-thumbnail.png"
                         alt="placeholder image">
                </a>
            </div>
            <div class="media-body">
                <p>${eachModel.comment.content}</p>
                <ul class="list-inline">
                    <li><a class="media-heading" href="#">
                        <c:choose>
                            <c:when test="${userLoggedIn eq 'false'}">
                                Anonymous
                            </c:when>
                            <c:otherwise>
                                ${pageContext.request.userPrincipal.name}
                            </c:otherwise>
                        </c:choose>
                    </a></li>
                    <li><fmt:formatDate value="${eachModel.comment.createdate}" type="both"
                                        pattern="dd MMM, yyyy hh:mm:ss"/></li>
                    <li>
                        <button type="button" class="btn btn-primary"
                                onclick="showKpModal(${eachModel.comment.id})">Yorumu Tekrarla
                        </button>
                    </li>
                </ul>
                <c:forEach items="${eachModel.replyComments}" var="eachComment">
                    <div class="media nested-first">
                        <div class="media-left">
                            <a href="#">
                                <img class="media-object"
                                     src="http://www.mirchu.net/themes/BlogDesk/assets/images/comment-thumbnail.png"
                                     alt="placeholder image">
                            </a>
                        </div>
                        <div class="media-body">
                            <p>${eachComment.content}</p>
                            <ul class="list-inline">

                                <li><a class="media-heading" href="#">
                                    <c:choose>
                                        <c:when test="${userLoggedIn eq 'false'}">
                                            Anonymous
                                        </c:when>
                                        <c:otherwise>
                                            ${pageContext.request.userPrincipal.name}
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                                </li>
                                <li><fmt:formatDate value="${eachComment.createdate}" type="both"
                                                    pattern="dd MMM, yyyy hh:mm:ss"/></li>
                                <li>
                                    <button type="button" class="btn btn-primary"
                                            onclick="showKpModal(${eachComment.id})">Yorumu Tekrarla
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
</div>
<%@include file="commons/modal.jsp" %>