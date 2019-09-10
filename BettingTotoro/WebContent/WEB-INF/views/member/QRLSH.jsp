<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>		
<%String name = (String)request.getAttribute("name"); %>	
<style>
#qr{
	margin-top: 70px;
	margin-left: 30px;
	width: 300px;
	height: 300px;
}
#qrLabel{
	position: relative;
	font-size: 22px;
	top: 40px;
}
#qrName{
	font-size: 50px;
	margin-left: 120px;
}
section{
height: 500px;
}
#container{
	height:  914px;
}
</style>
<section>
<%if(name.equals("이수현")){ %>
<img id="qr" src="<%=request.getContextPath() %>/images/이수현.jpg"/>	
<label id="qrLabel"> ←← QR코드를 찍으시면 관리자의 정보를 보실 수 있습니다.</label>
<label id="qrName">이수현</label>
<%} else if(name.equals("정재훈")) {%>
<img id="qr" src="<%=request.getContextPath() %>/images/정재훈.jpg"/>	
<label id="qrLabel"> ←← QR코드를 찍으시면 관리자의 정보를 보실 수 있습니다.</label>
<label id="qrName">정재훈</label>
<%} else if(name.equals("김시준")) { %>
<img id="qr" src="<%=request.getContextPath() %>/images/김시준.jpg"/>	
<label id="qrLabel"> ←← QR코드를 찍으시면 관리자의 정보를 보실 수 있습니다.</label>
<label id="qrName">김시준</label>
<%} else if(name.equals("이다희")) { %>
<img id="qr" src="<%=request.getContextPath() %>/images/이다희.jpg"/>	
<label id="qrLabel"> ←← QR코드를 찍으시면 관리자의 정보를 보실 수 있습니다.</label>
<label id="qrName">이다희</label>
<%} else {%>
<img id="qr" src="<%=request.getContextPath() %>/images/김형민.jpg"/>	
<label id="qrLabel"> ←← QR코드를 찍으시면 관리자의 정보를 보실 수 있습니다.</label>
<label id="qrName">김형민</label>
<%} %>
</section>
		
<%@ include file="/WEB-INF/views/common/footer.jsp" %>