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
</head>

<body>

	<form class="form-horizontal" role="form" id="frm"
		action="/studentUpdate" method="get">
		<input type="hidden" id="id" name="id" value="${studentVo.id}">


		<div class="form-group">
			<label for="id" class="col-sm-2 control-label">프로필</label>
			<div class="col-sm-10">
				<img src="/pic?id=${studentVo.id}" width=100>
			</div>
		</div>


		<div class="form-group">
			<label for="id" class="col-sm-2 control-label">학생 아이디</label>
			<div class="col-sm-10">

				<label class="control-label">${studentVo.id}</label>

			</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">학생이름</label>
			<div class="col-sm-10">
				<label class="control-label">${studentVo.name}</label>
			</div>
		</div>

		<div class="form-group">
			<label for="coll_cnt" class="col-sm-2 control-label">호출횟수</label>
			<div class="col-sm-6">
				<label class="control-label">${studentVo.call_cnt}</label>
			</div>
		</div>

		<div class="form-group">
			<label for="addr1" class="col-sm-2 control-label">주소</label>
			<div class="col-sm-10">
				<label class="control-label">${studentVo.addr1}</label>
			</div>
		</div>

		<div class="form-group">
			<label for="addr2" class="col-sm-2 control-label">상세주소</label>
			<div class="col-sm-6">
				<label class="control-label">${studentVo.addr2}</label>
			</div>
		</div>

		<div class="form-group">
			<label for="zipcd" class="col-sm-2 control-label">우편번호</label>
			<div class="col-sm-10">
				<label class="control-label">${studentVo.zipcd}</label>
			</div>
		</div>


		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">학생 수정</button>
			</div>
		</div>


	</form>
</body>
</html>
