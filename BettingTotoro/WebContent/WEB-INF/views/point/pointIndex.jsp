<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/point.css" />
<%@ page import="java.util.*" %>
<%@ page import="point.model.vo.Point" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script>
history.replaceState({}, null, location.pathname);
</script>
<%
	List<Point> list = (List<Point>)request.getAttribute("list");
	String type = (String)request.getAttribute("type");

%>

<script src="<%=request.getContextPath()%>/js/jquery-3.4.0.js"></script>
<script>
    function selectType(e){
    	//클릭한 버튼의 아이디값 추출
    	var type = e.id;
    	location.href="<%=request.getContextPath()%>/point/pointShopList?type="+type;
    }
    $(function(){
    	var type = <%=type%>.id;
    	$("#"+type).css({background:"#0d1a23", color: "white", border: "3px solid aqua"});
    });
    function insertItem(){
   		location.href="<%=request.getContextPath()%>/point/pointInsertForm";
    }
    function informationPopup(e){
    	var popupX = (window.screen.width / 2) - 150;
    	var popupY= (window.screen.height / 2) - 135;
    	
    	var itemName = e.parentElement.children[0].innerHTML;
    	
    	//팝업생성
		var url = "<%=request.getContextPath()%>/point/informationPopup?itemName="+itemName;
		var title ="아이템 정보";
		var specs = "width=300px, height=270px, left="+popupX+", top="+popupY;
		
		var popup = open(url,title,specs);//팝업의 최상위 윈도우객체를 리턴함.
		
    }
    function deleteItem(e){
    	var itemName = e.parentElement.children[0].innerHTML;
    	if(confirm(itemName+" 아이템을 정말 삭제하시겠습니까?")){
    		location.href="<%=request.getContextPath()%>/point/pointDeleteItem?itemName="+itemName;
    	}
    }
    function buyItem(e){
    	var itemName = e.parentElement.children[0].innerHTML;
    	var type = <%=type%>.id;
    	var price = e.parentElement.children[1].id
		
  		if(confirm(itemName+" 아이템을 정말 구매하시겠습니까?")){
    		<%if(memberLoggedIn!=null){%>
  
    			var memberId = "<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():null%>";
    			if(<%=memberLoggedIn.getPoint()%>>=price){
    				if(type=="뱃지"){
	    				var buyBadge = "";
	    				var myBadge = "<%=memberUseItem.getBadge()%>";
	    				console.log(myBadge);
	    				switch(myBadge){
						case "9단계": ;
	    				case "8단계": buyBadge += "9단계§";
	    				case "7단계": buyBadge += "8단계§";
	    				case "6단계": buyBadge += "7단계§";
	    				case "5단계": buyBadge += "6단계§";
	    				case "4단계": buyBadge += "5단계§";
	    				case "3단계": buyBadge += "4단계§";
	    				case "2단계": buyBadge += "3단계§";
	    				case "1단계": buyBadge += "2단계§";
	    				case "null": buyBadge += "1단계§"; break;
	    				}
	    			
	    				var buyBadgeList = buyBadge.split("§")
	    				for(var i=0; i<buyBadgeList.length; i++){
	    					if(itemName==buyBadgeList[i]){
	    						var newPrice = <%=memberLoggedIn.getPoint()%>-price;
	    		    			location.href="<%=request.getContextPath()%>/point/pointBuyItem?type="+type+"&price="+price+"&newPrice="+newPrice+"&itemName="+itemName+"&memberId="+memberId;
	    		    			return;
	    					} 
	    				}
	    				if(myBadge!="null"){
	    					alert("아이템을 구매 하실수 없습니다.(현재등급:"+myBadge+")");
	    				} else{
	    					alert("아이템을 구매 하실수 없습니다.(현재등급: 0단계)");
	    				}
	    				return;
    				}
    				var newPrice = <%=memberLoggedIn.getPoint()%>-price;
	    			location.href="<%=request.getContextPath()%>/point/pointBuyItem?type="+type+"&price="+price+"&newPrice="+newPrice+"&itemName="+itemName+"&memberId="+memberId;
  		
    			}
    			else{
    				alert("포인트가 부족합니다.")
    			}
    		<%} else{%>
    			alert("로그인후 구매할 수 있습니다.");
    		<%}	%>
    	} 
    }
    function StartItem(e){
    	var itemName = e.parentElement.children[0].innerHTML;
    	var type = <%=type%>.id;
    	var price = e.parentElement.children[1].id;
    	
    	//룰렛영역
    	if(itemName=="룰렛"){
	    	if(confirm(itemName+"게임을 하시겠습니까?")){
	    		<%if(memberLoggedIn!=null){%>
		    		if(<%=memberLoggedIn.getPoint()%>>=price){
				    	//팝업띄우기
				    	var popupX = (window.screen.width / 2) - 400;
				    	var popupY= (window.screen.height / 2) - 300;
				    	
				    	
				    	//팝업생성
						var url = "<%=request.getContextPath()%>/point/ItemStartPopup?itemName="+itemName;
						var title ="아이템 시작";
						var specs = "width=800px, height=600px, left="+popupX+", top="+popupY;
					  	
						var popup = open(url,title,specs);//팝업의 최상위 윈도우객체를 리턴함.
						var prices = "";
						var interval = window.setInterval(function() {
					        try {
					            if (popup == null || popup.closed) {
					            	window.clearInterval(interval);
					            	var getPoint = ($("#getPoint").val())*1;
					            	//팝업종료시
			            			var memberId = "<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():null%>";
			            			var newPrice = <%=memberLoggedIn.getPoint()%>-price+getPoint;
			            			location.href="<%=request.getContextPath()%>/point/rouletteResult?type="+type+"&price="+price+"&newPrice="+newPrice+"&itemName="+itemName+"&memberId="+memberId+"&getPoint="+getPoint;
			         	
					            }
					        }
					        catch (e) {
					        }
					    }, 1000);
		    		}
	    		
				else{
					alert("포인트가 부족합니다.")
				}
				<%} else{%>
				alert("로그인후 시작	할 수 있습니다.");
			<%}	%>
	    	}
    	}//룰렛영역 종료
    	//가위바위보 영역
    	else if(itemName=="가위바위보"){
	    	if(confirm(itemName+"게임을 하시겠습니까?")){
	    		<%if(memberLoggedIn!=null){%>
		    		if(<%=memberLoggedIn.getPoint()%>>=price){
				    	//팝업띄우기
				    	var popupX = (window.screen.width / 2) - 400;
				    	var popupY= (window.screen.height / 2) - 300;
				    	
				    	
				    	//팝업생성
						var url = "<%=request.getContextPath()%>/point/ItemStartPopup?itemName="+itemName;
						var title ="아이템 시작";
						var specs = "width=800px, height=600px, left="+popupX+", top="+popupY;
					  	
						var popup = open(url,title,specs);//팝업의 최상위 윈도우객체를 리턴함.
						var prices = "";
						var interval = window.setInterval(function() {
					        try {
					            if (popup == null || popup.closed) {
					            	window.clearInterval(interval);
					            	var getPoint = ($("#getPoint2").val())*1;
					       	
					            	//팝업종료시
			            			var memberId = "<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():null%>";
			            			var newPrice = <%=memberLoggedIn.getPoint()%>-price+getPoint;
			            			location.href="<%=request.getContextPath()%>/point/RockScissorsPaperResult?type="+type+"&price="+price+"&newPrice="+newPrice+"&itemName="+itemName+"&memberId="+memberId+"&getPoint="+getPoint;
			            			
					            		
					            }
					        }
					        catch (e) {
					        }
					    }, 1000);
		    		}
	    		
				else{
					alert("포인트가 부족합니다.")
				}
				<%} else{%>
				alert("로그인후 시작	할 수 있습니다.");
			<%}	%>
	    	}
    	}//가위바위보 영역
    	//포인트충전소(3일) 영역
    	else if(itemName=="무료포인트충전"){
    		<%if(memberLoggedIn!=null){%>
	    		if(<%=memberLoggedIn.getPoint()%><=5000){
			    	//팝업띄우기
			    	var popupX = (window.screen.width / 2) - 150;
			    	var popupY= (window.screen.height / 2) -250;
			    	
			    	
			    	//팝업생성
					var url = "<%=request.getContextPath()%>/point/ItemStartPopup?itemName="+itemName+"&memberId="+"<%=memberLoggedIn.getMemberId()%>";
					var title ="무료충전소";
					var specs = "width=300px, height=500px, left="+popupX+", top="+popupY;
				  	
					var popup = open(url,title,specs);//팝업의 최상위 윈도우객체를 리턴함.
					var prices = "";
					var interval = window.setInterval(function() {
				        try {
				            if (popup == null || popup.closed) {
				            	window.clearInterval(interval);
				            	var getPoint = ($("#getPoint2").val())*1;
				       	
				            	//팝업종료시
		            			var memberId = "<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():null%>";
		            			var newPrice = <%=memberLoggedIn.getPoint()%>-price+getPoint;
		            			location.href="<%=request.getContextPath()%>/point/giveFreePoint?point="+getPoint+"&memberId="+"<%=memberLoggedIn.getMemberId()%>";
		            			
				            		
				            }
				        }
				        catch (e) {
				        }
				    }, 1000);
	    		}
			else{
				alert("포인트가 넘처나시네요.^^")
			}
			<%} else{%>
			alert("로그인후 시작	할 수 있습니다.");
		<%}	%>
    	}//포인트충전소(3일)종료
    }
