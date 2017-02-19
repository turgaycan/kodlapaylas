<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 14/02/17
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="<spring:url value="/resources/static/admin/admin-style.css" />" type="text/css"/>

<div id="wrapper">
    <%@include file="navigation.jsp" %>
    <div id="page-wrapper">
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Kullanıcı Bilgileri
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <form action="<c:url value='/update/user' />" method="post" role="userUpdateInfo">

                                    <input type="hidden" id="id" name="id" value="${user.id}"/>

                                    <div class="form-group">
                                        <label for="disabledSelect">E-Posta</label>
                                        <input class="form-control" id="email" name="email" type="text"
                                               placeholder="${user.email}" value="${user.email}" disabled>
                                    </div>

                                    <div class="form-group">
                                        <label for="disabledSelect">Kullanıcı Ad</label>
                                        <input class="form-control" id="username" name="username" type="text"
                                               placeholder="${user.username}" value="${user.username}" disabled>
                                    </div>

                                    <div class="form-group">
                                        <label>Kayıt Tarihi</label>
                                        <p class="form-control-static"><input class="form-control" id="createdate"
                                                                              name="createdate" type="text"
                                                                              placeholder="${user.createdate}"
                                                                              value="${user.createdate}" disabled></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Ad Soyad</label>
                                        <input class="form-control" id="fullname" name="fullname"
                                               value="${user.fullname}"
                                               placeholder="${user.fullname}">
                                    </div>

                                    <div class="form-group">
                                        <label>Website</label>
                                        <input class="form-control" id="website" name="website" value="${user.website}"
                                               placeholder="${user.website}">
                                    </div>

                                    <div class="form-group">
                                        <label for="userStatus">Statü</label>
                                        <select id="userStatus" name="userStatus"
                                                class="form-control selectpicker col-xs-3">
                                            <option value="${user.userStatus.name()}">${user.userStatus.name()}</option>
                                            <c:forEach var="userStatus" items="<%=com.kp.domain.model.UserStatus.values()%>">
                                                <c:if test="${user.userStatus.name() ne userStatus.name()}">
                                                    <option value="${userStatus.name()}">${userStatus.name()}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label for="role">Rol</label>
                                        <select id="role" name="role" class="form-control selectpicker col-xs-3">
                                            <option value="${user.role.name()}">${user.role.name()}</option>
                                            <c:forEach var="role" items="<%=com.kp.domain.model.Role.values()%>">
                                                <c:if test="${user.role.name() ne role.name()}">
                                                    <option value="${role.name()}">${role.name()}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                    </div>
                                    <button type="submit" class="btn btn-bitbucket">Güncelle</button>
                                </form>
                                <div class="alert-danger"> ${errors}</div>
                                <div class="alert-success"> ${success}</div>
                            </div>

                        </div>
                        <!-- /.row (nested) -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->
</div>