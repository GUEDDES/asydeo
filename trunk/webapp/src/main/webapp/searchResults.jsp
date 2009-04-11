<%@ include file="/taglibs.jsp" %>

<br/>
<c:forEach var="v" items="${actionBean.queryResult}">
<li class="listitem"> 
  <stripes:link class="button" beanclass="com.asydeo.action.EditAction">
  <stripes:param name="uri" value="${v.URI}"/>
  <stripes:param name="classUri" value="asydeo:ConfigurableItem"/>
    <span class="label">${v.label}</span>
  </stripes:link>
</li>
</c:forEach>