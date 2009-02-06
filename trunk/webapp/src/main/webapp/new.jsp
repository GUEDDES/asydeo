<%@ page isELIgnored ="false" %> 
<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="html-head">
<div> ${asdfasfaf}</div>
</stripes:layout-component>

<stripes:layout-component name="content">
<c:forEach items="${actionBean.classes}" var="cls" varStatus="loop">
<li>${cls.label} ( ${cls.URI} ) </li>
</c:forEach>
</stripes:layout-component>





</stripes:layout-render>

