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

<style type="text/css">
.boardNum {width: 100px;}
.boardTitle {width: 500px;}
.boardWriter {width: 100px;}
.boardDate {width: 100px;}
</style>

<script>
function gotoDetailView(w_id){
		location.href="/write/writeDetail?&w_id="+w_id;
}

</script>
</head>
<body>

<div class="container-fluid">
		<div class="row">
		<h2 class="sub-header">${boardName}</h2>
		<p align="right">																	<!-- writeView 만으로 이동하는것? -->
			<button type="button" class="btn btn-primary" onclick="location.href='writeNew?b_id=${b_id}'">글쓰기</button>
		</p>
	
		<div class="table-responsive">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th class="boardNum">게시글번호</th>
						<th class="boardTitle">제목</th>
						<th class="boardWriter">작성자ID</th>
						<th class="boardDate">생성일자</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${writeList}" var="vo">
						<tr onclick="'${vo.w_delny}'=='Y' ? true : gotoDetailView(${vo.w_id })">
							<td>${vo.numview}  </td>
							<td>${vo.titleview}</td>
							<td>${vo.std_id }  </td>
							<td><fmt:formatDate value="${vo.w_regdt}" pattern="yyyy-MM-dd" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	
			<div class="text-center">
				<ul class="pagination">
					<%=request.getAttribute("pageNavi")%>
				</ul>
			</div>
			
		</div>
	</div>
</div>

</body>
</html>