<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="htmlHead">
<script type="text/javascript" src="http://malsup.com/jquery/form/jquery.form.js"></script>
<script type="text/javascript"><!--


$(document).ready(function() { 
 
});

// --> 
</script> 

</stripes:layout-component>

<stripes:layout-component name="header">
  <c:import url="/menu.jsp" />
</stripes:layout-component>

<stripes:layout-component name="content">

<stripes:form name="form1" beanclass="com.asydeo.action.ModelAction">
<stripes:hidden name="chooseModel" value=""/>
<fieldset class="ui-dialog-content ui-widget-content">
<legend class="ui-widget-header ui-corner-all">Selected Model</legend>
<label>Current</label>
<stripes:radio onclick="document.form1.submit()" name="modelName" value="com.asydeo.currentmodel"/><br/>
<label>Planned</label>
<stripes:radio onclick="document.form1.submit()" name="modelName" value="com.asydeo.plannedmodel"/><br/>
<label>Discovered</label>
<stripes:radio onclick="document.form1.submit()" name="modelName" value="com.asydeo.discoveredmodel"/><br/>
<label>Inferred</label>
<stripes:radio onclick="document.form1.submit()" name="modelName" value="com.asydeo.infmodel"/>
</fieldset>
</stripes:form>

<stripes:form id="uploadForm" beanclass="com.asydeo.action.UploadAction" method="POST" enctype="multipart/form-data"> 
<fieldset class="ui-dialog-content ui-widget-content">
<legend class="ui-widget-header ui-corner-all">Upload data file (rdf, n3, ttl)</legend>
   <input type="hidden" name="MAX_FILE_SIZE" value="100000" /> 
   <label>File:</label> 
   <input type="file" name="newAttachment" /><br/>
   <input type="submit" value="Submit" class="ui-button ui-corner-all"/>
</fieldset>
</stripes:form>



</stripes:layout-component>
</stripes:layout-render>