<%--
  Created by IntelliJ IDEA.
  User: Derek
  Date: 13/11/2017
  Time: 18:55
  Template Jsp Page
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
      <div class="col col-lg-12">
      <h3>${message}</h3>


        <form method="POST" action="${pageContext.request.contextPath}/uploadMultipleFile" enctype="multipart/form-data">
          File1 to upload: <input type="file" name="file"><br />
          File2 to upload: <input type="file" name="file"><br />
          File3 to upload: <input type="file" name="file"><br />
          <input type="submit" value="Upload"> Press here to upload the file!
        </form>
        <form action="${pageContext.request.contextPath}/convert">
          <button type="submit" class="btn btn-primary" title="convert"><i class="fa fa-refresh" aria-hidden="true"></i>Convert</button>
        </form>


        <p>This application will convert files form .dat format to .csv format</p>
      <br>
      <h5>Input Example</h5>
      <code>
        OFC002  SPEC       EFF DATE    000000,999999 <br>
        SEL        CPL         102<br>
        EVAL       E MODEL     6AB69,6AC69,6AD69,6AE69,6AG69<br>
        SOL        ERROR       ORDER HOLD ORDER FLOW<br>
      </code>

      <h5>Output Example</h5>
      <code>
        OFC002,000000,999999,102,6AB69 6AC69 6AD69 6AE69 6AG69,,,ORDER HOLD ORDER FLOW,
      </code>
      </div>
    </div>

    <div class="row">
      <!-- Include Footer Template-->
      <jsp:include page="../templates/footer.jsp"/>
    </div>
  </div>
  </body>


</html>
