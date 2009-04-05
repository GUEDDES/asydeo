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
<c:forEach var="v" items="${actionBean.candidates}">
<li>
<stripes:checkbox name="bean.o" value="${v.URI}"/>
${v.label}
</li>
</c:forEach>
</ul>

<stripes:submit class="button" value="Add" name="add"/>
<stripes:submit class="button" value="Cancel" name="cancel"/>
</stripes:form>

</stripes:layout-component>
</stripes:layout-render>


