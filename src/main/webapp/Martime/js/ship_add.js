$(function(){
    //添加anchorage数据
   $("#anchorage-add").click(function(){
    var data = $("#form-anchorage").serialize();
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
    			}
    		},
    		error: function(result) {
    			alert("发送请求失败:状态码" + result.status);
    		}
    	});
        $("#form-wharfs input").val("");
    })
})