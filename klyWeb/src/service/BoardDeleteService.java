package service;

import static db.JDBCUtil.*;


import java.sql.Connection;

import bean.BoardBean;
import dao.BoardDAO;

public class BoardDeleteService {
	
	public boolean deleteService(BoardBean boardBean) {
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		
		boolean deleteresult = false;
		
		int deletecount = boardDAO.boardDelete(boardBean);
		
		if(deletecount>0) {
			deleteresult = true;
			commit(con);
			System.out.println("deleteresult");
		}else {
			rollback(con);
			deleteresult = false;
		}
		close(con);
		return deleteresult;
	}

}
