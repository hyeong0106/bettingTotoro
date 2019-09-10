<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/member/memberMyInfo.css" />
<meta charset="sUTF-8">
<title>답장보내기</title>
<%
	String memberId = (String)request.getAttribute("memberId");
	String sendId = (String)request.getAttribute("sendId");
%>
</head>
<script>
function sendReplyMessage(){
	if(!confirm("이 메세지를 보내시겠습니까?")){
		return;
	}
	var messageContent = $("#messageContent").val().trim();
	if(messageContent.length < 0){
		alert("메세지를 입력해주세요");
		return;
	}
	
	window.opener.document.getElementById("messageContent").value = $("#messageContent")[0].value;
	window.close();
}
</script>
<style>
ul#autocomplete{
	min-width: 171px; 
	border: 1px solid gray;
	display: none;
	padding: 0;
	margin: 0;
}
ul#autocomplete li{
	padding: 0 10px;
	list-style: none;
	cursor: pointer;
}
ul#autocomplete li.sel{
	background: lightseagreen;
	color: white;
}
/*사용자 입력값 하이라이트처리*/
span.srchval{color:red;}
</style>
<body>
	<div id="sendFormDiv">
		<form action="<%=request.getContextPath()%>/message/sendReplyMessage"
	  	   	  id="sendReplyMessageFrm"
	 	   	  method="post">
			<table>
				<thead>
					<tr class="table-warning">
						<th>
							보내는사람 <input type="text" name="sendId" id="sendId" value="<%=memberId %>" readonly />							
						</th>
						<th>
							받는사람 <input type="text" name="receiveId" id="srchName" value="<%=sendId %>" readonly/>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="2"><textarea name="messageContent" id="messageContent" cols="65" rows="10" ></textarea></td>
					</tr>
					<tr>
						<td colspan="2" id="button-container">
							<input type="button" class="btn btn-outline-warning" value="전송" onclick="sendReplyMessage();" />
							<input type="button" class="btn btn-outline-danger" value="취소" onclick="window.close();"/>
						</td>
					</tr>
				</tbody>
				
			</table>
		</form>
	</div>
</body>
</html>