$(document).ready(function(){
	
	$('ul.tabs li').click(function(){
		var tab_id = $(this).attr('data-tab');
		

		$('ul.tabs li').removeClass('current');
		
		$('.tab-content').removeClass('current');

		$(this).addClass('current');
		$("#"+tab_id).addClass('current');
	})

})
  function rentalExtension() {
            console.log('rental extention btn click event');
            if(confirm('책 대여 기간을 연장 하겠습니까?') == true){
                location.href = '/book/rental/extension';
            }else{
                return false;
            }
            // location.href = '/book/rental/extension';
        }
        function returnBook() {
            alert("책 반납이 완료 되었습니다.")
            location.href = '/member/iinfo';
            // location.href = '/book/rental/extension';
        }