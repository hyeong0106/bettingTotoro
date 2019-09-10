<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/betting.css" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="java.util.*,
                 team.model.vo.*,
                 match.model.vo.*" %>	
<%
	ArrayList<Match> MatchList = (ArrayList<Match>)request.getAttribute("MatchList");
	System.out.println("매치리스트 사이즈 : "+MatchList.size());
	ArrayList<Team> TeamList =(ArrayList<Team>)request.getAttribute("TeamList");
	String bettingType = (String)request.getAttribute("bettingType");
	int gameCount=1;
	if(bettingType.equals("single")||bettingType.equals("scroe-hit")){
		gameCount=1;
	}
	else if(bettingType.equals("double")){
		gameCount=2;
	}
	else if(bettingType.equals("triple")){
		gameCount=3;
	}
	System.out.println(bettingType);
	for(int i=0;i<TeamList.size();i++){
		System.out.println(TeamList.get(i).getTeamName());
	}
%>

<body>
	<section>
    	<div id="BettingResultInsertTable1">
			<table>
        		<%for(int i=0;i<gameCount;i++) {%>
        		<tr>
        			<td>
        				<div class="card" style="width: 18rem;">
        					<h3 class="BettingResultInsertStrapline">홈팀</h3>
        	  				<img class="card-img-top" src="<%=request.getContextPath() %>/images/<%=TeamList.get(i).getImage_file()%>.png">
			  				<ul class="list-group list-group-flush">
			    				<li class="list-group-item">리그 : KBO</li>
			    				<li class="list-group-item">팀명 : <%=TeamList.get(i).getTeamName()%></li>
			    				<li class="list-group-item">시즌성적 : <%=TeamList.get(i).getWin()%>승 <%=TeamList.get(i).getDraw()%>무 <%=TeamList.get(i).getLose()%>패</li>
			  				</ul>
						</div>
        			</td>
        			<td id="vs">vs</td>
        			<td>
        				<div class="card" style="width: 18rem;">
        					<h3 class="BettingResultInsertStrapline">원정팀</h3>
        	  				<img class="card-img-top" src="<%=request.getContextPath() %>/images/<%=TeamList.get((gameCount)+i).getImage_file()%>.png">
			  				<ul class="list-group list-group-flush">
			    				<li class="list-group-item">리그 : KBO</li>
			    				<li class="list-group-item">팀명 : <%=TeamList.get((gameCount)+i).getTeamName()%></li>
			    				<li class="list-group-item">시즌성적 : <%=TeamList.get((gameCount)+i).getWin()%>승 <%=TeamList.get((gameCount)+i).getDraw()%>무 <%=TeamList.get((gameCount)+i).getLose()%>패</li>
			  				</ul>
						</div>
        			</td>
				</tr>
        		<%}%>
        	</table>
        </div>
        <div id="BettingResultInsertTable2">
        	<form action="<%=request.getContextPath()%>/betting/bettingResultEnd" method="post">
        		<table class="table">
        			<thead class="thead-dark">
        				<tr>
        					<th scope="col" colspan="2">ORDER</th>
        				</tr>
        			</thead>
        			<tbody>
            		<%for(int i=0;i<gameCount;i++) {%>
        			<input type="text" id="type_of_sports" name="type_of_sports" value="<%=bettingType%>" hidden />
        			<input type="number" id="match_no<%=i+1%>" name="match_no<%=i+1%>" value="<%=MatchList.get(i).getMatchNo()%>" hidden/>
               		<input type="number" id="betting_no" name="betting_no" value="<%=MatchList.get(i).getBettingNo()%>" hidden />
        				<tr>
        					<td rowspan="2" class="matchNum"><%=i+1%>경기</td>
        					<td>
        						<span><%=TeamList.get(i).getTeamName()%> 점수 :</span> 
                   				<input type="number" name="Homescore<%=i+1%>" id="Homescore<%=i+1%>" min=0 width:2px;/>
        					</td>
        				</tr>
        				<tr>
        					<td>
        						<span><%=TeamList.get((gameCount)+i).getTeamName()%>  점수 :</span>
                   				<input type="number" name="Awayscore<%=i+1%>" id="Awayscore<%=i+1%>" min=0 width:2px;/>
        					</td>
        				</tr>
        			<%} %>
        				<tr>
        					<td colspan="2" id="button-container">
        						<input type="submit" class="btn btn-outline-warning" value="배팅결과등록" />
        					</td>
        				</tr>
        			</tbody>	
        		</table>
        	</form>
        </div>  
	</section>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>