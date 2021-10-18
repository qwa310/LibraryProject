$(function(){	
	const contextPath = $('#contextPathHolder').attr('data-contextPath') ? $('#contextPathHolder').attr('data-contextPath') : '';
	$('#searchA').on('click',function(){
		let searchText = $('#searchText').val();
		let bookCon = $('#bookcon').val();
		if(searchText == ""){
			alert("검색 값을 입력주세요");
			return;
		}
		$("#searchBookList *").empty();
		$("#firstList").empty();
		$.get(contextPath + "/showBookList/"+searchText+"/"+bookCon,
			function(json){
				if(json.length == 0){
					alert("검색 결과가 없습니다.");
					return;
				}else{
					location.href="/book/bookSearch?search="+searchText+"&bookcon="+bookCon;
				}
			}
		)	
	})
})