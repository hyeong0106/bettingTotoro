<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/betting.css" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="java.util.*, match.model.vo.* , team.model.vo.*" %>
<%
	int UseDouble =(int)(request.getAttribute("UseDouble"));
	int BettingNo = (int)(request.getAttribute("BettingNo"));
	int h_member_no = (int)(request.getAttribute("h_member_no"));
	ArrayList<Match> MatchList = (ArrayList<Match>)request.getAttribute("MatchList");
	ArrayList<Team> TeamList = (ArrayList<Team>)request.getAttribute("TeamList");
	String bettingType = (String)request.getAttribute("bettingType");
	int gameCount=MatchList.size();
	for(int i=0;i<TeamList.size();i++){
		System.out.println(TeamList.get(i).getTeamName());
	}
	int MaxMoney = 0;
	if(UseDouble==0)
		MaxMoney = 20000;
	else{
		MaxMoney = 40000;
	}

%>
<style>
section {
    width: 85%;
    height: 1087px;
    float: left;
}
</style>
<body>
	<section>
    	<div id="BettingViewTable1">
			<table>
	       		<%for(int i=0;i<gameCount;i++) {%>
	       		<tr>
	       			<td>
	       				<div class="card" style="width: 18rem;">
	       					<h3 class="BettingViewStrapline">홈팀</h3>
	       	  				<img class="card-img-top" src="<%=request.getContextPath() %>/images/<%=TeamList.get(i).getImage_file()%>.png">
			  				<ul class="list-group list-group-flush">
			    				<li class="list-group-item">리그 : KBO</li>
			    				<li class="list-group-item">팀명 : <%=TeamList.get(i).getTeamName()%></li>
			    				<li class="list-group-item">시즌성적 : <%=TeamList.get(i).getWin()%>승 <%=TeamList.get(i).getDraw()%>무 <%=TeamList.get(i).getLose()%>패</li>
			  				</ul>
						</div>
	       			</td>
	       			<td>
	       				<div class="card " style="width: 18rem;">
	       					<h3 class="BettingViewStrapline">원정팀</h3>
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
       	<div id="BettingViewTable2">
       		<form action="<%=request.getContextPath()%>/betting/gobetting" method="post" onsubmit="return checkmoney();">
				<input type="number" name="BettingNo" id="BettingNo" value="<%=BettingNo%>" hidden />
				<input type="number" name="member_No" id="member_No" value="<%=memberLoggedIn.getMemberNo()%>" hidden />
				<input type="text" name="member_Id" id="member_Id" value="<%=memberLoggedIn.getMemberId()%>" hidden />
				<input type="text" name="bettingType" id="bettingType" value="<%=bettingType %>" hidden/>
				<input type="number" name="h_member_no" id="h_member_no" value="<%=h_member_no%>" hidden/>
       			<%for(int i=0;i<gameCount;i++) {%>
       			<div class="input-group">
		  			<div class="input-group-prepend">
		    			<div class="input-group-text bg-success text-white">
		      				<input type="radio" id="CheckMatchResult<%=i+1%>" name="CheckMatchResult<%=i+1%>" value="Homewin"><%=TeamList.get(i).getTeamName()%>승
		     					&nbsp;
		      				<input type="radio" id="CheckMatchResult<%=i+1%>" name="CheckMatchResult<%=i+1%>" value="Draw" checked="checked">무승부
					      		&nbsp;
					      	<input type="radio" id="CheckMatchResult<%=i+1%>" name="CheckMatchResult<%=i+1%>" value="HomeLose"><%=TeamList.get((gameCount)+i).getTeamName()%>승
		    			</div>
		  			</div>
	       		</div>
	       		<%}%>
	       		<%if(UseDouble==0){ %>
		  			<input type="number" class="form-control" id="money" name="money" max="20000" placeholder="최대 20000포인트"  required/>
		  			<% }else{ %>
		  			<input type="number" class="form-control" id="money" name="money" max="40000" placeholder="최대 40000포인트"  required/>
		  		<%}%>
		  		<input type="submit" class="btn btn-outline-success" id="button-addon2" value="배팅하기"/>
			</form>
       	</div>  
	</section>
<style>
.form-control {
    display: block;
    width: 100%;
    height: calc(1.5em + .75rem + 2px);
    padding: .375rem .75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;
    border-radius: .25rem;
    transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}
#button-addon2 {
    margin-left: 62px;
    margin-top: 13px;
}
</style>        
<script>
function checkmoney() {
    if($('#money').val() <=0 || $('#money').val() ><%=MaxMoney%>)
    	{
   alert("배팅금액을 확인하세요.");
   return false;
    	}
    else if($('#money').val() > <%=memberLoggedIn.getPoint()%>){
    	alert("보유 포인트가 적습니다.");
    	 return false;
    }
    else{
    	return true;
    }
}
</script>
        
<%@ include file="/WEB-INF/views/common/footer.jsp" %>