<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, member.model.vo.*" %>

<% 
	ArrayList<String> PointTopbadgeList = (ArrayList<String>)request.getAttribute("PointTopbadgeList");
	System.out.println("이상무1="+PointTopbadgeList.size());
	ArrayList<String> ScoreTopbadgeList = (ArrayList<String>)request.getAttribute("ScoreTopbadgeList");
	System.out.println("이상무2="+ScoreTopbadgeList.size());
	ArrayList<String> ScoreWorstbadgeList = (ArrayList<String>)request.getAttribute("ScoreWorstbadgeList");
    ArrayList<Member> memberlistPointTop = (ArrayList<Member>)request.getAttribute("memberlistPointTop");
    System.out.println("이상무4="+memberlistPointTop.size());
	ArrayList<Member> memberlistScoreTop = (ArrayList<Member>)request.getAttribute("memberlistScoreTop");
	System.out.println("이상무5="+memberlistScoreTop.size());
	ArrayList<Member> memberlistScoreWorst = (ArrayList<Member>)request.getAttribute("memberlistScoreWorst");
	System.out.println("이상무6="+memberlistScoreWorst.size());
	
%>
<script>
var image1 = new Image();
	image1.src="<%=request.getContextPath()%>/images/연습1.jpg";
var image2 = new Image();
	image2.src="<%=request.getContextPath()%>/images/연습2.png";
var image3 = new Image();
	image3.src="<%=request.getContextPath()%>/images/연습3.jpg";
