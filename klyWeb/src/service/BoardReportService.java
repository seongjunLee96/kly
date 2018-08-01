package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.commit;
import static db.JDBCUtil.getConnection;
import static db.JDBCUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import bean.ReportBean;
import dao.BoardDAO;

public class BoardReportService {
	
	public boolean reportUpdate(ReportBean reportBean) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		boolean reportResult = false;
		int result = boardDAO.reportAdd(reportBean);		
		if(result != 0) {
			boardDAO.reportUpdate(reportBean);
			reportResult = true;
			commit(con);
		} else {
			rollback(con);
		}
		return reportResult;
	}
	public ArrayList<ReportBean> getReportList (){
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<ReportBean> reportList = boardDAO.getReportList();
		close(con);
		return reportList;
	}
}
