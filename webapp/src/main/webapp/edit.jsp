
<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="htmlHead">

</stripes:layout-component>


<stripes:layout-component name="header">
 <c:import url="/menu.jsp" />
</stripes:layout-component>

<stripes:layout-component name="content">

<stripes:form class="niceform" name="form1" action="/asset/edit">
<stripes:hidden name="uri" value="${actionBean.uri}"/>
<stripes:hidden name="classUri" value="${actionBean.classUri}"/>
<fieldset>
    <legend>General Information</legend>
    
    
<c:forEach var="v" items="${actionBean.views}">
${v.content}<br/>
</c:forEach>

</fieldset>


<fieldset class="action"> 
<stripes:submit name="update" class="button">SAVE</stripes:submit>
<stripes:submit name="cancel" class="button">CANCEL</stripes:submit>
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

