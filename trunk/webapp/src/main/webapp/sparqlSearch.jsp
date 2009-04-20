<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="htmlHead">
  <c:import url="/menu.jsp" />
</stripes:layout-component>

<stripes:layout-component name="content">

<stripes:form beanclass="com.asydeo.action.SparqlSearchAction" method="get">
  <fieldset class="ui-dialog-content ui-widget-content ui-corner-all">
  <c:import url="/searchMenu.jsp" />
  <stripes:textarea cols="50" rows="5" name="q"/><br/>
  <stripes:submit name="sparqlSearch" value="Search" class="ui-button ui-state-default ui-corner-all"/>
  </fieldset
</stripes:form>

<c:import url="/searchResults.jsp" />

</stripes:layout-component>
</stripes:layout-render>
