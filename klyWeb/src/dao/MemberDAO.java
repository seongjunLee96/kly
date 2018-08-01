package dao;

import static db.JDBCUtil.*;

import java.sql.*;
import java.util.ArrayList;

import bean.BoardBean;
import bean.MemberBean;

public class MemberDAO {
	// singleton
	private static MemberDAO memberDAO;
	private MemberDAO() {
		
	}
	
	// MemberDAO 인스턴스 생성 메소드
	public static MemberDAO getInstance() {
		if(memberDAO==null) {
			memberDAO = new MemberDAO();
		}
		
		return memberDAO;
	}
	
	// db 설정용 필드
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// db 연결 메소드
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int joinMember(MemberBean mb) {
		String sql = "INSERT INTO MEMBER (MEMBER_ID, MEMBER_PW, MEMBER_EMAIL, MEMBER_DATE) VALUES(?,?,?, SYSDATE)";
		int result = 0;
		
		System.out.println(":: MemberDAO ::");
		System.out.println("id :" + mb.getMEMBER_ID());
		System.out.println("pass :" + mb.getMEMBER_PW());
		System.out.println("email :" + mb.getMEMBER_EMAIL());
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getMEMBER_ID());
			pstmt.setString(2, mb.getMEMBER_PW());
			pstmt.setString(3, mb.getMEMBER_EMAIL());
			
