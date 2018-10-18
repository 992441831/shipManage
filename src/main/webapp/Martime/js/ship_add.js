$(function(){
    //添加anchorage数据
   $("#anchorage-add").click(function(){
    var data = $("#form-anchorage").serialize();
	   console.log(data);
    $.ajax({
		type: "get",
		//dataType: "json",
		url:"/shipManage/module/shipManage/insertAnchorageSource.do",
		data:data,
		success: function(result)
		{
			console.log(result);
			var status=result.status;
			var res = $("#div");
			if(status==0){
				alert(result.msg);
				res.append(JSON.stringify(result.newAddr));
				res.append("<br>");
			}else{
				alert(result.msg);
			}
		},
		error: function(result) {
			alert("发送请求失败:状态码" + result.status);
		}
	});
    $("#form-anchorage input").val("");
   });
   //添加vessel数据
    $("#vessel-add").click(function(){
        var data = $("#form-vessel").serialize();
		console.log(data);
		$.ajax({
    		type: "get",
    		//dataType: "json",
    		url:"/shipManage/module/shipManage/insertVesselsSource.do",
    		data:data,
    		success: function(result)
    		{
    			console.log(result);
    			var status=result.status;
    			var res = $("#div");
    			if(status==0){
    				alert(result.msg);
    				res.append(JSON.stringify(result.newAddr));
    				res.append("<br>");
    			}else{
					alert(result.msg);
				}
    		},
    		error: function(result) {
    			alert("发送请求失败:状态码" + result.status);
    		}
    	});
        $("#form-vessel input").val("");
    });
    //添加wharfs数据
    $("#wharfs-add").click(function(){
        var data = $("#form-wharfs").serialize();
		console.log(data);
		$.ajax({
    		type: "get",
    		//dataType: "json",
    		url:"/shipManage/module/shipManage/insertWharfsSource.do",
    		data:data,
    		success: function(result)
    		{
    			console.log(result);
    			var status=result.status;
    			var res = $("#div");
    			if(status==0){
    				alert(result.msg);
    				res.append(JSON.stringify(result.newAddr));
    				res.append("<br>");
    			}else{
					alert(result.msg);
				}
    		},
    		error: function(result) {
    			alert("发送请求失败:状态码" + result.status);
    		}
    	});
        $("#form-wharfs input").val("");
    })

	//获取当前时间，格式YYYY-MM-DD
	var dateStr;
	function getNowFormatDate() {
		var date = new Date();
		var seperator1 = "-";
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var currentdate = year + seperator1 + month + seperator1 + strDate;
		return currentdate;
	}
	dateStr = getNowFormatDate();
	console.log(dateStr);
	$("#wharfs_date").val(dateStr);
	$("#anchorage_date").val(dateStr);
})