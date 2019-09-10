<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<style>
section{
	width : 870px !important;
	height: 550px !important;
}
#container{
	height: 1085px;
}
</style>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/board.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/smartEditor/js/service/HuskyEZCreator.js"></script>
<section>
	<div id="BoardInsertTable1">
	    <h2>게시글작성</h2>
	    <form action="<%=request.getContextPath()%>/board/boardInsertEnd"
	  	   	  id="boardInsertFrm"
	 	   	  method="post">
	    	<table class="table table-sm">
	 		<thead>
			  <tr class="table-primary">
			    <th scope="col" id="insert-title">
			    	제목 : <input type="text" name="boardTitle">
			    	<input type="hidden" name="boardWriter" value="<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>" readonly>
			    </th>
			  </tr>
			</thead>
	  		<tbody>
		       <tr>
			       <td id="insert-content">
			       		<textarea id="popContent" name="boardContent" style="width:798px;" cols="25" rows="10"></textarea>
			       </td>
			   </tr>
		        <tr>
		        	<th id="button-container">
		            	<input class="btn btn-outline-primary" type="button" onclick="Post();"value="등록"/>
						<input class="btn btn-outline-danger"type="button" onclick="Cancle();" value="취소"/> 
		            </th>
		        </tr>
		  </tbody>
		  </table>
        </form>
    </div>
</section>
<script type="text/javascript">
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
	    fCreator: "createSEditor2"
	});
});
</script>
<script>
function Cancle(){
	location.href = "<%=request.getContextPath()%>/board/boardList";
	
}
function Post(){
	if(!confirm("이 게시물을 등록 하시겠습니까?")){
		
		return;
	}
	//폼을 사용해서 삭제요청
	oEditors.getById["popContent"].exec("UPDATE_CONTENTS_FIELD", []);
	$("#boardInsertFrm").submit();
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>