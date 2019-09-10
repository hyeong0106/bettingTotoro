<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,board.model.vo.Board,board.model.vo.BoardComment" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Board board = (Board)request.getAttribute("board");
	System.out.println("board="+board.getBoardContent());
	List<BoardComment> commentList = (List<BoardComment>)request.getAttribute("commentList");
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/board.css" />	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/smartEditor/js/service/HuskyEZCreator.js"></script>
<section>
 <div id="BoardUpdateTable1">
	 <h2>게시글수정</h2>
	 <form action="<%=request.getContextPath()%>/board/boardUpdateEnd"
	  	   id="boardUpFrm"
	 	   method="post">
	 	<table class="table table-sm">
	 		<thead>
			  <tr class="table-success">
			    <th scope="col" id="update-title">
			    	<input type="hidden" name="boardNo" value="<%=board.getBoardNo()%>"/>
			    	<input type="text" 
	 	   				   name="boardTitle"
	  	   				   id="boardTitle"
	  	   				   value="<%=board.getBoardTitle() %>"
	  	   				   required/>
			    	[<%=board.getBoardWriter() %>]
			    </th>
			    <th scope="col"><%=board.getBoardDate() %></th>
			    <th scope="col">view : <%=board.getReadCount() %></th>
			  </tr>
			</thead>
	  		<tbody>
	    		<tr>
	      			<td colspan="3" id="update-content">
	      				<textarea name="boardContent" 
	                          id="popContent"
	                          style="width:798px;" 
							  cols="25" rows="10">
	                	</textarea>
	      			</td>
	    		</tr>
		        <tr>
		        	<th colspan="3" id="button-container">
		            	<input type="button" class="btn btn-outline-success" value="수정하기"  onclick="updateBoard();"/> 
		            </th>
		        </tr>
		  </tbody>
		</table>
	 </form>
  </div>
</section>

<script>
	var oEditors = [];
	$(function(){
		nhn.husky.EZCreator.createInIFrame({
		    oAppRef: oEditors,
		    elPlaceHolder: "popContent",  //textarea ID
		    sSkinURI: "<%=request.getContextPath()%>/js/smartEditor/SmartEditor2Skin.html",  //skin경로
		    htParams : {
				bUseToolbar : true,
				bUseVerticalResizer : false,
				bUseModeChanger : false,
				fOnBeforeUnload : function(){
				}
			},
			fOnAppLoad : function(){
				oEditors.getById["popContent"].exec("SET_IR", [""]); //내용초기화
				oEditors.getById["popContent"].exec("PASTE_HTML", ['<%=board.getBoardContent()%>']); //내용밀어넣기
			},
		    fCreator: "createSEditor2"
		});
	});
	function updateBoard(){
		if(!confirm("이 게시물을 수정 하시겠습니까?")){
			return;
		}
		oEditors.getById["popContent"].exec("UPDATE_CONTENTS_FIELD", []);
		$("#boardUpFrm").submit();
	}
</script>   
<%@ include file="/WEB-INF/views/common/footer.jsp" %>