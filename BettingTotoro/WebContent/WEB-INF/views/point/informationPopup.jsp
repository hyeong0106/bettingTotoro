<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="point.model.vo.Point" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/point.css" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이템 정보</title>
<%
	Point p = (Point)request.getAttribute("point");
%>

</head>
<body>
	<div id="informationMiddleDiv">
	<div id="leftDiv">
	<img src="<%=request.getContextPath()%>/images/<%=p.getItemImage()%>" alt="이미지 준비중입니다." />
	</div>
	<div id="rightDiv">
	<table>
		<tr>
			<td>목록</td>
			<td><%=p.getItemType() %></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><%=p.getItemName() %></td>
		</tr>
		<tr>
			<td>가격</td>
			<td><%=p.getPrice()%> Point</td>
		</tr>
		<tr>
			<td rowspan="3">내용</td>
			<td rowspan="3"><textarea cols="15" rows="5" readonly><%=p.getContent() %></textarea></td>
		</tr>
	</table>
	<%-- 
	<label>이름 : <%=p.getItemName() %></label>
	<label>이름 : <%=p.getPrice() %></label>
	<textarea cols="30" rows="10"><%=p.getContent() %></textarea> --%>
	</div>
	</div>
	<div id="informationFooterDiv">
		<button onclick="window.close();">닫기</button>
	</div>
</body>
</html>