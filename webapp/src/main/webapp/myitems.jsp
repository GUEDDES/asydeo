<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="header">
  <c:import url="/menu.jsp" />

</stripes:layout-component>

<stripes:layout-component name="content">


<c:if test="${fn:length(actionBean.list) == 0}">
<div class="ui-widget"> 
	<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;"> 
	<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span> 
	<strong>Your Collection is Empty</strong>
	Select items from the query page or class browser and select "Collect Item".</p> 
	</div> 
</div> 
</c:if>
  
<ul>
<c:forEach var="v" items="${actionBean.list}" >
<li class="listing"><span class="label">${v.label}</span>
<div style="display:inline">
<stripes:link class="button" beanclass="com.asydeo.action.EditAction">
  <stripes:param name="uri" value="${v.URI}"/>
 EDIT</stripes:link>
|
<stripes:link class="button delete" event="delete" beanclass="com.asydeo.action.ListMyItemsAction">
  <stripes:param name="deleteUri" value="${v.URI}"/>
DELETE</stripes:link>
</div>
</li>
</c:forEach>
</ul>

</stripes:layout-component>
</stripes:layout-render>


