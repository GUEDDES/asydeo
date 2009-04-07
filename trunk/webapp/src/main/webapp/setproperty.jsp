<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="header">
  <c:import url="/menu.jsp" />

</stripes:layout-component>

<stripes:layout-component name="content">


Add ${actionBean.verb.label} to
${actionBean.subject.label}

<stripes:form beanclass="com.asydeo.action.SetPropertyAction">
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

<stripes:submit value="SAVE" name="set" class="ui-button ui-state-default ui-corner-all"/>
<stripes:submit value="CANCEL" name="cancel" class="ui-button ui-state-default ui-corner-all"/>
</stripes:form>

</stripes:layout-component>
</stripes:layout-render>


