<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="member.model.vo.*" %>
 <%@ page import="point.model.vo.*" %>
 <%
 	Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
	MemberUseItem memberUseItem = (MemberUseItem)session.getAttribute("memberUseItem");
	//쿠키관련 처리
	String memberId = "";
 %> 
 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>타이틀 제목</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.4.0.js"></script>
<script src="<%=request.getContextPath()%>/js/script.js"></script>

</head>
<script>
$(function(){
	<%if(memberLoggedIn!=null){
		if(memberUseItem!=null){%>
	
			<%if(memberUseItem.getBackground()!=null){%>
			var itemImage = "<%=memberUseItem.getBackground()%>.gif";
			var str1 ="<%=request.getContextPath()%>"+"/images/";
		    str1 += itemImage;
		    $("#title-bar").css({"background":"url("+str1+")",
				"background-repeat": "no-repeat",
				"background-size": "100% 100%"}); 
			<%}
			if(memberUseItem.getBadge()!=null){%>
			var itemImage = "<%=memberUseItem.getBadge()%>.png";
			var str1 ="<%=request.getContextPath()%>"+"/images/";
		    str1 += itemImage;
		    
		    $("#bedgeImg").attr('src',str1);
		
			<%}
		}
		
	}%>
});
function searchid(){
	window.open("<%=request.getContextPath()%>/member/MemberSearchId", "a", "width=400, height=300, left=100, top=50"); 
}
function searchpwd(){
	window.open("<%=request.getContextPath()%>/member/MemberSearchPwd","pwd","width=400, height=350,left=100,top=50");
  
}
</script>
<body>
    <div id="container">
    	
        <!-- header -->
        <header>
        <div id="title-bar" >
            <div class="left-div">
                <a href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath() %>/images/이웃집토토중.png" alt="" ></a>
            </div>
            <div class="right-div">
            	<% if(memberLoggedIn == null){ %>
                <div id="login-bar">
	                <form action="<%=request.getContextPath()%>/member/login"  method="post">
                       <input type="text" placeholder="아이디" id="input-id" name="input-id">
                       <input type="password" name="input-pwd" id="input-pwd" placeholder="비밀번호">
                       <input type="submit" value="로그인" id="login-btn"/>
                        <input type="button"
                       				id="signup-btn"
                                      value="회원가입"
                                      onclick="location.href='<%=request.getContextPath() %>/member/UserTerms'" />            
                       <br />
                       
                       
                       <label id="search-id" onclick="searchid();">아이디찾기</label>
                       <label id="search-pwd" onclick="searchpwd();">비밀번호찾기</label>
	                </form>
             	</div>
                <%} 
                else{%>
                <div id="login-bar">
                	<table id="Login-table">
						<tr>
							<td>
							<%if(memberUseItem.getBadge()!=null){%>
								<img id="bedgeImg" src = "">
							<%} %>
							<%=memberLoggedIn.getMemberName() %>님, 안녕하세요.
							<a href="<%=request.getContextPath()%>/member/Message?memberId=<%=memberLoggedIn.getMemberId()%>&type=MyMessage">
								<img src="<%=request.getContextPath() %>/images/쪽지.png" id="message" />
							</a>
							</td>
						</tr>
						<tr>
							<td>현재 포인트 : <%=memberLoggedIn.getPoint()%> Point</td>
						</tr>
						<tr>
							<td>
								<input type="button" class="btn btn-warning" value="내정보보기" onclick="location.href='<%=request.getContextPath()%>/member/MyInfo?memberId='+'<%=memberLoggedIn.getMemberId()%>'"> 
								&nbsp;							
								<input type="button"  class="btn btn-warning" value="로그아웃" onclick="location.href='<%=request.getContextPath()%>/member/logout?memberId='+'<%=memberLoggedIn.getMemberId()%>'">
							</td>
						</tr>
					</table>
				</div>
				<%}%>
				
            </div>
            
        </div>
         <nav id="menu-bar">
             <ul >
                 <!-- 공통부분 -->
                 <%if(memberLoggedIn!=null){%>
    			<li><a class = "menuLink" href="<%=request.getContextPath()%>/betting/bettingList?h_member_no=<%=memberLoggedIn.getMemberNo()%>&memberId=<%=memberLoggedIn.getMemberId()%>">배팅하기</a></li>
    			<%}else{%>
    			<li><a class = "menuLink" href="<%=request.getContextPath()%>/betting/bettingList?h_member_no=<%=-10%>">배팅하기</a></li>
    			<%}%>
    			 <li><a class = "menuLink" href="<%=request.getContextPath()%>/match/MatchList">경기정보</a></li>
                 <li><a class = "menuLink" href="<%=request.getContextPath()%>/board/boardList">게시판</a></li>
                 <li><a class = "menuLink" href="<%=request.getContextPath()%>/point/pointShopList">포인트상점</a></li>
                 <% if(memberLoggedIn != null){ 
                 if(memberLoggedIn.getMemberId().equals("admin")){ %>
                 <li><a class = "menuLink" href="<%=request.getContextPath()%>/admin/userList">회원관리</a></li>
                 <%}}%>
                 <li><a class = "menuLink" href="<%=request.getContextPath()%>/help/helpIndex">고객센터</a></li>
             </ul>
         </nav>
        </header>