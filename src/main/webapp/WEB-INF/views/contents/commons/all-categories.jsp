<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 08/11/15
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="dropdown-menu">

    <div class="container">

        <div class="mega-dropdown-menu">

            <div class="tabbable tabs-left">

                <ul class="nav nav-tabs">
                    <c:forEach items="${categoryUIModels}" var="categoryUIModel" varStatus="status">
                        <c:choose>
                            <c:when test="${status.first}">
                                <li class="active"><a title="${categoryUIModels[status.index].category.name}"
                                        href="#${categoryUIModels[status.index].category.name}"
                                        data-toggle="tab">${categoryUIModels[status.index].category.name}<i
                                        class="arrow_carrot-right"></i></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a title="${categoryUIModel.category.name}"
                                        href="#${categoryUIModel.category.name}"
                                        data-toggle="tab">${categoryUIModel.category.name}<i
                                        class="arrow_carrot-right"></i></a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>

                <div class="tab-content">
                    <c:forEach items="${categoryUIModels}" var="categoryUIModel" varStatus="subStatus">

                        <div class="tab-pane <c:if test="${subStatus.first}">active</c:if>"
                             id="${categoryUIModel.category.name}">
                            <c:forEach items="${categoryUIModel.subCategories}" var="subCategory">

                                <div class="col-sm-6 col-xs-12 col-md-2" style="width:150px !important;height:105px !important;">
                                    <div class="thumbnail" style="margin-bottom: 15px !important;">
                                        <a title="${fn:toLowerCase(subCategory.name)}" href="<c:url value="/kategori/${fn:toLowerCase(subCategory.name)}" />">
                                            <img src="<c:url value='/resources/static/img/${fn:toLowerCase(subCategory.name)}.png' />"
                                                 alt="${fn:toLowerCase(subCategory.name)}">
                                        </a>

                                        <div class="caption">
                                            <h3>
                                                <a title="${fn:toLowerCase(subCategory.name)}" href="<c:url value="/kategori/${fn:toLowerCase(subCategory.name)}" />">
                                                        ${subCategory.name}
                                                </a>
                                            </h3>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>

            </div>

        </div>

    </div>

</div>