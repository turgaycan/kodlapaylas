<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 06/02/17
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="wrapper">
    <%@include file="navigation.jsp" %>
    <div id="page-wrapper">
        <%--<div class="row">--%>
        <%--<div class="col-lg-12">--%>
        <%--<h1 class="page-header">Yorumlar</h1>--%>
        <%--</div>--%>
        <%--<!-- /.col-lg-12 -->--%>
        <%--</div>--%>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Makaleler
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <table width="100%" class="table table-striped table-bordered table-hover" id="dtArticles">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Tarih</th>
                                <th>Görüntülenme</th>
                                <th>Uygulama Ad</th>
                                <th>İndirme</th>
                                <th>Güncelleme</th>
                                <th>Statü</th>
                                <th>Kullanıcı</th>
                                <th>Kategori</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="article" items="${articles}">
                                <tr class="odd gradeX">
                                    <td>
                                        <a href="<c:url value="/show/article/${article.id}" />"
                                           class="btn btn-primary btn-lg">${article.id}
                                        </a>
                                    </td>
                                    <td>${article.createdate}</td>
                                    <td>${article.viewNumber}</td>
                                    <td>${article.applicationName}</td>
                                    <td>${article.downloadNumber}</td>
                                    <td>${article.modifydate}</td>
                                    <td>${article.articleStatus}</td>
                                    <td>${article.user.email}</td>
                                    <td>${article.category.name}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
