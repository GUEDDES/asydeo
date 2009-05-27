<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="htmlHead">
  <c:import url="/menu.jsp" />

</stripes:layout-component>

<stripes:layout-component name="content">


Add ${actionBean.verb.label} to
${actionBean.subject.label}

<stripes:form beanclass="com.asydeo.action.AddPropertyAction">
<stripes:hidden name="bean.s" />
<stripes:hidden name="bean.v" />
<stripes:hidden name="bean.classUri"/>

<ul>
<c:forEach var="v" items="${actionBean.candidates}" end="${actionBean.offset + 19}" begin="${actionBean.offset}">
<li>
<stripes:checkbox name="bean.o" value="${v.URI}"/>
${v.label}
</li>
</c:forEach>
</ul>
<stripes:submit class="ui-button ui-state-default ui-corner-all" value="Add" name="add"/>
<stripes:submit class="ui-button ui-state-default ui-corner-all" value="Cancel" name="cancel"/>
</stripes:form>

<c:forEach var="v" varStatus="status" items="${actionBean.candidates}" step="20">
<stripes:link beanclass="com.asydeo.action.AddPropertyAction">
<stripes:param name="bean.s" value="${actionBean.bean.s}"/>
<stripes:param name="bean.v" value="${actionBean.bean.v}"/>
<stripes:param name="bean.classUri" value="${actionBean.bean.classUri}"/>
<stripes:param name="offset" value="${status.count * 20 - 20}"/>
${status.count}
</stripes:link>
</c:forEach>
</stripes:layout-component>
</stripes:layout-render>


