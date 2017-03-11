<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 20/02/17
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="<spring:url value="/resources/static/admin/admin-style.css" />" type="text/css"/>
<link rel="stylesheet" href="<spring:url value="/resources/static/admin/bootstrap-select.min.css" />" type="text/css"/>
<script href="<spring:url value="/resources/static/admin/bootstrap-select.min.js" />" type="text/javascript"></script>

<script src="<spring:url value="/resources/static/admin/tinymce/tinymce.min.js"/>"
        type="text/javascript"></script>
<script src="<spring:url value="/resources/static/admin/tinymce/jquery.tinymce.min.js"/>"
        type="text/javascript"></script>

<script type="text/javascript">
    tinymce.init({
        selector: 'textarea',
        height: 500,
        theme: 'modern',
        skin: "lightgray",
        codesample_languages: [
            {text: 'HTML/XML', value: 'markup'},
            {text: 'JavaScript', value: 'javascript'},
            {text: 'CSS', value: 'css'},
            {text: 'PHP', value: 'php'},
            {text: 'Ruby', value: 'ruby'},
            {text: 'Python', value: 'python'},
            {text: 'Java', value: 'java'},
            {text: 'C', value: 'c'},
            {text: 'C#', value: 'csharp'},
            {text: 'C++', value: 'cpp'}
        ],
        plugins: [
            'advlist autolink lists link image charmap print preview hr anchor pagebreak',
            'searchreplace wordcount visualblocks visualchars code fullscreen',
            'insertdatetime media nonbreaking save table contextmenu directionality',
            'emoticons template paste textcolor colorpicker textpattern imagetools codesample toc codesample'
        ],
        toolbar1: 'undo redo | insert | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
        toolbar2: 'print preview media | forecolor backcolor emoticons | codesample',

        image_advtab: true,
        templates: [
            {title: 'Test template 1', content: 'Test 1'},
            {title: 'Test template 2', content: 'Test 2'}
        ],
        content_css: [
            '<spring:url value="/resources/static/admin/tinymce/skins/lightgray/skin.min.css"/>'
        ]
    });
</script>


<div id="wrapper">
    <%@include file="navigation.jsp" %>
    <div id="page-wrapper">
        <div class="container">
            <form action="<c:url value="/new"/>" method="POST">
                <div class="row">

                    <div class="col-md-9 col-lg-9">

                        <article class="post">

                            <div class="post-type post-img">

                            </div>

                            <div class="caption">

                                <div class="form-group">
                                    <label for="articleStatus">Statü</label>
                                    <select id="articleStatus" name="articleStatus" class="form-control selectpicker col-xs-3">
                                        <option value="${article.articleStatus.name()}">${article.articleStatus.name()}</option>
                                        <c:forEach var="articleStatus" items="<%=com.kp.domain.model.ArticleStatus.values()%>">
                                            <c:if test="${article.articleStatus.name() ne articleStatus.name()}">
                                                <option value="${articleStatus.name()}">${articleStatus.name()}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="category">Kategori</label>
                                    <select id="category" name="category" class="form-control selectpicker col-xs-3">
                                        <%--<option value="${article.category.id}">${article.category.name}</option>--%>
                                        <c:forEach var="category" items="${categories}">
                                            <c:if test="${category.name ne article.category.name}">
                                                <option value="${category.id}">${category.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label>Başlık</label>
                                    <input class="form-control" id="title" name="title" value="${article.title}"
                                           placeholder="${article.title}">
                                </div>

                                <p>
                                <div id="divContent">

                                    <textarea id="content" name="content" style="width: 100%;">
                                        ${article.content}
                                    </textarea>
                                </div>
                                </p>

                            </div>

                            <div class="line-block">

                                <div class="form-group">
                                    <label>Etiketler</label>
                                    <input class="form-control" id="tags" name="tags" value="${article.tags}"
                                           placeholder="${article.tags}">
                                </div>

                            </div>

                            <div id="error">${errors}</div>
                        </article>

                        <div class="clearfix"></div>

                    </div>
                </div>
                <button type="submit" class="btn btn-outline btn-primary btn-lg btn-block">Kaydet</button>
            </form>
        </div>

    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->