function imageClick(){
	if($("#slide")[0].src=="http://localhost:9090/BettingTotoro/images/%EC%97%B0%EC%8A%B51.jpg"){
		window.open("https://www.google.com/search?q=%EC%86%90%ED%9D%A5%EB%AF%BC&source=lnms&tbm=nws&sa=X&ved=0ahUKEwjttsm6jdbiAhXqyosBHc-mDRwQ_AUIEigD&biw=1920&bih=920");
	}else if($("#slide")[0].src=="http://localhost:9090/BettingTotoro/images/%EC%97%B0%EC%8A%B52.png"){
		window.open("https://www.google.com/search?biw=1920&bih=920&tbm=nws&ei=lq75XI7qJse4mAWEuqS4DA&q=%EC%8A%A4%ED%85%8C%ED%8C%90%EC%BB%A4%EB%A6%AC&oq=%EC%8A%A4%ED%85%8C%ED%8C%90%EC%BB%A4%EB%A6%AC&gs_l=psy-ab.3...463.2010.0.2095.0.0.0.0.0.0.0.0..0.0....0...1c.1j4.64.psy-ab..0.0.0....0.pQ6u4V2mhWw");
	}else if($("#slide")[0].src=="http://localhost:9090/BettingTotoro/images/%EC%97%B0%EC%8A%B53.jpg"){
		window.open("https://www.google.com/search?q=%EB%A5%98%ED%98%84%EC%A7%84&source=lnms&tbm=nws&sa=X&ved=0ahUKEwi9y-3TjdbiAhXcyosBHULHANgQ_AUIESgC&biw=1920&bih=920");
	}
}
</script>
<style>
#content{
width:880px;
height: 520px;
margin-top: 30px;
}
#content #left{
	background-color: black;

}
#content #slide{
	opacity: 0.9;
	border: 3px solid black;
	width: 790px;
	height: 450px;
}
#content #right{
	background-color: black;
}
#pageNum{
	width:880px;
	height: 30px;
	text-align: center;
	margin-top: 20px;
}
#pageNum div{
	width:30px;
	height: 30px;
	border: 2px solid black;
	display: inline-block;
}
</style>
<!-- section -->
<section> 
    <div id="content">
	    <img id="left" src="<%=request.getContextPath()%>/images/left.gif" onclick="leftClick();">
	    <img id="slide" src="<%=request.getContextPath()%>/images/연습1.jpg" name="slide" width="840" height="400" onclick="imageClick(this);">
	    <img id="right" src="<%=request.getContextPath()%>/images/right.gif" onclick="rightClick();">
	    <div id="pageNum">
		    <div id="page1" onclick="pageClick(this);">1</div>
		    <div id="page2" onclick="pageClick(this);">2</div>
		    <div id="page3" onclick="pageClick(this);">3</div>
    	</div>
    </div>
   <table class="table table-striped">
		  <thead>
		    <tr>
		      <th scope="col">\</th>
		      <th scope="col">Best 배팅</th>
		      <th scope="col">Worst 배팅</th>
		      <th scope="col">Top point</th>
		    </tr>
		  </thead>
		  <tbody>
		  <% for(int i=0;i<3;i++){%>
		  <tr>
              <th scope="row"><%=i+1%></th>
              <td>
	              <%if(ScoreTopbadgeList.get(i)!=null){%><img src = "<%=request.getContextPath()%>/images/<%=ScoreTopbadgeList.get(i)%>.png"><%} %>
	              <%=memberlistScoreTop.get(i).getMemberName() %>(<%=memberlistScoreTop.get(i).getMemberId()%>) : <%=memberlistScoreTop.get(i).getScore() %>
	          </td>
              <td>
                <%if(ScoreWorstbadgeList.get(i)!=null){%><img src = "<%=request.getContextPath()%>/images/<%=ScoreWorstbadgeList.get(i)%>.png"><%} %>
              	<%=memberlistScoreWorst.get(i).getMemberName() %>(<%=memberlistScoreTop.get(i).getMemberId()%>) : <%=memberlistScoreWorst.get(i).getScore() %>
              </td>
              <td>
              	  <%if(PointTopbadgeList.get(i)!=null){%><img src = "<%=request.getContextPath()%>/images/<%=PointTopbadgeList.get(i)%>.png"><%} %>
                  <%=memberlistPointTop.get(i).getMemberName() %>(<%=memberlistScoreTop.get(i).getMemberId()%>) : <%=memberlistPointTop.get(i).getPoint() %>
              </td>
            </tr>
           <tr>
 		<% }%>
           
		  </tbody>
	</table>
    <script type = "text/javascript">
    var step = 1;
    function slideit(){
    if(step==4){
    	step=1;
    }
    	document.images.slide.src=eval("image"+step+".src");
    	$("#page1").css({"background-color": "white"});
    	$("#page2").css({"background-color": "white"});
    	$("#page3").css({"background-color": "white"});
    	$("#page"+step).css({"background-color": "yellowgreen"});
    if(step<3)
    	step++;
    else 
    	step=1;
    setTimeout("slideit()",2500);
    }
    slideit();
    function leftClick(){
    if(step==1){
    	step=3;
    }else{
    	step--;
    }
    
    document.images.slide.src=eval("image"+step+".src");
    $("#page1").css({"background-color": "white"});
	$("#page2").css({"background-color": "white"});
	$("#page3").css({"background-color": "white"});
	$("#page"+step).css({"background-color": "yellowgreen"});
    }
    function rightClick(){
    	if(step==2){
    	document.images.slide.src=eval("image"+step+".src");
    	}else if(step==4){
    		step=1;
    		document.images.slide.src=eval("image"+step+".src");
    	} else if(step==3){
        	document.images.slide.src=eval("image"+step+".src");
    	}
    	  $("#page1").css({"background-color": "white"});
    		$("#page2").css({"background-color": "white"});
    		$("#page3").css({"background-color": "white"});
    		$("#page"+step).css({"background-color": "yellowgreen"});
    	step++;
    	
    }
    function pageClick(e){
    	step = e.innerText
    	document.images.slide.src=eval("image"+step+".src");
    	$("#page1").css({"background-color": "white"});
		$("#page2").css({"background-color": "white"});
		$("#page3").css({"background-color": "white"});
		$("#page"+step).css({"background-color": "yellowgreen"});
    }
    </script>
</section>