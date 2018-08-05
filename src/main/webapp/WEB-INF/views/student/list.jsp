<%@page import="board.student.model.StudentVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<title>Jsp</title>

<script src="/js/jquery-1.12.4.js"></script>
<link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<script src="/bootstrap/js/bootstrap.js"></script>
<!-- Custom styles for this template -->
<link href="/css/dashboard.css" rel="stylesheet">
<link href="/css/blog.css" rel="stylesheet">
<link href="/css/common.css" rel="stylesheet">

<script>
	$(function(){
		
	$("#studentList").on("click","tr",function(){
			//tr태그의 data-id 속성값을 읽어서 input 태그의 id값으로 설정
			//form 태그를 submit
			$("#id").val($(this).data("id"));
			$("#frm").submit();
	});

		
	//수기로 작성한 페이지 링크 클릭시 이벤트 햄들러
	$("#pageLink li").on("click",function(){
		var page = $(this).text();  //this 는 li안에 있는 text 를 의미한다.
		
		$.ajax({
				url : "/student/list/json",
				method : "post",
				//contentType : "application/json; charset=utf-8",	//json 전송을 알려주는 contentType
				dataType : "json",									//server로 부터 받을 data type
				//data : JSON.stringify(rangers),						//전송할 json 문자열 
				data : "page="+page+"&pageSize=10",
				success : function(data){  						//callback 출력
											//1.기존에 화면에 있는 학생 리스트를 삭제한다. 
					$("#studentList").html("");
											//2. data.studentList 에 있는 학생 리스트를 데이터로 이용하여
											//html 로 생성한다.
					var html="";
					$.each(data.studentList, function(idx, student){
						html = html +"<tr data-id='"+student.id+"'>";
						html = html +"<td>"+student.id+"</td>";
						html = html +"<td>"+student.name+"</td>";
						html = html +"<td>"+student.call_cnt+"</td>";
						html = html +"<td>"+student.reg_dt+"</td>";
						html = html +"</tr>";
					});
					
					//3. 학생리스트 위치에 html 붙여준다 %.html("2번에서 만든 html");
					$("#studentList").html(html);
			}	
		});   
	});
	
});	
	
</script>
</head>

<body>

	<!-- top 
	<%@ include file="/WEB-INF/views/common/top.jsp"%> -->
	
	<form id="frm" action="/student/detail" method="get">
		<input type="hidden" name="id" id="id">
	</form>
	
	<div class="container-fluid">
		<div class="row">

			<!-- left 
			<%@ include file="/WEB-INF/views/common/left.jsp"%> -->

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">학생</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>학생 아이디</th>
										<th>학생 이름</th>
										<th>지명 횟수</th>
										<th>등록일자</th>
									</tr>
								</thead>

								<tbody id="studentList">
									<c:forEach items="${studentList}" var="vo">
										<!--  items 루프할 대상-->
										<tr data-id="${vo.id}">
											<%--tr태그 클릭시 , 상세페이지로 이동 --%>
											<td>${vo.id}</td>
											<td>${vo.name}</td>
											<td>${vo.call_cnt}</td>
											<td><fmt:formatDate value="${vo.reg_dt }"
													pattern="yyyy-MM-dd" /></td>
										</tr>
									</c:forEach>
								</tbody>
								
							</table>
						</div>
						<a class="btn btn-default pull-right">사용자 등록</a>

						<div class="text-center">
							<ul class="pagination">
				<%--			<%=request.getAttribute("pageNavi")%>  --%>	
							</ul>
							
							<ul id ="pageLink" class="pagination">
								<li>1</li>
								<li>2</li>
								<li>3</li>
								<li>3</li>									
							</ul>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
