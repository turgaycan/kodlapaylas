<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 05/02/17
  Time: 20:06
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
                        Yorumlar
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <table width="100%" class="table table-striped table-bordered table-hover" id="dtComments">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>İçerik</th>
                                <th>Tarih</th>
                                <th>Statü</th>
                                <th>Makale Id</th>
                                <th>Kullanıcı Id</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${comments}" var="comment">
                                <tr class="odd gradeX">
                                    <td>${comment.id}</td>
                                    <td>${comment.content}</td>
                                    <td>${comment.createdate}</td>
                                    <td>${comment.commentStatus}</td>
                                    <td>${comment.article.id}</td>
                                    <td>${comment.user.id}</td>
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