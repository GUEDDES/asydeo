<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="htmlHead">
  <c:import url="/menu.jsp" />

</stripes:layout-component>

<stripes:layout-component name="content">
<stripes:form name="form1" action="/auth/user">

<label>Username</label><stripes:text class="normal" name="username"/><br/>
<label>Password</label><stripes:text class="normal" name="password"/><br/>
<label>Verify Password</label><stripes:text class="normal" name="passwordCheck"/><br/>



<br style="clear:both"/>

<stripes:submit name="save" value="SAVE"/>
<stripes:submit name="cancel" value="CANCEL"/>
</stripes:form>



</stripes:layout-component>
</stripes:layout-render>
