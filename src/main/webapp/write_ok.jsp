<%@ include file="inc/top.jsp" %>
<%@ page import="org.example.jsp_crud_webservice.bean.BoardVO" %>
<%@ page import="org.example.jsp_crud_webservice.dao.BoardDAO" %>
<%
    String title = request.getParameter("title");
    String writer = request.getParameter("writer");
    String content = request.getParameter("content");
    int score = Integer.parseInt(request.getParameter("score"));

    BoardVO vo = new BoardVO();
    vo.setTitle(title);
    vo.setWriter(writer);
    vo.setContent(content);
    vo.setScore(score);

    BoardDAO dao = new BoardDAO();
    boolean success = dao.insertBoard(vo);

    if (success) {
        response.sendRedirect("list.jsp"); // 목록 페이지로 이동
    } else {
        System.out.println("<p class='text-danger'>Error: Failed to write the post.</p>");
    }
%>
<%@ include file="inc/bottom.jsp" %>
