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

.UserTermsbuttom{
 width: 700px;
  height: 200px;
   /*  background-color: blue;  */
  position : relative;
  top:10px;
 
}

#title{
  font-weight: bold;

}

.button-design #btn{
     width: 122px;
    height: 33px;
    border: 1px solid #ffffff;
    border-radius: 6px;
    text-align: center;
    background-color: #060606fc;
    color: #ffffff;
  
}
.button-design #btn:hover{
  background-color: orange; 
 color:white;


}
.toptext{
 line-height: 8px;
}
.UserTermsmiddle table{
 margin: 0 auto;

}

 input[type="text"]{
    border: 1px solid;
    border-top-style: none;
    border-right-style: none;
    border-left-style: none;
       width: 310px;
 
 }
 input[type="email"]{
    border: 1px solid;
    border-top-style: none;
    border-right-style: none;
    border-left-style: none;
       width: 310px;
 
 }
 input[type="phone"]{
    border: 1px solid;
    border-top-style: none;
    border-right-style: none;
    border-left-style: none;
       width: 310px;
 
 }
 input[type="password"]{
    border: 1px solid;
    border-top-style: none;
    border-right-style: none;
    border-left-style: none;
       width: 310px;
 
 }
 input[type="button"]{
       border: 0.1px solid;
    background-color: #d6ffe596;
 }   
 
 .imgtest{
    position: absolute;
    left: 420px;
    top: 234px;
 }
 .imgtest img{
    width: 300px;
    height: 300px;
    opacity: 0.35;
 
 }
</style>
</head>
<body>
<div class="UserTermstop">
<img src="<%=request.getContextPath() %>/images/useraccept2.jpg" alt="안나와요" width="700px" />
<br />
<div class="toptext">
<h4>ToToro Betting 사이트의 회원정보입력 창 입니다.</h4>
<h6>저희 사이트는 고객님의 개인정보를 신중히 보관할 계획입니다. </h6>
<h6>회원정보입력 조건에 맞도록 확인하여 정보를 적어주시면 감사하겠습니다. </h6>
<h3>회원정보입력</h3>
</div>
<hr />
</div>
<div class="UserTermsmiddle">
<br />
 <form name="checkIdDuplicateFrm"    
 action="memberEnrollEnd" 
		  action="<%=request.getContextPath()%>/member/memberEnrollEnd" 
		  onsubmit="return validate();"  class="enrollform">
 <table id="enroll">
 <tr>
<th>아이디: </th>
 <td>
  <input type="text" name="memberId_" id="memberId_"  placeholder="영문,숫자만 입력가능하며 최소 4자이상 입력" pattern="^[A-Za-z0-9+]*$"  required>
  </td>
  <td><input type="button" value="중복확인" id="idcheck-btn" onclick="idcheck();"></td>
 </tr>

  <tr>
  <th>비밀번호:</th>
     <td>
    <input type="password" name="password" id="password"  placeholder="영문,숫자를 한자 이상 반드시 포함한 4~16자로 입력" required>
    </td>
 </tr>
     <tr>
    <tr>
    <th>비밀번호확인:</th>
  <td>
 <input type="password" name="pwd_" id="pwd_" placeholder="비밀번호확인" required >
 </td>
     </tr>
       <tr>       
      <th>회원이름:</th>
              <td>
  <input type="text"  name="memberName" id="memberName" placeholder="이름" required>
     </td>
     </tr>
     <tr>
      <th>이메일:</th>
      <td>
          <input type="email" name="email" id="email" placeholder="이메일" >
      </td>
  </tr>
  <tr>
      <th>전화번호:</th>
      <td>
          <select name="tel0" id="tel0">
		 	<option value ="010" selected> 010</option>
			<option value ="011" > 011</option>
			<option value ="016" > 016</option>
			<option value ="070" > 070</option>
		  </select>
		  -<input type="tel" name="tel1" id="tel1" size=4 maxlength=4/>-
		  <input type="tel" name="tel2" id="tel2" size=4 maxlength=4/>		

      </td>
     
  </tr>
  <tr>
       <th>성별:</th>
       <td id="radio-color">
           <input type="radio" name="gender" value="M">
           <label for="gender" id="gender" >남</label>
           <input type="radio" name="gender" value="F">
           <label for="gender" id="gender" >여</label>
        
       </td>
   </tr>
   <tr>
        <th>질문:</th>
        <td>
                <input type="text" name="ask" id="ask" value="좋아하는 음식은 ?"  disabled>
        </td>
        
    </tr>
    <tr>
    <th>답:</th>
     <td>
    <input type="text" name="answer" id="answer" placeholder="비밀번호 및 아이디 찾기 분실시 필요합니다." required>
     </td>    
        </tr> 
     </table>
     <br />
