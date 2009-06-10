<%@ include file="/taglibs.jsp" %>
<style>
a.bigbutton {margin: 2px; 
position: relative; 
padding: 4px; 
cursor: pointer; 
height: 220px;
float:left;text-decoration:none;
font-size: 18px;
}

span.number {
  font-size:30px
}
</style>
<stripes:layout-render name="/layout/default.jsp">

<stripes:layout-component name="header">
  <c:import url="/menu.jsp" />
</stripes:layout-component>

<stripes:layout-component name="content">



<a href="../search/text" class="bigbutton ui-state-default ui-corner-all">
<img src="../img/search.jpg"/><br/><br/>
<span class="number">1</span> Search the knowledge base
</a>

<a href="../asset/classes" class="bigbutton ui-state-default ui-corner-all">
<img src="../img/shapes.jpg"/><br/><br/>
<span class="number">2</span> Explore by Type
</a>

<a href="../asset/model" class="bigbutton ui-state-default ui-corner-all">
<img src="../img/cube.jpg"/><br/><br/>
<span class="number">3</span> Manage/Choose Models
</a>

</stripes:layout-component>
</stripes:layout-render>