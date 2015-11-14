<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: turgaycan
  Date: 9/29/15
  Time: 2:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="innercontent">

    <div class="container">

        <div class="row">

            <div class="col-md-12">

                <div class="error-post">

                    <h1 id="error_title">
                        <%--${error.type}--%>
                    </h1>

                    <div id="error_msg">
                        <%--${error}--%>
                        NOT FOUND!
                    </div>

                    <form action="<c:url value='/search' />" class="error-search-form">

                        <div class="input-group">

                            <input type="text" class="form-control" placeholder="Search for...">

                              <span class="input-group-btn">

                                <button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>

                              </span>

                        </div>

                    </form>

                </div>

            </div>

        </div>

    </div>

</section>
