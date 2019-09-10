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
<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">
<%
	Point p = (Point)request.getAttribute("point");
	String memberId = (String)request.getAttribute("memberId");
%>
<style>
html, body, div, span, applet, object, iframe,
h1, h2, h3, h4, h5, h6, p, blockquote, pre,
a, abbr, acronym, address, big, cite, code,
del, dfn, em, img, ins, kbd, q, s, samp,
small, strike, strong, sub, sup, tt, var,
b, u, i, center,
dl, dt, dd, ol, ul, li,
fieldset, form, label, legend,
table, caption, tbody, tfoot, thead, tr, th, td,
article, aside, canvas, details, embed, 
figure, figcaption, footer, header, hgroup, 
menu, nav, output, ruby, section, summary,
time, mark, audio, video {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 20%;
	font: inherit;
	vertical-align: baseline;
}
/* HTML5 display-role reset for older browsers */
article, aside, details, figcaption, figure, 
footer, header, hgroup, menu, nav, section {
	display: block;
	
}
#canvas{
	width: 770px;
	height: 470px;
}
body {
	line-height: 1;
}
ol, ul {
	list-style: none;
}
blockquote, q {
	quotes: none;
}
blockquote:before, blockquote:after,
q:before, q:after {
	content: '';
	content: none;
}
table {
	border-collapse: collapse;
	border-spacing: 0;
}
#venues{
    display: none;
}
</style>
<script type=text/javascript>
// Helpers
	shuffle = function(o) {
		for ( var j, x, i = o.length; i; j = parseInt(Math.random() * i), x = o[--i], o[i] = o[j], o[j] = x)
			;
		return o;
	};

	String.prototype.hashCode = function(){
		// See http://www.cse.yorku.ca/~oz/hash.html		
		var hash = 5381;
		for (i = 0; i < this.length; i++) {
			char = this.charCodeAt(i);
			hash = ((hash<<5)+hash) + char;
			hash = hash & hash; // Convert to 32bit integer
		}
		return hash;
	}

	Number.prototype.mod = function(n) {
		return ((this%n)+n)%n;
	}
</script>
<script type=text/javascript>
venues = {
		"116208"  : "6000",
		"66271"   : "0",
        "123"   : "5000",
        "52"   : "9000",
        "633"   : "10000",
        "623623"   : "0"
	};

	$(function() {

		var venueContainer = $('#venues ul');
		$.each(venues, function(key, item) {
			venueContainer.append(
		        $(document.createElement("li"))
		        .append(
	                $(document.createElement("input")).attr({
                         id:    'venue-' + key
                        ,name:  item
                        ,value: item
                        ,type:  'checkbox'
                        ,checked:true
	                })
	                .change( function() {
	                	var cbox = $(this)[0];
	                	var segments = wheel.segments;
	                	var i = segments.indexOf(cbox.value);

	                	if (cbox.checked && i == -1) {
	                		segments.push(cbox.value);

	                	} else if ( !cbox.checked && i != -1 ) {
	                		segments.splice(i, 1);
	                	}

	                	segments.sort();
	                	wheel.update();
	                } )

		        ).append(
	                $(document.createElement('label')).attr({
	                    'for':  'venue-' + key
	                })
	                .text( item )
		        )
		    )
		});

		$('#venues ul>li').tsort("input", {attr: "value"});
	});
