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
    <jsp:useBean id="indexPageService" scope="request" class="com.gm.spring.services.IndexService"/>

  </head>

  <body>
  <div class="container">

    <div class="row">
      <!-- Include Navigation Template-->
      <jsp:include page="../templates/navigation.jsp"/>
    </div>

    <div class="row">
      <h3>${message}</h3>
    </div>

    <div class="row">
      <!-- Include Footer Template-->
      <jsp:include page="../templates/footer.jsp"/>
    </div>
  </div>
  </body>


</html>
