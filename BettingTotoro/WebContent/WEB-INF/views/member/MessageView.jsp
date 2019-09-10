<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/member/memberMyInfo.css" />
<%@ page import="java.util.*" %>
<%@ page import="Message.model.vo.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
   Message message = (Message)request.getAttribute("message");
   String messageType = (String)request.getAttribute("messageType");
   String loginId = (String)request.getAttribute("memberId");
%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.0.js"></script>
<script>
	function deleteMessage(){
		if(!confirm("이 게시물을 정말 삭제 하시겠습니까?")){
			return;
		}
		location.href="<%=request.getContextPath()%>/message/deleteMessage?messageNo=<%=message.getMessageNo()%>&loginId=<%=loginId%>";
	}
	function replyMessage(){
		var popupX = (window.screen.width / 2) - 150;
	       var popupY= (window.screen.height / 2) - 135;
	       
	       //팝업생성
	      var url = "<%=request.getContextPath()%>/message/replyMessagePopup?memberId=<%=memberLoggedIn.getMemberId()%>&sendId=<%=message.getSendId()%>";
	      var title ="쪽지보내기";
	      var specs = "width=610px, height=580px, left="+popupX+", top="+popupY;
	      
	      var popup = open(url,title,specs);//팝업의 최상위 윈도우객체를 리턴함.
	      var interval = window.setInterval(function() {
		        try {
		            if (popup == null || popup.closed) {
		            	window.clearInterval(interval);
		            	
		            	var sendId = "<%=memberLoggedIn.getMemberId()%>";
		            	var receiveId = "<%=message.getSendId()%>";
		            	var messageContent = ($("#messageContent")[0].value);
      					console.dir(sendId);
      					console.dir(receiveId);
      					console.log(messageContent)
      					if(messageContent == undefined){
      						
      					}
      					else{  						
		      				location.href="<%=request.getContextPath()%>/message/sendReplyMessage?sendId="+sendId+"&receiveId="+receiveId+"&messageContent="+messageContent;
      					}
		            
		            	
		            }
		        }
		        catch (e) {
		        }
		    }, 1000);
	}
</script>
 <section>
 	<label id="messageContent" hidden></label>
    <div id="MessageViewDiv">
       <table class="table">
       	<thead class="thead-dark">
       		<tr>
			    <th scope="col"><%=message.getMessageNo() %></th>
			    <%if(messageType.equals("send")){%>
			    	<th scope="col">받는사람[<%=message.getReceiveId() %>]</th>
			    <%}
			    else if(messageType.equals("receive")) {%>
			    	<th scope="col">보낸사람[<%=message.getSendId() %>]</th>
			    <%} %>
			</tr>
       	</thead>
       	<tbody>
       		<tr>
				<td colspan="2">
					<div id="messageTextarea">
						<%=message.getContent()%>
					</div>
				</td>
			</tr>
			<tr>
				<th id="button-container" colspan="2">
					<input type="button" 
						   class="btn btn-outline-success" 
						   value="목록가기" 
						   onclick="location.href='<%=request.getContextPath()%>/member/Message?memberId=<%=memberLoggedIn.getMemberId()%>&type=MyMessage&messageType=<%=messageType%>'"/>
					<%if(messageType.equals("receive")) {%>
					<input type="button" class="btn btn-outline-success" value="답장" onclick="replyMessage();"/>
					<input type="button" class="btn btn-outline-danger" value="삭제" onclick="deleteMessage();"/>
					<%} %>
				</th>
			</tr>
       	</tbody>
       </table>
    </div>   
</section>
<style>
#container{
    height: 1390px;
}
section{
    height: 950px;
}
</style>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>