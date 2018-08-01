package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import bean.BoardBean;
import bean.CommentBean;
import service.BoardCommentListService;
import service.BoardListService;

public class BoardArrayAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		BoardBean boardBean = new BoardBean();
		String category = request.getParameter("category");
		String array = request.getParameter("array"); // 받아온값 저장
		boardBean.setBOARD_CATEGORY(category);  // 쿼리문 전송을 위한 세팅
		
		BoardListService boardlistservice = new BoardListService();  
		BoardCommentListService boardCommentListService = new BoardCommentListService(); //객체 선언
		ArrayList<CommentBean> commentList = boardCommentListService.BoardCommentListService(); //댓글목록 객체배열선언 
		ActionForward actionForward = null;
		if(category!=null) { 
			if(array.equals("추천")) { //카테고리에 값이 null이 아니고 array에 추천값을 받아온 경우
				ArrayList<BoardBean> ArrayList = boardlistservice.getLikeList(boardBean);
				for(int i=0 ; i<ArrayList.size();i++) { // ArrayList 객체를 선언해서 카테고리 값을 비교
					if(category.equals(ArrayList.get(i).getBOARD_CATEGORY())) {
						request.setAttribute("category", ArrayList.get(i));
						request.setAttribute("boardlist", ArrayList);
						request.setAttribute("commentlist", commentList);
						actionForward = new ActionForward();
						actionForward.setPath("List.jsp");
						return actionForward;
						}
					}
			}else {  //카테고리에 값이 null이 아닌경우 array에 조회값을 받아온 경우
				ArrayList<BoardBean> ArrayList = boardlistservice.getReadList(boardBean);
				for(int i=0 ; i<ArrayList.size();i++) {
					if(category.equals(ArrayList.get(i).getBOARD_CATEGORY())) {
						request.setAttribute("category", ArrayList.get(i));
						request.setAttribute("boardlist", ArrayList);
						request.setAttribute("commentlist", commentList);
						actionForward = new ActionForward();
						actionForward.setPath("List.jsp");
						return actionForward;
						}
					}
				}
			}

		if(array.equals("추천")) { //카테고리에 값이 null인 경우 array에 추천값을 받아온 경우
			ArrayList<BoardBean> ArrayList = boardlistservice.getLikeList(boardBean);
			request.setAttribute("boardlist", ArrayList);
			request.setAttribute("commentlist", commentList);
			actionForward = new ActionForward();
			actionForward.setPath("List.jsp");
			return actionForward;
		}else {  //카테고리에 값이 null인 경우 array에 조회값을 받아온 경우
			ArrayList<BoardBean> ArrayList = boardlistservice.getReadList(boardBean);
			request.setAttribute("boardlist", ArrayList);
			request.setAttribute("commentlist", commentList);
			actionForward = new ActionForward();
			actionForward.setPath("List.jsp");
			return actionForward;
			}
		}
	}



