package service;

import static db.JDBCUtil.getConnection;
import static db.JDBCUtil.close;


import java.sql.Connection;
import java.util.ArrayList;

import bean.BoardBean;
import dao.BoardDAO;

public class BoardListService {
	public ArrayList<BoardBean> getboardList() {
			
			Connection con = getConnection();
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(con);
			
			ArrayList<BoardBean> boardlist = boardDAO.getboardlist();
			close(con);
			return boardlist;
		}
	
	public ArrayList<BoardBean> getReadList(BoardBean category) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<BoardBean> boardlist = boardDAO.getReadList(category);
		close(con);
		return boardlist;
	}
	public ArrayList<BoardBean> getLikeList(BoardBean category) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		
		ArrayList<BoardBean> boardlist = boardDAO.getLikeList(category);
		close(con);
		return boardlist;
	}
	
	public ArrayList<BoardBean> getCategory(BoardBean category) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		
		ArrayList<BoardBean> boardList = boardDAO.getCategory(category);
		close(con);
		return boardList;
	}

}
