<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,board.model.vo.Board,board.model.vo.BoardComment" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Board board = (Board)request.getAttribute("board");
	System.out.println("board="+board.getBoardContent());
	List<BoardComment> commentList = (List<BoardComment>)request.getAttribute("commentList");
	String pageBar = (String)request.getAttribute("pageBar");
    int cPage = (Integer)request.getAttribute("cPage");
    int result = (Integer)request.getAttribute("result");
	System.out.println(result);
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/board.css" />	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/smartEditor/js/service/HuskyEZCreator.js"></script>
<section>
<div class="background">
	<img src="<%=request.getContextPath()%>/images/축구게시판배경2.png" id="soccerbackground" alt="" />
	<div id="BoardViewTable1">
		 <h2>게시글상세보기</h2>
		 <table class="table table-sm">
			<thead>
			  <tr class="table-active">
			    <th scope="col" id="view-title"><%=board.getBoardTitle() %>[<%=board.getBoardWriter() %>]</th>
			    <th scope="col"><%=board.getBoardDate() %></th>
			    <th scope="col">view : <%=board.getReadCount() %></th>
			  </tr>
			</thead>
	  		<tbody>
	    		<tr>
	      			<td colspan="3" id="view-content"><%=board.getBoardContent() %></td>
	    		</tr>
	    		<%
		        	if(memberLoggedIn != null && 
		          	  (memberLoggedIn.getMemberId().equals("admin") || memberLoggedIn.getMemberId().equals(board.getBoardWriter()))){
		        %>
		        <tr>
		        	<th colspan="3" id="button-container">
		            	<input type="button" class="btn btn-outline-success" value="수정하기" onclick="location.href='<%=request.getContextPath()%>/board/boardUpdate?boardNo=<%=board.getBoardNo()%>'"/> 
						<input type="button" class="btn btn-outline-danger" value="삭제하기" onclick="deleteBoard();"/>
		            </th>
		        </tr>
		        <%
		        	}
		        %>
		  </tbody>
		</table>
  	</div>
  <div id="BoardViewTable2">
	  <div id="comment-container">
	  		<%if(memberLoggedIn!=null) {%>
			<div class="comment-editor">
			  	<form action="<%=request.getContextPath()%>/board/boardCommentInsert"
			  		  name="boardCommentFrm"
			          method="post">
				  <div class="input-group mb-3">
					  <input type="text" class="form-control" name="boardCommentContent" placeholder="댓글을 입력하세요.">
					  <div class="input-group-append">
					    <button class="btn btn-outline-secondary" type="BU" id="button-addon2">등록</button>
					  </div>
					  <input type="hidden" name="boardRef" value="<%=board.getBoardNo()%>"/>
					  <input type="hidden" name="boardCommentWriter" value="<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>"/>
					  <input type="hidden" name="boardCommentLevel" value="1"/>
					  <input type="hidden" name="boardCommentRef" value="0"/>
				  </div>					
				</form>
			</div>
			<%}%>
			<!-- 댓글목록 테이블 -->
			<table id="tbl-comment">
		   <%if(!commentList.isEmpty()) {
		       for(BoardComment bc: commentList){
		           if(bc.getBoardCommentLevel()==1){
		   %>
		           <!-- 댓글인경우 -->
		           <tr class="level1">
		               <td>
		                   <sub class="comment-writer"><%=bc.getBoardCommentWriter() %></sub>
		                   <sub class="comment-date"><%=bc.getBoardCommentDate() %></sub>
		                   <br />
		                   <%=bc.getBoardCommentContent() %>
		               </td>
		               <td>
		               		<%if(memberLoggedIn!=null) {%>
		                   <button class="btn-reply" value="<%=bc.getBoardCommentNo() %>" >답글</button>
		                   <%} %>
		                   <%-- 삭제버튼 추가 --%>
		                   <%if(memberLoggedIn!=null 
							&& ("admin".equals(memberLoggedIn.getMemberId()) 
									|| bc.getBoardCommentWriter().equals(memberLoggedIn.getMemberId()) )){%>
		              	   <button class="btn-delete" onclick="location.href='<%=request.getContextPath()%>/board/boardCommentDelete?boardCommentNo=<%=bc.getBoardCommentNo()%>&boardRef=<%=bc.getBoardRef()%>'">삭제</button>
		                   <%} %>
		               </td>
		           </tr>
		   
		   <%            
		           }
		           else{
		   %>            
		           <!-- 대댓글인경우 -->    
		           <tr class="level2">
		               <td>
		                   <sub class="comment-writer"><%=bc.getBoardCommentWriter() %></sub>
		                   <sub class="comment-date"><%=bc.getBoardCommentDate() %></sub>
		                   <br />
		                   <%=bc.getBoardCommentContent() %>
		                   
		               </td>
		               <td>
		               		<%if(memberLoggedIn!=null 
								&& ("admin".equals(memberLoggedIn.getMemberId()) 
								|| bc.getBoardCommentWriter().equals(memberLoggedIn.getMemberId()) )){%>
		               		<button class="btn-delete" onclick="location.href='<%=request.getContextPath()%>/board/boardCommentDelete?boardCommentNo=<%=bc.getBoardCommentNo()%>&boardRef=<%=bc.getBoardRef()%>'">삭제</button>
		              		<%} %>
		               </td>
		           </tr>
		   <%            
		           }//end of if(bc.getBoardCommentLevel()==1)
		       
		       }//end of for
		       
		   }//end of if(!commentList.isEmpty())
		   %>
	    </table>
		<div id="pageBar">
           <%=pageBar %>
        </div>  
	</div>
  
  </div>
 </div>	