</script>	
 <section>
 <%if(memberLoggedIn!=null){%>
    <div id="userPointDiv">
        <label>나의 현재 포인트 : </label>
        <label><%=memberLoggedIn!=null?memberLoggedIn.getPoint():""%></label>
        <label>Point</label>
        <label id="getPoint" hidden></label>
        <label id="getPoint2" hidden></label>
    </div>
  <%} %>
    <%if(memberLoggedIn!=null&&memberLoggedIn.getMemberId().equals("admin")){%>
    <div id="insertBtnDiv">
        <button id="insertItem" onclick="insertItem();" class="btn btn-warning">상품등록</button>
    </div>
    <%} %>
    <div id="itemTypeDiv">
        <button class="btn btn-warning" id="아이템" onclick="selectType(this);">아이템</button>
        <button class="btn btn-warning" id="배경" onclick="selectType(this);">배경</button>
        <button class="btn btn-warning" id="뱃지" onclick="selectType(this);">뱃지</button>
        <button class="btn btn-warning" id="게임" onclick="selectType(this);">게임</button>
        <button class="btn btn-warning" id="포인트충전" onclick="selectType(this);">포인트충전</button>
    </div>
    <div id="PointShopDiv">
    	<%for(Point p: list){%>
    		<div>
	            <div id="leftDiv"><img src="<%=request.getContextPath()%>/images/<%=p.getItemImage()%>" alt="이미지 준비중입니다." /></div>
	            <div id="rightDiv">
	            <label class="itemName"><%=p.getItemName()%></label>
	            <label class="Price" id="<%=p.getPrice()%>"><%=p.getPrice()%> Point</label>
	           	<%if(memberLoggedIn!=null&&memberLoggedIn.getMemberId().equals("admin")){%>
	            	<button class="btn btn-warning" onclick="deleteItem(this);">삭제</button>
	            <%} else{ if(type.equals("게임") || type.equals("포인트충전")){%>
	            	<button class="btn btn-warning" onclick="StartItem(this);">시작</button>
	            <%} else{%>
	            	<button class="btn btn-warning" onclick="buyItem(this);">구매</button>
	            <%}
	          	} %>
	            <button class="btn btn-warning" class="information" onclick="informationPopup(this);">정보</button>
	            </div>
        	</div>
    	<%} %>
    </div>   
</section>
<style>
#container{
   height: 1460px !important;
}
section{
   height: 1015px !important;
}
</style>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>