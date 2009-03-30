<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="htmlHead">
</stripes:layout-component>

<stripes:layout-component name="content">



<form method="POST" action="j_security_check">
<label>Username</label><input type="text" class="normal" name="j_username"><br/>
<label>Password</label><input type="password" class="normal" name="j_password">
<br/>
<input type="submit" value="Log In" name="login"/>
</form>

</stripes:layout-component>
</stripes:layout-render>
