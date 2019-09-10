<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.0.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/member/memberMyInfo.css" />
<style>
#MyInfoDiv #title{
	margin-top: 50px;
	font-size: 35px;
	text-align: center;
}
#MyInfoDiv #reasonTable{
	width: 790px;
	height: 650px;
}
#MyInfoDiv .left{
	width: 230px;
	text-align: right;
	font-size: 32px;
	color: #d39e00;
	
}
#MyInfoDiv .right{
	text-align: center;
}
#MyInfoDiv .right *{
	width: 300px;
}
#reasonTable #etcContents{
	background-color: lightgray;
}
#reasonTable #goodBye{
	text-align: center;
	font-size: 32px;
	color: black;
}
#reasonTable{
	text-align: center !important;
}
#reasonTable input{
	width: 100px;
	height: 40px;
	font-size: 18px;
}
</style>
<script>
$(function(){
	$("#reason").change(function(){
		 var value = $(this).val();
			if(value==13){
				$("#etcContents").attr("readonly", false);
				$("#etcContents").css({"background-color": "white"});
				
			}
			else{
				$("#etcContents").attr("readonly", true);
				$("#etcContents").css({"background-color": "lightgray"});
				$("#etcContents")[0].value="";
			}
		});
});
function delBtnClick(){
	var value = $("#reason").val();
	if($("#reason")[0][value].innerHTML!="기타"){
		$("#content").val($("#reason")[0][value].innerHTML) 
	}else{
		$("#content").val($("#etcContents")[0].value)
	}
	if($("#content").val()==null || $("#content").val()=="" || value==0){
		alert("내용을 입력해주세요");
		return false;
	}
	return true;
};
</script>
 <section>
   <div id="MyInfoDiv">
    	<!-- 내정보보기  -->
    		<p id="title"><%=memberLoggedIn.getMemberName()%>님 정말 탈퇴하시겠습니까?</p>
    		<form onsubmit="return delBtnClick();" action="<%=request.getContextPath()%>/member/memberDeleteEnd?memberId=<%=memberLoggedIn.getMemberId()%>"
    		method="post">
    			<table id="reasonTable">
    				<tr>
    				<td class="left">Why?</td>
	    				<td  class="right">
	    				<input type="text" id="content" name="content" hidden/>
	    					<select name='reason' id="reason">
							  <option value="0" selected>-- 선택 --</option>
							  <option value="1">재가입</option>
							  <option value="2">학업/업무 전념</option>
							  <option value="3">단조로운 사이트 구성</option>
							  <option value="4">사이트 성격 적응 불가</option>
							  <option value="5">너무 잦은 변화로 인한 실망</option>
							  <option value="6">변화가 없는 싸이트</option>
							  <option value="7">회원간의 트러블</option>
							  <option value="8">군입대영창</option>
							  <option value="9">한가람아파트 재개발</option>
							  <option value="10">지구온난화</option>
							  <option value="11">자연으로의 회귀</option>
							  <option value="12">외계인의 침입</option>
							  <option value="13">기타</option>
							</select>
	    				</td>
    				</tr>
    				<tr>
    					<td class="left">기타 이유</td>
	    				<td  class="right">
	    				<textarea id="etcContents" cols="30" rows="10" readonly></textarea>
	    				</td>
    				</tr>
    				<tr>
    					<td id="goodBye" colspan="2">보다 더 나은 서비스로 돌아오겠습니다 ^_^ <br> 그동안 이웃집토토중을 이용해주셔서 감사합니다.</td>
    				</tr>
    				<tr>
    					<td colspan="2">
    						<input class="btn btn-warning" type="submit" value="확인" />
    					</td>
    				</tr>
    			</table>	
    		</form>
    	</div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
