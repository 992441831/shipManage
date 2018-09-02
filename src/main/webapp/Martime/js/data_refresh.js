function shipDataRefresh(){
    $.ajax({
        type:'GET',
        url:"/shipManage/module/shipManage/refreshData.do",
        data:{
        },
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
    })
}