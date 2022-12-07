//이미지 fade in/out
$(function(){
	
const options = {
		root : document.querySelector(".root"),
			threshold: 0.35314312235
};
	
const observer = new IntersectionObserver((entries, observer) => {
	  entries.forEach((entry) => {
	    	entry.target.classList.toggle('visible',entry.isIntersecting);
	  });                            
	}, options);

	document.querySelectorAll('.wrapper').forEach((wrapper) => observer.observe(wrapper));
	
});
//리뷰 대표 이미지 출력
$('img[name="modal-image"]').each(function() {
	var review_id = $(this).data("id");
	var modalimage = $(this);
	//사진 데이터 가져오기
	$.ajax({
		type: "GET",
		url: "/review/imglist",
		data: {
			review_id: review_id
		},
		success: function(result) {
			if (result != null) {
				modalimage.attr("src", result[0]);
			}
		},
		error: function() {
		}

	}); //$.ajax
});
//모달 이미지 출력
$('div[class="carousel-inner"]')
	.each(
		function() {
			var review_id = $(this).data("id");
			var div = $(this);
			console.log(review_id);
			//사진 데이터 가져오기
			$
				.ajax({
					type: "GET",
					url: "/review/imglist",
					data: {
						review_id: review_id
					},
					success: function(result) {
						for (var i = 0; i < result.length; i++) {
							var str = "";
							if (i == 0) {
								str += "<div class='carousel-item active'>";
							} else {
								str += "<div class='carousel-item '>";
							}
							str += "<div class='d-flex justify-content-center h-100'><img class='d-block' src='" + result[i] + "'alt='이미지가 없습니다' onerror=" + "this.src='/img/picture.jpg'" + "></div>";
							str += "</div>";
							div.append(str);
						}
					},
					error: function() {
					}

				}); //$.ajax
		});
//방 별 리뷰 select
$(function() {
	$(".sel-box").change(function() {

		$(".select-form").submit();
	});
});
