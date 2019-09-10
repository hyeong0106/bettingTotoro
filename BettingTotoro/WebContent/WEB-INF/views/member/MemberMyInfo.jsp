<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/member/memberMyInfo.css" />
<%@ page import="java.util.*" %>
<%@ page import="point.model.vo.*" %>
<%@ page import="Message.model.vo.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
   List<Point> list = (List<Point>)request.getAttribute("list");
   List<MemberItemList> itemList = (List<MemberItemList>)request.getAttribute("itemList");
   List<Message> messageList = (List<Message>)request.getAttribute("message");
   Member member = (Member)request.getAttribute("member");
   String type = (String)request.getAttribute("type");
   String messageType = (String)request.getAttribute("messageType");
   String pageBar = (String)request.getAttribute("pageBar");
   int cPage = (Integer)request.getAttribute("cPage");
%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.0.js"></script>
<script>
   $(function(){
      $("#messageType").change(function(){
	         var messageType = $(this).val();
	         location.href="<%=request.getContextPath()%>/member/Message?type=MyMessage&messageType="+messageType+"&memberId="+'<%=member.getMemberId()%>';
	      });
      
      $("#numPerPage").change(function(){
         $("#numPerPageFrm").submit();
      });
      $(".select").click(function(){
			var messageNo = this.cells[0].innerText;
			var messageType = "";
			$("option").filter(":selected").each(function(){
                messageType=this.value;
            });
			location.href="<%=request.getContextPath()%>/message/messageView?messageType="+messageType+"&messageNo="+messageNo+"&memberId="+'<%=member.getMemberId()%>';
		});
   });
   function sendMessage(e){
       var popupX = (window.screen.width / 2) - 150;
       var popupY= (window.screen.height / 2) - 135;
       
       //팝업생성
      var url = "<%=request.getContextPath()%>/message/sendMessagePopup?sendId="+'<%=memberLoggedIn.getMemberId()%>';
      var title ="쪽지보내기";
      var specs = "width=610px, height=580px, left="+popupX+", top="+popupY;
      
      var popup = open(url,title,specs);//팝업의 최상위 윈도우객체를 리턴함.
      var interval = window.setInterval(function() {
	        try {
	            if (popup == null || popup.closed) {
	            	window.clearInterval(interval);
	            	var sendId = ($("#sendId").val());
	            	var srchName = ($("#srchName").val());
	            	var messageContent = ($("#messageContent").val());
	       			
	            	
	            	if(srchName == "" && messageContent == ""){		
	            	}
	            	else{
		      			location.href="<%=request.getContextPath()%>/message/sendMessage?sendId="+sendId+"&srchName="+srchName+"&messageContent="+messageContent;	            	
	            	}
	            }
	        }
	        catch (e) {
	        }
	    }, 1000);
      
    }
   function selectType(e){
      //클릭한 버튼의 아이디값 추출
      var type = e.id;
      if(type=="MyMessage"){         
         location.href="<%=request.getContextPath()%>/member/Message?type="+type+"&memberId="+'<%=member.getMemberId()%>';
      }
      else{
         
         location.href="<%=request.getContextPath()%>/member/MyInfo?type="+type+"&memberId="+'<%=member.getMemberId()%>';
      }
   }
   $(function(){
      var type = <%=type%>.id;
      $("#"+type).css({background:"#0d1a23", color: "white" ,border: "3px solid aqua" });
   });
   function informationPopup(e){
       var popupX = (window.screen.width / 2) - 250;
       var popupY= (window.screen.height / 2) - 250;
       
       var itemName = e.parentElement.children[0].innerHTML;
       
       //팝업생성
      var url = "<%=request.getContextPath()%>/point/informationPopup?itemName="+itemName;
      var title ="아이템 정보";
      var specs = "width=300px, height=300px, left="+popupX+", top="+popupY;
      
      var popup = open(url,title,specs);//팝업의 최상위 윈도우객체를 리턴함.
      
    }
   function useItem(itemtype, itemImage, itemName){
      
      
      if(itemtype=="배경"){
         if(confirm(itemName+" 아이템을 정말 사용하시겠습니까?")){
            
            location.href="<%=request.getContextPath()%>/member/memberItemUse?memberId="+'<%=member.getMemberId()%>'+"&itemName="+itemName+"&itemtype="+itemtype+"&type="+'<%=type%>';
          }
      }else if(itemtype=="뱃지"){
         if(confirm(itemName+" 아이템을 정말 사용하시겠습니까?")){
            location.href="<%=request.getContextPath()%>/member/memberItemUse?memberId="+'<%=member.getMemberId()%>'+"&itemName="+itemName+"&itemtype="+itemtype+"&type="+'<%=type%>';
          }
      }else if(itemtype=="아이템"){
         if(confirm(itemName+" 아이템을 정말 적용하시겠습니까?")){
            location.href="<%=request.getContextPath()%>/member/memberItemUse?memberId="+'<%=member.getMemberId()%>'+"&itemName="+itemName+"&itemtype="+itemtype+"&type="+'<%=type%>';
          }
      }
   }
   <%-- function updateMember(){
      var $frm = $("[name=memberUpdateFrm]");
      var url = "<%=request.getContextPath()%>/member/memberUpdate";
      $frm.attr("action", url);
      $frm.submit();
   } --%>
   function deleteMember(){
      var bool = confirm("정말로 탈퇴하시겠습니까?");
      if(bool){
         location.href = "<%=request.getContextPath()%>/member/memberDelete?memberId=<%=member.getMemberId()%>";
      }
   }
   function bettingList(){
      location.href = "<%=request.getContextPath()%>/member/memberBettingList?memberId=<%=member.getMemberId()%>";
   }
   function validate(){
	   var pwd = $("#password_").val();
	   var pwd_ = $("#password2_").val();
	   var regExp = /^(?=.*[a-zA-Z])(?=.*[0-9]).{4,16}$/;
	   
	   if(pwd == pwd_){
			if(!regExp.test(pwd)){
				alert("비밀번호는 영문을포함한 4~16자리이내입니다.");
				return false;
			}
			else{
				var memberName = $("#memberName").val();
				var regExp2 = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|\*]+$/
				if(!regExp2.test(memberName)){
					alert("이름에 특수문자나 한글을 포함할수없습니다.")
					return false;
				}
				else{
				   var tel1 =$("#tel1").val();
				   var tel2 =$("#tel2").val();
				   var regExp3 = /^[0-9][0-9][0-9][0-9]$/;
				   
				   if(!regExp3.test(tel1)){
					   if(!regExp2.test(tel2)){
						   alert("핸드폰 번호 다시 입력해주세요");
						   return false;
					   }
					   else{
						   return true;
					   }
				   }
				}
				
			}
		}
	   else{
			alert("비밀번호가 일치하지 않습니다.");
			$("#password_").select();
			return false;
		}
	   return true;
	   
   }
