<%@ include file="/taglibs.jsp" %>

<div style="float:left">
<c:choose>
<c:when test="${!empty actionBean.context.user}">
<stripes:link beanclass="com.asydeo.action.HomeAction">Home</stripes:link>
|
<stripes:link href="/asset/classes">Classes</stripes:link>
|
<stripes:link href="/asset/list?uri=asydeo:ConfigurableItem">List Items</stripes:link>
|
<stripes:link href="/admin/dump">View Model</stripes:link>
|
<stripes:link href="/admin/delete">Delete Model</stripes:link>
</c:when>
</c:choose>
</div>


<div style="text-align:right">
<c:choose>
<c:when test="${!empty actionBean.context.user}"> 
Signed in as ${actionBean.context.user.username}
|
<stripes:link beanclass="com.asydeo.action.ModelAction">Models</stripes:link>
|
<stripes:link href="/auth/logout">Sign out</stripes:link>
</c:when>
<c:otherwise>
<stripes:link href="/auth/login">Sign in</stripes:link>
</c:otherwise>
</c:choose>
</div>
