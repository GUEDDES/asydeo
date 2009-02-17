<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="html-head">
</stripes:layout-component>

<stripes:layout-component name="content">
<stripes:form name="form1" action="/asset/new">

<c:forEach var="v" items="${actionBean.views}">
${v.content}<br/>
</c:forEach>

<stripes:hidden name="classUri" />

<stripes:hidden name="create" value=""/>
<a href="javascript:document.form1.submit()">Save</a>
<stripes:link href="/asset/list">
  <stripes:param name="uri" value="${actionBean.classUri}"/>Cancel</stripes:link>
</stripes:form>



</stripes:layout-component>
</stripes:layout-render>