<div class="button-design">
  <input type="submit" value="가입하기" id="btn"> 
     &nbsp;&nbsp;&nbsp;&nbsp;
     <input type="button" value="취소하기" id="btn" onclick="backToTheHome();">
</div>
  </form>  
  <input type="hidden" id="choice" />
</div>

<script>


//중복버튼 누를시  cnt가 1이됨 
var checkcnt = 0;
var id2 ="";


function idcheck(){

	var regId =/^[A-Za-z0-9+]*$/;
	var memberId = $("#memberId_").val().trim();
	
	if(memberId.length < 4){
		alert("아이디는 4글자 이상 가능합니다.");
		return;
	}
	$.ajax({
		type: "Post",
		url : "<%=request.getContextPath() %>/member/checkIdDuplicate",
		data : {
			"checkId" : memberId
		},
		success:function(data){
		console.log(data);
		if(data ==0){

			if(!regId.test(memberId)){
				alert("아이디는 영문 또는 숫자만 가능합니다.");
				
			}
			else{
			alert("사용가능한 아이디입니다.");
			checkcnt = 1;
			console.log(checkcnt);
		 	$("#password").focus(); 
		 	id2 = memberId;
		 	console.log(id2);
			
			}
		}
		else{
			alert("아이디가존재합니다.");
			checkcnt = 0;
			$("#password").focus();
			
		}
		
			
		}	
	});
	
			
		
	 
    
	}


	
	
function validate(){
	 var phone = $("#telnumber").val().trim(); 
	var pwd = $("#password").val();
	var pwd_ = $("#pwd_").val();
	
	/*  var regphone =/^[0-9]{11}$/;  */
	var regExp = /^(?=.*[a-zA-Z])(?=.*[0-9]).{4,16}$/;
	//아이디 
	var memberId = $("#memberId_");
	if(memberId.val().trim().length < 4){
		alert("아이디는 최소 4자리 이상이어야 합니다.");
		memberId.focus();
		return false;
	}
	console.log(checkcnt);
 
	 if(checkcnt == "1"){
		   if(memberId.val() == id2 ){
			   checkcnt =1;
			   
				console.log(checkcnt);
			  /*  $("#choice").focus(); */
		   }
		   else{
			   checkcnt=0;
		   }
		 
		  }
			 
	//아이디 중복검사여부 
	
	if(checkcnt == "0"){
		alert("아이디 중복검사 해주세요.");
		return false;
	}


	//회원가입 비밀번호 정규식표현
	if(pwd == pwd_){
	if(!regExp.test(pwd)){
		alert("비밀번호는 영문을포함한 4~16자리이내입니다.");
		return false;
	}
	else{
		return true;
		
	}
	}
	else{
		alert("비밀번호가 일치하지 않습니다.");
		$("#password").select();
		return false;
	}
	//핸드폰 11자리 정규식

 	/* if(!regphone.test(phone)){
		alert("번호는 공백없이 11자리 숫자입니다.");
	$("#telnumber").select();
		
		return false;
	} */
	 
	return true;
}
function backToTheHome(){
	location.href = "<%=request.getContextPath()%>";
}


</script>
</body>
</html>