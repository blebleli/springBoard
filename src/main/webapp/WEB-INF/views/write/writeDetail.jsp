<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<title>Spring</title>

<script src="/js/jquery-1.12.4.js"></script>
<style type="text/css">
.commentCnt {
	height: 50px;
	border: 2px solid #245580;
	border-radius: 5px;
	padding: 10px;
	text-align: left;
	}
</style>

<script type="text/javascript">
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
    
    
//	$("#screen").load("/comment/commentList");
    	
		var element = $('input[name=w_id]') ;
		var w_id = element.val();
		var c_cmt = $("#c_cmt").val();
	
 		$.ajax({
			url : "/comment/commentList",						//"/comment/commentList.jsp",
			method : "get",
			contentType : "application/json; charset=utf-8",	//json 전송을 알려주는 contentType
			dataType : "html",									//server로 부터 받을 data type
			data : {"w_id" : w_id},
			success : function(data){  //callback 출력
			 	//console.log(data);
			 	$("#screen").html(data);
			}
		});
	
	
     	$("#btnCreateCmt").on("click",function(){
	  
    		var c_cmt = $('textarea[id=c_cmt]').val();
     		
     		
			$.ajax({
			url : "/comment/commentCreate",						//"/comment/commentList.jsp",
			method : "POST",
			//contentType : "application/json; charset=utf-8",	//json 전송을 알려주는 contentType
			dataType : "html",									//server로 부터 받을 data type
			data : {"w_id" : w_id,
					"c_cmt" : c_cmt	},								//전송할 parameters
			success : function(data){  //callback 출력
			 	//console.log(data);
				$("#screen").empty();
			 	$("#screen").html(data);
			}
		});
	})
})
	
</script>

	<div class="row">
		<div class="col-md-1" style="padding-right: 20px">
			<label class="control-label">제목</label>
		</div>
		<div class="col-md-11">
			<label class="control-label">${writeVo.w_title }</label>
		</div>                      
			<div class="col-md-1">작성자		 </div> 
			<div class="col-md-8">${writeVo.std_id }</div>
 			<div class="col-md-1">작성일		</div>
 			<div class="col-md-2">
			<fmt:formatDate value="${writeVo.w_regdt }" pattern="yyyy-MM-dd" />
 			</div>

		<div class="col-md-12"
		 style="padding: 15px 0; border-top: 1px solid #ddd; border-bottom: 1px solid #ddd"> 
		 ${writeVo.w_content}	</div>
			
<!-- 답글 ------------------------------------------------------>
		<div class="col-md-12" role="group" style="text-align: center;">
		
			
			<form action="/write/writeNew" method="post" id="frm">
				<input type="hidden" name="b_id" value="${writeVo.b_id}"> 
				<input type="hidden" name="w_parent" value="${writeVo.w_id}">
				<button type="submit" class="btn btn-primary">답글</button>
			</form>
			
<!-- 수정 ------------------------------------------------------>

			<form action="/write/updateIndex" method="post" id="frmUpdate">
				<input type="hidden" name="w_id" value="${writeVo.w_id}"> 
				<button type="submit" class="btn btn-default">수정</button>
			</form>

<!--삭제 ------------------------------------------------------>	
		
			<c:set value="${studentVo.std_id}" var="loginStd" />
			<c:if test="${loginStd eq writeVo.std_id}" var="result">
			<form action="/write/writeDelete" method="post" id="frmDelete">
				<input type="hidden" name="w_id" value="${writeVo.w_id}"> 
				<input type="hidden" name="b_id" value="${writeVo.b_id}">
				<button type="submit" class="btn btn-default">삭제</button>
			</form>
			
			</c:if>
		</div>
		
<!-- 댓글 리스트 ------------------------------------------------------>

		<div id="screen" class="row col-md-12"></div>
	</div>

<!-- 첨부파일 ------------------------------------------------------>

	<div style="padding: 15px 0; border-top: 1px solid #ddd; border-bottom: 1px solid #ddd">
		첨부파일 List
		<c:forEach items="${fileList }" var="item" varStatus="i">
			<a href="/write/fileDown?f_id=${item.f_id}"> 
		<c:out value="${item.f_name}" /></a>
		</c:forEach>
	</div>

<!-- 댓글등록 ------------------------------------------------------>

	<div>
		<textarea class="form-control col-md-12" rows="3" id="c_cmt" name="c_cmt"></textarea>
		<input type="hidden" name="b_id" value="${writeVo.b_id}"> 
		<input type="hidden" id="w_id" name="w_id" value="${writeVo.w_id}">
		<button id="btnCreateCmt" type="button"
				class="btn btn-primary col-md-12">댓글등록</button>
	</div>
