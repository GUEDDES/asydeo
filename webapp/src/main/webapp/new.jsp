<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="htmlHead">
  <c:import url="/menu.jsp" />

</stripes:layout-component>

<stripes:layout-component name="content">
<stripes:form name="form1" action="/asset/new">

<c:forEach var="v" items="${actionBean.views}">
${v.content}<br/>
</c:forEach>

<stripes:hidden name="classUri" />
<stripes:hidden name="create" value=""/>

<br style="clear:both"/>
<a  class="button" href="javascript:document.form1.submit()">SAVE</a>
|
<stripes:link  class="button" href="/asset/list">
  <stripes:param name="uri" value="${actionBean.classUri}"/>CANCEL</stripes:link>
</stripes:form>



</stripes:layout-component>
</stripes:layout-render>

