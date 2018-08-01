package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.commit;
import static db.JDBCUtil.getConnection;
import static db.JDBCUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;

public class BoardSuspendRelieveService {

	public boolean RelieveSuspendBoard(int relieveNum) {
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		
		boolean relieveResult = false;
		
		int relieveCount = boardDAO.relieveSuspendBoard(relieveNum);
		
		if(relieveCount > 0) {
			commit(con);
			relieveResult = true;
		}else {
			rollback(con);
			relieveResult = false;
		}
		close(con);
		return relieveResult;
	}

}
