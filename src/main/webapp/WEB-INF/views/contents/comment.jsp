<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 05/10/15
  Time: 01:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="comment-form">

    <h4>Yorum Yap</h4>

    <form role="commentModel" action="<c:url value="/yorum/yeni" />" method="post" id="comment-form">

        <input type="hidden" value="${article.id}" name="articleId" id="articleId" />

        <div class="row">

            <div class="col-md-6 col-lg-5">

                <div class="form-group">

                    <input type="text" class="form-control" name="fullname" id="fullname" autocomplete="off"
                           placeholder="Ad Soyad">

                </div>

            </div>

        </div>

        <div class="row">

            <div class="col-md-6 col-lg-5">

                <div class="form-group">

                    <input type="email" class="form-control" name="email" id="email" autocomplete="off"
                           placeholder="E-Posta Adres">

                </div>

            </div>

        </div>

        <div class="form-group">

            <textarea class="form-control" name="message" id="message" placeholder="Mesaj" rows="3"></textarea>

        </div>


        <button type="submit" class="btn btn-default">Yorum Yap</button>

    </form>
    <div class="error">${notfound}</div>

</div>
