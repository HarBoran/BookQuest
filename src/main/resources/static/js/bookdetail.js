function increment() {
	var value = parseInt(document.getElementById("number").value, 10);
	value++;
	document.getElementById("number").value = value;
}

function decrement() {
	var value = parseInt(document.getElementById("number").value, 10);
	if (value > 1) {
		value--;
	}
	document.getElementById("number").value = value;
}

function incrementbook() {
	var value = parseInt(document.getElementById("bookquantity").value, 10);
	value++;
	document.getElementById("bookquantity").value = value;
}

function decrementbook() {
	var value = parseInt(document.getElementById("bookquantity").value, 10);
	if (value > 1) {
		value--;
	}
	document.getElementById("bookquantity").value = value;
}

function openTab(evt, tabName) {
	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}
	tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace(" active", "");
	}
	document.getElementById(tabName + "-content").style.display = "block";
	evt.currentTarget.className += " active";
}


function putonitem() {
	// 확인 메시지 띄우기
	if (confirm("장바구니에 담으시겠습니까 ?")) {
		return true;
	} else {
		return false;
	}
}

//리뷰작성할떄 별점 없으면 alert창
$('#buttonDone').click(function(event) {
  if (!$('input[name="starRating"]').is(':checked')) {
    // prevent form submission
    event.preventDefault();
    alert('별점을 선택해주세요.');
  }
});

//클릭했을때 리뷰 수정창 보이게하기
$(document).ready(function() {
	$('.showEditForm').click(function() {
		var num = $(this).attr('id').replace('reivewsBoxComment', '');
		document.getElementById("reivewsBoxComment" + num).style.display = "none";
		document.getElementById("reivewsBoxCommentEdit" + num).style.display = "block";
	});
});

function cancelEdit(num) {
	document.getElementById("reivewsBoxComment" + num).style.display = "block";
	document.getElementById("reivewsBoxCommentEdit" + num).style.display = "none";
}


//리뷰 삭제 하기
function submitDeleteForm() {
	if (confirm("정말 리뷰를 삭제 하시 겠습니까?")) {
		document.getElementById("deleteForm").submit();
	} else {
		return false;
	}
}

