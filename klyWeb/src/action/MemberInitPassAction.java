package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ActionForward;
import bean.MemberBean;
import service.MemberModifyService;

public class MemberInitPassAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//session 객체의 정보 가져오기(가입정보)
		HttpSession session = request.getSession();
		
		String memberID = null;
		String tempPass = null;
		
		// 값을 받아오기
		// if parameter 값이 있다면?
		
		// 받은 값으로 settemp 1로 변경 

		// 다시 로그인 해주세요 알림
		
		ActionForward af = null;
		PrintWriter out = response.getWriter();
		/*if(result) {
			System.out.println("수정 완료");
			session.invalidate();
			af = new ActionForward();
			af.setPath("./index.jsp");
			af.setRedirect(true);
		} else {
			out.println("<script>");
			out.println("alert('정보가 수정되지 않았습니다.');");
			out.println("location.href='index.jsp';");
			out.println("</script>");
			out.close();
		}*/
				
		return af;
	}

}

