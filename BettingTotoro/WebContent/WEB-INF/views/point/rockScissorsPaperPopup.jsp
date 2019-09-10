<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="point.model.vo.Point" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/point.css" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="IE=EmulateIE7" http-equiv=X-UA-Compatible />
<title>Lunch Wheel</title>
<script type=text/javascript src=https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js></script>
<script type=text/javascript src=jquery.tinysort.min.js></script>
<%
	Point p = (Point)request.getAttribute("point");
	String memberId = (String)request.getAttribute("memberId");
%>

<style type="text/css">
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
@import url(http://fonts.googleapis.com/earlyaccess/nanumbrushscript.css);
body{
	font-family: 'Nanum Gothic', sans-serif;
	margin: 0 auto;
}
header{
	color: white;
	margin: 0 auto;
	margin-top: 20px;
	width: 700px; 
	height: 100px;
	text-align: center;
	background-color: #99CCFF;
}
h1{
	margin: 0;
	line-height: 100px;
	font-family: 'Nanum Brush Script', cursive;
	font-size: 60px;
}
h2{
	line-height: 50px;
	margin: 0px;
}
button{
	font-size: 15px;
}
footer{
	font-size: 15px;
	text-align: center;
}
.choice{
	color: #FF6666;
	text-align: center;
	padding: 0;
	width: 700px; 
	height: 300px;
	margin: 0 auto;
	background-color: #C7DFF7;
}
.hidden{
	display: none;
}
.result{
	color: #FF6666;
	text-align: center;
	padding: 0;
	width: 700px; 
	height: 300px;
	margin: 0 auto;
	background-color: #C7DFF7;
}
.status{
	color: white;
	text-align: center;
	padding: 0;
	width: 700px; 
	height: 150px;
	margin: 0 auto;
	background-color: #99CCFF;
}
.rcpContainer{
	display:inline-block;
	margin: 10px;
	width: 200px;
	height: 220px;
	background-color: rgba(140, 140, 140, .5);
}
.comMoveImg{
	margin: 10px auto;
	width: 150px;
	height: 150px;
}
.playerMoveImg{
	margin: 10px auto;
	width: 150px;
	height: 150px;
}
.moveContainer{
	display:inline-block;
	width: 300px;
	height: 220px;
	margin: 10px;
}
.moveText{
	margin: 10px 0 0 0 ;
	font-size: 20px;
}
.scoreContainer{
	width: 200px;
	height: 70px;
	margin: 0 10px 0 10px;
	background-color: rgba(225, 225, 225, .9);
	display:inline-block;
}
.score{
	margin: 0;
	line-height: 70px;
	font-size: 30px;
}
.scissorsImg{
	margin: 10px auto;
	width: 150px;
	height: 150px;
	content:url("https://d33wubrfki0l68.cloudfront.net/223f1ad0bd97ce1c31dcbd8bee96c11cfe3c5442/673df/img/scissors.png");
}
.rockImg{
	margin: 10px auto;
	width: 150px;
	height: 150px;
	content:url("https://d33wubrfki0l68.cloudfront.net/94dfd95e62f1df66cf214c2747d7c5357c2b91a5/1c44f/img/rock.png");
}
.paperImg{
	margin: 10px auto;
	width: 150px;
	height: 150px;
	content:url("https://d33wubrfki0l68.cloudfront.net/6b219b7d219e011115605d86c4cee8e1920d4340/58c8b/img/papper.png");
}
#comScore{
	color: #54C7FF;
}
#playerScore{
	color: #FFFF00;
}
</style>
<title>삼세판 가위바위보게임</title>
</head>
<body>
	<header>
		<h1>삼세판 가위! 바위! 보! </h1>
	</header>
	<div class="choice">
		<h2>그림 밑의 버튼을 클릭해 주세요</h2>
		<div class="rcpContainer">
			<p>
				<img class="scissorsImg"><br />
			<button id="scissors">가위</button>
			</p>
		</div>
		<div class="rcpContainer">
			<p>
				<img class="rockImg"><br />
			<button id="rock">바위</button>
			</p>
		</div>
		<div class="rcpContainer">
			<p>
				<img class="paperImg"><br />
			<button id="paper">보</button>
			</p>
		</div>
	</div>
	<div class="result">
		<div class="moveContainer">
			<p class="moveText"><font color="#FFFF00">YOU</font></p><img class="playerMoveImg">
		</div>
		<div class="moveContainer">
			<p class="moveText"><font color="#54C7FF">COMPUTER</font></p><img class="comMoveImg">
		</div>
		<div id="resultText">
		<p> <button id="continue">계속</button></p>
		</div>
	</div>

	<div class="status">
		<h2>3번 승리시 종료!</h2>
		<div class="scoreContainer" id="playerScore">
			<p class="score">YOU <span>0</span></p>
		</div>
		<div class="scoreContainer" id="comScore">
			<p class="score">COM <span>0</span></p>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script>
		var playerMove;
		var comMove;
		var playerWins =0;
		var comWins = 0;
		var numOfGame = 0;
		
		function toggleChoice_Result(){
			$('.result').toggleClass('hidden');
			$('.choice').toggleClass('hidden');
		}

		function backToChoice(){
			toggleChoice_Result()
			$('#resultText span').remove();
			switch(playerMove){
				case 0:
					$('.playerMoveImg').toggleClass('scissorsImg');
					break;
				case 1:
					$('.playerMoveImg').toggleClass('rockImg');
					break;
				case 2:
					$('.playerMoveImg').toggleClass('paperImg');
					break;
			}
			switch(comMove){
				case 0:
					$('.comMoveImg').toggleClass('scissorsImg');
					break;
				case 1:
					$('.comMoveImg').toggleClass('rockImg');
					break;
				case 2:
					$('.comMoveImg').toggleClass('paperImg');
					break;
			}
		}

		function playGame(move, img){
			playerMove = move;
			$('.playerMoveImg').toggleClass(img);

			var randomNumber = Math.random();
			if (randomNumber < 0.33){
				comMove = 0;
				$('.comMoveImg').toggleClass('scissorsImg');
			} else if (randomNumber < 0.66){
				comMove = 1;
				$('.comMoveImg').toggleClass('rockImg');
			} else{
				comMove = 2;
				$('.comMoveImg').toggleClass('paperImg');
			}

			var winCheck = (3+playerMove-comMove)%3

			switch(winCheck){
				case 0:
					$('#resultText p').prepend("<span>비겼습니다!</span>");
					break;
				case 1:
					playerWins++;
					$('#resultText p').prepend("<span>플레이어가 이겼습니다!</span>");
					$('#playerScore span').remove();
					$('#playerScore p').append("<span>"+playerWins+"</span>");
					break;
				case 2:
					comWins++;
					$('#resultText p').prepend("<span>컴퓨터가 이겼습니다!</span>");
					$('#comScore span').remove();
					$('#comScore p').append("<span>"+comWins+"</span>");
					break;
			}

			toggleChoice_Result();

			if(playerWins == 3 || comWins == 3){
				if(playerWins>comWins) {
					alert("게임종료! 플레이어의 승리입니다! 10000Point 획득!");
					window.opener.document.getElementById("getPoint2").value = "10000";
				}
				else {
					alert("게임종료! 컴퓨터의 승리입니다! Point획득 실패ㅠㅠ!");
					
					window.opener.document.getElementById("getPoint2").value = "0";
				}
				playerWins = 0;
				comWins = 0;
				$('#playerScore span').remove();
				$('#playerScore p').append("<span>"+playerWins+"</span>");
				$('#comScore span').remove();
				$('#comScore p').append("<span>"+comWins+"</span>");
				backToChoice();
				window.opener.document.getElementById("getPoint").value = $("#resultText")[0].innerHTML;
				window.close();
			}
		}

		$('.result').toggleClass('hidden');

		$('#scissors').click(function() {
			playGame(0, 'scissorsImg');
		});
		$('#rock').click(function() {
			playGame(1, 'rockImg');
		});
		$('#paper').click(function() {
			playGame(2, 'paperImg');
		});
		$('#continue').click(function() {
			backToChoice();
		});
	</script>
</body>
</html>