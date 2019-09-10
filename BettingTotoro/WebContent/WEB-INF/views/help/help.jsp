<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/help.css" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.0.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.easing.1.3.js"></script>
<script>
    $(function(){
    	$(".question").click(function(){
			
    		
    		var target = $(this);
    	    $(".question").next().each(function(){
    	    	if($(this).is(target.next())){
    	        	$(this).slideToggle(1000,'easeOutBounce');
    	        }
    	        else{
    	        	$(this).slideUp();
    	        }
    	    });
    	});
    });
	
</script>				
<section>
	<div id="HelpTable">
 		<h2>자주 묻는 질문</h2>
        <table class="table">
          <thead class="thead-dark" >
        	<tr class="table-warning">
	            <th>번호</th>
	            <th>질문</th>
         	</tr>
           </thead>
		   <tbody class="tbody-question">
            <tr>
                <td>01</td>
                <td>
                    <div class="question">배팅 당청금은 어떻게 수령하나요?</div>
                    <p>
                           	관리자가 배팅결과를 입력하면 사용자가 배팅한 금액과 배팅한 결과에 따라 자동으로 포인트가 지급이 됩니다.
                           	포인트가 지급된 내용은 내 정보보기 - 배팅내역에서 확인이 가능합니다. 
					</p>
                </td>
            </tr>
            <tr>
                <td>02</td>
                <td>
                    <div class="question">진행중인 배팅인 어디서 확인하나요?</div>
                    <p>
						현재 진행중인 배팅은 배팅하기 목록에서 자동으로 확인이 가능하며, 내정보보기 - 배팅내역에서도 확인이 가능합니다.
						그리고 배팅을 참여했다면 취소는 불가능합니다.
                    </p>		
                </td>
            </tr>
            <tr>
                <td>03</td>
                <td>
                    <div class="question">포인트를 다 잃어서 충전하고 싶어요.</div>
                    <p>
                           	서비스를 이용하다 포인트가 부족할 경우가 발생하는데, 이는 포인트 상점에서 무료충전소를 이용해서 충전이 가능합니다.
                           	무료충전소는 10분에 한번씩 이용가능하며 포인트가 5000이상이라면 사용이 불가능합니다.
					</p>
                </td>
            </tr>
            <tr>
                <td>04</td>
                <td>
                    <div class="question">아이템은 어떻게 적용 시키는 건가요?</div>
                    <p>
                    	아이템은 포인트 상점에서 사용자가 가지고 있는 포인트로 구매할 수 있으며, 아이템 구매시 내 정보 보기 -> 내 아이템 에서
                    	사용이 가능합니다. 뱃지의 경우 단계별로 구매가 가능합니다. 배경이나 뱃지는 1번 적용하고 사라지며 재적용이 불가능하오니 유의해서 사용해야합니다.
                    	더블배팅 아이템의 경우 1번만 사용해도 계속 적용하오니 구매시 유의하시길 바랍니다.                   	
                    </p>
                </td>
            </tr>
          </tbody>
        </table>
 </div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>