<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.BoardBean" %>
<%@ page import="bean.CommentBean" %>
<%@ page import = "java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<style>
	    #leftbody{
	        padding: 5px;
	    }
	    #category{
	        margin: 5px 5px 20px 5px;
	    } 
	    #register{
	        margin: 5px 5px 20px 5px;
	    }
	</style>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<title> Title </title>
</head>

<body>
	 <%@include file="./navbarTemplate.jsp" %>
	
<div class="main" style="height: 1080px; width: 1920px">
<div class="bg-dark" id="leftbody" style="height: 1080px; width: 250px;float: left;">
    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location='boardList.kly'">전체보기</button>
    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location='boardCategory.kly?category=유머">유머</button>
    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location='boardCategory.kly?category=정보'">정보</button>
    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location='boardCategory.kly?category=게임'">게임</button>
    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location='boardCategory.kly?category=감동'">감동</button>
    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location='boardCategory.kly?category=스포츠'">스포츠</button>
    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location='boardCategory.kly?category=취미'">취미</button>
    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location='boardCategory.kly?category=동물'">동물</button>
    <button type="button" class="btn btn-success" style="width:95%; margin: 5px 5px 80px 5px;" id="category" onclick="location='boardCategory.kly?category=기타'" >기타</button>
    <button type="button" class="btn btn-primary" style="width:95%;" id="register" onclick="location='boardWrite.jsp'">게시글 등록</button>
    <form action="./boradList.kly" method="post">
    <div class="input-group mb-3">
      <input type="text" class="form-control" placeholder="Search">
        <div class="input-group-append">
        <button class="btn btn-success" type="submit">검색</button> 
        </div>
    </div>
    </form>
    </div>
<div class="bg-light" id="rightbody" style="height: 1080px; width: 1670px; float:right">
    <div class="dropdown">
        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
          정렬 기준
        </button>
        <div class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="location='boardList.kly?array=추천&category=${board.BOARD_CATEGORY}'">추천 수</a>
          <a class="dropdown-item" href="#" onclick="location='boardList.kly?array=조회&category=${board.BOARD_CATEGORY}'">조회 수</a>
    </div>
  </div>
    <div>
    <c:forEach var = "board" items = "${boardlist}" varStatus="status">
        <div class="videoplay">
            <button class="btn" onclick="document.getElementById('id0${status.count}').style.display='block'">${board.BOARD_SUBJECT}<!-- <img class="btn-img" src="./images/logo_black.png" style="width:100px; height:50px;"> --></button>
            <div id="id0${status.count}" class="panel black display-container" style="display:none; width:1000px; height:400px">
 	 		<span onclick="this.parentElement.style.display='none'">x</span>
		 	<div>
		 	${board.BOARD_VIDEO_URL}
		 	<div>
		 	<div>
		 	<button type="button" class="btn btn-success">추천</button>${board.BOARD_LIKECOUNT}
		 	<button type="button" class="btn btn-danger">신고</button>
		 	</div>
		 	<div>
		 	<c:forEach var = "comment" items = "${commentlist}">
		 	<c:if test="${comment.BOARD_NUM == board.BOARD_NUM}">
		 	<div>
		 	${comment.MEMBER_ID} : ${comment.COMMENT_CON}
		 	</div>
		 	</c:if>
		 	</c:forEach>
		 	<div>
		 		<form action="./boradComment.kly?board_num=${board.BOARD_NUM}&member_id=${board.MEMBER_ID}" method="post">
    			<div class="input-group mb-3">
      			<input type="text" class="form-control" name="comment">
        		<div class="input-group-append">
        		<button class="btn btn-success" type="submit">등록</button> 
        		</div>
    			</div>
   			 	</form>
		 	</div>
		 	</div>
		 	</div>
		 	</div>

			</div>
            <span class="videotitle">${board.BOARD_SUBJECT}</span>
            <span class="writer">${board.MEMBER_ID}</span>
            <div>
                <span class="videoCategory">분류 :${board.BOARD_CATEGORY}</span>
                <span class="videoLike">추천:${board.BOARD_LIKECOUNT}</span>
                <%-- <span class="videoComment">${board.board_comment}</span> --%>
                <span class="videoUptime">${board.BOARD_DATE}</span>
                <span class="videoReadCount">조회:${board.BOARD_READCOUNT}</span>
            </div>
        </div>
        </c:forEach>
    </div>
    <div>
    </div>
    </div>
</div>
    <button type="button" class="btn btn-default btn-sm btn-block">더보기</button>
</body>
</html>