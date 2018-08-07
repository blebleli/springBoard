<%--------------------------------------------------------------------------------
	* 화면명 : Smart Editor 2.8 에디터 연동 페이지
	* 파일명 : /page/test/index.jsp
--------------------------------------------------------------------------------%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Smart Editor</title>

<style>


</style>


<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico" />

<!-- jQuery -->
<!-- <script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>-->

<!--  <script type="text/javascript" src="/js/jquery/jquery-3.2.1.js"></script>-->
<script type="text/javascript" src="/js/jquery-1.12.4.js"></script>
<link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<script src="/bootstrap/js/bootstrap.js"></script>
<!-- Custom styles for this template -->
<link href="/css/dashboard.css" rel="stylesheet">
<link href="/css/blog.css" rel="stylesheet">
<script src="/SE2/js/HuskyEZCreator.js"></script>

</head>

<body>


	<script>
		var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

		$(document).ready(
				function() {
					// Editor Setting
					nhn.husky.EZCreator.createInIFrame({
						oAppRef : oEditors, // 전역변수 명과 동일해야 함.
						elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
						sSkinURI : "/SE2/SmartEditor2Skin.html", // Editor HTML
						fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
						htParams : {
							// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
							bUseToolbar : true,
							// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
							bUseVerticalResizer : true,
							// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
							bUseModeChanger : true,
						}
					});

					// 전송버튼 클릭이벤트
					$("#savebutton").click(
							function() {
								if (confirm("저장하시겠습니까?")) {
									// id가 smarteditor인 textarea에 에디터에서 대입
									oEditors.getById["smarteditor"].exec(
											"UPDATE_CONTENTS_FIELD", []);

									// 이부분에 에디터 validation 검증
									if (validation()) {
										$("#frm").submit();
									}
								}
							})
				});

		// 필수값 Check
		function validation() {
			var contents = $.trim(oEditors[0].getContents());
			if (contents === '<p>&nbsp;</p>' || contents === '') { // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
				alert("내용을 입력하세요.");
				oEditors.getById['smarteditor'].exec('FOCUS');
				return false;
			}

			return true;
		}
		
		
		function submitContents(elClickedObj) {
		    // 에디터의 내용이 textarea에 적용된다.
		    oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);
		
		    // 에디터의 내용에 대한 값 검증은 이곳에서
		    // document.getElementById("ir1").value를 이용해서 처리한다.

		    try {
		        elClickedObj.form.submit();

		    } catch(e) {}
		} 

    function gotoWriteList(b_id){

		location.href="/writeView?b_id="+b_id+"&page=1&pageSize=10"
	 }
		
	</script>
</head>

<body>
	<!--  top.jsp -->
	<%@ include file="/common/top.jsp"%>

	<!--  left.jsp -->
	<%@ include file="/common/left.jsp"%>

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

		<form action="/writeCreate" method="post" id="frm">
			<div class="form-horizontal">
				<div class="col-sm-1">
					<label for="inputEmail3" class="control-label">제목</label>
				</div>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="w_title" name="w_title">
				</div>
			</div>
			<div>
				<textarea name="smarteditor" id="smarteditor" rows="10" cols="100"
					style="width: 90%; height: 412px;"></textarea>
			</div>
			
			<div class="col-sm-11" style="text-align: center;">
				<button class="btn btn-primary" type="button" onclick="submitContents(this)">등록</button>
				<input type="hidden" name="b_id" value="<%= request.getParameter("b_id") %>">
				<input type="hidden" name="w_parent" value="<%= request.getParameter("w_parent") %>">
				
		
				<button type="button" class="btn btn-default"
					onclick="gotoWriteList(<%= request.getParameter("b_id") %>)">
					돌아가기</button>
				<button type="button" class="btn btn-default" onclick="">첨부파일</button>
			
			</div>
			<div>
				<hr>
					첨부파일 list
					<input class="btn btn-default" type="file" name="uploadFile">
					<input class="btn btn-default" type="file" name="uploadFile">
					<input class="btn btn-default" type="file" name="uploadFile">
					<input class="btn btn-default" type="file" name="uploadFile">
					<input class="btn btn-default" type="file" name="uploadFile">
					<input class="btn btn-primary" type="submit" value="첨부파일 저장" /><br>
			</div>
		</form>
	
	</div>

</body>
</html>