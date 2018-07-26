package service;

import static db.JDBCUtil.*;

import java.sql.*;
import java.util.ArrayList;

import bean.BoardBean;
import bean.CommentBean;
import bean.LikeBean;
import dao.BoardDAO;
import dao.CommentDAO;
import dao.LikeDAO;

public class MemberContentListService {

	/** 멤버 한명의 게시글 가져오기 */
	public ArrayList<BoardBean> getMemberArticleList(String memberID) {
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = getConnection();
		boardDAO.setConnection(con);
		
		ArrayList<BoardBean> ariticleList = boardDAO.getMemberArticle(memberID);
		if(ariticleList != null) {
			System.out.println(memberID+"의 게시물 리스트 불러오기 성공 at service\n");
		}
		close(con);
		return ariticleList;
	}
	
	/** 멤버 한명의 댓글 가져오기 */
	public ArrayList<CommentBean> getMemberCommentList(String memberID) {
		CommentDAO commentDAO = CommentDAO.getInstance();
		Connection con = getConnection();
		commentDAO.setConnection(con);

		ArrayList<CommentBean> commentList = commentDAO.getMemberComment(memberID);
		if(commentList.size() > 2) {
			System.out.println(memberID+"의 댓글 리스트 불러오기 성공 at service\n");
		}
		close(con);
		
		return commentList;
	}

	/** 멤버 한명의 추천 게시물 가져오기 */
	public ArrayList<LikeBean> getMemberLikedList(String memberID) {
		LikeDAO likeDAO = LikeDAO.getInstance();
		Connection con = getConnection();
		likeDAO.setConnection(con);

		ArrayList<LikeBean> likeList = likeDAO.getMemberLike(memberID);
		close(con);
		
		return likeList;
	}

}
