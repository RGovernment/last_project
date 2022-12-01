$(function(){
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		let object = {
				"item" : $("#item").val(), 
				"price": $("#price").val() , 
				"name" : $("name").val()			
		};

		$.ajax({
			url:'/kakao/kakaoPay',
			dataType:'json',
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			data:object
			,
			success:function(data){
				if (/Mobi|Android/i.test(navigator.userAgent)) {
					
					window.open(data.next_redirect_mobile_url, "모바일");
				}else{
				window.open(data.next_redirect_pc_url, "PC");
				}
			},
			error:function(error){
				alert(error);
			}
		});
});