<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="html-head">
  <c:import url="/menu.jsp" />
</stripes:layout-component>

<stripes:layout-component name="content">
<stripes:link href="/auth/login">Sign in</stripes:link>
home

</stripes:layout-component>
</stripes:layout-render>