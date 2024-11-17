<%@ include file="inc/top.jsp" %>
<%@ page import="org.example.jsp_crud_webservice.bean.BoardVO" %>
<%@ page import="org.example.jsp_crud_webservice.dao.BoardDAO" %>

<%
  int id = Integer.parseInt(request.getParameter("id"));
  String title = request.getParameter("title");
  String writer = request.getParameter("writer");
  String content = request.getParameter("content");
  int score = Integer.parseInt(request.getParameter("score")); // score 값 처리

  BoardVO vo = new BoardVO();
  vo.setId(id);
  vo.setTitle(title);
  vo.setWriter(writer);
  vo.setContent(content);
  vo.setScore(score); // score 값 설정

  BoardDAO dao = new BoardDAO();
  boolean success = dao.updateBoard(vo, id); // 업데이트 실행

  if (success) {
    response.sendRedirect("list.jsp"); // 성공 시 목록 페이지로 이동
  } else {
    System.out.println("<p class='text-danger'>Error: Failed to update the post.</p>");
  }
%>
<%@ include file="inc/bottom.jsp" %>
