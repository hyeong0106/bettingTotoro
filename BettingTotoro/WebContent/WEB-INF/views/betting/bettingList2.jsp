<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/betting.css" />
<link href="/SRC2/digitalclock/style.css" rel="stylesheet" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="java.util.*,betting.model.vo.BettingList,java.text.SimpleDateFormat" %>
<%
	List<BettingList> BettingList = (List<BettingList>)request.getAttribute("BettingList");
	List<Integer> bettingListNo = (List<Integer>)request.getAttribute("bettingListNo");
	ArrayList<Integer> timeList = new ArrayList<Integer>();
	ArrayList<String> timeStringList = new ArrayList<String>();
	String OK = (String)request.getAttribute("OK");
	SimpleDateFormat format1 = new SimpleDateFormat ("yyyyMMdd HH:mm:ss");
	Date time = new Date();
	String time1 = format1.format(time);
	int d1;
	int c1;
	String d = "";
	String c = "";
	int max=0;
	int ctime=0;
	if(!BettingList.isEmpty()){
		if(max<BettingList.get(0).getBettingNo()){
			max=BettingList.get(0).getBettingNo();
		}
		for(int i=0;i<BettingList.size();i++){
			if(OK.equals("OK")){
				d += BettingList.get(i).getBettingEndTime().substring(4,8)+BettingList.get(i).getBettingEndTime().substring(9,11)+
						BettingList.get(i).getBettingEndTime().substring(12,14);
			}
			else{
				d += BettingList.get(i).getBettingEndTime().substring(4,8)+BettingList.get(i).getBettingEndTime().substring(9,11)+
						BettingList.get(i).getBettingEndTime().substring(12,14);
			}
			timeList.add(Integer.parseInt(d));
			d="";
			if(OK.equals("OK")){
				d += BettingList.get(i).getBettingEndTime().substring(4,6)+"월 "+BettingList.get(i).getBettingEndTime().substring(6,8)+"일 "+
			         BettingList.get(i).getBettingEndTime().substring(9,11)+"시 "+BettingList.get(i).getBettingEndTime().substring(12,14)+"분";
			}
			else{
				d += BettingList.get(i).getBettingEndTime().substring(4,6)+"월 "+BettingList.get(i).getBettingEndTime().substring(6,8)+"일 "+
				         BettingList.get(i).getBettingEndTime().substring(9,11)+"시 "+BettingList.get(i).getBettingEndTime().substring(12,14)+"분";
			}
			timeStringList.add(d);
			d="";
			c="";
			if(max<BettingList.get(i).getBettingNo()){
				max=BettingList.get(i).getBettingNo();
			}
	}
	
	
	c += time1.substring(4,8)+time1.substring(9,11)+time1.substring(12,14);
	ctime = Integer.parseInt(c);
	max+=1;
	}
	Date currentTime = new Date();
	
	String date="";
	String Hours = "";
	String minutes = "";
	if(currentTime.getDate()<10){
		date="0"+currentTime.getDate();
	}else{
		date=""+currentTime.getDate();
	}
	
	if(currentTime.getHours()<10){
		Hours="0"+currentTime.getHours();
	}else{
		Hours=""+currentTime.getHours();
	}
	if(currentTime.getMinutes()<10){
		minutes="0"+currentTime.getMinutes();
	}else{
		minutes=""+currentTime.getMinutes();
	}
	
	String pageBar = (String)request.getAttribute("pageBar");
