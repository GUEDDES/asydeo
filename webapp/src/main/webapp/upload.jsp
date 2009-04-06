<html>

<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.3.2.min.js"></script>  
<script type="text/javascript" src="http://malsup.com/jquery/form/jquery.form.js"></script>
<script type="text/javascript"><!--
$().ajaxError(function(ev,xhr,o,err) {
    alert(err);
    if (window.console && window.console.log) console.log(err);
});

$(document).ready(function() { 
    $('#uploadForm').ajaxForm({
        beforeSubmit: function(a,f,o) {
             $('#uploadOutput').html('Submitting...');
        },
        success: function(data) {
            var $out = $('#uploadOutput');
            $out.html('Form success handler received: <strong>' + typeof data + '</strong>');
            $out.append('<div><pre>'+ data +'</pre></div>');
        }
    });
});

// --> 
</script> 

</head>

<body>

<form id="uploadForm" action="asset/upload" method="POST" enctype="multipart/form-data"> 
   <input type="hidden" name="MAX_FILE_SIZE" value="100000" /> 
   File: <input type="file" name="newAttachment" /> 
   <input type="submit" value="Submit" /> 
</form>
<label>Output:</label> 
<div id="uploadOutput"></div> 

</body> 