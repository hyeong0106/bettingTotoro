<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/betting.css" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<%@page import="match.model.vo.*, team.model.vo.*" %>
<%
    Match m = (Match)request.getAttribute("m");
    Team Away = (Team)request.getAttribute("Away");
    Team Home = (Team)request.getAttribute("Home");
    int HasResult = (int)request.getAttribute("HasResult");
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<style>
#pic3{
    position: absolute;
    top: 737px;
    width: 401px;
    left: 368px;
    opacity: 0.8;
}
#BettingResultInsertTable1 {
    margin-top: 20px;
    margin-left: 188px;
    float: left;
}
</style>
<section>
<img src="/BettingTotoro/images/basketiler2.png" alt="" id="pic3" />
<div class="bettingbackground">
<img src="<%=request.getContextPath() %>/images/농구배경이미지.png" id="imgbackground" />
<div id="BettingResultInsertTable1">

<% if(HasResult==0){%>
    <h2>아직 경기가 끝나지 않았습니다.</h2>
<%}else{
	if(m.getHomeTeamScore()>m.getAwayTeamScore()){%>
		<img src="<%=request.getContextPath() %>/images/win수정.png" alt="" id="Home"/>
		<img src="<%=request.getContextPath() %>/images/lose.png" alt="" id="Away"/>
	<%}else if(m.getHomeTeamScore()<m.getAwayTeamScore()){%>
		<img src="<%=request.getContextPath() %>/images/win수정.png" alt="" id="Away"/>
		<img src="<%=request.getContextPath() %>/images/lose.png" alt="" id="Home"/>
	<%}else{%>
		<img src="<%=request.getContextPath() %>/images/draw.png" alt="" id="Draw"/>		
	<%}
}%>
         <table>
                <tr>
                    <td>
                        <div class="card" style="width: 18rem;">
                            <h3 class="BettingResultInsertStrapline">홈팀</h3>
                            <img class="card-img-top" src="<%=request.getContextPath() %>/images/<%=Home.getImage_file() %>.png">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">리그 : <%=m.getTypeOfSports() %></li>
                                <li class="list-group-item">팀명 : <%=m.getHomeTeamName() %>
                                                            <br />승 : <%=Home.getWin() %>
                                                            <br />패 : <%=Home.getLose() %>
                                                            <br />무승부 : <%=Home.getDraw() %></li>
                                <li class="list-group-item">점수 : <%=m.getHomeTeamScore() %></li>
                                
                            </ul>
                        </div>
                    </td>
                    <td id="vs">vs</td>
                    <td>
                        <div class="card" style="width: 18rem;">
                            <h3 class="BettingResultInsertStrapline">원정팀</h3>
                            <img class="card-img-top" src="<%=request.getContextPath() %>/images/<%=Away.getImage_file() %>.png">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">리그 : <%=m.getTypeOfSports() %></li>
                                <li class="list-group-item">팀명 : <%=m.getAwayTeamName() %>
                                                            <br />승 : <%=Away.getWin() %>
                                                            <br />패 : <%=Away.getLose() %>
                                                            <br />무승부 : <%=Away.getDraw() %></li>
                                <li class="list-group-item">점수 : <%=m.getAwayTeamScore() %></li>
                            </ul>
                        </div>
                    </td>
                </tr>
            </table>
</div>
</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>