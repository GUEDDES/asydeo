<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="htmlHead">
  <c:import url="/menu.jsp" />
</stripes:layout-component>

<stripes:layout-component name="content">

An unexpected error was encountered!
<br><br>
<li>Session ID: <%=request.getAttribute("sessionId")%>
<li>Error: <%=request.getAttribute("exception")%>

</stripes:layout-component>
</stripes:layout-render>