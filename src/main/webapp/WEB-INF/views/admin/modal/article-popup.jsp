<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 06/02/17
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel-body">
    <!-- Modal -->
    <div class="modal fade" id="articleDetailPopup" tabindex="-1" role="dialog" aria-labelledby="articleModalTitle"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="articleModalTitle"></h4>
                    <h6 id="createdate"> </h6>
                    <div class="form-group">
                        <label for="articleStatus">${artcile.articleStatus}</label>
                        <select id="articleStatus" class="form-control">
                            <c:forEach var="articleStatus" items="<%=com.kp.domain.model.ArticleStatus.values()%>">
                                <option>${articleStatus.name()}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="modal-body">
                    <%--${artcile.content}--%>
                </div>
                <%--<div> ${artcile.tags}</div>--%>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
</div>
