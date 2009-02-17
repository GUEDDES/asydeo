
<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="html-head">
<div></div>
</stripes:layout-component>

<stripes:layout-component name="content">


<stripes:form name="form1" action="/asset/edit">

<c:forEach var="v" items="${actionBean.views}">
${v.content}<br/>
</c:forEach>

<stripes:hidden name="uri" value="${actionBean.uri}"/>
<stripes:hidden name="update" value=""/>

<a href="javascript:document.form1.submit()">Save</a>
<stripes:link beanclass="com.asydeo.action.ListAction">
  <stripes:param name="uri" value="${actionBean.classUri}"/>Cancel</stripes:link>
</stripes:form>


</stripes:layout-component>
</stripes:layout-render>

