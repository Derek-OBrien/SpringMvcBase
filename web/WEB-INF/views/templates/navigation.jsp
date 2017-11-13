<%--
  Created by IntelliJ IDEA.
  User: Derek
  Date: 13/11/2017
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>

<div class="navigation">
    <ul class="nav nav-tabs">
        <li class="nav-tab"><a class="nav-link" href="${pageContext.request.contextPath}/">Tab 1</a></li>
        <li class="nav-tab"><a class="nav-link" href="${pageContext.request.contextPath}#">Tab 2</a> </li>
        <li class="nav-tab"><a class="nav-link" href="${pageContext.request.contextPath}#">Tab 3</a></li>
        <li class="nav-tab"><a class="nav-link" href="${pageContext.request.contextPath}#">Tab 4</a> </li>
        <li class="nav-tab"><a class="nav-link" href="${pageContext.request.contextPath}#">Tab 5</a></li>
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
