<%@ include file="/taglibs.jsp" %>

<br/><br/>
<c:choose>
  <c:when test="${empty actionBean.queryResult}">
<font>No results found</font>
  </c:when>
  <c:otherwise>
<table border=1>
    <c:forEach var="v" items="${actionBean.queryResult}">
<tr>
  <td>
    <stripes:link class="button" beanclass="com.asydeo.action.EditAction">
      <stripes:param name="uri" value="${v.URI}"/>
      <span class="label">${v.label}</span>
    </stripes:link>
  </td>
  <td>
    ${v.classLabel}
  </td>
</tr>
    </c:forEach>
</table>
  </c:otherwise>
</c:choose>
