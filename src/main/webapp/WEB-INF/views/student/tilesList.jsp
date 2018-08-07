<%@page import="board.student.model.StudentVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

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
		$("table tbody tr").on("click",function(){
			//tr태그의 data-id 속성값을 읽어서 input 태그의 id값으로 설정
			//form 태그를 submit
			$("#id").val($(this).data("id"));
			$("#frm").submit();
		});
	});
</script>
</head>

<body>

	<form id="frm" action="/student/tiles/detail" method="get">
		<input type="hidden" name="id" id="id">
	</form>

	<div class="row">
		<div class="col-sm-8 blog-main">
			<h2 class="sub-header">학생</h2>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>학생 아이디</th>
							<th>학생 이름</th>
							<th>등록일자</th>
						</tr>
					</thead>

					<tbody>
				
								<tbody>
									<c:forEach items="${studentList}" var="vo">
										<!--  items 루프할 대상-->
										<tr data-id="${vo.std_id}">
											<%--tr태그 클릭시 , 상세페이지로 이동 --%>
											<td>${vo.std_id}</td>
											<td>${vo.name}</td>
											<td><fmt:formatDate value="${vo.reg_dt }"
													pattern="yyyy-MM-dd" /></td>
										</tr>
									</c:forEach>
								</tbody>
					</tbody>

				</table>
			</div>

			<div class="text-center">
				<ul class="pagination">
					<%=request.getAttribute("pageNavi")%>
				</ul>
			</div>

		</div>
	</div>

</body>
</html>
