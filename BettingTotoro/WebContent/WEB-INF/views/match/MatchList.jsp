<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/match.css" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="java.util.*,match.model.vo.*" %>
<%
	ArrayList<Match> MatchList = (ArrayList<Match>)request.getAttribute("MatchList");
	String pageBar = (String)request.getAttribute("pageBar");
	int cPage = (Integer)request.getAttribute("cPage");
	
	int numPerPage = (Integer)request.getAttribute("numPerPage");
%>
<script>
	$(function(){
		$("#searchType").change(function(){
			var type = $(this).val();
			//모두안보임처리
			$("#search-teamName, #search-sportsType, #search-bettingAll").hide();
			//한줄표현을 위해 show메소드가 아닌 css메소드사용
			//show메소드는 해당엘레먼트의 기본 display속성값 사용.form -> display:block
			$("#search-"+type).css('display','inline-block');
		});
		
		$("#numPerPage").change(function(){
			$("#numPerPageFrm").submit();
		});
		$(".select").click(function(){
			var MatchNo = this.cells[0].innerText;
			location.href="<%=request.getContextPath()%>/match/matchView?MatchNo="+MatchNo;
			
		});
	});
</script>					
<section>
 <div class="Bettingmiddle">
	 <img src="<%=request.getContextPath() %>/images/야구장배경3.jpg" id="backgroundimg" alt="" />
  <div id="MatchListTable2">
      <table class="table">
          <thead class="thead-dark">
          	<tr>
              <th>경기 번호</th>
              <th>매치업</th>
              <th>경기시간</th>
              <th>리그</th>
          	</tr>
          </thead>
          <tbody>
          <%for(int i=0;i<MatchList.size();i++){ %>
          <tr class="select">
              <td><%=MatchList.get(i).getMatchNo() %></td>
              <td><%=MatchList.get(i).getHomeTeamName() %> vs <%=MatchList.get(i).getAwayTeamName() %></td>
              <td><%=MatchList.get(i).getMatchDate()%></td>
              <td><%=MatchList.get(i).getTypeOfSports()%></td>
          </tr>
          <% }%>
          </tbody>
      </table>
      <div id="pageBar">
		<%=pageBar %>
	  </div> 
  </div>
    </div>
	 
  </div>

</section>
        
<%@ include file="/WEB-INF/views/common/footer.jsp" %>