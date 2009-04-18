<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="htmlHead">
  <c:import url="/menu.jsp" />
</stripes:layout-component>

<stripes:layout-component name="content">

<c:import url="/searchMenu.jsp" />

<stripes:form beanclass="com.asydeo.action.TextSearchAction" method="get">
  <stripes:text name="q"/><br/>
  <stripes:submit name="textSearch" value="Search" class="ui-button ui-state-default ui-corner-all"/>
</stripes:form>

<c:import url="/searchResults.jsp" />

</stripes:layout-component>
</stripes:layout-render>