%>
<style>
#container{
    height: 1390px;
}
section{
    height: 950px;
}
</style>
<body>
	<section>
    	<!--관리자만 class로 묶어서 display:none하자-->
    	<%if(memberLoggedIn!=null&&memberLoggedIn.getMemberId().equals("admin")) {%>
        <h3 class="BettingListStrapline">관리자 배팅 등록</h3>
       	<div id="BettingListTable2">
     		<form action="<%=request.getContextPath()%>/betting/bettingadd" method="post" onsubmit="return checkteam();">
     		<input type="text" name="h_member_no" id="h_member_no" value="<%=memberLoggedIn.getMemberNo()%>" hidden/>
	       	<table id="Betting-table">
	       		<tr>
	       			<td colspan="2">
	       				<label for="gamecount" class="fixed_table2">배팅타입 : </label>
	           			<select name="gamecount" id="gamecount" class="fixed_table2" onchange="changeGameCount();">
			            	<option value="single" selected>싱글</option>
			               	<option value="double">더블</option>
			               	<option value="triple" >트리플</option>
			               	<!--  <option value="score-hit">점수맞추기</option>-->
	           			</select>
	           		</td>
	           		<td colspan="2">
	           			<input type="number" readonly value="<%=max%>" hidden name="bettingno"/>
		           		<label for="admin_competition" class="fixed_table2">종목 : </label>
		           		<select name="admin_cometition" id="admin_competition" class="fixed_table2" onchange="choiceSports();">
			            	<option value="K리그" selected>K리그</option>
			               	<option value="야구">국내야구(KBO)</option>
			               	<option value="일본야구(센트럴)">일본야구(센트럴)</option>
			               	<option value="일본야구(퍼시픽)">일본야구(퍼시픽)</option>
			               	<option value="내셔널">MLB(내셔널리그)</option>
			               	<option value="아메리칸">MLB(아메리칸리그)</option>
			            </select>
	       			</td>
	       		</tr>
				<tr>
					<td class="moveRight">
						<label for="team1" class="table2_input1">홈팀:</label>
			          	<select name="team1" id="team1" class="table2_input1 table2_changeteam" onchange="changeTeam()" style="width: 80px;">
				          	<option  value='team1'>원정팀 선택</option>
				          	<option value="전북">전북</option>
					        <option value="울산">울산</option>
					        <option value="서울">서울</option>
					        <option value="대구">대구</option>
					        <option value="상주">상주</option>
					        <option value="포항">포항</option>
					        <option value="강원">강원</option>
					        <option value="수원">수원</option>
					        <option value="성남">성남</option>
					        <option value="경남">경남</option>
					        <option value="제주">제주</option>
					        <option value="인천">인천</option>
				        </select>
					</td>
					<td class="moveRight">
						<label for="team2" class="table2_input1">원정팀:</label>
			          	<select name="team2" id="team2" class="table2_input1 table2_changeteam" onchange="changeTeam()" style="width: 80px;">
				           	<option  value='team2'>원정팀 선택</option>
					        <option value="전북">전북</option>
					        <option value="울산">울산</option>
					        <option value="서울">서울</option>
					        <option value="대구">대구</option>
					        <option value="상주">상주</option>
					        <option value="포항">포항</option>
					        <option value="강원">강원</option>
					        <option value="수원">수원</option>
					        <option value="성남">성남</option>
					        <option value="경남">경남</option>
					        <option value="제주">제주</option>
					        <option value="인천">인천</option>
					    </select>
					</td>
					<td class="moveRight" colspan="2">
						<label for="game1_time" class="table2_input1">경기시간:</label>
	          			<input type="datetime-local" class="table2_input1" id="time1" name="game1_time" value="<%=currentTime.getYear()-100+2000%>-0<%=currentTime.getMonth()+1%>-<%=date%>T<%=Hours%>:<%=minutes%>">
					</td>
				</tr>
				<tr>
					<td>
						<label for="team3" class="table2_input2">홈팀:</label>
				        <select name="team3" id="team3" class="table2_input2 table2_changeteam" onchange="changeTeam()" style="width: 80px;">
				        	<option  value='team3'>원정팀 선택</option>
					        <option value="전북">전북</option>
					        <option value="울산">울산</option>
					        <option value="서울">서울</option>
					        <option value="대구">대구</option>
					        <option value="상주">상주</option>
					        <option value="포항">포항</option>
					        <option value="강원">강원</option>
					        <option value="수원">수원</option>
					        <option value="성남">성남</option>
					        <option value="경남">경남</option>
					        <option value="제주">제주</option>
					        <option value="인천">인천</option>
					   </select>
					</td>
					<td>
						<label for="team4" class="table2_input2">원정팀:</label>
				        <select name="team4" id="team4" class="table2_input2 table2_changeteam" onchange="changeTeam()" style="width: 80px;">
							<option  value='team4'>원정팀 선택</option>
							<option value="전북">전북</option>
							<option value="울산">울산</option>
							<option value="서울">서울</option>
							<option value="대구">대구</option>
							<option value="상주">상주</option>
							<option value="포항">포항</option>
							<option value="강원">강원</option>
							<option value="수원">수원</option>
							<option value="성남">성남</option>
							<option value="경남">경남</option>
							<option value="제주">제주</option>
							<option value="인천">인천</option>
				    	</select>
					</td>
					<td colspan="2">
						<label for="game2_time" class="table2_input2">경기시간:</label>
	          			<input type="datetime-local" class="table2_input2" id="time2" name="game2_time" value="<%=currentTime.getYear()-100+2000%>-0<%=currentTime.getMonth()+1%>-<%=date%>T<%=Hours%>:<%=minutes%>" >
					</td>
				</tr>
				<tr>
					<td>
						<label for="team5" class="table2_input3">홈팀:</label>
						<select name="team5" id="team5" class="table2_input3 table2_changeteam" onchange="changeTeam()" style="width: 80px;">
							<option  value='team5'>원정팀 선택</option>
							<option value="전북">전북</option>
							<option value="울산">울산</option>
							<option value="서울">서울</option>
							<option value="대구">대구</option>
							<option value="상주">상주</option>
							<option value="포항">포항</option>
							<option value="강원">강원</option>
							<option value="수원">수원</option>
							<option value="성남">성남</option>
							<option value="경남">경남</option>
							<option value="제주">제주</option>
							<option value="인천">인천</option>
	         			</select>
					</td>
					<td>
						<label for="team6" class="table2_input3">원정팀:</label>
						<select name="team6" id="team6" class="table2_input3 table2_changeteam" onchange="changeTeam()" style="width: 80px;">
							<option  value='team6'>원정팀 선택</option>
							<option value="전북">전북</option>
							<option value="울산">울산</option>
							<option value="서울">서울</option>
							<option value="대구">대구</option>
							<option value="상주">상주</option>
							<option value="포항">포항</option>
							<option value="강원">강원</option>
							<option value="수원">수원</option>
							<option value="성남">성남</option>
							<option value="경남">경남</option>
							<option value="제주">제주</option>
							<option value="인천">인천</option>
	          			</select>
					</td>
					<td colspan="2">
						 <label for="game3_time" class="table2_input3">경기시간:</label>
	          			<input type="datetime-local" class="table2_input3" id="time3" name="game3_time" value="<%=currentTime.getYear()-100+2000%>-0<%=currentTime.getMonth()+1%>-<%=date%>T<%=Hours%>:<%=minutes%>">
					</td>
				</tr>
				<tr >
					<td colspan="4" id="button-container">
						<button type="submit" value="Submit" class="Betting-add btn btn-outline-danger" onclick="toEnabled();">등록</button>
						<button type="reset" Value="Reset" class="Betting-add btn btn-outline-secondary">초기화</button>
					</td>
				</tr>
				
	       	</table>
		</form>
	</div>
	<% }%>
	
	
	<%if(memberLoggedIn!=null &&memberLoggedIn.getMemberId().equals("admin") ){ %>
		<h3 class="BettingListStrapline">배팅</h3> 
		  <img src="<%=request.getContextPath()%>/images/야구캐릭터.png" id="character"/>
    <div id="BettingListTable3">
    	<table class="table" id="BttinglistTable-Table">
        	<thead class="thead-dark">
            	<tr>
				    <th>배팅번호</th>
				    <th>경기종목</th>
				    <th>배팅타입</th>
				    <th>배팅금액</th>
				    <th>배팅마감시간</th>
				   <th>상태</th>
				   <th>대상경기보기</th>
				</tr>
            </thead>
          	<tbody>
			<%if(!BettingList.isEmpty()){ %>
			<%for(int i=0;i<BettingList.size();i++){ %>
 			<%
				if(timeList.get(i)-ctime<=0&&BettingList.get(i).getHasResult()==0){
			%>
			<form action="<%=request.getContextPath()%>/betting/BettingResultInsert?bettingNo=<%=BettingList.get(i).getBettingNo()%>">
				<tr class="table3_list" >
					<input type="hidden" name="BettingNo" id="BettingNo" value="<%=BettingList.get(i).getBettingNo()%>" style="width:'30px' height" />
		                <td><%=BettingList.get(i).getBettingNo() %></td>
		                <td><img src="<%=request.getContextPath() %>/images/<%=BettingList.get(i).getTypeOfSports()%>.png" alt="" style="width: 30px; height: 30px;"></td>
		                <td><%=BettingList.get(i).getBettingType() %></td>
		                <td><%=BettingList.get(i).getTotalBettingMoney() %></td>
		                <td><%=timeStringList.get(i)%></td>
		                <td>결과 등록 대기중</td>
		                <td><input type="submit" class="btn btn-outline-danger" value="결과등록" /></td>
		          	</tr>
			</form>
			<%} else if(timeList.get(i)-ctime>0){
				if(BettingList.get(i).getBettingType().equals("score-hit")){ %>
					<form action="<%=request.getContextPath()%>/betting/BettingView2?BettingNo=<%=BettingList.get(i).getBettingNo()%>">
			<%}
			else{
			%>
				<form action="<%=request.getContextPath()%>/betting/BettingView1?BettingNo=<%=BettingList.get(i).getBettingNo()%>">	
			<%}%>	   	
					<tr class="table3_list" >
						<input type="text" name="BettingNo" id="BettingNo" value="<%=BettingList.get(i).getBettingNo()%>" hidden/>
						<input type="text" name="member_Id" id="member_Id" value="<%=memberLoggedIn.getMemberId()%>" hidden/>
						<input type="number" name="h_member_no" id="h_member_no" value="<%=memberLoggedIn.getMemberNo()%>" hidden/>
							<td><%=BettingList.get(i).getBettingNo() %></td>
							<td><img src="<%=request.getContextPath() %>/images/<%=BettingList.get(i).getTypeOfSports()%>.png" alt="야구" style="width: 30px; height: 30px;"></td>
							<td><%=BettingList.get(i).getBettingType() %></td>
							<td><%=BettingList.get(i).getTotalBettingMoney() %></td>
							<td><%=timeStringList.get(i)%></td>
							<td>배팅가능</td>
							<%if(bettingListNo.contains(BettingList.get(i).getBettingNo())){%>
								<td>
								<input type="button" class="btn btn-outline-success" value="배팅참여중" onclick="AlreadyBetting();"/>
								</td>
							<%}else{%>
							<td>
								<input type="submit" class="btn btn-outline-success" value="배팅하기" />
							</td>
							<%}%>
                   </tr>
				</form>   
				<%}else if(timeList.get(i)-ctime<=0&&BettingList.get(i).getHasResult()==1){%>
						<form action="<%=request.getContextPath()%>/betting/matchResultView">
							<tr class="table3_list" >
								<input type="number" value="<%=BettingList.get(i).getBettingNo() %>" id="bettingNo" name="bettingNo" hidden/>
								<td><%=BettingList.get(i).getBettingNo() %></td>
								<td><img src="<%=request.getContextPath() %>/images/<%=BettingList.get(i).getTypeOfSports()%>.png" alt="야구" style="width: 30px; height: 30px;"></td>
								<td><%=BettingList.get(i).getBettingType() %></td>
								<td><%=BettingList.get(i).getTotalBettingMoney() %></td>
								<td><%=timeStringList.get(i)%></td>
								<td>배팅마감</td>
								<td><input type="submit" class="btn btn-outline-success" value="경기결과보기" /></td>
							</tr>
						</form>
				<%}%>
			<% }%>
			<%}else{ %>
				<tr>
					<td> 등록된 배팅이 없습니다.
				</tr>
			<%} %>
			</tbody>
          </table>
          <div class="pageBar">
          	<%=pageBar %>
          </div>
		</div>
	<%}else{%>
		<h3 class="BettingListStrapline">배팅</h3> 
		<img src="<%=request.getContextPath()%>/images/야구캐릭터.png" id="character"/>
    <div id="BettingListTable3">
    	<table class="table" id="BttinglistTable-Table">
        	<thead class="thead-dark">
            	<tr>
				    <th>배팅번호</th>
				    <th>경기종목</th>
				    <th>배팅타입</th>
				    <th>배팅금액</th>
				    <th>배팅마감시간</th>
				   <th>상태</th>
				   <th>대상경기보기</th>
				</tr>
            </thead>
          	<tbody>
			<%if(!BettingList.isEmpty()){ %>
			<%for(int i=0;i<BettingList.size();i++){ %>
 			<%
				if(timeList.get(i)-ctime<=0&&BettingList.get(i).getHasResult()==0){
			%>
				<tr class="table3_list" >
					<input type="hidden" name="BettingNo" id="BettingNo" value="<%=BettingList.get(i).getBettingNo()%>" style="width:'30px' height" />
		                <td><%=BettingList.get(i).getBettingNo() %></td>
		                <td><img src="<%=request.getContextPath() %>/images/<%=BettingList.get(i).getTypeOfSports()%>.png" alt="" style="width: 30px; height: 30px;"></td>
		                <td><%=BettingList.get(i).getBettingType() %></td>
		                <td><%=BettingList.get(i).getTotalBettingMoney() %></td>
		                <td><%=timeStringList.get(i)%></td>
		                <td>결과 등록 대기중</td>
		                <td><input type="button" onclick="Warming();" class="btn btn-outline-danger" value="결과등록대기중" /></td>
		          	</tr>
			<%} else if(timeList.get(i)-ctime>0){
				if(BettingList.get(i).getBettingType().equals("score-hit")){ %>
					<form action="<%=request.getContextPath()%>/betting/BettingView2?BettingNo=<%=BettingList.get(i).getBettingNo()%>">
			<%}
			else{
			%>
				<form action="<%=request.getContextPath()%>/betting/BettingView1?BettingNo=<%=BettingList.get(i).getBettingNo()%>">	
			<%}%>	   	
					<tr class="table3_list" >
						<input type="text" name="BettingNo" id="BettingNo" value="<%=BettingList.get(i).getBettingNo()%>" hidden/>
						<% if(memberLoggedIn!=null){%>
						<input type="text" name="member_Id" id="member_Id" value="<%=memberLoggedIn.getMemberId()%>" hidden/>
						<input type="number" name="h_member_no" id="h_member_no" value="<%=memberLoggedIn.getMemberNo()%>" hidden/>
						<%}%>
							<td><%=BettingList.get(i).getBettingNo() %></td>
							<td><img src="<%=request.getContextPath() %>/images/<%=BettingList.get(i).getTypeOfSports()%>.png" alt="야구" style="width: 30px; height: 30px;"></td>
							<td><%=BettingList.get(i).getBettingType() %></td>
							<td><%=BettingList.get(i).getTotalBettingMoney() %></td>
							<td><%=timeStringList.get(i)%></td>
							<td>배팅가능</td>
							<td>
							<%if(memberLoggedIn!=null){%>
									<%if(bettingListNo.contains(BettingList.get(i).getBettingNo())){%>	
									<input type="button" class="btn btn-outline-success" value="배팅참여중" onclick="AlreadyBetting();"/>
									<%}else{%>
									<input type="submit" class="btn btn-outline-success" value="배팅하기" />
									<%}%>
								<%}else{%>
								<input type="button" class="btn btn-outline-success" value="로그인필요" onclick="logincheck();"/>
								<%}%>
							</td>
                   </tr>
				</form>   
				<%}else if(timeList.get(i)-ctime<=0&&BettingList.get(i).getHasResult()==1){%>
						<form action="<%=request.getContextPath()%>/betting/matchResultView">
							<tr class="table3_list" >
								<input type="number" value="<%=BettingList.get(i).getBettingNo() %>" id="bettingNo" name="bettingNo" hidden/>
								<td><%=BettingList.get(i).getBettingNo() %></td>
								<td><img src="<%=request.getContextPath() %>/images/<%=BettingList.get(i).getTypeOfSports()%>.png" alt="야구" style="width: 30px; height: 30px;"></td>
								<td><%=BettingList.get(i).getBettingType() %></td>
								<td><%=BettingList.get(i).getTotalBettingMoney() %></td>
								<td><%=timeStringList.get(i)%></td>
								<td>배팅마감</td>
								<td><input type="submit" class="btn btn-outline-success" value="경기결과보기" /></td>
							</tr>
						</form>
				<%}%>
			<% }%>
			<%}else{ %>
				<tr>
					<td> 등록된 배팅이 없습니다.
				</tr>
			<%} %>
			</tbody>
          </table>
          <div class="pageBar">
          	<%=pageBar %>
          </div>
          
		</div>
	<%}%>
   
	</section>
