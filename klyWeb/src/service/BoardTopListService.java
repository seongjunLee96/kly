package service;

import static db.JDBCUtil.*;

import java.sql.*;
import java.util.ArrayList;

import bean.BoardBean;
import bean.CommentBean;
import bean.LikeBean;
import dao.BoardDAO;
import dao.CommentDAO;
import dao.LikeDAO;

public class BoardTopListService {

	/** 조회수 top 3 가져오기 */
	public ArrayList<BoardBean> getTopReadcountList() {
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		
		ArrayList<BoardBean> topReadcountList = boardDAO.getTopReadcountList();
		close(con);
		return topReadcountList;
	}
	
	/** 추천수 top 3 가져오기 */
	public ArrayList<BoardBean> getTopLikecountList() {
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		
		ArrayList<BoardBean> topLikedcountList = boardDAO.getTopLikeList();
		close(con);
		return topLikedcountList;
	}

}
