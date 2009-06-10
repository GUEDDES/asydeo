<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="header">
  <c:import url="/menu.jsp" />
</stripes:layout-component>

<stripes:layout-component name="content">

<div id="icons">
<c:forEach var="v" items="${actionBean.list}">
<stripes:link style="float:left;text-decoration:none;text-align:bottom" beanclass="com.asydeo.action.ListAction" class="ui-state-default ui-corner-all">
<img style="border:none" src="../img/${v.image}" height="75"/><br/>
<stripes:param name="uri" value="${v.URI}"/>
${v.label}</stripes:link>
</c:forEach>
</div>

</stripes:layout-component>
</stripes:layout-render>