			result = pstmt.executeUpdate();
						
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("MemberDAO에서의 에러 메세지"+e.getMessage());
		} finally {
			try {
				close(pstmt);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		return result;
	}

	public MemberBean loginMember(MemberBean mb) {
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getMEMBER_ID());
			rs = pstmt.executeQuery();
			System.out.println("::DB에서 아이디 값 : "+mb.getMEMBER_ID());
			System.out.println("로그인 시도\n");
			if(rs.next()) {
				System.out.println("쿼리 결과 존재");
				if(rs.getString("MEMBER_PW").equals(mb.getMEMBER_PW())) {
					System.out.println("로그인 성공");
					mb.setMEMBER_ID(rs.getString(1));
					mb.setMEMBER_PW(rs.getString(2));
					mb.setMEMBER_EMAIL(rs.getString(3));
					mb.setMEMBER_CHECKED(rs.getInt(4));
					mb.setMEMBER_DATE(rs.getDate(5));
					mb.setMEMBER_SUSPENED(rs.getDate(6));
				} else {
					System.out.println("비밀번호 틀림");
					mb = null;
				}
			} else {
				mb = null;
			}
						
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
				close(rs);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return mb;
	}

	public int dropMember(MemberBean mb, String inputPassword) {
		String confirm = "SELECT MEMBER_PW FROM MEMBER WHERE MEMBER_ID = ?";
		String sql = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(confirm);
			pstmt.setString(1, mb.getMEMBER_ID());
			rs = pstmt.executeQuery();

			// 해당 아이디가 존재
			if(rs.next()) {
				System.out.println("원래의 비밀번호는 ?"+rs.getString(1));
				System.out.println("원래의 비밀번호는 ?"+rs.getString("MEMBER_PW"));
				System.out.println("입력한 비밀번호는 ?"+inputPassword);
				// 입력 비밀번호가 일치
				if(rs.getString(1).equals(inputPassword)) {
					System.out.println("일단 true로 삭제 시도 까진 온다.");
					System.out.println("삭제할 아이디는 : "+mb.getMEMBER_ID());
					
					pstmt = con.prepareStatement(sql);
					System.out.println("pstmt = con.prepareStatement(sql); 시도");
					pstmt.setString(1, mb.getMEMBER_ID());
					System.out.println("pstmt.setString(1, mb.getMEMBER_ID());");
					
					result = pstmt.executeUpdate();
					System.out.println("result = pstmt.executeUpdate();");
					System.out.println(":: 삭제 결과");
					System.out.println("result : "+result);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
				close(rs);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}

	public int modifyMember(MemberBean mb, String currentPassword, String changePassword) {
		String confirm = "SELECT MEMBER_PW FROM MEMBER WHERE MEMBER_ID = ?";
		String sql = "UPDATE MEMBER SET MEMBER_PW = ? WHERE MEMBER_ID = ?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(confirm);
			pstmt.setString(1, mb.getMEMBER_ID());
			rs = pstmt.executeQuery();

			// 해당 아이디가 존재
			if(rs.next()) {
				System.out.println("아이디가 존재");
				// 입력 비밀번호가 일치
				if(rs.getString(1).equals(currentPassword)) {
					System.out.println("수정 단계");
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, changePassword);
					pstmt.setString(2, mb.getMEMBER_ID());
					result = pstmt.executeUpdate();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
				close(rs);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return result;

	}

	public boolean getUserEmailChecked(String memberID) {
		String sql = "SELECT MEMBER_CHECKED FROM MEMBER WHERE MEMBER_ID = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt(1) == 1)
					return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return false; // 데이터베이스 오류
	}
	
	public String getUserEmail(String memberID) {
		String sql = "SELECT MEMBER_EMAIL FROM MEMBER WHERE MEMBER_ID = ?";
		String result = null;
		System.out.println("MemberDAO 로 넘어온 memberID의 값 : "+memberID);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("이메일 결과 값이 존재");
				result = rs.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result; // 데이터베이스 오류
	}

	public int setUserEmailChecked(String memberID) {
		String sql = "UPDATE MEMBER SET MEMBER_CHECKED = 1 WHERE MEMBER_ID = ?";
		System.out.println("setUserEmailChecked에 넘어온 유저 아이디 : "+memberID);
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			result = pstmt.executeUpdate();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int setTempMember(String memberID, String tempPass) {
		String sql = "UPDATE MEMBER SET MEMBER_TEMPPASS = "+tempPass+" WHERE MEMBER_ID = ?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public ArrayList<MemberBean> getMemberSuspendList() {	//멤버 리스트 출력
		String sql = "SELECT * FROM MEMBER";
		System.out.println("memberSuspendList DAO");
		ArrayList<MemberBean> memberlist = new ArrayList<MemberBean>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberBean memberBean = new MemberBean();
				memberBean.setMEMBER_ID(rs.getString("MEMBER_ID"));
				memberBean.setMEMBER_DATE(rs.getDate("MEMBER_DATE"));
				memberlist.add(memberBean);
			}
		} catch (Exception e) {
			System.out.println("list 오류" + e);
		} finally {
			close(rs);
			close(pstmt);

		}
		return memberlist;

	}

	public int memberSuspend(String iD) {	//멤버 정지
		String sql = "UPDATE MEMBER SET MEMBER_SUSPENDED = 1 WHERE MEMBER_ID = ?";
		System.out.println("memberSuspend DAO");
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, iD);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteSuspendMember(String mEM_ID) {	//멤버 삭제
		System.out.println("DELETE MEMBER DAO");
		String sql1 = "SELECT * FROM LIKED WHERE MEMBER_ID=?";
		String sql2 = "SELECT * FROM BOARD_COMMENT WHERE MEMBER_ID=?";
		String sql3 = "SELECT * FROM REPORT WHERE MEMBER_ID=?";
		String sql4 = "SELECT * FROM BOARD WHERE MEMBER_ID=?";
		String sql5 = "SELECT * FROM MEMBER WHERE MEMBER_ID=?";

		String sql11 = "DELETE FROM LIKE WHERE MEMBER_ID=?";
		String sql12 = "DELETE FROM BOARD_COMMENT WHERE MEMBER_ID=?";
		String sql13 = "DELETE FROM REPORT WHERE MEMBER_ID=?";
		String sql14 = "DELETE FROM BOARD WHERE MEMBER_ID=?";
		String sql15 = "DELETE FROM MEMBER WHERE MEMBER_ID=?";

		int selectResult = 0;
		int deleteResult = 0;

		try {
			/*
			 * for (int i = 1; i <= 5; i++) { System.out.println("sql" +
			 * Integer.toString(i)); pstmt = con.prepareStatement("sql" +
			 * Integer.toString(i)); pstmt.setString(1, mEM_ID); selectResult =
			 * pstmt.executeUpdate(); System.out.println(selectResult); if (selectResult !=
			 * 0) { pstmt = con.prepareStatement("sql1" + Integer.toString(i));
			 * pstmt.setString(1, mEM_ID); deleteResult = pstmt.executeUpdate();
			 * selectResult = 0; } }
			 */

			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, mEM_ID);
			selectResult = pstmt.executeUpdate();
			System.out.println(selectResult);
			if (selectResult != 0) {
				pstmt = con.prepareStatement(sql11);
				pstmt.setString(1, mEM_ID);
				deleteResult = pstmt.executeUpdate();
				selectResult = 0;
			}
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, mEM_ID);
			selectResult = pstmt.executeUpdate();
			System.out.println(selectResult);
			if (selectResult != 0) {
				pstmt = con.prepareStatement(sql12);
				pstmt.setString(1, mEM_ID);
				deleteResult = pstmt.executeUpdate();
				selectResult = 0;
			}
			pstmt = con.prepareStatement(sql3);
			pstmt.setString(1, mEM_ID);
			selectResult = pstmt.executeUpdate();
			System.out.println(selectResult);
			if (selectResult != 0) {
				pstmt = con.prepareStatement(sql13);
				pstmt.setString(1, mEM_ID);
				deleteResult = pstmt.executeUpdate();
				selectResult = 0;
			}
			pstmt = con.prepareStatement(sql4);
			pstmt.setString(1, mEM_ID);
			selectResult = pstmt.executeUpdate();
			System.out.println(selectResult);
			if (selectResult != 0) {
				pstmt = con.prepareStatement(sql14);
				pstmt.setString(1, mEM_ID);
				deleteResult = pstmt.executeUpdate();
				selectResult = 0;
			}
			pstmt = con.prepareStatement(sql5);
			pstmt.setString(1, mEM_ID);
			selectResult = pstmt.executeUpdate();
			System.out.println(selectResult);
			if (selectResult != 0) {
				pstmt = con.prepareStatement(sql15);
				pstmt.setString(1, mEM_ID);
				deleteResult = pstmt.executeUpdate();
				selectResult = 0;
			}

		} catch (Exception e) {
			System.out.println("deleteMember 오류" + e);
		} finally {
			close(pstmt);
		}

		return deleteResult;
	}

	public int relieveSuspendBoard(String mEM_ID) { // 멤버 정지 해제
		String sql = "UPDATE MEMBER SET MEMBER_SUSPENDED = 0 WHERE MEMBER_ID=?";
		int updateResult = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mEM_ID);
			updateResult = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("relieveMember 오류" + e);
		} finally {
			close(pstmt);
		}
		return updateResult;
	}
	
}