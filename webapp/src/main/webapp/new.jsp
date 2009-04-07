<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="header">
  <c:import url="/menu.jsp" />

</stripes:layout-component>

<stripes:layout-component name="content">
<stripes:form name="form1" beanclass="com.asydeo.action.NewAction">
<stripes:hidden name="classUri" />

<fieldset>
    <legend>General Information</legend>
		<c:forEach var="v" items="${actionBean.views}">${v.content}<br/>
</c:forEach>
</fieldset>


<br style="clear:both"/>

<stripes:submit name="create" class="ui-button ui-state-default ui-corner-all">SAVE</stripes:submit>
<stripes:submit name="cancel" class="ui-button ui-state-default ui-corner-all">CANCEL</stripes:submit>

</stripes:form>


</stripes:layout-component>
</stripes:layout-render>

