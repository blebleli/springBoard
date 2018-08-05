<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp</title>

<script src="/js/jquery-1.12.4.js"></script>
<link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<script src="/bootstrap/js/bootstrap.js"></script>
<!-- Custom styles for this template -->
<link href="/css/dashboard.css" rel="stylesheet">
<link href="/css/blog.css" rel="stylesheet">
<link href="/css/common.css" rel="stylesheet">

<style type="text/css">
	.commentCnt{
	 height:50px; border: 2px solid #245580; border-radius: 5px; margin : 5px 5px 5px 5px;
	 padding-right : 5px;
	 text-align: left; 
	}
</style>

<script type="text/javascript" >
var textCountLimit = 500;

$(document).ready(function() {
    $('textarea[name=c_cmt]').keyup(function() {
        var textLength = $(this).val().length;
        
        $('#textCount').text(textLength);
        if (textLength > textCountLimit) {
        	alert("500자가 초과하였습니다.");
            $(this).val($(this).val().substr(0, textCountLimit));
        }
    });
});
</script>



</head>
<body>
	<!--  top.jsp -->
	<%@ include file="/common/top.jsp"%>

	<!--  left.jsp -->
	<%@ include file="/common/left.jsp"%>


				

	<div class="col-md-10 col-md-offset-2 main">
		
			
			<div class="row">
				<div class="col-md-1">
				<label class="control-label">제목</label></div>
 				 <div class="col-md-11">
 				 <label class="control-label">${writeVo.w_title }</label></div>
				<hr>
				<div class="col-md-1">작성자</div>
				<div class="col-md-8">${writeVo.std_id }</div>
				<div class="col-md-1">작성일</div>
				<div class="col-md-2">
				<fmt:formatDate value="${writeVo.w_regdt }" pattern="yyyy-MM-dd" />
				</div>

			<hr>
			<div class="col-md-12" style="height : 461px">
				${writeVo.w_content}
				
			</div>
		
	
			<div class="col-md-12" role="group" style="text-align: center;">
			<hr>
				<form action="/SE2/index.jsp" method="post" id="frm">
					<input type="hidden" name="b_id" value="${writeVo.b_id}">
					<input type="hidden" name="w_parent" value="${writeVo.w_id}">
					<button type="submit" class="btn btn-primary">답글</button>
				</form>	
				<!-- <button class="btn btn-primary" type="button" onclick="submitContents(this)">답글</button> -->
		
				<form action="/SE2/indexUpdate.jsp" method="post" id="frmUpdate">
					<input type="hidden" name="w_id" value="${writeVo.w_id}">
					<input type="hidden" name="b_id" value="${writeVo.b_id}">
					<input type="hidden" name="w_parent" value="${writeVo.w_id}">
					<input type="hidden" name="w_title" value="${writeVo.w_title}">
					<input type="hidden" name="w_content" value="${writeVo.w_content.replace("\"", "'")}">	
				<button type="submit" class="btn btn-default" onclick="">수정</button>
				</form>
					<c:set value="${studentVo.std_id}" var="loginStd"/>
					<c:if test="${loginStd eq writeVo.std_id}" var="result">
					<form action="/writeDelete" method="post" id="frmDelete">
						<input type="hidden" name="w_id" value="${writeVo.w_id}">
						<input type="hidden" name="b_id" value="${writeVo.b_id}">
						<button type="submit" class="btn btn-default">삭제</button>
					</form>
					</c:if>
			</div>
			
			<%@ include file="/comment/commentList.jsp"%>
		
		</div>
	
		<div>
			<hr>
			
			<div>첨부파일 List	
				<c:forEach items="${fileList }" var="item" varStatus="i">					
					<a href="/boardFileDownload?
					f_id=${item.f_id}&
					b_id=${writeVo.b_id}&
					w_id=${writeVo.w_id}&
					f_name=${item.f_name}"><c:out value="${item.f_name}" /></a>   <!-- f_id=${item.f_id} -->
					<input type="hidden" name="f_id" value="${item.f_id}">
					<input type="hidden" name="b_id" value="${writeVo.b_id}">
					<input type="hidden" name="w_id" value="${writeVo.w_id}">
				</c:forEach>
			</div>	
			  
		</div>
			
		<hr>

	<!-- 첨부파일 -->
		
		<!-- 댓글 -->
		<form id="createFrm" action="/commentCreate" method="post">
			<div>
			<textarea class="form-control col-md-12" rows="3" name="c_cmt"></textarea>
			<input type="hidden" name="b_id" value="${writeVo.b_id}">
			<input type="hidden" name="w_id" value="${writeVo.w_id}">
			<button type="submit" class="btn btn-primary col-md-12" >댓글등록</button>
			</div>
		</form>	
	</div>
	

</body>
</html>