<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: turgaycan
  Date: 9/29/15
  Time: 2:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<section class="innercontent">

    <div class="container">

        <div class="row">

            <div class="col-md-12">

                <div class="error-post">

                    <h1 id="error_title">
                        Bilinmedik bir hata! Şlaps bit with around..
                    </h1>

                    <div id="error_msg">
                        404
                        <br/>
                    </div>

                    <form action="<c:url value='/ara' />" class="error-search-form">

                        <div class="input-group">

                            <input type="text" class="form-control" placeholder="Search for...">

                              <span class="input-group-btn">

                                <button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>

                              </span>

                        </div>

                    </form>
                    <div class="error-search-form">
                        <a href='<c:url value='/' />'><img
                                src='<c:url value='resources/static/img/error/uzgunuzpampa.png' />'
                                class='img-responsive' style='height: 400px !important;' alt='Bulunamadı..'/>
                        </a>
                    </div>

                </div>
            </div>

        </div>

    </div>

    </div>

</section>
