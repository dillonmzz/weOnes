$(document).ready(function(){
    $(window).scroll(function(){
        	if($(document).scrollTop() >= 150){
            		$('#listSide').css('top','-146px');
        	}else{
            		$('#listSide').css('top','0px');
        	}
    });
})