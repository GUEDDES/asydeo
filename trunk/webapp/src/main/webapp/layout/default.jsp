<%@ include file="../taglibs.jsp" %>
<stripes:layout-definition>
<html>
<head> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css" type="text/css"> 
  <stripes:layout-component name="html-head"/>     
</head>

<body>
  <c:import url="/menu.jsp" />
  <stripes:layout-component name="header"/> 
  <stripes:layout-component name="content"/>

</body>
</html>
</stripes:layout-definition>
