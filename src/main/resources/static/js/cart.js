$(document).ready(function() {

	$(".UpBtn").click(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		cartId = this.value;
		bookPrice = $("#price" + cartId).text();
		totalPriceForBuy = $("#totalPriceForBuy").text();
		bookPriceint = parseFloat(bookPrice);
		totalPriceForBuyint = parseFloat(totalPriceForBuy);
		console.log(bookPriceint + totalPriceForBuyint);
		$.ajax({
			type: 'POST',       // 요청 메서드
			url: '/BookQuest/up',  // 요청 URI
			headers: { "content-type": "application/json" }, // 요청 헤더
			dataType: 'json', // 전송받을 데이터의 타입
			data: JSON.stringify(cartId),
			beforeSend: function(xhr) {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
				xhr.setRequestHeader(header, token);
			},
			success: function(result) {
				bookQuantity = JSON.parse(result);    // 서버로부터 응답이 도착하면 호출될 함수
				alert("수량이 " + result + "권으로 변경 되었습니다 ");        // result는 서버가 전송한 데이터
				$("#quantity" + cartId).text(bookQuantity);
				$("#totalprice" + cartId).text(bookQuantity * bookPrice);
				$("#totalPriceForBuy").text(bookPriceint + totalPriceForBuyint);
			},

			error: function() { alert("error") } // 에러가 발생했을 때, 호출될 함수
		}); // $.ajax()

	});

});


$(document).ready(function() {

	$(".DownBtn").click(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		cartId = this.value;
		bookPrice = $("#price" + cartId).text();
		totalPriceForBuy = $("#totalPriceForBuy").text();
		bookPriceint = parseFloat(bookPrice);
		totalPriceForBuyint = parseFloat(totalPriceForBuy);
		console.log(bookPrice);
		$.ajax({
			type: 'POST',       // 요청 메서드
			url: '/BookQuest/down',  // 요청 URI
			headers: { "content-type": "application/json" }, // 요청 헤더
			dataType: 'json', // 전송받을 데이터의 타입
			data: JSON.stringify(cartId),
			beforeSend: function(xhr) {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
				xhr.setRequestHeader(header, token);
			},
			success: function(result) {

				bookQuantity = JSON.parse(result);
				if (bookQuantity == -1) {
					alert("이미 최소 수량입니다");
					return;
				}

				alert("수량이 " + result + "권으로 되었습니다 ");       // result는 서버가 전송한 데이터
				$("#quantity" + cartId).text(bookQuantity);
				$("#totalprice" + cartId).text(bookQuantity * bookPrice);
				$("#totalPriceForBuy").text(totalPriceForBuyint - bookPriceint);
			},
			error: function() { alert("error") } // 에러가 발생했을 때, 호출될 함수
		}); // $.ajax()

	});

});