
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
</style>

</stripes:layout-component>


<stripes:layout-component name="header">
 <c:import url="/menu.jsp" />
</stripes:layout-component>

<stripes:layout-component name="content">

<stripes:form name="form1" action="/asset/edit">
<stripes:hidden name="uri" value="${actionBean.uri}"/>
<stripes:hidden name="classUri" value="${actionBean.classUri}"/>
<fieldset class="ui-dialog-content ui-widget-content">
    <legend class="ui-widget-header ui-corner-all">General Information</legend>
    
    
<c:forEach var="v" items="${actionBean.views}">
${v.content}<br/>
</c:forEach>


</fieldset>


<fieldset class="ui-widget-header"> 
<stripes:submit name="update" class="ui-button ui-state-default ui-corner-all">SAVE</stripes:submit>
<stripes:submit name="cancel" class="ui-button ui-state-default ui-corner-all">CANCEL</stripes:submit>
<div style="float:right">
<a id="collect_link" class="ui-state-default ui-corner-all" href="#">
<span class="ui-icon ui-icon-plusthick"></span>Collect this item
</a>
</div>

</fieldset>

</stripes:form>
<c:forEach var="v" items="${actionBean.functionalProperties}">
${v.label} 
<c:forEach var="item" items="${v.items}">
    <span>${item.label}</span>
</c:forEach>

<stripes:link class="button" beanclass="com.asydeo.action.SetPropertyAction">
<stripes:param name="bean.s" value="${actionBean.uri}"/>
<stripes:param name="bean.v" value="${v.URI}"/>
<stripes:param name="bean.classUri" value="${actionBean.classUri}"/>
CHOOSE</stripes:link>
<br/>
</c:forEach>

<c:forEach var="v" items="${actionBean.objectProperties}">
${v.label}
<stripes:link class="button" beanclass="com.asydeo.action.AddPropertyAction">
<stripes:param name="bean.s" value="${actionBean.uri}"/>
<stripes:param name="bean.v" value="${v.URI}"/>
<stripes:param name="bean.classUri" value="${actionBean.classUri}"/>
ADD</stripes:link>
<br/>
  <ul>
  <c:forEach var="item" items="${v.items}">
    <li>${item.label}
    <stripes:link class="button confirm" event="unrelate" beanclass="com.asydeo.action.EditAction" >
    <stripes:param name="uri" value="${actionBean.uri}"/>
    <stripes:param name="classUri" value="${actionBean.classUri}"/>
    <stripes:param name="bean.s" value="${actionBean.uri}"/>
    <stripes:param name="bean.v" value="${v.URI}"/>
    <stripes:param name="bean.o" value="${item.URI}"/>
    UNRELATED</stripes:link>
    </li>
  </c:forEach>  
  </ul>
</c:forEach>



</stripes:layout-component>
</stripes:layout-render>

