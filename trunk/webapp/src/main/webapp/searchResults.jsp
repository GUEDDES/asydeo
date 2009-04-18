<%@ include file="/taglibs.jsp" %>

<script>
    $(document).ready(function() {
        $('table.striped tbody tr:not([th]):odd').addClass('odd')
        $('table.striped tbody tr:not([th]):even').addClass('even')
    })
</script> 

<br/><br/>
<c:choose>
  <c:when test="${empty actionBean.queryResult}">
    <c:if test="${not empty actionBean.q}">
<font>No results found for <b>${actionBean.q}</b></font>
    </c:if>
  </c:when>
  <c:otherwise>
<font>${actionBean.numQueryResults} results found in ${actionBean.elapsedQueryTime} seconds</font>
<table border=1 class="striped">
<thead>
<tr class="tableTitle">
    <td><div id="tableTitle">Name</div></td>
    <td><div id="tableTitle">Class</div></td>
</tr>
</thead>
<tbody>
    <c:forEach var="v" items="${actionBean.queryResult}">
<tr>
  <td class="label">
    <div id="tableData">
    <font>
    <stripes:link beanclass="com.asydeo.action.EditAction">
      <stripes:param name="uri" value="${v.URI}"/>
      ${v.label}
    </stripes:link>
    </font>
    </div>
  </td>
  <td class="class"><div id="tableData"><font>${v.classLabel}</font></div>
  </td>
</tr>
    </c:forEach>
</tbody>
</table>
  </c:otherwise>
</c:choose>
