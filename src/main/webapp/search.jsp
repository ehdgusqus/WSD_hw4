<%@ include file="inc/top.jsp" %>
<%@ page import="org.example.jsp_crud_webservice.bean.BoardVO" %>
<%@ page import="org.example.jsp_crud_webservice.dao.BoardDAO" %>
<%
    String keyword = request.getParameter("keyword");
    BoardVO result = null;

    if (keyword != null && !keyword.trim().isEmpty()) {
        BoardDAO dao = new BoardDAO();
        result = dao.searchBoard(keyword); // 검색 결과 가져오기
        request.setAttribute("result", result); // JSP에서 JSTL로 접근 가능하도록 설정
    }
%>


<div class="container my-5">
    <h2 class="text-center">Search Board</h2>

    <form action="search.jsp" method="get" class="d-flex mb-4">
        <input type="text" name="keyword" class="form-control me-2" placeholder="Search by title" value="<%= keyword != null ? keyword : "" %>" required>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>

    <c:choose>
        <c:when test="${result != null}">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Search Result</h3>
                </div>
                <div class="card-body">
                    <p><strong>ID:</strong> <%= result.getId() %></p>
                    <p><strong>Title:</strong> <%= result.getTitle() %></p>
                    <p><strong>Writer:</strong> <%= result.getWriter() %></p>
                    <p><strong>Score:</strong> <%= result.getScore() %></p>
                    <p><strong>Content:</strong> <%= result.getContent() %></p>
                    <p><strong>Created At:</strong> <%= result.getCreate_date() %></p>
                </div>
                <div class="card-footer">
                    <a href="list.jsp" class="btn btn-secondary">Back to List</a>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <c:if test="${keyword != null}">
                <p class="text-center text-muted">No results found for "<strong><%= keyword %></strong>".</p>
            </c:if>
        </c:otherwise>
    </c:choose>
</div>

<%@ include file="inc/bottom.jsp" %>
