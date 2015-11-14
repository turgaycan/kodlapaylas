<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 09/10/15
  Time: 02:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="kpModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Tekrar Yorumla</h4>
            </div>
            <div class="modal-body">
                <form id="comment-reply-form">
                    <div class="row">

                        <div class="col-md-6 col-lg-5">

                            <div class="form-group">

                                <input type="text" class="form-control" name="commentFullname" id="commentFullname"
                                       autocomplete="off" required="true"
                                       placeholder="Ad Soyad">

                            </div>

                        </div>

                    </div>

                    <div class="row">

                        <div class="col-md-6 col-lg-5">

                            <div class="form-group">

                                <input type="email" class="form-control" name="commentEmail" id="commentEmail"
                                       autocomplete="off" required="true"
                                       placeholder="E-Posta Adres">

                            </div>

                        </div>

                    </div>

                    <div class="form-group">

                    <textarea class="form-control" name="commentMessage" id="commentMessage" placeholder="Mesaj" required="true"
                              rows="3"></textarea>

                    </div>
                    <div id="replyCommentErrorMessage"></div>
                    <input type="hidden" value="" id="commentId" name="commentId"/>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Kapat</button>
                <button type="button" class="btn btn-primary" id="replyComment" name="replyComment">Yorumla</button>
            </div>
        </div>
    </div>
</div>
