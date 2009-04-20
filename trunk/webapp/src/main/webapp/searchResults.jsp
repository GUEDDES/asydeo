<%@ include file="/taglibs.jsp" %>

<script>
    $(document).ready(function() {
        $('table.striped tbody tr:not([th]):odd').addClass('odd')
        $('table.striped tbody tr:not([th]):even').addClass('even')
    })
</script> 

<div style="padding-top:20px; text-align:left;">
<c:choose>
  <c:when test="${empty actionBean.queryResult}">
    <c:if test="${not empty actionBean.q}">
No results found for <b>${actionBean.q}</b>
    </c:if>
  </c:when>
  <c:otherwise>
<span style="text-align:left;"><b>${actionBean.numQueryResults}</b> results found in <b>${actionBean.elapsedQueryTime}</b> seconds</span>
<table border=1 class="striped">
<tbody>
    <c:forEach var="v" items="${actionBean.queryResult}">
<tr>
  <td class="label">
    <stripes:link beanclass="com.asydeo.action.EditAction">
      <stripes:param name="uri" value="${v.URI}"/>
      ${v.label}
    </stripes:link>
    <c:if test="${not empty v.organization}">
    <div id="searchResultDescription"><font>${v.organization}</font></div>
    </c:if>
    <c:if test="${not empty v.description}">
    <div id="searchResultDescription"><font>${v.description}</font></div>
    </c:if>
  </td>
  <td class="class"><div id="tableData">${v.classLabel}</div>
  </td>
</tr>
    </c:forEach>
</tbody>
</table>
<!--</fieldset>-->
  </c:otherwise>
</c:choose>
</div>