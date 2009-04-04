u<%@ include file="../taglibs.jsp" %>
<stripes:layout-definition>
<html>
<head> 
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.3.2.min.js"></script>  
  <link type="text/css" href="${pageContext.request.contextPath}/css/cupertino/jquery-ui-1.7.1.custom.css" rel="Stylesheet" />	
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.7.1.custom.min.js"></script>


  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css" type="text/css"> 
  <stripes:layout-component name="htmlHead"/> 
  <script>  
  $(document).ready(function() {
    $("a.confirm").click(function() {
      return confirm("Are you SURE you want to remove this relationship?");
    });
    
    $("a.delete").click(function() {
      return confirm("Are you SURE you want to remove this entity?");
    });

  
    $("#collect_link").click( function() {
       $.ajax({
			type: "POST",
			url: "collect",
			data: {uri:document.form1.uri.value},
			success: function(msg){
			  alert( "Item added to your collection.");
   		    },
   		    error: function(XMLHttpRequest, textStatus, errorThrown) {
			  alert('Item could not be saved');
   		    }
        });      
    });


	$('.ui-state-default').hover(
			function(){ 
				$(this).addClass("ui-state-hover"); 
			},
			function(){ 
				$(this).removeClass("ui-state-hover"); 
			}
		).mousedown(function(){
			$(this).addClass("ui-state-active"); 
		})
		.mouseup(function(){
				$(this).removeClass("ui-state-active");
		})
		.mouseout(function(){
				$(this).removeClass("ui-state-active");
		});
	
    
  });
  </script>
</head>

<body>
<div id="header">
<stripes:layout-component name="header"/> 
</div>
<div style="clear:both"></div>

<div id="content">  
  <stripes:layout-component name="content"/>
</div>
</body>
</html>
</stripes:layout-definition>
