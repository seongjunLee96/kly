package service;

import static db.JDBCUtil.getConnection;
import static db.JDBCUtil.close;
import static db.JDBCUtil.commit;
import static db.JDBCUtil.getConnection;
import static db.JDBCUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;

public class BoardSuspendDeleteService {

	public boolean deleteSuspendBoard(int delNum) {
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		
		boolean deleteResult = false;
		
		int deleteCount = boardDAO.deleteSuspendBoard(delNum);
		
		if(deleteCount > 0) {
			commit(con);
			deleteResult = true;
		}else {
			rollback(con);
			deleteResult = false;
		}
		close(con);
		return deleteResult;
	}

}
