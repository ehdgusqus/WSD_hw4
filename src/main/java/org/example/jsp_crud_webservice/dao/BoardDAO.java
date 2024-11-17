package org.example.jsp_crud_webservice.dao;

import org.example.jsp_crud_webservice.bean.BoardVO;
import org.example.jsp_crud_webservice.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    String board_insert = "insert into BOARD (title, writer, score,content) values (?,?,?,?)";
    String board_delete = "delete from BOARD where id = ?";
    String board_update = "update BOARD set title = ?, writer = ?, score =?, content = ? where id = ?";
    String board_list = "select * from BOARD";
    String board_get = "select * from BOARD where id = ?";
    String board_search = "SELECT * FROM BOARD WHERE title LIKE ?";

    public boolean insertBoard(BoardVO vo){
        conn = JDBCUtil.getConnection();

        if(conn == null){
            System.out.println("DB 연결 실패(insertBoard)");
            return false;
        }

        try {
            pstmt = conn.prepareStatement(board_insert);

            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getWriter());
            pstmt.setInt(3, vo.getScore());
            pstmt.setString(4, vo.getContent());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateBoard(BoardVO vo, int id){
        conn = JDBCUtil.getConnection();

        if(conn == null){
            System.out.println("DB연결 실패(updateBoard)");
            return false;
        }

        try{
            pstmt = conn.prepareStatement(board_update);

            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getWriter());
            pstmt.setInt(3, vo.getScore());
            pstmt.setString(4, vo.getContent());
            pstmt.setInt(5, id);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteBoard(int id){
        conn = JDBCUtil.getConnection();

        if(conn == null){
            System.out.println("DB연결 실패(deleteBoard)");
            return false;
        }

        try{
            pstmt = conn.prepareStatement(board_delete);

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // SELECT Table id
    public BoardVO getBoard(int id){
        conn = JDBCUtil.getConnection();

        if(conn == null){
            System.out.println("DB연결 실패(getBoard)");
            return null;    // 연결 실패 null return
        }

        try{
            pstmt = conn.prepareStatement(board_get);
            pstmt.setInt(1, id);

            rs= pstmt.executeQuery(); // SELECT 실행 결과를 rs 반환
            if(rs.next()){
                BoardVO vo = new BoardVO();
                vo.setId(rs.getInt("id"));
                vo.setTitle(rs.getString("title"));
                vo.setWriter(rs.getString("writer"));
                vo.setContent(rs.getString("content"));
                vo.setScore(rs.getInt("score"));
                vo.setCreate_date(rs.getDate("time"));

                return vo;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // 데이터가 없는 경우
    }

    // Get Table
    public ArrayList<BoardVO> getBoardList(){
        conn = JDBCUtil.getConnection();
        ArrayList<BoardVO> boardList = new ArrayList<>();

        if(conn == null){
            System.out.println("DB연결 실패 getBoardList");
            return null;    // 연결 실패 시 null return
        }

        try {
            pstmt = conn.prepareStatement(board_list);
            rs = pstmt.executeQuery();

            while(rs.next()){
                BoardVO vo = new BoardVO();
                vo.setId(rs.getInt("id"));
                vo.setTitle(rs.getString("title"));
                vo.setWriter(rs.getString("writer"));
                vo.setContent(rs.getString("content"));
                vo.setScore(rs.getInt("score"));
                vo.setCreate_date(rs.getDate("time"));

                boardList.add(vo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return boardList;
    }

    public BoardVO searchBoard(String title) {
        conn = JDBCUtil.getConnection();
        BoardVO vo = null;

        try {
            pstmt = conn.prepareStatement(board_search);
            pstmt.setString(1, "%" + title + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    vo = new BoardVO();
                    vo.setId(rs.getInt("id"));
                    vo.setTitle(rs.getString("title"));
                    vo.setWriter(rs.getString("writer"));
                    vo.setScore(rs.getInt("score"));
                    vo.setContent(rs.getString("content"));
                    vo.setCreate_date(rs.getDate("time"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("검색 중 오류 발생", e);
        }

        return vo;
    }
}
