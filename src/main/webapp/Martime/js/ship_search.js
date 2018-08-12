
//模块六:完成船舶信息搜索功能    9:25--9:35
//1:创建一个函数分页搜索船舶内容
//2:创建一个函数 searchShipByName
//3:参数 pno pageSize name


function searchShipByName(name){
    //3:如果网络繁忙，网页会出一片空白状态
    //4:发送ajax请求  data/09_product_search.php
    //5:参数
    $.ajax({
        type:"GET",
        url:"/shipManage/module/shipManage/queryShipByName.do",
        data:{"name":name},
        success:function(data){
            //console.log(data);
            //6:获取返回数据  9:50--9:53
            //7:拼接当前页内容
            var html = "";
         var item=data.shipObj;
                html += `
              <tr>
                <td>${item.id}</td>
                <td>${item.create_date}</td>
                <td>${item.name}</td>
                <td>${item.length}</td>
                <td>${item.tonnage}</td>
                <td>${item.anchor_date}</td>
                <td>${item.target_port}</td>
                <td>${item.weigh_date}</td>
                <td>${item.anchor_days}</td>
                <td>${item.telephone}</td>
                <td>${item.break_rules}</td>
                        </tr>`;
           
            $("#tbody1").html(html);
          /*//5:动态创建分页条 1 2 【3】 4 5 最多5页
            var html = "";
            //上上一页
            if(data.pno-2>0){
             html += `<li><a href="#">${data.pno-2}</a></li>`;
            }
            //上一页 10:53--11:03
            if(data.pno-1>0){
            html += `<li><a href="#">${data.pno-1}</a></li>`;
            }
            //当前页
            html += `<li class="active"><a href="#">${data.pno}</a></li>`;
            //下一页
            if(data.pno+1<=data.pageCount){
            html += `<li><a href="#">${data.pno+1}</a></li>`;
            }
            //下下一页
            if(data.pno+2<=data.pageCount){
              html += `<li><a href="#">${data.pno+2}</a></li>`;
            }
            $("#pagination").html(html);*/
            
            $("#pagination").html("");
        },
        error:function(){
            alert("网络故障请检查");
        }
    });
}


//处理分页点击事件
//1:获取所有分页按钮绑定点击事件
//2:pno pageSize name
//3:searchShipByName(1,8,"");
/*$("#pagination").on("click","li a",function(e){
    e.preventDefault();
    var name = $(this).data("page");
    var pno = parseInt($(this).html());
    searchShipByName(pno,8,name);*/












