<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/betting.css" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="java.util.*, match.model.vo.* , team.model.vo.*" %>	
<%
	ArrayList<Match> MatchList = (ArrayList<Match>)request.getAttribute("MatchList");
	ArrayList<Team> TeamList = (ArrayList<Team>)request.getAttribute("TeamList");
%>

<body>
        <section>
         <h3 class="BettingView2Strapline">1경기</h3>
            <div id="BettingView2Table1">
                <img src="<%=request.getContextPath() %>/images/<%=TeamList.get(0).getImage_file()%>.png" class="team-icon" >
                <img src="<%=request.getContextPath() %>/images/vs.png" style="height: 180px; margin-left: 50px; margin-right: 50px;">
                <img src="<%=request.getContextPath() %>/images/<%=TeamList.get(1).getImage_file()%>.png" class="team-icon" > 
                <div>
                    <br><br><br>
                    <form action="<%=request.getContextPath()%>/betting/GoScoreBetting" method="post">
                    <input type="number" id="member_no" name="member_no" value="<%=memberLoggedIn.getMemberNo()%>" hidden/>
                    <input type="number" id="betting_no" name="betting_no" value="<%=MatchList.get(0).getBettingNo()%>" hidden/>
                    <input type="number" id="match_no" name="match_no" value="<%=MatchList.get(0).getMatchNo()%>" hidden/>
                    
                    
                    <%=MatchList.get(0).getHomeTeamName()%> : <input type="number" name="hometeamscore" id="hometeamscore" style="width: 30px;"> 점
                    <br><br><br>
                    <%=MatchList.get(0).getAwayTeamName()%> : <input type="number" name="awayteamscore" id="awayteamscore" style="width: 30px;"> 점
                </div>
            </div>
        <br><br><br>
        <br><br><br>
        <br><br><br>
        <input type="number" placeholder="배팅금액을 입력해주세요." />
        <input type="submit" value="배팅 하기 ">
        </form>
  
        </section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>