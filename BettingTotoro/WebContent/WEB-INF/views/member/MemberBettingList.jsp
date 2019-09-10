<%@page import="betting.model.vo.BettingOrder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/match.css" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<style>



.memberDiv {
	position: relative;
	left:-20px;
	margin-top:20px;
	margin-bottom: 20px;
}

.memberDiv table {
	width: 800px;
	border-collapse: collapse;
	box-shadow: 0 0 20px rgba(0,0,0,0.1);
}

th,
td {
	padding: 15px;
	background-color: rgba(255,255,255,0.2);
	color: black;
}

th {
	text-align: center;
	background-color: #55608f;
}

tbody td{
	text-align: center;
}

#pageBar{
	 margin-top: 20px;
   text-align: center;
}


</style>

<%@ page import="java.util.*,match.model.vo.*" %>
<%
	ArrayList<BettingOrder> BOlist = (ArrayList<BettingOrder>)request.getAttribute("BOlist");
    String memberId2 = (String)request.getAttribute("memberId");
    String pageBar = (String)request.getAttribute("pageBar");
%>

<section>
 <div class="Bettingmiddle">
 <img src="<%=request.getContextPath() %>/images/야구장배경3.jpg" id="backgroundimg" alt="" />
 	<div id="MatchListTable1">
	<%if(BOlist.size()==0) {%>
		
		<h2 style="color:black"><%=memberId2 %>님의 배팅내역이 존재하지 않습니다.</h3>
    <%}else{%>
    	
    	
    	<h2 style="color:black"><%=memberId2 %>님의 배팅내역</h3>
    	<div class="memberDiv">
    		<table>
    		<thead>
    			<tr>
    				<th>배팅번호</th>
    				<th>주문번호</th>
    				<th>배팅시간</th>
    				<th>배팅결과</th>
    			</tr>
    		</thead>
    		<tbody>
    	<%for(int i=0;i<BOlist.size();i++ ){%>
    	<tr>
    	<td><%=BOlist.get(i).getBettingNo() %></td>
    	<td><%=BOlist.get(i).getBettingOrderNo() %></td>
    	<td><%=BOlist.get(i).getBettingDate() %></td>
    	  <%if(BOlist.get(i).getPass()==1){ %>
    	  		<td>￦<%=BOlist.get(i).getBettingMoney() %> 배팅중 </td>
    	  <%}else if(BOlist.get(i).getPass()==2){%>
    	  		<td style="color:blue">+<%=BOlist.get(i).getBettingMoney() %></td>
    	   <%}else{%>
    	   		<td style="color:red"> -<%=BOlist.get(i).getBettingMoney() %></td>
    	   <%}%>
    	   </tr>
    		<%}%> <!-- for of end -->
    		</tbody>
    		</table>
    	</div>	
    <%} %> <!-- 배팅내역 존재할때 else of end -->
    
  	<div id="pageBar">
		<%=pageBar %>
	</div>
	</div>
	</div>
  	

</section>
        
<%@ include file="/WEB-INF/views/common/footer.jsp" %>