<script>
function gotomatch(){
	location.href 
	= "<%=request.getContextPath()%>/betting/matchView?bettingNo=";
}

function goto2(){
	location.href 
	= "<%=request.getContextPath()%>/betting/bettingView2";
}
function changeGameCount(){
    var gamecount = $("#gamecount").val();
    
    console.log(gamecount);
    if(gamecount =="single"){
        $(".table2_input2").css('display','none');
        $(".table2_input3").css('display','none');
    }
    else if(gamecount =="score-hit"){
        $(".table2_input2").css('display','none');
        $(".table2_input3").css('display','none');
    }
    else if(gamecount =="double"){
        $(".table2_input2").css('display','inline-block');
        $(".table2_input3").css('display','none');
    }
    else if(gamecount =="triple"){
        $(".table2_input2").css('display','inline-block');
        $(".table2_input3").css('display','inline-block');
    }
    
}
function logincheck(){
	alert("로그인이 필요합니다!");
}
function choiceSports(){
	
	$.ajax({
		url:"<%=request.getContextPath()%>/betting/csv",
		type: "get",
		data: { type : $('#admin_competition').val()},
		success: function(data){
			var teamArr = data.split("§");
			
			
			console.log(teamArr);
			$("#team1").empty();
			$("#team2").empty();
			$("#team3").empty();
			$("#team4").empty();
			$("#team5").empty();
			$("#team6").empty();
			
			$("#team1").append("<option  value='team1'>홈팀 선택</option>");
			$("#team2").append("<option  value='team2'>원정팀 선택</option>");
			$("#team3").append("<option  value='team3'>홈팀 선택</option>");
			$("#team4").append("<option  value='team4'>원정팀 선택</option>");
			$("#team5").append("<option  value='team5'>홈팀 선택</option>");
			$("#team6").append("<option  value='team6'>원정팀 선택</option>");
			
			for(var i=0; i<teamArr.length; i++){
				var team = teamArr[i].split(",");
				var html = "<option value='"+team[0]+"'>"+team[0]+"</option>";
				$("#team1").append(html);
				$("#team2").append(html);
				$("#team3").append(html);
				$("#team4").append(html);
				$("#team5").append(html);
				$("#team6").append(html);
			}
		}
	});
		
}

