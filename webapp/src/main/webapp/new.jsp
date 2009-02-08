<%@ page isELIgnored ="false" %> 
<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="html-head">
<div> ${asdfasfaf}</div>
</stripes:layout-component>

<stripes:layout-component name="content">


<stripes:form action="/asset/new">
Create a new 
<stripes:select name="type">
<stripes:options-collection collection="${actionBean.classes}"
  label="label" value="URI"/>
</stripes:select>
</stripes:form>


</stripes:layout-component>





</stripes:layout-render>

