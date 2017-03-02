<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 25/02/17
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="page-wrapper">
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Şifre değiştir..
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <form action="<c:url value='/sifre-degistir' />" method="post" role="userUpdateInfo">

                                <div class="form-group">
                                    <label>Şifre</label>
                                    <input class="form-control" id="password" name="password" type="password"
                                           placeholder="Şifrenizi giriniz..">
                                </div>

                                <div class="form-group">
                                    <label>Şifre</label>
                                    <input class="form-control" id="newPassword" name="newPassword" type="password"
                                           placeholder="Yeni Şifrenizi giriniz..">
                                </div>

                                <div class="form-group">
                                    <label>Yeni Şifre Tekrar</label>
                                    <input class="form-control" id="newPasswordRepeated" name="newPasswordRepeated"
                                           type="password"
                                           placeholder="Yeni Şifrenizi tekrar giriniz..">
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