</script>
<script type=text/javascript>
// WHEEL!
	var wheel = {

		timerHandle : 0,
		timerDelay : 33,

		angleCurrent : 0,
		angleDelta : 0,

		size : 290,

		canvasContext : null,

		colors : [ '#ffafb0' , '#aee4ff', '#bee9b4' , '#ffe4af', '#caa6fe', '#f2cfa5', '#83a7a3'],

		//segments : [ 'Andrew', 'Bob', 'Fred', 'John', 'China', 'Steve', 'Jim', 'Sally', 'Andrew', 'Bob', 'Fred', 'John', 'China', 'Steve', 'Jim'],
		segments : [],

		seg_colors : [], // Cache of segments to colors
		
		maxSpeed : Math.PI / 16,

		upTime : 1000, // How long to spin up for (in ms)
		downTime : 4000, // How long to slow down for (in ms)

		spinStart : 0,

		frames : 0,

		centerX : 300,
		centerY : 300,

		spin : function() {
			// Start the wheel only if it's not already spinning
			if (wheel.timerHandle == 0) {
				wheel.spinStart = new Date().getTime();
				wheel.maxSpeed = Math.PI / (7 + Math.random()); // Randomly vary how hard the spin is
				wheel.frames = 0;
				wheel.sound.play();

				wheel.timerHandle = setInterval(wheel.onTimerTick, wheel.timerDelay);
			}
		},

		onTimerTick : function() {

			wheel.frames++;

			wheel.draw();

			var duration = (new Date().getTime() - wheel.spinStart);
			var progress = 0;
			var finished = false;

			if (duration < wheel.upTime) {
				progress = duration / wheel.upTime;
				wheel.angleDelta = wheel.maxSpeed
						* Math.sin(progress * Math.PI / 2);
			} else {
				progress = duration / wheel.downTime;
				wheel.angleDelta = wheel.maxSpeed
						* Math.sin(progress * Math.PI / 2 + Math.PI / 2);
				if (progress >= 1)
					finished = true;
			}

			wheel.angleCurrent += wheel.angleDelta;
			while (wheel.angleCurrent >= Math.PI * 2)
				// Keep the angle in a reasonable range
				wheel.angleCurrent -= Math.PI * 2;

			if (finished) {
				clearInterval(wheel.timerHandle);
				wheel.timerHandle = 0;
				wheel.angleDelta = 0;
				console.dir($("#resultText")[0].innerHTML);
				alert($("#resultText")[0].innerHTML+" POINT 획득!!");
				window.opener.document.getElementById("getPoint").value = $("#resultText")[0].innerHTML;
				window.close();
			}

		},

		init : function(optionList) {
			try {
				wheel.initWheel();
				wheel.initAudio();
				wheel.initCanvas();
				wheel.draw();

				$.extend(wheel, optionList);

			} catch (exceptionData) {
				alert('Wheel is not loaded ' + exceptionData);
			}

		},

		initAudio : function() {
			var sound = document.createElement('audio');
			sound.setAttribute('src', 'wheel.mp3');
			wheel.sound = sound;
		},

		initCanvas : function() {
			var canvas = $('#wheel #canvas').get(0);

			if ($.browser.msie) {
				canvas = document.createElement('canvas');
				$(canvas).attr('width', 1000).attr('height', 600).attr('id', 'canvas').appendTo('.wheel');
				canvas = G_vmlCanvasManager.initElement(canvas);
			}
			
			canvas.addEventListener("click", wheel.spin, {
				once: true});
			
			wheel.canvasContext = canvas.getContext("2d");
			
		},

		initWheel : function() {
			shuffle(wheel.colors);
		},

		// Called when segments have changed
		update : function() {
			// Ensure we start mid way on a item
			//var r = Math.floor(Math.random() * wheel.segments.length);
			var r = 0;
			wheel.angleCurrent = ((r + 0.5) / wheel.segments.length) * Math.PI * 2;

			var segments = wheel.segments;
			var len      = segments.length;
			var colors   = wheel.colors;
			var colorLen = colors.length;

			
			// Generate a color cache (so we have consistant coloring)
			var seg_color = new Array();
			for (var i = 0; i < len; i++)
				seg_color.push( colors [ segments[i].hashCode().mod(colorLen) ] );

			wheel.seg_color = seg_color;

			wheel.draw();
			
		},

		draw : function() {
			wheel.clear();
			wheel.drawWheel();
			wheel.drawNeedle();
		},

		clear : function() {
			var ctx = wheel.canvasContext;
			ctx.clearRect(0, 0, 1000, 800);
		},

		drawNeedle : function() {
			var ctx = wheel.canvasContext;
			
			var centerX = wheel.centerX;
			var centerY = wheel.centerY;
			var size = wheel.size;

			ctx.lineWidth = 1;
			ctx.strokeStyle = '#000000';
			ctx.fileStyle = '#000000';

			ctx.beginPath();

			ctx.moveTo(centerX + size - 40, centerY);
			ctx.lineTo(centerX + size + 20, centerY - 10);
			ctx.lineTo(centerX + size + 20, centerY + 10);
			ctx.closePath();

			ctx.stroke();
			ctx.fill();

			// Which segment is being pointed to?
			var i = wheel.segments.length - Math.floor((wheel.angleCurrent / (Math.PI * 2))	* wheel.segments.length) - 1;

			// Now draw the winning name
			//가리키는 글씨
			ctx.textAlign = "left";
			ctx.textBaseline = "middle";
			ctx.fillStyle = '#000000';
			ctx.font = "2em Arial";
			ctx.fillText(wheel.segments[i]+"Point 획득!!!", centerX + size + 25, centerY);
			$("#rouletteBtn").html(wheel.segments[i]+"획득");
			$("#resultText").html(wheel.segments[i]);
		},

		drawSegment : function(key, lastAngle, angle) {
			var ctx = wheel.canvasContext;
			var centerX = wheel.centerX;
			var centerY = wheel.centerY;
			var size = wheel.size;

			var segments = wheel.segments;
			var len = wheel.segments.length;
			var colors = wheel.seg_color;

			var value = segments[key];
			
			ctx.save();
			ctx.beginPath();

			// Start in the centre
			ctx.moveTo(centerX, centerY);
			ctx.arc(centerX, centerY, size, lastAngle, angle, false); // Draw a arc around the edge
			ctx.lineTo(centerX, centerY); // Now draw a line back to the centre

			// Clip anything that follows to this area
			//ctx.clip(); // It would be best to clip, but we can double performance without it
			ctx.closePath();

			ctx.fillStyle = colors[key];
			ctx.fill();
			ctx.stroke();

			// Now draw the text
			ctx.save(); // The save ensures this works on Android devices
			ctx.translate(centerX, centerY);
			ctx.rotate((lastAngle + angle) / 2);
			//돌림판 내부글자
			ctx.fillStyle = '#ffffff';
			ctx.font = "2em Arial";
			ctx.fillText(value.substr(0, 20), size / 2 + 20, 0);
			ctx.restore();

			ctx.restore();
		},

		drawWheel : function() {
			var ctx = wheel.canvasContext;

			var angleCurrent = wheel.angleCurrent;
			var lastAngle    = angleCurrent;

			var segments  = wheel.segments;
			var len       = wheel.segments.length;
			var colors    = wheel.colors;
			var colorsLen = wheel.colors.length;

			var centerX = wheel.centerX;
			var centerY = wheel.centerY;
			var size    = wheel.size;

			var PI2 = Math.PI * 2;

			ctx.lineWidth    = 1;
			//돌림판내부 border
			ctx.strokeStyle  = 'white';
			ctx.textBaseline = "middle";
			ctx.textAlign    = "center";
			ctx.font         = "1.4em Arial";

			for (var i = 1; i <= len; i++) {
				var angle = PI2 * (i / len) + angleCurrent;
				wheel.drawSegment(i - 1, lastAngle, angle);
				lastAngle = angle;
			}
			// Draw a center circle
			ctx.beginPath();
			ctx.arc(centerX, centerY, 20, 0, PI2, false);
			ctx.closePath();
			//화살표
			ctx.fillStyle   = '#ffffff';
			ctx.strokeStyle = '#000000';
			ctx.fill();
			ctx.stroke();

			// Draw outer circle
			ctx.beginPath();
			ctx.arc(centerX, centerY, size, 0, PI2, false);
			ctx.closePath();

			ctx.lineWidth   = 10;
			ctx.strokeStyle = '#ffffff';
			ctx.stroke();
		},
	}

	window.onload = function() {
		wheel.init();

		var segments = new Array();
		$.each($('#venues input:checked'), function(key, cbox) {
			segments.push( cbox.value );
		});

		wheel.segments = segments;
		wheel.update();

		
	}															
</script>
<title><%=p.getItemName()%>게임</title>
</head>
<body>
	<div id="itemStartDiv">
	<label><%=p.getItemName()%>게임</label>
	</div>
	<div id="itemStartMidDiv">
		<div id=venues style=float:left><ul/></div>
		<div id=wheel>
		<canvas height=600 id=canvas width=1000>
		</canvas>
		</div>
		<div id=stats>
		</div>
		<div>
		<label id="firstLabel"><%=p.getPrice()%>포인트를 걸고 게임을 시작합니다.</label>
		<label id="resultText" hidden></label>
		</div>
	</div>
</body>
</html>


