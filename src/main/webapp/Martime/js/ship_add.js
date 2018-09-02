$(function(){
    //添加anchorage数据
   $("#anchorage-add").click(function(){
    var data = $("#form-anchorage").serialize();
    console.log(data);
    $("#form-anchorage input").val("");
   });
   //添加vessel数据
    $("#vessel-add").click(function(){
        var data = $("#form-vessel").serialize();
        console.log(data);
        $("#form-vessel input").val("");
    });
    //添加wharfs数据
    $("#wharfs-add").click(function(){
        var data = $("#form-wharfs").serialize();
        console.log(data);
        $("#form-wharfs input").val("");
    })
})