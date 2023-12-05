<%@ page pageEncoding="UTF-8" %>
<%
util.Page p = (util.Page)session.getAttribute("SESSION_PAGE"); 
if(p!=null)p.getList().clear();
%>