function changeType(){
	
	$.ajax({
		url:"<%=request.getContextPath()%>/betting/csv",
		type: "get",
		data: { type : $('#user-competition').val()},
		success: function(data){
			var teamArr = data.split("§");
			
			
			console.log(teamArr);
			console.log(team[0]);
			$("#team").empty();
			for(var i=0; i<teamArr.length; i++){
				var team = teamArr[i].split(",");
				var html = "<option value='"+team[0]+"'>"+team[0]+"</option>";

				$("#team").append(html);
			}
			
		}
	});
}
function gomatch(){
	location.href 
	= "<%=request.getContextPath()%>/betting/matchView?bettingNo=";
}
function changeTeam(){
	$(".table2_changeteam option").prop('disabled',false);
	$(".table2_changeteam option:eq(0)").prop('disabled',true);	
	var changeteam1 =$("#team1 option:selected").val();
	var changeteam2 =$("#team2 option:selected").val();
	var changeteam3 =$("#team3 option:selected").val();
	var changeteam4 =$("#team4 option:selected").val();
	var changeteam5 =$("#team5 option:selected").val();
	var changeteam6 =$("#team6 option:selected").val();
	console.log(changeteam1);
	$(".table2_changeteam option[value='"+changeteam1+"']").prop('disabled',true);
	$(".table2_changeteam option[value='"+changeteam2+"']").prop('disabled',true);
	$(".table2_changeteam option[value='"+changeteam3+"']").prop('disabled',true);
	$(".table2_changeteam option[value='"+changeteam4+"']").prop('disabled',true);
	$(".table2_changeteam option[value='"+changeteam5+"']").prop('disabled',true);
	$(".table2_changeteam option[value='"+changeteam6+"']").prop('disabled',true);
}

