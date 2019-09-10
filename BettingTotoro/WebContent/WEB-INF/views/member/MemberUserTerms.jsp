<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>이용약관동의</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.0.js"></script>
<style>
.UserTermstop{
   width: 700px;
   height: 296px;
   text-align: center;
 /*   background-color: red; */
}
.UserTermsmiddle{
  width: 700px;
  height: 555px;
  /* background-color: yellow; */
  text-align: center;
      margin-top: -22px;
}
.UserTermsexplain1{
 width: 500px;
    height: 100px;
     background-color: #e6e3e67a; 
    position: relative;
    left: 95px;
    top: 10px;
    overflow: auto;
    padding: 3px;
    border: 1px solid #ada7a7;
}
.UserTermsbuttom{
 width: 700px;
  height: 200px;
  /* background-color: blue; */


}
#UserTermstext{
  color:green;
  font-size:15px;

}
#title{
  font-weight: bold;

}
.textchoice1{
position: relative;
left: -172px;
}
.textchoice2{
position: relative;
left: -140px;
}
.textchoice3{
position: relative;
left: -81px;
}
.UserTermsbuttom{  text-align: center;}
.UserTermsbuttom button{
 width: 122px;
    height: 40px;
  border : 1px solid #ff842c;
  border-radius: 30px;
  text-align: center; 
  background-color: #ffffff;
  color :#ff842c;
  
  
}
.UserTermsbuttom button:hover{
 background-color: orange;
 color:#ffffff;

}





</style>
</head>
<body>
<div class="UserTermstop">
<img src="<%=request.getContextPath() %>/images/useraccept.png" alt="안나와요" width="700px" />
<br />
<h4 id="maintext">ToToro Betting 사이트에 오신걸 환영합니다.</h4>
<h5>회원가입하시기 전 이용약관에 동의해주세요.</h5>
<h3>이용약관동의</h3>
<hr />
</div>
<div class="UserTermsmiddle">
<br /><br />
<div class="textchoice1">
<input type="checkbox" id="e1" name="e1" /><label id="title" for="e1">서비스이용약관</label><label id="UserTermstext">(필수)</label>
</div>
  <div class="UserTermsexplain1" >
  <p>제1조: 목적 이 약관은 토토로닷컴주식회사(이하'회사')가 http://127.0.0.1:9090/BettingTotoro
     (이하'사이트')에서 제공하는 교육 정보 종합 서비스(이하'서비스')의 이용 조건 및 절차, 그리고 기타 필요한 사항을 규정하는
     것을 목적으로 합니다.</p>
    <p>사이트 서비스를 이용하시거나 사이트 서비스 회원으로 가입하실 경우 여러분은 본 약관 및 관련 운영 정책을 확인하거나 동의하게 되므로,
       잠시 시간을 내시어 주의 깊게  살펴봐 주시기 바랍니다.</p> 
   
     
  </div>
  <br /><br />
  <div class="textchoice2">
<input type="checkbox" id="e2" name="e2" value="on"/><label id="title" for="e2">개인정보 수집 동의 내용</label><label id="UserTermstext">(필수)</label>
</div>

<div class="UserTermsexplain1" >
   <p>정보통신망법 규정에 따라 해당 사이트에 회원가입 신청하시는 분께 수집하는 개인 정보의 항목, 개인 정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간을
      안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.</p>
    <p><h5>1.수집하는 개인정보</h5> 이용자는 회원가입을 하지 않아도 정보 검색 및 보기 등 해당 사이트의 서비스를 이용할 수 있습니다. 
       이용자가 배팅하기, 포인트상점 구매, 고객센터 문의글 등을 이용하기 위해 회원가입을 할 경우, 해당 사이트는 서비스 이용을 위해 필요한 최소한의 개인정보를 수집합니다. 
       </p>
       <h5>회원가입 시점에 해당 사이트가 이용자로부터 수집하는 개인정보는 아래와 같습니다.</h5>
       <p>- 회원 가입 시에 '아이디, 비밀번호, 이름, 휴대전화, 이메일, 질문에 대한 답'을 필수항목으로 수집합니다. 그리고 선택항목으로 성별을 수집합니다. </p>
       <h5>회사는 원칙적으로 이용자의 개인정보를 회원 탈퇴 시 지체없이 파기하고 있습니다.</h5>
       <p>단, 이용자에게 개인정보 보관기간에 대해 별동의 동의를 얻은 경우, 또는 법령에서 일정 기간 정보보관 의무를 부과하는 경우에는 해당 기간 동안 개인정보를 
          안전하게 보관합니다. </p>
</div>
<br /><br />
 <div class="textchoice3">
<input type="checkbox" id="e3" name="e3" value="on"/><label id="title" for="e3">개인정보의 제3자 제공의 대한 동의 내용</label><label id="UserTermstext">(선택)</label>
</div>
<div class="UserTermsexplain1" >
    <p>토토로 베팅 서비스(이하 '서비스'라 한다)와 관련하여, 본인은 아래의 내용을 숙지하였으며, 이에 따라 귀사가 수집한 본인의 개인정보를 아래와 같이 제3자에게
       제동하는 것에 대해 동의합니다.</p>
    <p><h5>1.개인정보를 제공받는 자의 개인정보 보유 및 이용 기간</h5>
    개인정보는 본 서비스를 제공하는 기간 동안에 보유 및 이용되고, 이용자의 서비스 해지(회원탈퇴)시 해당인의 개인정보가 열람 또는 이용될 수 없도로 파기 처리함. 단, 아래의 경우는 예외로 함.
    (1)법령에서 따로 정하는 경우에는 해당 기간까지 보유</p>
    <h5>2.고객은 개인정보의 제3자 제공에 대한 동의를 거부할 권리가 있으며, 동의를 거부할 경우 받는 별도의 불이익은 없습니다. 단, 서비스 이용이 불가능하거나, 서비스 이용 목적에 따른
    서비스 제공에 제한이 따르게 됩니다.</h5>
    <h5>3.상기에 기재되지 않은 개인정보 취급과 관련된 일반 사항은 서비스의 개인정보 취급방침에 따릅니다.
        본인은 본 개인정보 제3자 제공에 대한 동의 내용을 자세히 숙지 및 이해하고, 이에 동의합니다.</h5>
       
</div>
</div>
<div class="UserTermsbuttom">
<button onclick="test();">동의합니다</button>&nbsp;&nbsp;&nbsp;&nbsp;
<button onclick="history.back();">동의하지않습니다</button>&nbsp;&nbsp;&nbsp;&nbsp;
</div>
<script>
function test(){
	 var e1 = $('input:checkbox[id="e1"]').is(":checked");
	 var e2 = $('input:checkbox[id="e2"]').is(":checked");
	 if(e1 ==true && e2 ==true){
		 location.href='<%=request.getContextPath()%>/member/memberEnroll'
	 }
	 else{
		alert("필수사항을 모두 선택해주세요");
	 }
}


</script>
</body>
</html>