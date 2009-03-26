u<%@ include file="../taglibs.jsp" %>
<stripes:layout-definition>
<html>
<head> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css" type="text/css"> 
  <stripes:layout-component name="html-head"/> 
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.3.2.min.js"></script>  
  <script> 
  
  $(document).ready(function() {
    $("a.confirm").click(function() {
      return confirm("Are you SURE you want to remove this relationship?");
    });
    
    $("a.delete").click(function() {
      return confirm("Are you SURE you want to remove this entity?");
    });
    
  });
  </script>
</head>

<body>
  <div>
  <stripes:layout-component name="header"/> 
  </div>
  <div style="clear:both"></div>
  
  <stripes:layout-component name="content"/>

</body>
</html>
</stripes:layout-definition>