</section>

<% if(memberLoggedIn != null && 
	(memberLoggedIn.getMemberId().equals("admin") || memberLoggedIn.getMemberId().equals(board.getBoardWriter()))){
%>
<form action="<%=request.getContextPath()%>/board/boardDelete"
	  id="boardDelFrm"
	  method="post">
	  <input type="hidden" name="boardNo" value="<%=board.getBoardNo()%>"/>
</form>
<script>
	function deleteBoard(){
		if(!confirm("이 게시물을 정말 삭제 하시겠습니까?")){
			return;
		}
		//폼을 사용해서 삭제요청
		$("#boardDelFrm").submit();
	}
	<%} %>   
</script>
<script>
	$(function(){
		<%if(result>0){%>
			alert("게시글 수정완료! ");
		<%}else if(result==-100){%>
		
		<%}else{%>
			alert("게시글 수정실패! ");
		<%}%>
		//댓글textarea focus시에 로그인여부확인
		$("[name=boardCommentContent]").focus(function(){
			if(<%=memberLoggedIn==null%>){
				loginAlert();
			}
		});
		
		//댓글폼 submit이벤트처리
		$("[name=boardCommentFrm]").submit(function(e){
			//로그인여부검사
			if(<%=memberLoggedIn==null%>){
				loginAlert();
				e.preventDefault();//기본행위인 submit을 하지 않는다.
				return;
			}
			//댓글작성여부 검사
			var content = $("[name=boardCommentContent]").val().trim();
			if(content.length == 0){
				alert("댓글을 작성해 주세요.");
				e.preventDefault();//submit이 일어나지 않는다
			}
		});
		
		//대댓글입력
		$(".btn-reply").click(function(){
			var tr = $("<tr></tr>");
			var html = '<td style="display:none; text-align:left;" colspan="2">';
			html += '<form action="<%=request.getContextPath()%>/board/boardCommentInsert" mehtod="post">';
			html += '<textarea name="boardCommentContent" cols="60" rows="3"></textarea>';
			html += '<button type="submit" class="btn-insert2">등록</button>';
			html += '<input type="hidden" name="boardRef" value="<%=board.getBoardNo()%>"/>';
			html += '<input type="hidden" name="boardCommentWriter" value="<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>"/>';
			html += '<input type="hidden" name="boardCommentLevel" value="2"/>';
			html += '<input type="hidden" name="boardCommentRef" value="'+$(this).val()+'"/>';
			html += '</form></td>';
			
			tr.html(html);
			tr.insertAfter($(this).parent().parent()).children("td").slideDown(800);
			
			//답글버튼을 연속적으로 누르지 않도록 핸들러제거
			$(this).off('click');
			
			//새로생성한 요소에 대해 submit이벤트 핸들러 작성
			tr.find("form").submit(function(e){
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           					//댓글textarea 유효성검사
				var content = $(this).children("textarea").val().trim();
				if(content.length == 0){
					e.preventDefault();
				}
			});
		});
	});
	function loginAlert(){
		alert("로그인 후 이용할 수 있습니다.");
		$("#memberId").focus();
	}
</script>
     
<%@ include file="/WEB-INF/views/common/footer.jsp" %>