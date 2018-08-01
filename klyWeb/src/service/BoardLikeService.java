package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.commit;
import static db.JDBCUtil.getConnection;
import static db.JDBCUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import bean.LikeBean;
import dao.BoardDAO;

public class BoardLikeService {
	
	public boolean likeUpdate(LikeBean likeBean) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		boolean likeResult = false;
		int result = boardDAO.likeAdd(likeBean);		
		if(result != 0) {
			boardDAO.likeUpdate(likeBean);
			likeResult = true;
			commit(con);
		} else {
			rollback(con);
		}
		return likeResult;
	}
	public ArrayList<LikeBean> getLikeList (){
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<LikeBean> likeList = boardDAO.getLikeList();
		close(con);
		return likeList;
	}

}
