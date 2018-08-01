package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import bean.BoardBean;
import bean.MemberBean;

public class MemberSuspendListService {

	public static ArrayList<MemberBean> getMemberSuspendList() {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);

		// DAO 클래스 메소드 호출해서 결과를 받아옴
		ArrayList<MemberBean> MemberSuspendList = memberDAO.getMemberSuspendList();
		close(con);
		System.out.println("service에서의 " + MemberSuspendList.size());
		return MemberSuspendList;
	}

}
