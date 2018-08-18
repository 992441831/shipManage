function shipDataRefresh(){
	$.ajax({
		type:'GET',
		url:"/shipManage/module/shipManage/refreshData.do",
	    data:{
	    },
	    success: function(result)
	    {
		  console.log(result);
	   },
	   error: function(result) {
		//alert("发送请求失败:状态码" + result.status);
	}
})
}