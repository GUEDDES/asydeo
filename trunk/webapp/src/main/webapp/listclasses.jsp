<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="header">
  <c:import url="/menu.jsp" />

</stripes:layout-component>

<stripes:layout-component name="content">

<c:forEach var="v" items="${actionBean.list}">
<stripes:link beanclass="com.asydeo.action.ListAction">
<stripes:param name="uri" value="${v.URI}"/>
${v.label}</stripes:link><br/>
</c:forEach>

</stripes:layout-component>
</stripes:layout-render>


