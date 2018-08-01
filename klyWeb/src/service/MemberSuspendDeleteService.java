package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.commit;
import static db.JDBCUtil.getConnection;
import static db.JDBCUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;

public class MemberSuspendDeleteService {

	public boolean deleteSuspendMember(String mEM_ID) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);

		boolean deleteResult = false;

		int deleteCount = memberDAO.deleteSuspendMember(mEM_ID);

		if (deleteCount > 0) {
			commit(con);
			deleteResult = true;
		} else {
			rollback(con);
			deleteResult = false;
		}
		close(con);
		return deleteResult;
	}

}
