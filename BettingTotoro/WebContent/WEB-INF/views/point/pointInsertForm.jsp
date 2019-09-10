<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/point.css" />
<%@ page import="java.util.*" %>
<%@ page import="point.model.vo.Point" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
	List<Point> list = (List<Point>)request.getAttribute("list");
	String type = (String)request.getAttribute("type");
%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.0.js"></script>
<script>
function removeAll(){
	$("#input_type").val("");
	$("#input_picture").val("");
	$("#input_name").val("");
	$("#input_content").val("");
	$("#input_price").val("");
}
</script>
 <section>
    <div id="insertItemDiv">
        <form action="<%=request.getContextPath()%>/point/InsertItem"
		  method="post"
		  onsubmit="return enrollValidation();">
            <table class="insertItemTable">
                <tr>
                    <td colspan="2">아이템등록</td>
                </tr>
                <tr>
                    <td width="200px">목록</td>
                    <td>
                        <select name="itemType" id="input_type" required>
                            <option value="" disabled selected>목록을 선택하세요.</option>
                            <option value="아이템">아이템</option>
                            <option value="배경">배경</option>
                            <option value="뱃지">뱃지</option>
                            <option value="게임">게임</option>
                            <option value="포인트충전">포인트충전</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>사진</td>
                    <td>
                        <input type="file" name="itemImage" id="input_picture" required>
                    </td>
                </tr>
                <tr>
                    <td>아이템명</td>
                    <td><input type="text" placeholder="아이템명" name="itemName" id="input_name" required></td>
                </tr>
                <tr>
                    <td>정보</td>
                    <td><textarea name="content" id="input_content"" cols="30" rows="10" placeholder="내용을 입력하세요." required></textarea></td>
                </tr>
                <tr>
                    <td>가격</td>
                    <td><input type="number" placeholder="가격" name="price" id="input_price" required></td>
                </tr>
                <tr>
                    <td colspan="3">
                        
                        <input type="submit" value="등록하기">
                    </td>
                </tr>
            </table>
        </form>
        <button onclick="removeAll();">초기화</button>
    </div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>