<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/betting.css" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<%@page import="match.model.vo.*, team.model.vo.*, java.util.*" %>
<%
	ArrayList<Match> MatchList = (ArrayList<Match>)request.getAttribute("MatchList");
	ArrayList<Team> TeamList = (ArrayList<Team>)request.getAttribute("TeamList");
	int gameCount=MatchList.size();
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script>
$(function(){
   if("<%=MatchList.size()%>"==1){
      $("section")[0].style.height="520px";
      $("#container")[0].style.height="860px";
   }else if("<%=MatchList.size()%>"==3){
      $("section")[0].style.height="1473px";
      $("#container")[0].style.height="1640px";
   }else{
	   $("section")[0].style.height="941px";
   }
});

</script>
<style>
#Home0{
    position: relative;
    top: 165px;
    z-index: 1;
}
#Away0{
    position: relative;
    z-index: 1;
    top: 165px;
}
#Draw0{
	position: relative;
    z-index: 1;
    top: 193px;
    left: 101px;
    
}
.card {
    position: relative;
    display: -ms-flexbox;
    display: flex;
    -ms-flex-direction: column;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #fff;
    background-clip: border-box;
    border: 1px solid rgba(0,0,0,.125);
    border-radius: .25rem;
    top: -72px;
}
#BackGround{
	width: 870px;
    height: 835px;
    opacity: 0.5;
    position: absolute;
}
#BettingResultInsertTable1 {
    margin-top: 20px;
    margin-left: 148px;
    float: left;
}
#pic1{
    position: absolute;
    top: 460px;
    left: 480px;
    opacity: 0.4;
}
#pic2{
    position: absolute;
    top: 716px;
    left: -5px;
    opacity: 0.4;
}
#pic3{
    position: absolute;
    top: 1149px;
    left: 342px;
    opacity: 0.4;
}
</style>
<section>

 <% if(MatchList.size()==1){%>
<img src="/BettingTotoro/images/illersccor.png" alt="" id="pic1" />
<%}else if(MatchList.size()==2){%>
<img src="/BettingTotoro/images/illersccor.png" alt="" id="pic1" />
<img src="/BettingTotoro/images/basketiler.png" alt="" id="pic2" />
<%}else{%>
<img src="/BettingTotoro/images/illersccor.png" alt="" id="pic1" />
<img src="/BettingTotoro/images/basketiler.png" alt="" id="pic2" />
<img src="/BettingTotoro/images/basketiler2.png" alt="" id="pic3" />
<%}%>
<%for(int i =0; i<MatchList.size();i++){ %>
<div id="BettingResultInsertTable1">
		<%if(MatchList.get(i).getHomeTeamScore()>MatchList.get(i).getAwayTeamScore()) {%>

			<img src="/BettingTotoro/images/win수정.png" alt="" id="Home<%=0%>">
			<img src="/BettingTotoro/images/lose.png" alt="" id="Away<%=0%>">
		<%}else if(MatchList.get(i).getHomeTeamScore()<MatchList.get(i).getAwayTeamScore()){%>
			<img src="/BettingTotoro/images/lose.png" alt="" id="Home<%=0%>">
			<img src="/BettingTotoro/images/win수정.png" alt="" id="Away<%=0%>">
		<%}else{%>
			<img src="/BettingTotoro/images/draw.png" alt="" id="Draw0">
		<%}%>
			<table>
        		<tr>
        			<td>
        				<div class="card" style="width: 18rem;">
        					<h3 class="BettingResultInsertStrapline">홈팀</h3>
        	  				<img class="card-img-top" src="<%=request.getContextPath() %>/images/<%=TeamList.get(i).getImage_file()%>.png">
			  				<ul class="list-group list-group-flush">
			    				<li class="list-group-item">팀명 : <%=TeamList.get(i).getTeamName() %>
			    											<br />승 : <%=TeamList.get(i).getWin() %>
			    											<br />패 : <%=TeamList.get(i).getLose() %>
			    											<br />무승부 : <%=TeamList.get(i).getDraw() %></li>
			    				<li class="list-group-item">점수 : <%=MatchList.get(i).getHomeTeamScore() %></li>
			  				</ul>
						</div>
        			</td>
        			<td id="vs">vs</td>
        			<td>
        				<div class="card" style="width: 18rem;">
        					<h3 class="BettingResultInsertStrapline">원정팀</h3>
        	  				<img class="card-img-top" src="<%=request.getContextPath() %>/images/<%=TeamList.get(i+gameCount).getImage_file()%>.png">
			  				<ul class="list-group list-group-flush">
			    				<li class="list-group-item">팀명 : <%=TeamList.get(i+gameCount).getTeamName() %>
			    											<br />승 :<%=TeamList.get(i+gameCount).getWin() %>
			    											<br />패 : <%=TeamList.get(i+gameCount).getLose() %>
			    											<br />무승부 : <%=TeamList.get(i+gameCount).getDraw() %></li>
			    				<li class="list-group-item">점수 : <%=MatchList.get(i).getAwayTeamScore() %></li>
			  				</ul>
						</div>
        			</td>
				</tr>
        	</table>     	
</div>
<br />
<br />
<br />
<br />
<%} %>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>