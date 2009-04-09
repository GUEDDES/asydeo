<%@ include file="../taglibs.jsp" %><stripes:layout-definition><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 

<html>
<head> 
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.3.2.min.js"></script>  
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.7.1.custom.min.js"></script>
  <script type="text/javascript" src="http://malsup.com/jquery/form/jquery.form.js"></script>


  <link type="text/css" href="${pageContext.request.contextPath}/css/cupertino/jquery-ui-1.7.1.custom.css" rel="Stylesheet" />
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
  			    $('#ajaxContent').html('Item saved to your collection.');
  			    $("#dialog").dialog('open');
   		    },
   		    error: function(XMLHttpRequest, textStatus, errorThrown) {
              $('#ajaxContent').html('Item could not be saved');
              $("#ajaxConsole").show().fadeTo(1000, 1).fadeOut(3000);
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
	
    $('#uploadForm').ajaxForm({
        beforeSubmit: function(a,f,o) {
            $('#ajaxContent').html('Submitting...');
            $("#ajaxConsole").show();
        },
        success: function(data, code) {
            $('#ajaxContent').html('Data uploaded successfuly.');
            $("#ajaxConsole").fadeTo(2000, 1).fadeOut(2000);
        },
        error: function() { 
        	$('#ajaxContent').html('File upload failed.'); 
        	$("#ajaxConsole").fadeTo(2000, 1).fadeOut(2000);
        	
        } 
     });
     
     // menu stuff
     	$("#nicemenu img.arrow").click(function(){ 
								
		$("span.head_menu").removeClass('active');
		
		submenu = $(this).parent().parent().find("div.sub_menu");
		
		if(submenu.css('display')=="block"){
			$(this).parent().removeClass("active"); 	
			submenu.hide(); 		
			$(this).attr('src','../img/arrow_hover.png');									
		}else{
			$(this).parent().addClass("active"); 	
			submenu.fadeIn(); 		
			$(this).attr('src','../img/arrow_select.png');	
		}
		
		$("div.sub_menu:visible").not(submenu).hide();
		$("#nicemenu img.arrow").not(this).attr('src','../img/arrow.png');
						
	})
	.mouseover(function(){ $(this).attr('src','../img/arrow_hover.png'); })
	.mouseout(function(){ 
		if($(this).parent().parent().find("div.sub_menu").css('display')!="block"){
			$(this).attr('src','../img/arrow.png');
		}else{
			$(this).attr('src','../img/arrow_select.png');
		}
	});
 
	$("#nicemenu span.head_menu").mouseover(function(){ $(this).addClass('over')})
								 .mouseout(function(){ $(this).removeClass('over') });
	
	$("#nicemenu div.sub_menu").mouseover(function(){ $(this).fadeIn(); })
							   .blur(function(){ 
							   		$(this).hide();
									$("span.head_menu").removeClass('active');
								});		
								
	$(document).click(function(event){ 		
			var target = $(event.target);
			if (target.parents("#nicemenu").length == 0) {				
				$("#nicemenu span.head_menu").removeClass('active');
				$("#nicemenu div.sub_menu").hide();
				$("#nicemenu img.arrow").attr('src','../img/arrow.png');
			}
	});			   
     
	$("#dialog").dialog({
		  bgiframe: true,
		  modal: true,
		  autoOpen: false,
		  buttons: {
			Ok: function() {
				$(this).dialog('close');
			}
		  }
	});    
    
    
  //end ready function   
  });
  </script>
</head>

<body>

<div id="dialog" title="Download complete" class="ui-helper-hidden">
	<p>
		<span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
		<span id="dialogContent"></span>
	</p>
</div>

<div id="ajaxConsole" class="ui-helper-hidden" style="width:30%; position:absolute; z-index:1000; top:0; left:0">
<div class="ui-widget">
	<div class="ui-state-highlight ui-corner-all" style=" padding: 0 .7em;"> 
		<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span> 
			<span id="ajaxContent"></span>
		</p> 
	</div> 
</div>
</div>



<stripes:layout-component name="header"/> 

<div style="clear:both"></div>

<div id="content">  
  <stripes:layout-component name="content"/>
</div>
</body>
</html>
</stripes:layout-definition>
