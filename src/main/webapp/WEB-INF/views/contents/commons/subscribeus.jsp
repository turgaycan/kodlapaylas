<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: turgaycan
  Date: 9/28/15
  Time: 12:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-sm-6 col-md-12 col-lg-12">
    <div class="panel panel-default theme-panel">
        <div class="panel-heading">Abone Ol</div>
        <div class="panel-body">
            <form id="subscribe-form" name="subscriberModel">
                <div class="form-group">
                    <input type="text" class="form-control" id="subscriberEmail" name="subscriberEmail" placeholder="@ Email">
                </div>
            </form>
            <div class="form-group">
                <button class="btn btn-default btn-lg pull-right" id="subscribe" type="button">Abone Ol</button>
            </div>
            <div id='message'></div>
        </div>

    </div>

</div>
