<%@ include file="inc/top.jsp" %>
<%@ page import="org.example.jsp_crud_webservice.bean.BoardVO" %>
<%@ page import="org.example.jsp_crud_webservice.dao.BoardDAO" %>
<%
  int id = Integer.parseInt(request.getParameter("id"));
  BoardDAO dao = new BoardDAO();
  BoardVO board = dao.getBoard(id);
%>
<h2>View Post</h2>
<p><strong>Title:</strong> <%= board.getTitle() %></p>
<p><strong>Writer:</strong> <%= board.getWriter() %></p>
<p><strong>Content:</strong> <%= board.getContent() %></p>
<p><strong>Score:</strong> <%= board.getScore() %></p>

<%@ include file="inc/bottom.jsp" %>
