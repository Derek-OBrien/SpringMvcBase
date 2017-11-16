<%--
  Created by IntelliJ IDEA.
  User: Derek
  Date: 13/11/2017
  Time: 18:55
  Template Jsp Page
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Spring MVC</title>
    <!-- Include Includes template -->
    <jsp:include page="../templates/includes.jsp"/>

    <!-- Map java -->

</head>

<body>
<div class="container">

    <div class="row">
        <!-- Include Navigation Template-->
        <jsp:include page="../templates/navigation.jsp"/>
    </div>
    <div class="row">
        <div class="col col-lg-6">
            <p>${message}</p>
        </div>

        <div class="col col-lg-6 buttons">
            <span class="col col-lg-4">
                <a href="${pageContext.request.contextPath}/searchResults">
                    <button type="button" class="btn btn-primary" title="Search">
                        <i class="fa fa-search" aria-hidden="true"></i>
                    </button>
                </a>
            </span>

            <span class="col col-lg-4">
                <a href="<c:url value="/downloadFullAll"/>">
                    <button type="button" class="btn btn-primary"  title="Download All">
                        <i class="fa fa-download" aria-hidden="true"></i>
                    </button>
                </a>
            </span>

            <span class="col col-lg-4">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalFullAll" title="Delete All">
                    <i class="fa fa-trash" aria-hidden="true"></i>
                </button>
            </span>
        </div>

    </div>


    <div class="row">
        <form method="get" action="${pageContext.request.contextPath}/sortfull" class="sort-form">
            <button class="btn btn-primary" name="sortBtn" value="asc"><i class="fa fa-arrow-up" aria-hidden="true"></i></button>
            <button class="btn btn-primary" name="sortBtn" value="desc"><i class="fa fa-arrow-down" aria-hidden="true"></i>
            </button>
        </form>
        <ul class="fileList col col-12">
            <c:forEach var="listValue" items="${lists}">

                <li class="col col-lg-3 col-sm-12">
                    <div class="row">
                        <span class="file-name col col-12">${listValue}</span>
                    </div>
                    <div class="row">
                    <span class="col col-6">
                        <a href="<c:url value='/downloadFull/${listValue}'/>">
                            <button type="button" class="btn btn-primary" title="Download">
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                        </a>
                    </span>

                        <span class="col col-6">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-${listValue.substring(0,listValue.lastIndexOf("."))}" title="Delete">
                            <i class="fa fa-trash" aria-hidden="true"></i>
                        </button>
                    </span>
                    </div>
                </li>


                <!-- Modal -->
                <div class="modal fade" id="modal-${listValue.substring(0,listValue.lastIndexOf("."))}" tabindex="-1" role="dialog" aria-labelledby="Modal" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Confirm Deletion</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                Are you sure you want to delete: <b>${listValue}</b>
                            </div>
                            <div class="modal-footer">
                                <a href="#" class="btn btn-secondary" data-dismiss="modal" title="No">
                                    <i class="fa fa-times" aria-hidden="true"></i>
                                </a>
                                <a href="<c:url value='/deleteFull-${listValue}'/>" class="btn btn-primary" title="Yes">
                                    <i class="fa fa-check" aria-hidden="true"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

            </c:forEach>
        </ul>
    </div>

    <div class="row">
        <div class="modal fade" id="modalFullAll" tabindex="-1" role="dialog" aria-labelledby="Modal" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Confirm Deletion</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to delete all full records?
                    </div>
                    <div class="modal-footer">
                        <a href="#" class="btn btn-secondary" data-dismiss="modal" title="No">
                            <i class="fa fa-times" aria-hidden="true"></i>
                        </a>
                       <a href="<c:url value='/deleteFullAll'/>" class="btn btn-primary" title="Yes">
                            <i class="fa fa-trash" aria-hidden="true"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>

    </div>
    </div>

    <div class="row">
        <!-- Include Footer Template-->
        <jsp:include page="../templates/footer.jsp"/>
    </div>
</div>
</body>


</html>
