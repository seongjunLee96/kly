package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import bean.BoardBean;
import dao.BoardDAO;

public class BoardSearchService {
	public ArrayList<BoardBean> listSearch(BoardBean search) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<BoardBean> boardlist = boardDAO. listSearch(search);
		close(con);
		return boardlist;
	}
}
