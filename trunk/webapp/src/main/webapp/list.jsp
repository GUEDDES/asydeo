<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="html-head">

</stripes:layout-component>

<stripes:layout-component name="content">

<div>
<stripes:link beanclass="com.asydeo.action.NewAction">
<stripes:param name="classUri" value="${actionBean.uri}"/>
Create a new ${actionBean.uri}</stripes:link>
</div>


<c:forEach var="v" items="${actionBean.list}">
<stripes:link beanclass="com.asydeo.action.EditAction">
  <stripes:param name="uri" value="${v.URI}"/>
  <stripes:param name="classUri" value="${actionBean.uri}"/>
${v.label}</stripes:link> 
<stripes:link event="delete" beanclass="com.asydeo.action.ListAction">
  <stripes:param name="deleteUri" value="${v.URI}"/>
  <stripes:param name="uri" value="${actionBean.uri}"/>
(-)
</stripes:link>
<br/>
</c:forEach>

</stripes:layout-component>
</stripes:layout-render>


