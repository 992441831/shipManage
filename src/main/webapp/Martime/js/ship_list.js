/**
 * 分页显示船舶列表数据
 * @param pno       页码:        示例  1 2 3
 * @param pageSize  当前页记录数
 */

function loadShipByPage(pno1,pageSize1,dataStr1){
    dataStr=dataStr1;
    pageSize=pageSize1;
    pno=pno1;
    pattern=1;
    $.ajax({
        type:"GET",
        url:"/shipManage/module/shipManage/queryShip.do",
        data:{dataStr:dataStr,pno:pno,pageSize:pageSize},
        success:function(data){
            //*3:动态创建表格中多行 id="tbody1"
            //console.log(data.list.length);
            var rows = data.list;
            var html = "";                 //声明空字符串
            if(rows.length==0){
                html="";
            }else{
                for(var i=0;i<rows.length;i++) {//循环拼接
                    var obj = rows[i];
                    if(obj.access_port_date=='1900-01-01'){
                        obj.access_port_date='-';
                    }
                    if(obj.length==0){
                        obj.length='-';
                    }
                    if(obj.tonnage==0){
                        obj.tonnage='-';
                    }
                    if(obj.weigh_date=='2118-01-01'){
                        obj.weigh_date='-';
                    }
                    html += `
                  <tr>   
                                <td>${obj.id}</td>
                                <td>${obj.name}</td>
                                <td>${obj.length}</td>
                                <td>${obj.tonnage}</td>
                                <td>${obj.anchor_date}</td>
                                <td>${obj.target_port}</td>
                                <td>${obj.weigh_date}</td>
                                <td>${obj.anchor_days}</td>
                                <td>${obj.access_port_date}</td>
                                <td>${obj.telephone}</td>
                                <td>${obj.break_rules}</td>
                            </tr>`;
                }//for end
                $("#tbody1").html(html);       //保存tbody
            }

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
//选择添加船舶数据按钮
$("#myselect").change(function(){
    var opt=$("#myselect").val();
    if(opt==0){
        return;
    }else if(opt==1){
        $(".anchorage-jumbotron").show();
        $("#anchorage-close").click(function(){
            $(".anchorage-jumbotron").hide();
        })
    }else if(opt==2){
        $(".vessel-jumbotron").show();
        $("#vessel-close").click(function(){
            $(".vessel-jumbotron").hide();
        })
    }else if(opt==3){
        $(".wharfs-jumbotron").show();
        $("#wharfs-close").click(function(){
            $(".wharfs-jumbotron").hide();
        })
    }
});







