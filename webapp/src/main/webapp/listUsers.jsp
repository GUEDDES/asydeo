<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="html-head">
</stripes:layout-component>

<stripes:layout-component name="content">

<c:forEach var="user" items="${actionBean.users}">
<li>
<stripes:link event="edit" class="button" beanclass="com.asydeo.action.UserAction">
<stripes:param name="id" value="${user.username}"/>${user.username},</stripes:link>
${user.email}
<stripes:link event="delete" class="button" beanclass="com.asydeo.action.UserAction">
<stripes:param name="id" value="${user.username}"/>
[ - ]
</stripes:link>
</li>
</c:forEach>

<stripes:link event="new" beanclass="com.asydeo.action.UserAction">New User</stripes:link>
</stripes:layout-component>
</stripes:layout-render>
