<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    
<%@ page import="java.util.*,betting.model.vo.BettingList,java.text.SimpleDateFormat" %>


<%
	String date = (String) request.getAttribute("date");
	String str ="";
	int ok=1;
	if(!date.equals("빔")){
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyyMMdd HH:mm:ss");
		

		Date time = new Date();
		String time1 = format1.format(time);
		String ctime1="";
		String dtime1="";
		ctime1+=time1.substring(4,8)+time1.substring(9,11)+time1.substring(12,14);
		dtime1+=date.substring(5,7)+date.substring(8,10)+date.substring(11,13)+date.substring(14,16);
		int d1=Integer.parseInt(dtime1);
	    int c1=Integer.parseInt(ctime1);
	    String d = "";
	    Date currentTime = new Date();
	    String Hours = "";
	    String minutes = "";
	    
	    if(c1-d1>10){
	    	str = "충전해드릴게요 충전대상이네요";
	    	ok=0;
	    }
	    else {
	    	str = "다음 충전 까지"+(10-((c1-d1)%60))+"분 남음";
	    }
	}
	else{
		str = "충전해드릴게요 충전대상이네요";
		ok=0;
	}
	    	
%>
<!DOCTYPE html>
<html>
<head>
<title>무료포인트충전</title>
</head>
<body>
		<%if(ok!=0){ %>
			<div class="card" style="border:1px solid #ffc200 width: 18rem;">
  				<img src="<%=request.getContextPath() %>/images/거지.png" class="card-img-top" alt="이미지업떠">
  				<div class="card-body">
    			<h5 class="card-title">충전대상이 아닙니다.</h5>
    			<p class="card-text"><%=str%></p>
    			<button type="button" class="btn btn-outline-dark" onclick="exit2();">닫기</button>
  				</div>
			</div>		    
		<%}else{%>
			<div class="card" style="border:1px solid #ffc200 width: 18rem;">
  				<img src="<%=request.getContextPath() %>/images/거지.png" class="card-img-top" alt="이미지업떠">
  				<div class="card-body">
    			<h5 class="card-title">10000포인트 지급</h5>
    			<p class="card-text"><%=str%></p>
    			<button type="button" class="btn btn-outline-dark" onclick="exit();">충전하기</button>
  				</div>
			</div>
		<%}%>
</body>
<script>
function exit(){
	window.opener.document.getElementById("getPoint2").value = "10000";
	window.close();
}
function exit2(){
	window.opener.document.getElementById("getPoint2").value = "0";
	window.close();
}
</script>
</html>
