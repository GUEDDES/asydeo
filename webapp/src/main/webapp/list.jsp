<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="header">
  <c:import url="/menu.jsp" />

</stripes:layout-component>

<stripes:layout-component name="content">

<div>
<stripes:link beanclass="com.asydeo.action.NewAction">
<stripes:param name="classUri" value="${actionBean.uri}"/>
Create a new ${actionBean.ontView.label}</stripes:link>
</div>


<ul>
<c:forEach var="v" items="${actionBean.list}" end="${actionBean.offset + 19}" begin="${actionBean.offset}">
<li class="listing"><span class="label">${v.label}</span>
<div style="display:inline">
<stripes:link class="button" beanclass="com.asydeo.action.EditAction">
  <stripes:param name="uri" value="${v.URI}"/>
  <stripes:param name="classUri" value="${actionBean.uri}"/>EDIT</stripes:link>
|
<stripes:link class="button delete" event="delete" beanclass="com.asydeo.action.ListAction">
  <stripes:param name="deleteUri" value="${v.URI}"/>
  <stripes:param name="uri" value="${actionBean.uri}"/>DELETE</stripes:link>
</div>
</li>
</c:forEach>
</ul>

<div class="paginator">
<c:forEach var="v" varStatus="status" items="${actionBean.list}" step="20">
<stripes:link beanclass="com.asydeo.action.ListAction">
<stripes:param name="uri" value="${actionBean.uri}"/>
<stripes:param name="offset" value="${status.count * 20 - 20}"/>
${status.count}
</stripes:link>
</c:forEach>
</div>


</stripes:layout-component>
</stripes:layout-render>


