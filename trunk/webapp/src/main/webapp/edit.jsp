
<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="html-head">
<div></div>
</stripes:layout-component>

<stripes:layout-component name="content">


<stripes:form name="form1" action="/asset/edit">

<c:forEach var="v" items="${actionBean.views}">
${v.content}<br/>
</c:forEach>


<stripes:hidden name="uri" value="${actionBean.uri}"/>
<stripes:hidden name="classUri" value="${actionBean.classUri}"/>
<stripes:hidden name="update" value=""/>

<br style="clear:both"/>
<a class="button" href="javascript:document.form1.submit()">SAVE</a>
<stripes:link  class="button" beanclass="com.asydeo.action.ListAction">
  <stripes:param name="uri" value="${actionBean.classUri}"/>CANCEL</stripes:link>
</stripes:form>


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
    <stripes:link class="button" event="unrelate" beanclass="com.asydeo.action.EditAction" >
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

