$(function(){
	const contextPath = $('#contextPathHolder').attr('data-contextPath') ? $('#contextPathHolder').attr('data-contextPath') : '';
	$('#a').on('click',function(){
		$("#searchBookList *").remove();
		$("#firstList *").empty();
		let searchText = $('.a').val();
		let bookCon = $('#bookcon').val();
		let arr = ["제목","장르","출판사","작가",]
	    searchText != "" ?
			$.get(contextPath + "/showBookList/"+searchText+"/"+bookCon,
					function(json){
						console.log(json)
						let dataLength = json.length;
						let	sCont ="";
						if(dataLength >= 1){
								sCont = "<div class='table1'>"
								arr.map((v)=>{
									sCont += "<div class='th'>"+v+"</div>"	
								})
								sCont += "</div>"
							for(i = 0; i < dataLength; i++){
								sCont += "<div class='table2'>"
								sCont += "<a href='bookDetail?isbn="+json[i].isbn+"'>"
								sCont += "<span>"+ json[i].b_title +"</span>"
								sCont += "<span>"+ json[i].c_name +"</span>"
								sCont += "<span>"+ json[i].b_publisher +"</span>"
								sCont += "<span>"+ json[i].b_author +"</span>"
								sCont += "</div></a>"
								
							}              	
						}else{
							sCont += "<h1>검색결과가 없습니다.</h1>"
						}
						$("#searchBookList").append(sCont);
			})	
		: alert("검색 값을 입력주세요");
	})
	
})