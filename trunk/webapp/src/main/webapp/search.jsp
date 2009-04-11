<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="htmlHead">
  <c:import url="/menu.jsp" />

  <script type="text/javascript" src="http://jqueryui.com/latest/ui/ui.tabs.js"></script>
  <script type="text/javascript">
    $(document).ready(function(){
        $("#tabs").tabs({
            fx: { opacity: 'toggle', duration: 'fast', height: 'toggle' },
            show: function(event, ui) {
                var $tabs = $('#tabs').tabs();
                var selected = $tabs.tabs('option', 'selected');

                if ( selected == 0 ) {
                    $("input#searchStr").focus();
                }
            }
        });
    });
  </script>
  
  <script type="text/javascript">
    // Called when a form is submitted
    $(function() {
        $(".ui-button").click(function() {
            var $tabs = $('#tabs').tabs();
            var selectedTab = $tabs.tabs('option', 'selected');
            var searchVar;
            var eventName;

            if ( selectedTab == 0 ) {
                searchVar = "searchStr";
                eventName = "textQueryResults";
            }
            else if ( selectedTab == 2 ) {
                searchVar = "sparqlStr";
                eventName = "sparqlQueryResults";
            }
      
            var value = document.getElementById(searchVar).value;

            if (value == "") {
                document.getElementById(searchVar).focus();
                return false;
            }

            // Important to pass event name so code receiving the request
            // will know which event handle to use
            var dataString = eventName + '&' +
                             searchVar + '=' + value;
       
            // Perform an AJAX call to get search results in order to
            // maintain the state of the currently selected tab. 
            $.ajax({
                type: "POST",
                url: "search",
                data: dataString,
                dataType: "html",
                success: function(html) {
                    $('#searchResults').html(html);
                },
                error: function() {
                    alert("Search failure");
                },
                complete: function() {
                    //alert("complete");
                }
            });

            return false;
        });
    });
  </script>
  
</stripes:layout-component>

<stripes:layout-component name="content">

<div id="tabs">
    <ul>
        <li><a href="#tab-1"><span>Search</span></a></li>
        <li><a href="#tab-2"><span>Advanced</span></a></li>
        <li><a href="#tab-3"><span>SPARQL</span></a></li>
    </ul>
    <div id="tab-1">
        <stripes:form action="/asset/search">
        <stripes:text name="searchStr" id="searchStr"/>
        <br/>
        <stripes:submit name="textQuery" value="Search" class="ui-button ui-state-default ui-corner-all"/>
        </stripes:form>
    </div>
    <div id="tab-2">
        TODO
    </div>
    <div id="tab-3">
        <stripes:form action="/asset/search">
        <stripes:textarea cols="50" rows="5" name="sparqlStr" id="sparqlStr"/>
        <br/>
        <stripes:submit name="sparqlQuery" value="Query" class="ui-button ui-state-default ui-corner-all"/>
    </div>
</div>

<div id="searchResults"/>

<br/><br/>
<c:forEach var="v" items="${actionBean.queryResult}">
<li class="listitem"> 
  <stripes:link class="button" beanclass="com.asydeo.action.EditAction">
  <stripes:param name="uri" value="${v.URI}"/>
  <stripes:param name="classUri" value="asydeo:ConfigurableItem"/>
    <span class="label">${v.label}</span>
  </stripes:link>
</li>
</c:forEach>

</stripes:form>
</stripes:layout-component>
</stripes:layout-render>
