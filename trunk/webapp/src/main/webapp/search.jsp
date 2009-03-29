<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="html-head">

</stripes:layout-component>

<stripes:layout-component name="content">
<stripes:form action="/asset/search">

<stripes:textarea cols="50" rows="5" name="sparqlQuery"/>
<br/>
<stripes:submit name="submitQuery" value="Query"/>
<br/><br/>

<c:forEach var="v" items="${actionBean.queryResult}">
<li class="listitem"> 
  <stripes:link class="button" beanclass="com.asydeo.action.EditAction">
  <stripes:param name="uri" value="${v.URI}"/>
  <stripes:param name="classUri" value="asydeo:ConfigurableItem"/>
    <span class="label">${v.label}</span>
  </stripes:link>
</li>
</c:forEach>

</stripes:layout-component>
</stripes:layout-render>
