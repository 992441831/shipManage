
//模块六:完成船舶信息搜索功能    9:25--9:35
//1:创建一个函数分页搜索船舶内容
//2:创建一个函数 searchShipByName
//3:参数 pno pageSize name


function searchShipByName(pno,pageSize,name){
    //3:如果网络繁忙，网页会出一片空白状态
    //4:发送ajax请求  data/09_product_search.php
    //5:参数
	pattern=0;
    $.ajax({
        type:"GET",
        url:"/shipManage/module/shipManage/queryShip.do",
        data:{pno:pno,pageSize:pageSize,name:name},
        success:function(data){
            //console.log(data);
            //6:获取返回数据  9:50--9:53
            //7:拼接当前页内容
        	 var rows = data.list;
             var html = "";                 //声明空字符串
             for(var i=0;i<rows.length;i++) {//循环拼接
                 var obj = rows[i];
                 html += `
                   <tr>
                                 <td>${obj.id}</td>
                                 <td>${obj.create_date}</td>
                                 <td>${obj.name}</td>
                                 <td>${obj.length}</td>
                                 <td>${obj.tonnage}</td>
                                 <td>${obj.anchor_date}</td>
                                 <td>${obj.target_port}</td>
                                 <td>${obj.weigh_date}</td>
                                 <td>${obj.anchor_days}</td>
                                 <td>${obj.telephone}</td>
                                 <td>${obj.break_rules}</td>
                             </tr>`;
             }//for end
           
            $("#tbody1").html(html);
          //5:动态创建分页条 1 2 【3】 4 5 最多5页
            var html = "";
            //上上一页
           
            if(pno-2>0){
             html += '<li><a href="#">'+(pno-2)+'</a></li>';
            }
            //上一页 10:53--11:03
            if(pno-1>0){
            html += '<li><a href="#">'+(pno-1)+'</a></li>';
            }
            //当前页
            html += '<li class="active"><a href="#">'+pno+'</a></li>';
            //下一页
            if(pno+1<=data.pageCount){
            html += '<li><a href="#">'+(pno+1)+'</a></li>';
            }
            //下下一页
            if(pno+2<=data.pageCount){
              html += '<li><a href="#">'+(pno+2)+'</a></li>';
            }
            $("#pagination").html(html);
        },
        error:function(){
            alert("网络故障请检查");
        }
    });
  
}













