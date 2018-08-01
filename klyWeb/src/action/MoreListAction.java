package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import bean.BoardBean;
import dao.BoardDAO;

public class MoreListAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int more = 0;
		ActionForward actionForward = null;
		if(request.getParameter("more")!=null) {
			more = Integer.parseInt(request.getParameter("more"));	
		}
		ArrayList<BoardBean> boardList = BoardDAO.getInstance().getboardlist();
		request.setAttribute("moreList", boardList);
		actionForward = new ActionForward();
		actionForward.setPath("moreList.jsp");
		return actionForward;
	}

}	
