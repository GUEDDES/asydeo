<%@ include file="/taglibs.jsp" %>


<div id="nicemenu"> 
    <ul class="ui-widget">
        <span>
        
        <li>
        <span class="head_menu">
        <stripes:link beanclass="com.asydeo.action.HomeAction">Home</stripes:link>
		</span>
		</li>
		
		<li>
        <span class="head_menu">
        <stripes:link href="/asset/classes">Classes</stripes:link>
		</span>
		</li>

		<li>
        <span class="head_menu">
        <stripes:link href="/asset/list?uri=asydeo:ConfigurableItem">List Items</stripes:link>
		</span>
		</li>
		
		<li>
 		<span class="head_menu">
 		<stripes:link href="/asset/model">Data Models</stripes:link> 
 		<img src="${pageContext.request.contextPath}/img/arrow.png" width="18" height="15" align="top" class="arrow" />
 		</span>             
 		<div class="sub_menu"> 
 		    <stripes:link href="/asset/model">Manage...</stripes:link>                    
 			<stripes:link href="/admin/dump">View Model</stripes:link>           
 			<stripes:link href="/admin/delete">Delete Model</stripes:link>            
 		</div>
		</li>
       <li></li>
        </span>
    </ul> 
</div> 


<div style="text-align:right">
		<stripes:form beanclass="com.asydeo.action.TextSearchAction" class="head_menu" style="display:inline" method="get">
            <stripes:text id="searchfield" class="ui-widget-content ui-corner-all" name="q" size="20" value="<search>"/>
        </stripes:form>
<c:choose>
<c:when test="${!empty actionBean.context.user}"> 
Signed in as ${actionBean.context.user.username}
|
<stripes:link href="/auth/logout">Sign out</stripes:link>
</c:when>
<c:otherwise>
<stripes:link href="/auth/login">Sign in</stripes:link>
</c:otherwise>
</c:choose>
</div>
