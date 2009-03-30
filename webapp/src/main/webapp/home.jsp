<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="htmlHead">
  <c:import url="/menu.jsp" />
</stripes:layout-component>

<stripes:layout-component name="content">

<stripes:form name="form1" beanclass="com.asydeo.action.ModelAction">
<fieldset><legend>Selected Model</legend>
<label>Current</label>
<stripes:radio onclick="document.form1.submit()" name="modelName" value="com.asydeo.currentmodel"/><br/>
<label>Planned</label>
<stripes:radio onclick="document.form1.submit()" name="modelName" value="com.asydeo.plannedmodel"/><br/>
<label>Discovered</label>
<stripes:radio onclick="document.form1.submit()" name="modelName" value="com.asydeo.discoveredmodel"/>
</fieldset>
</stripes:form>

</stripes:layout-component>
</stripes:layout-render>