function changeTeam1(){
	var changeteam1 =$(".table2_changeteam option:selected");
	console.log(changeteam1);
	changeteam1.prop('disabled',true);	
}


function toEnabled() {

	$(".table2_changeteam option").attr("disabled", false);

}

function AlreadyBetting(){
	alert("이미 배팅에 참여중입니다.");
}
function Warming(){
	alert("결과 등록 대기중입니다. 잠시후 시도해주세요");
}

function checkteam(){
	var changeteam1 =$("#team1 option:selected").val();
	var changeteam2 =$("#team2 option:selected").val();
	var changeteam3 =$("#team3 option:selected").val();
	var changeteam4 =$("#team4 option:selected").val();
	var changeteam5 =$("#team5 option:selected").val();
	var changeteam6 =$("#team6 option:selected").val();	
	
	if($("#gamecount").val() =="single" || $("#gamecount").val() =="score-hit" ){
			if($("#team1 option:selected").val()=="team1" || $("#team2 option:selected").val()=="team2"){
			alert('팀을 선택해주세요');
			$(".table2_changeteam option:eq(0)").prop('disabled',true);
			$(".table2_changeteam option[value='"+changeteam1+"']").prop('disabled',true);
			$(".table2_changeteam option[value='"+changeteam2+"']").prop('disabled',true);
			$(".table2_changeteam option:eq(0)").prop('disabled',true);
			return false;
		}
	}
	else if($("#gamecount").val() =="double"){
			if($("#team1 option:selected").val()=="team1" || $("#team2 option:selected").val()=="team2" || $("#team3 option:selected").val()=="team3" ||
			$("#team4 option:selected").val()=="team4"){
			alert('팀을 선택해주세요');
			$(".table2_changeteam option:eq(0)").prop('disabled',true);
			$(".table2_changeteam option[value='"+changeteam1+"']").prop('disabled',true);
			$(".table2_changeteam option[value='"+changeteam2+"']").prop('disabled',true);
			$(".table2_changeteam option[value='"+changeteam3+"']").prop('disabled',true);
			$(".table2_changeteam option[value='"+changeteam4+"']").prop('disabled',true);
			$(".table2_changeteam option:eq(0)").prop('disabled',true);
			return false;
		}
	}
	else if($("#gamecount").val() =="triple"){
			if($("#team1 option:selected").val()=="team1" || $("#team2 option:selected").val()=="team2" || $("#team3 option:selected").val()=="team3" ||
			$("#team4 option:selected").val()=="team4" || $("#team5 option:selected").val()=="team5" ||$("#team6 option:selected").val()=="team6"){
			alert('팀을 선택해주세요');
			$(".table2_changeteam option:eq(0)").prop('disabled',true);
			$(".table2_changeteam option[value='"+changeteam1+"']").prop('disabled',true);
			$(".table2_changeteam option[value='"+changeteam2+"']").prop('disabled',true);
			$(".table2_changeteam option[value='"+changeteam3+"']").prop('disabled',true);
			$(".table2_changeteam option[value='"+changeteam4+"']").prop('disabled',true);
			$(".table2_changeteam option[value='"+changeteam5+"']").prop('disabled',true);
			$(".table2_changeteam option[value='"+changeteam6+"']").prop('disabled',true);
			$(".table2_changeteam option:eq(0)").prop('disabled',true);
			return false;
		}
	}
}


$('#time1').change(function(){
    var time1val = $("#time1").val();
    $('#time2').val(time1val);
    $('#time3').val(time1val);
});    


$('#time2').change(function(){
    var time2val = $("#time2").val();
    $('#time1').val(time2val);
    $('#time3').val(time2val);

});    


$('#time3').change(function(){
    var time3val = $("#time3").val();
    $('#time1').val(time3val);
    $('#time2').val(time3val);
});

</script>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>