<%@ include file="/taglibs.jsp" %>

<div>
<stripes:link href="/asset/classes">Classes</stripes:link>
|
<stripes:link href="/asset/list?uri=asydeo:ConfigurableItem">List Items</stripes:link>
|
<stripes:link href="/admin/dump">View Model</stripes:link>
|
<stripes:link href="/admin/delete">Delete Model</stripes:link>

<c:if test="${!empty actionBean.context.user}"> 
|
Signed in as ${actionBean.context.user.username}
|
<stripes:link href="/auth/logout">Sign out</stripes:link>
</c:if>
</div>
