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
<meta charset="UTF-8">
<title>쪽지보내기</title>
<%
	String sendId = (String)request.getAttribute("sendId");
%>
</head>
<script>
function sendMessage(){
	if(!confirm("이 메세지를 보내시겠습니까?")){
		return;
	}
	
	if($("#srchName").val()==$("#sendId").val()){
		alert("본인에게는 쪽지를 전송할 수 없습니다.");
		$("#srchName").val('');
		return;
	}
	
	window.opener.document.getElementById("srchName").value = $("#srchName")[0].value;
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
		<form action="<%=request.getContextPath()%>/message/sendMessage"
	  	   	  id="sendMessageFrm"
	 	   	  method="post">
			<table>
				<thead>
					<tr class="table-warning">
						<th>
							보내는사람 <input type="text" name="sendId" id="sendId" value="<%=sendId %>" required readonly/>							
						</th>
						<th>
							받는사람 <input type="text" name="srchName" id="srchName" />
							<ul id="autocomplete">
								<li class="sel">입력하세요</li>
							</ul>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="2"><textarea name="messageContent" id="messageContent" cols="65" rows="10" ></textarea></td>
					</tr>
					<tr>
						<td colspan="2" id="button-container">
							<input type="button" class="btn btn-outline-warning" value="전송" onclick="sendMessage();" />
							<input type="button" class="btn btn-outline-danger" value="취소" onclick="window.close();"/>
						</td>
					</tr>
				</tbody>
				
			</table>
		</form>
	</div>
	<script>
	$(function(){
		$("#srchName").keyup(function(e){
			var srchName = $("#srchName").val();
			//사용자입력값이 공백인 경우, ajax요청하지 않는다.
			if(srchName.length == 0)
				return;
			
			var $sel = $(".sel");
			var $li = $("#autocomplete li");
			
			//사용자 입력값이 ArrowUp인 경우
			if(e.key == 'ArrowUp'){
				
				if($sel.length == 0){
					//처리코드 없음.
				}

				else if($sel.is($li.first())){
					$sel.removeClass("sel");
				}
				else{
					$sel.removeClass("sel")
						.prev()
						.addClass("sel");
				}
			}
			//사용자 입력값이 ArrowDown인 경우
			else if(e.key == 'ArrowDown'){
				
				if($sel.length == 0){
					$li.first().addClass("sel");
				}
	
				else if($sel.is($li.last())){
	
				}
				else{
					$sel.removeClass("sel")
						.next()
						.addClass("sel");
				}
			}
			//사용자 입력값이 Enter인 경우
			else if(e.key == 'Enter'){

				$(this).val($sel.text());
				$("#autocomplete").hide()
								  .children()
								  .remove();
			}
			//그외(ajax요청)
			else{
				$.ajax({
					url: "<%=request.getContextPath()%>/message/sendMessagePopupAjax",
					data: "srchName="+srchName,//파라미터직렬화
					success: function(data){
						console.log(data);
						
						//넘어온 csv데이터가, 공백인경우
						if(data.trim().length == 0){
							$("#autocomplete").hide();
						}
						else{
							var nameArr = data.split(",");
							var html = "";
							
							for(var i=0; i<nameArr.length; i++){
								html += "<li>"+nameArr[i].replace(srchName,"<span class='srchval'>"+srchName+"</span>")+"</li>";
							}
							$("#autocomplete").html(html).fadeIn(300);
						
						}
					}
				});//end of ajax
				
			}
			
		});//end of $("#srchName").keyup
		
		$("#autocomplete").on("click","li", function(){
			//클릭이벤트가 일어난 li태그의 text를 input태그에 입력
			$("#srchName").val($(this).text());
			$("#autocomplete").hide()
							  .children()
							  .remove();
		});
		
		$("#autocomplete").on("mouseover","li",function(){
			$(this).siblings().removeClass("sel");
			$(this).addClass("sel");
		});
		
		$("#autocomplete").on("mouseout","li",function(){
			$(this).removeClass("sel");	
		});

	});
	</script>
</body>
</html>