function rentalCheck(){
		let status = document.getElementById("status");
	    let rental =document.getElementById("rental");
	    console.log(rental)
	    if(rental != null){
	    	if(confirm('책을 대여 하시겠습니까?') == true){
	    		if(status.value == 1){     //
	    			let date = document.getElementById("date");
	    	    	alert(`연체 기간 : ${date.value}`);
	    	    	return false;
	    	    }
				return true;
		    }else{
		        return false;
		    }
	    }
	}
	
	function notiCheck(){
		let noti =document.getElementById("noti");
	    console.log(noti)
	    if(noti != null){
	    	if(confirm('책 알림 설정을 하시겠습니까?') == true){
				return true;
		    }else{
		        return false;
		    }
	    }
	}