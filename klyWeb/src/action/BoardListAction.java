package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import bean.BoardBean;
import bean.CommentBean;
import service.BoardCommentListService;
import service.BoardListService;


public class BoardListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		
		ActionForward actionForward = null;
		BoardListService boardlistservice = new BoardListService();
		BoardCommentListService boardCommentListService = new BoardCommentListService();
		ArrayList<BoardBean> boardlist = boardlistservice.getboardList();
		ArrayList<CommentBean> commentList = boardCommentListService.BoardCommentListService();
		request.setAttribute("boardlist", boardlist);
		request.setAttribute("commentlist", commentList);
		actionForward = new ActionForward();
		actionForward.setPath("List.jsp");
		return actionForward;
	}
}
