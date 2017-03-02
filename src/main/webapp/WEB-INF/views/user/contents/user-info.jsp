<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 05/02/17
  Time: 00:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                            <form action="<c:url value='/user/info-update' />" method="post" role="userUpdateInfo">

                                <%--<input type="hidden" id="username" name="username" value="${currentUser.username}"/>--%>
                                <%--<input type="hidden" id="email" name="email" value="${currentUser.email}"/>--%>

                                <div class="form-group">
                                    <label for="disabledSelect">E-Posta</label>
                                    <input class="form-control" id="email" name="email" type="text" value="${currentUser.email}"
                                           placeholder="${currentUser.email}" disabled>
                                </div>

                                <div class="form-group">
                                    <label f>Kullanıcı Ad</label>
                                    <input class="form-control" id="username" name="username" type="text" value="${currentUser.username}"
                                           placeholder="${currentUser.username}">
                                </div>

                                <div class="form-group">
                                    <label>Kayıt Tarihi</label>
                                    <p class="form-control-static">${currentUser.createdate}</p>
                                </div>

                                <div class="form-group">
                                    <label>Ad Soyad</label>
                                    <input class="form-control" id="fullname" name="fullname"
                                           value="${currentUser.fullname}"
                                           placeholder="${currentUser.fullname}">
                                </div>

                                <div class="form-group">
                                    <label>Website</label>
                                    <input class="form-control" id="website" name="website"
                                           value="${currentUser.website}"
                                           placeholder="${currentUser.website}">
                                </div>

                                <div class="form-group">
                                    <label>Unvan</label>
                                    <p class="form-control-static">${currentUser.role}</p>
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
