<%@page import="com.waracle.cakemgr.CakeEntity"%>
<%@page import="java.util.List"%>
<%@page import="com.waracle.cakemgr.dao.CakeDaoImpl"%>
<%@page import="com.waracle.cakemgr.dao.CakeDao"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<body>
	<h2>Hello World Cakes!</h2>
	<%! CakeDao dao = new CakeDaoImpl(); 
		List<CakeEntity> list = dao.getCakes();
	%>
	
	<br/><br/>
	<table>
		<thead><tr>
			<td><strong>Image</strong></td>
			<td><strong>Title</strong></td>
			<td><strong>Description</strong></td>
		</tr></thead>
	<tbody>
	
	<c:forEach items="<%= list %>" var="item">
		<tr>
			<td><img alt="Image" src="${item.image}" height="50" width="50"/></td>
			<td>${item.title}</td>
			<td>${item.description}</td>
		</tr>
	</c:forEach>
	
	</tbody>
	</table>
</body>
</html>
