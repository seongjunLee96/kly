package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ActionForward;
import bean.BoardBean;
import bean.CommentBean;
import bean.LikeBean;
import bean.MemberBean;
import service.CommentListService;
import service.MemberContentListService;

public class MemberContentListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("loginInfo");
		String memberID = memberBean.getMEMBER_ID();
		// 검색 타입
		String listType = request.getParameter("listType");
		
		MemberContentListService memberContentListService = new MemberContentListService();
		
		
		ActionForward forward = null;
		PrintWriter out = response.getWriter();

		if(listType.equals("article")) {
			ArrayList<BoardBean> articleList = memberContentListService.getMemberArticleList(memberID);
			if(articleList != null) {
				request.setAttribute("articleList", articleList);
				forward = new ActionForward();
				forward.setPath("./myPageList.jsp");
				System.out.println("action 테스트 : " +articleList.get(1).getBOARD_SUBJECT());
			} else {
				out.println("<script>");
				out.println("alert('게시물 조회를 실패했습니다.')");
				out.println("location.href='./myPageList.jsp';");
				out.println("</script>");
				out.close();
			}

		} else if(listType.equals("comment")) {
			CommentBean commentList = memberContentListService.getMemberCommentList(memberID);
			
			if(commentList != null) {
				request.setAttribute("commentList", commentList);
			} else {
				System.out.println("실패");
			}
		} else if(listType.equals("liked")) {
			LikeBean likeList = memberContentListService.getMemberLikedList(memberID);
			
			if(likeList != null) {
				request.setAttribute("likeList", likeList);
			} else {
				System.out.println("실패");
			}
		}
		
		
		return forward;
		
	}

}
