package cotroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.AdminCommentListAction;
import action.BoardCategoryAction;
import action.BoardCommentAction;
import action.BoardListAction;
import action.BoardSuspendListAction;
import action.BoardTopListAction;
import action.BoardWriteAction;
import action.EmailAuthAction;
import action.EmailCheckedAction;
import action.MemberContentListAction;
import action.MemberDetailAction;
import action.MemberDropAction;
import action.MemberJoinAction;
import action.MemberLoginAction;
import action.MemberLogoutAction;
import action.MemberModifyAction;
import ajax.Ajax;
import ajax.IndexLikeListAjax;
import ajax.IndexTopListAjax;
import bean.ActionForward;

@WebServlet("*.kly")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청받은 request의 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 요청 url에 대한 처리(command로 변환)
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		/***상시 검사용***
		***/
		System.out.println("-- now At FrontController");
		System.out.println("RequestURI : "+RequestURI);
		System.out.println("RequestURI : "+contextPath);
		System.out.println("command : "+command);
		System.out.println();
		
		Action action = null;
		ActionForward forward = null;
		Ajax ajax = null;
		String responseText = null;
		
		
		// command에 따른 ActionForward 인스턴스 생성
		if(command.equals("/memberJoin.kly")) {
			action = new MemberJoinAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/indexTopList.kly")) {
			ajax = new IndexTopListAjax();
			try {
				responseText = ajax.getJSON(request, response); // JSON
				response.getWriter().write(responseText);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		} else if(command.equals("/indexLikeList.kly")) {
			ajax = new IndexLikeListAjax();
			try {
				responseText = ajax.getJSON(request, response); // JSON
				response.getWriter().write(responseText);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		} else if(command.equals("/emailAuthAction.kly")) {
			action = new EmailAuthAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/emailCheckedAction.kly")) {
			action = new EmailCheckedAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberLogin.kly")) {
			action = new MemberLoginAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberLogout.kly")) {
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberDetail.kly")) {
			action = new MemberDetailAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberDrop.kly")) {
			action = new MemberDropAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberInfoRivision.kly")) {
			action = new MemberModifyAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/myContent.kly")) {
			action = new MemberContentListAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}  else if(command.equals("/boardWrite.kly")) {
			action = new BoardWriteAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/boardList.kly")) {
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/adminComment.kly")) {
			action = new AdminCommentListAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardSuspendList.kly")) {
			System.out.println("11");
			action = new BoardSuspendListAction();
			System.out.println("22");
			try {
				System.out.println("88");
				forward = action.execute(request, response);
				System.out.println("99");
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/boardList.kly")) {
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/boardComment.kly")) {
			action = new BoardCommentAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/boardCategory.kly")) {
			action = new BoardCategoryAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		// ActionForward 인스턴스에 따른 forwarding
		if(forward!=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
		
	}

}
