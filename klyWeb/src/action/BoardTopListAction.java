package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import bean.BoardBean;
import service.BoardTopListService;

public class BoardTopListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		BoardTopListService boardTopListService = new BoardTopListService();
		
		ArrayList<BoardBean> topReadcountList = boardTopListService.getTopReadcountList();
		//ArrayList<BoardBean> topLikedList = boardTopListService.getTopLikeList();
		
		// topReadcountList를 JSON 형태로 변환
		
		request.setAttribute("topReadcountList", topReadcountList);
		//request.setAttribute("topLikedList", topLikedList);

		
		
		ActionForward forward = null;
		return forward;
	}

}
