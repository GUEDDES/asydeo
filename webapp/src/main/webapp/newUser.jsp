<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="header">
  <c:import url="/menu.jsp" />

</stripes:layout-component>

<stripes:layout-component name="content">
<stripes:form name="form1" beanclass="com.asydeo.action.UserAction">

<label>Username</label><stripes:text class="normal" name="user.username"/><br/>
<label>Email</label><stripes:text class="normal" name="user.email"/><br/>
<label>Password</label><stripes:password  class="normal" repopulate="true" name="user.password"/><br/>
<label>Verify Password</label><stripes:password class="normal" repopulate="true" name="user.passwordCheck"/><br/>


<label>Roles</label>
<stripes:checkbox name="roles" value="admin"/>admin | 
<stripes:checkbox name="roles" value="user"/>user |
<stripes:checkbox name="roles" value="guest"/>guest 
<br style="clear:both"/>
<stripes:submit name="save" value="SAVE"/>
<stripes:submit name="cancel" value="CANCEL"/>
</stripes:form>



</stripes:layout-component>
</stripes:layout-render>
