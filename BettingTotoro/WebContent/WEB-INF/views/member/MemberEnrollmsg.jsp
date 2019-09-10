<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String memberId_ = (String)request.getAttribute("memberId_");
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.UserTermstop{
   width: 700px;
   height: 296px;
   text-align: center;
/*    background-color: red;  */
}

.UserTermsmiddle{
  width: 700px;
  height: 555px;
   /*  background-color: yellow;   */
  text-align: center;
      margin-top: -22px;
 line-height: 41px;
position: relative;

}
.UserTermsmiddle img{
  width: 500px;
  height: 500px;
  opacity: 0.8;

}
#testfont{
 font-weight: bolder;
 font-size: 20px;
}
#testfont label{
    color: #ffbe5fe6;
    text-decoration: underline;
    font-size: 33px;

}

.button-design #btn{
       width: 217px;
    height: 33px;
    border: 1px solid #ffffff;
    border-radius: 6px;
    text-align: center;
    background-color: #6bb5f1;
    color: #ffffff;
  
}
.button-design #btn:hover{
  background-color: orange; 
 color:white;


}
</style>
<script>
function backToTheHome(){
	location.href = "<%=request.getContextPath()%>";
}
</script>
</head>

<body>
<div class="UserTermstop">
<img src="<%=request.getContextPath() %>/images/useraccept3.jpg" alt="안나와요" width="700px" />
<br />
<br />
<h2>축하합니다!! 회원가입이 되었습니다.</h2>
<hr />
<div id="testfont">
<span>아이디는 </span><label ><%=memberId_%></label><span> 입니다.</span>
</div>
</div>
<div class="UserTermsmiddle">
<br />
<img src="<%=request.getContextPath() %>/images/회원가입완료.gif" alt="안나와요" width="700px" />
<br />
 <div class="button-design">
     <input type="button" value="홈으로가기" id="btn" onclick="backToTheHome();">
</div>
</div>

</body>
</html>