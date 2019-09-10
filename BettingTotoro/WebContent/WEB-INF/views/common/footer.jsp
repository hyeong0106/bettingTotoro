<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
function asideClick(e){
	var name = e.nextSibling.innerHTML;
	location.href="<%=request.getContextPath()%>/member/QRLSH?name="+name;
}
</script>
<style>
.col-md-6{
	width: 1000px !important;
	margin-left: 236px;
}
</style>
<!-- aside -->
<aside>
    <div class="asideDiv" id="num1Div">
    <br />
    <a href="#" onclick="$('html').animate({scrollTop : 0}, 1000)">맨위로</a>
    </div>
    <div class="asideDiv"><img src="<%=request.getContextPath()%>/images/이수현.jpg" onclick="asideClick(this);"/><label>이수현</label></div>
    <div class="asideDiv"><img src="<%=request.getContextPath()%>/images/정재훈.jpg" onclick="asideClick(this);"/><label>정재훈</label></div>
    <div class="asideDiv"><img src="<%=request.getContextPath()%>/images/김시준.jpg" onclick="asideClick(this);"/><label>김시준</label></div>
    <div class="asideDiv"><img src="<%=request.getContextPath()%>/images/이다희.jpg" onclick="asideClick(this);"/><label>이다희</label></div>
    <div class="asideDiv"><img src="<%=request.getContextPath()%>/images/김형민.jpg" onclick="asideClick(this);"/><label>김형민</label></div>
  
    
</aside>
<footer class="page-footer font-small special-color-dark pt-4">

  <!-- Footer Elements -->
  <div class="container">

    <!--Grid row-->
    <div class="row">

      <!--Grid column-->
      <div class="col-md-6 mb-4">
	한가람아파트 이웃집토토중은 건전한 토토 서비스를 제공합니다.
      </div>
      
    </div>
    <!--Grid row-->

  </div>
  <!-- Footer Elements -->

  <!-- Copyright -->
  <div class="footer-copyright text-center py-3">© 2018 Copyright:
    <a href="<%=request.getContextPath()%>"> BettingTotoro</a>
  </div>
  <!-- Copyright -->

</footer>
	</div>
</body>
</html>