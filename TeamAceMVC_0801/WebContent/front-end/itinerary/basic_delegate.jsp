<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<title>Insert title here</title>
<script>
// $(function(){
// 	$('.table1').on('click', '.add', function(){
// 		$(this).closest('tr').after('<tr><td class="rank"></td><td>資料zzz</td><td><button class="add">Add</button> <button class="remove">Remove</button></td></tr>');
// 		refreshItem();
// 	});
	

	
	$(function(){
		$('.table1').on('click', '.add', function(){
			$(this).closest('tr').after('<img src="<%=request.getContextPath()%>/front-end/home/images/5.png"> ');
			refreshItem();			
		});
	
	$('.table1').on('click', '.remove', function(){
		$(this).closest('tr').remove();
		refreshItem();
	});
	
	
	
	// 常用卻不能用的做法
// 	$('.add').on('click', function(){
// 		$(this).closest('tr').after('<tr><td class="rank"></td><td>資料zzz</td><td><button class="add">Add</button> <button class="remove">Remove</button></td></tr>');
// 		refreshItem();
// 	});
	
// 	$('.remove').on('click', function(){
// 		$(this).closest('tr').remove();
// 		refreshItem();
// 	});

    // 另外一種作法
// 	add();
//     remove();
});

// function add(){
//     $('.add').off().on('click', function(){
// 		$(this).closest('tr').after('<tr><td class="rank"></td><td>資料zzz</td><td><button class="add">Add</button> <button class="remove">Remove</button></td></tr>');
// 		refreshItem();
// 		add();
// 		remove();
// 	});
// }

function add(){
    $('.add').off().on('click', function(){
		$(this).closest('tr').after('<tr><td class="rank"></td><td>資料zzz</td><td><button class="add">Add</button> <button class="remove">Remove</button></td></tr>');
		refreshItem();
		add();
		remove();
	});
}

function remove(){
	$('.remove').off().on('click', function(){
		$(this).closest('tr').remove();
		refreshItem();
	});	
}

function refreshItem(){
	$('.table1 tr').each(function(index, item){
		$(this).find('.rank').text(index);
	});	
};

</script>
</head>
<body>
	<table class="table1" border="1" cellpadding="1">
		<thead>
			<tr>
				<th>表格標題</th>
				<th>表格標題</th>
				<th>表格標題</th>
			</tr>
		</thead>
		<tbody>
			<tr style='background-color:#fee;'>
				<td class="rank">1</td>
				<td>資料xxx</td>
				<td><button class="add">Add</button> <button class="remove">Remove</button></td>
			</tr>
			<tr style='background-color:#fee;'>
				<td class="rank">2</td>
				<td>資料yyy</td>
				<td><button class="add">Add</button> <button class="remove">Remove</button></td>
			</tr>
			
		</tbody>
			<div id="qqq">
				<button >Ad</button>
			</div>

	</table>
</body>

<script>
$('#qqq').click(function(){
	alert(123);
	$(this).append('123');
})
</script>

</html>