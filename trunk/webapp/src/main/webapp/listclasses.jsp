<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="header">
  <c:import url="/menu.jsp" />
</stripes:layout-component>

<stripes:layout-component name="content">

<ul id="icons">
<c:forEach var="v" items="${actionBean.list}">
<li class="ui-state-default ui-corner-all" >
<stripes:link beanclass="com.asydeo.action.ListAction">
<img src="../img/${v.image}" height="75"/><br/>
<stripes:param name="uri" value="${v.URI}"/>
${v.label}</stripes:link>
</li>
</c:forEach>
</ul>
</stripes:layout-component>
</stripes:layout-render>


