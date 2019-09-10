<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String changedpassword=(String)request.getAttribute("changedpassword");
    %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.0.js"></script>
<style>
.screen{
    width: 400px;
    height: 196px;
    text-align: center;
    margin-top: -14px;
    margin-left: -8px;

}
.screen2{
     width: 400px;
    height: 107px;
    text-align: center;
    margin-top: 0px;
    margin-left: -8px;
    

}
span{
 border:none;
 border-right-style:none ;
 font-size: 13px;
 
}
input{
  border:none;
    width: 230px;
    height: 24px;
    text-align: center;
}
h2{
  color:navy;
}
#see{
  position: relative;
    top: -354px;
    left: 246px;
}





</style>
</head>
<body>
  <div class="screen">
  <h2>비밀번호찾기</h2>
  <hr />
    <form action="<%=request.getContextPath()%>/member/MemberSearchPwdEnd" method="post">
     <span>아이디</span>&nbsp;<input type="text" name="searchid" id="searchid"  placeholder="아이디를입력해주세요" required/>
   <br />
   <span>이름</span>&nbsp;<input type="text" name="searchname" id="searchname"  placeholder="이름을입력해주세요" required/>
   <br />
   <span>전화번호</span>&nbsp;<input type="text" name="searchphone" id="searchphone"  placeholder="전화번호를입력해주세요" required/>
    <br />
     <span>질문</span>&nbsp;<input type="text" name="ask" id="ask" value="좋아하는 음식은 ?"  disabled>
    <br />
    <span>답</span>&nbsp;<input type="text" name="answer" id="answer"  placeholder="질문의답을입력해주세요" required/>
    <br />
    <br />
    <input type="submit" value="비밀번호찾기" />
    <input type="hidden" name="changedpassword"  value=<%=changedpassword %> />
    <input type="hidden" name="viewchangedpassword"  value=<%=changedpassword%>1 />
    </form>
  </div>
  <div class="screen2">
  <hr />
  <br />
  <br />
  <br />
  <br />
  <button onclick="self.close();">닫기</button> 
  
  
  </div>
   <img src="<%=request.getContextPath() %>/images/see.png"  alt="" id="see" />
   
   
 
</body>
</html>