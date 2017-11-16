<%--
  Created by IntelliJ IDEA.
  User: Derek
  Date: 13/11/2017
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>

<div class="navigation">
    <ul class="nav nav-tabs">
        <li class="nav-tab"><a class="nav-link" href="${pageContext.request.contextPath}/">Home</a></li>
        <li class="nav-tab"><a class="nav-link" href="${pageContext.request.contextPath}/full">Full</a> </li>
        <li class="nav-tab"><a class="nav-link" href="${pageContext.request.contextPath}#">UploadFile</a></li>
        <li class="nav-tab"><a class="nav-link" href="${pageContext.request.contextPath}#">Spec</a> </li>
        <li class="nav-tab"><a class="nav-link" href="${pageContext.request.contextPath}#">Help</a></li>
    </ul>
</div>


<!--Small Script for ".active" class to show which navigation tab is active-->
<script type="text/javascript">
    $(function() {
        // this will get the full URL at the address bar
        var url = window.location.href;
        // passes on every "a" tag
        $(".navigation a").each(function() {
            // checks if its the same on the address bar
            if (url == (this.href)) {
                $(this).closest("li").addClass("active");
            }
        });
    });
</script>
