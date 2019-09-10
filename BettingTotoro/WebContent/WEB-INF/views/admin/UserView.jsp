<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.Member" %>
<%
	Member member = (Member)request.getAttribute("member");
%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.0.js"></script>
<script>
	function updateMember(){
		var $frm = $("[name=memberUpdateFrm]");
		var url = "<%=request.getContextPath()%>/admin/userUpdate";
		$frm.attr("action", url);
		$frm.submit();
	}
	function deleteMember(){
		var bool = confirm("정말로 삭제하시겠습니까?");
		if(bool){
			location.href = "<%=request.getContextPath()%>/admin/userDelete?memberId=<%=member.getMemberId()%>";
		}
	}
	function bettingList(){
		location.href = "<%=request.getContextPath()%>/member/memberBettingList?memberId=<%=member.getMemberId()%>";
	}
</script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin.css" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<section>
	<div id="ListFormTable1">
    	<h2>회원정보 상세보기</h2>
        <form name="memberUpdateFrm"
  					method="post">
          <table id="tbl-list-form">
             <tr>
             	<th>아이디</th>
                <td>	
	                <input type="text" 
				  	 	   name="memberId"
				     	   id="memberId_"
				     	   value="<%=member.getMemberId() %>"
				     	   readonly
				     	   required/>
		   		</td>
             </tr>
             <tr>
             	<th>비밀번호</th>
                <td>
                	<input type="password" 
		  		  		   name="password"
			               id="password_"
		 		           required/>
	  			</td>
             </tr>
             <tr>
             	<th>회원이름</th>
                <td>
                	<input type="text" 
		   				   name="memberName"
		   				   id="memberName"
		   				   value="<%=member.getMemberName() %>"
		   				   required/>
	  			</td>
             </tr>
             <tr>
                 <th>이메일</th>
                 <td>
                 	<input type="email" 
		   				   name="email"
		   				   id="email"
		   				   value="<%=member.getEmail()!=null?member.getEmail():"" %>"
		   				   required/>
	   			</td>
             </tr>
             <tr>
                <th>전화번호</th>
                <td>
                	<input type="tel" 
		   				   name="phone"
		   				   id="phone"
		   				   placeholder="(-없이)01012345678"
		   				   value="<%=member.getPhone() %>"
		   				   required/>
	  			</td>
             </tr>
             <tr>
                 <th>성별</th>
                 <td>
                 	<input type="text" 
                 		   name="gender"
                 		   id="gender"
                 		   value="<%=member.getGender()%>"/>
                 </td>
             </tr>
             <tr>
                 <th>실적</th>
                 <td>
                 	<input type="text" 
                 		   name="score"
                 		   id="score"
                 		   value="<%=member.getScore()%>"/>
                 </td>
             </tr>             
             <tr>
                 <th colspan="2" id="button-container">
                 	<input type="button" class="btn btn-outline-success" onclick="updateMember();" value="수정"/>
    				<input type="button" class="btn btn-outline-danger" onclick="deleteMember();" value="회원삭제"/>
    				<input type="button" class="btn btn-outline-secondary" onclick="bettingList();" value="배팅내역"/>
                 </th>
             </tr>
          </table>
    	</form>
	</div>
 </section>
 <%@ include file="/WEB-INF/views/common/footer.jsp" %>