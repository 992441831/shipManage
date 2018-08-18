/**
 * 分页显示船舶列表数据
 * @param pno       页码:        示例  1 2 3
 * @param pageSize  当前页记录数
 */

function loadShipByPage(pno1,pageSize1){
	pageSize=pageSize1;
	pno=pno1;
	pattern=1;
$.ajax({
    type:"GET",
    url:"/shipManage/module/shipManage/queryShip.do",
    data:{pno:pno,pageSize:pageSize},
    success:function(data){
        //*3:动态创建表格中多行 id="tbody1"
    	//console.log(data);
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
        $("#tbody1").html(html);       //保存tbody
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
        alert("网络故障请检查！");
    }
})
};






