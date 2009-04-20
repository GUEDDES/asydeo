<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="htmlHead">
  <c:import url="/menu.jsp" />
</stripes:layout-component>

<stripes:layout-component name="content">

<stripes:form beanclass="com.asydeo.action.TextSearchAction" method="get">
  <fieldset class="ui-dialog-content ui-widget-content ui-corner-all">
  <c:import url="/searchMenu.jsp" />
  <stripes:text name="q" class="text ui-widget-content ui-corner-all"/><br/>
  <stripes:submit name="textSearch" value="Search" class="ui-button ui-state-default ui-corner-all"/>
  </fieldset>
</stripes:form>

<c:import url="/searchResults.jsp" />

</stripes:layout-component>
</stripes:layout-render>
