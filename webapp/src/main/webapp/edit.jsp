
<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="htmlHead">

<style>
#collect_link {
  padding:0.4em 1em 0.4em 20px;
  position:relative;
  text-decoration:none;
}

#collect_link span.ui-icon {
  left:0.2em;
  margin:-8px 5px 0 0;
  position:absolute;
  top:50%;
}

a.confirm {
  padding:0.4em 1em 0.4em 10px;
  position:relative;
  text-decoration:none;

}

ul { margin-top:0}

li {
	margin-left: 0;
}

li a span.ui-icon {
  margin:-8px 5px 0 0;
}

li span.ui-icon {
  margin:-8px 5px 0 -20px;
  position:absolute;
  top:50%;
}

#accordion {
	width: 260px;
	float: right;
}


</style>

</stripes:layout-component>


<stripes:layout-component name="header">
 <c:import url="/menu.jsp" />
</stripes:layout-component>

<stripes:layout-component name="content">

<script type="text/javascript">
	$(function() {
		$("#accordion").accordion({
		autoHeight: false
		});
	});
</script>

<div style="float:left">
<stripes:form name="form1" action="/asset/edit">
<stripes:hidden name="uri" value="${actionBean.uri}"/>
<stripes:hidden name="classUri" value="${actionBean.classUri}"/>
<fieldset class="ui-dialog-content ui-widget-content ui-corner-all">
<legend class="ui-widget-header ui-state-active ui-corner-all">General Information</legend>
<c:forEach var="v" items="${actionBean.views}">
${v.content}<br/>
</c:forEach>
</fieldset>

<fieldset class="ui-widget-header ui-corner-all" style="margin-top:3px"> 
<div style="float:left">
<stripes:submit name="update" class="ui-button ui-state-default ui-corner-all">SAVE</stripes:submit>
<stripes:submit name="cancel" class="ui-button ui-state-default ui-corner-all">CANCEL</stripes:submit>
</div>
<div style="float:right">
<a id="collect_link" class="ui-state-default ui-corner-all" href="#">
<span class="ui-icon ui-icon-plusthick"></span>Collect this item</a>
</div>
</fieldset>
</stripes:form>
</div>



<div id="accordion" style="position:relative">
    
	<h3><a href="#">Relationships</a></h3>
	<div>
<c:forEach var="v" items="${actionBean.objectProperties}">
${v.label} 
<stripes:link class="button" beanclass="com.asydeo.action.AddPropertyAction">
<stripes:param name="bean.s" value="${actionBean.uri}"/>
<stripes:param name="bean.v" value="${v.URI}"/>
<stripes:param name="bean.classUri" value="${actionBean.classUri}"/>
[+]</stripes:link>

  <ul style="list-style: none">
  <c:forEach var="item" items="${v.items}">
    <li style="position:relative; margin-left:-20px">
    <span class="ui-icon ui-icon-arrowthick-1-e">asdfasdf</span>
    <stripes:link beanclass="com.asydeo.action.EditAction">
    <stripes:param name="uri" value="${item.URI}"/>
    <stripes:param name="classUri" value="${item.type}"/>
    ${item.label}
    </stripes:link>
   
    <stripes:link class="confirm ui-state-default ui-corner-all" event="unrelate" beanclass="com.asydeo.action.EditAction" >
    <stripes:param name="bean.s" value="${actionBean.uri}"/>
    <stripes:param name="bean.v" value="${v.URI}"/>
    <stripes:param name="bean.o" value="${item.URI}"/>
    <span class="ui-icon ui-icon-trash"></span></stripes:link>
    </li>
  </c:forEach>  
  </ul>
</c:forEach>
	</div>
	
	<h3><a href="#">References</a></h3>
	<div>
    <c:forEach var="item" items="${actionBean.references}">
    <stripes:link beanclass="com.asydeo.action.EditAction">
    <stripes:param name="uri" value="${item.URI}"/>
    <stripes:param name="classUri" value="${item.type}"/>
    ${item.label}
    </stripes:link>
    </c:forEach>
	</div>	
	
</div>










</stripes:layout-component>
</stripes:layout-render>

