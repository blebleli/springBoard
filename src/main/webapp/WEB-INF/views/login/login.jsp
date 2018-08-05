<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">
    <title>Signin</title>
    <!-- Bootstrap core CSS -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/signin.css" rel="stylesheet">


   
      <script type="text/javascript" src="../js/jquery-1.12.4.js">
      function getCookie(name){
         var cookies = document.cookie;
         
         var cookieArr = cookies.split("; ");
         var cookieResult = "";
         for(var i in cookieArr){
            var cookie = cookieArr[i];
            var cookieNameValue = cookie.split("=");
            
            var cookieName = cookieNameValue[0];
         var cookieValue = cookieNameValue[1];
         
         if( name == cookieName ){
            cookieResult = cookieValue;
            break;
         }
         }
         return cookieResult;
      }
      // set쿠키 추가
      function setCookie(cookieName, cookieValue, expires){
         var dt = new Date();
         dt.setDate(dt.getDate() + parseInt(expires));
         document.cookie = cookieName + "=" + cookieValue + "; path=/; expires=" + dt.toGMTString();
      }
      
      // 쿠키 삭제
      function deleteCookieValue(cookieName){
         setCookie(cookieName, "", -1);
         
      }

      // 문서로딩 완료 후 실행  getCookie()
      $(function() {
         if(getCookie("rememberMe") == "y"){
            //userId Input에 userId cookie값을 설정 
            $("#userId").val(getCookie("userId"));
            $("#rememberMe").attr("checked", true);
            $("#rememberMe").val(getCookie("rememberMe"));
         }
         
         // rememberMe 체크박스 클릭 이벤트
         $("#rememberMe").on("click",function(){
            
            if($(this).is(":checked")){
            // rememberMe cookie 설정
               setCookie("rememberMe", "y" , 30);
               setCookie("userId", $("#userId").val(), 30);
            }else{
               // 쿠키 제거
               deleteCookieValue("rememberMe");
            }
         });
      }); 
      </script>
  </head>

  <body>
    <div class="container">
      <form class="form-signin" action="loginProcess" method="post">  
			<h2 class="form-signin-heading">Please sign in</h2>
			
			<label for="std_id" class="sr-only">ID</label> 
			<input type="text" id="std_id" name="std_id" class="form-control"
				placeholder="ID" required autofocus value="${param.std_id}"> 
				
			<label for="password" class="sr-only">Passward</label> 
			<input type="password" id="pass" name="pass" class="form-control" 
				placeholder="Password" required>
			
			<c:if test="${msg=='failure' }">
				<div style="color:red">아이디 또는 비밀번호가 일치하지 않습니다.</div>			
			</c:if>
			
			<div class="checkbox">
				<label> <input type="checkbox" id="rememberMe"name="rememberMe" value="">Remember me</label>
			</div>
			
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		</form>
	</div>
  </body>
</html>