</script>
 <section>
  <input type="hidden" id="sendId" value="<%=member.getMemberId()%>" ></label>
    <label id="srchName" hidden></label>
    <label id="messageContent" hidden></label>
    <div id="MyInfoIndexDiv">
        <button class="btn btn-warning" id="MyInfo" onclick="selectType(this);">내정보보기</button>
        <button class="btn btn-warning" id="MyItemList" onclick="selectType(this);">내 아이템 목록</button>
       <button class="btn btn-warning" id="MyMessage" onclick="selectType(this);">쪽지함</button>
    </div>
    
    <div id="MyInfoDiv">
       <!-- 내정보보기  -->
       <%if(type.equals("MyInfo")){%>
          <div id="MyInfoTable1">
              <form action="<%=request.getContextPath()%>/member/memberUpdate"
              		name="memberUpdateFrm"
                    method="post"
                    onsubmit="return validate();">
                <table id="tbl-myInfo-form">
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
                      <th>비밀번호확인</th>
                      <td>
                         <input type="password" 
                              id="password2_"
                              required/>
                    </td>
                   </tr>
                   <tr>
                      <th>이름</th>
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
                       <select name="tel0" id="tel0">
                      			<%if(member.getPhone().substring(0,3).equals("010")){ %>
			 						<option value ="010" selected> 010</option>
			 						<option value ="011" > 011</option>
			 						<option value ="016" > 016</option>
			 						<option value ="070" > 070</option>
		 						<%}else if(member.getPhone().substring(0,3).equals("011")){%>
			 						<option value ="010" > 010</option>
			 						<option value ="011" selected> 011</option>
			 						<option value ="016" > 016</option>
			 						<option value ="070" > 070</option>
								<%}else if(member.getPhone().substring(0,3).equals("016")){ %>
									<option value ="010" > 010</option>
			 						<option value ="011" > 011</option>
			 						<option value ="016" selected> 016</option>
			 						<option value ="070" > 070</option>
								<%} else if(member.getPhone().substring(0,3).equals("016")){%>
									<option value ="010" > 010</option>
			 						<option value ="011" > 011</option>
			 						<option value ="016" > 016</option>
			 						<option value ="070" selected> 070</option>
								<%} %>
		  					</select> 
		  					-<input type="tel" name="tel1" id="tel1" size=4 maxlength=4 value="<%=member.getPhone().substring(3,7) %>"/>-
		  					<input type="tel" name="tel2" id="tel2" size=4 maxlength=4 value="<%=member.getPhone().substring(7) %>"/>	
                    </td>
                   </tr>
                   <tr>
                       <th>성별</th>
                       <td>
                          <input type="text" 
                                name="gender"
                                id="gender"
                                value="<%=member.getGender()%>"
                                readonly/>
                       </td>
                   </tr>
                   <tr>
                       <th>실적</th>
                       <td>
                          <input type="text" 
                                name="score"
                                id="score"
                                value="<%=member.getScore()%>"
                                readonly/>
                       </td>
                   </tr>             
                   <tr>
                       <th colspan="2">
                          <input class="btn btn-warning" type="submit" value="수정"/>
                      <input class="btn btn-warning" type="button" onclick="deleteMember();" value="탈퇴"/>
                      <input class="btn btn-warning" type="button" onclick="bettingList();" value="배팅내역"/>
                       </th>
                   </tr>
                </table>
             </form>
         </div>
       <%} 
       /* 내 아이템 목록 */
       else if(type.equals("MyItemList")){%>
          <%for(MemberItemList m: itemList){%>
             <%for(Point p: list){%>
                <%if(m.getItemName().equals(p.getItemName())) {%>
                   <%if(p.getItemType().equals("게임") || p.getItemType().equals("포인트충전")){ %>
                   
                   <%} else 
                   {%>
                   <div id="itemDiv">
                        <div id="leftDiv"><img src="<%=request.getContextPath()%>/images/<%=p.getItemImage()%>" alt="이미지 준비중입니다." /></div>
                        <div id="rightDiv">
                           <label class="itemName"><%=p.getItemName()%></label>
                           <label class="itemEa"><%=m.getItemEA()%> 개</label>
                           <button class="btn btn-warning" onclick="useItem('<%=p.getItemType()%>','<%=p.getItemImage()%>','<%=p.getItemName()%>');">사용</button>
                        </div>
                    </div>
                   <%} %>
                <%} %>
             <%} %>
          <%}%> 
         <%}
         else{%>
             <div id="messageDiv">
                <div id="search-container">
                                       검색타입:
                   <select id="messageType">
                       <option value="receive" <%=messageType.equals("receive")?"selected":"" %>>받은쪽지</option>
                       <option value="send" <%=messageType.equals("send")?"selected":"" %>>보낸쪽지</option>
                   </select>
               </div>
                <div id="messageTable">
                   <input type="button" id="send-btn" class="search-btn btn btn-outline-warning" value="쪽지보내기" onclick="sendMessage(this);"/>
                      <table class="table">
                         <thead class="thead-dark">
                         <tr>
                           <th scope="col">번호</th>
                           <th scope="col">메세지</th>
                           <th scope="col">보낸사람</th>
                           <th scope="col">작성일</th>
                         </tr>
                      </thead>
                      <tbody>
                         <% if(messageList==null||messageList.isEmpty()){ %>
                        <tr>
                           <td colspan="9" align="center"> 검색 결과가 없습니다. </td>
                        </tr>
                          <% 
                        } 
                        else {
                           for(Message m : messageList){ 
                          %>
                         <tr class="select">
                           <td scope="row"><%=m.getMessageNo() %></th>
                           <td><%=m.getContent() %></td>
                           <td><%=m.getSendId() %></td>
                           <td><%=m.getSendTime() %></td>
                        </tr>      
                         <%      } 
                        }
                         %>
                      </tbody>
                      </table>
                   <div id="pageBar">
                  <%=pageBar %>
               </div>               
                   </div>
                </div>
             </div>
         <%}%>
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