// 是否需要注销函数
function logout(){
    if(confirm("是否要退出？")){
        jQuery.ajax({
            url:"/user/logout",
            type:"POST",
            data:{

            },
            success:function(res){
                if(res.code == 200 && res.data == 1) {
                    location.href = "blog_list.html";
                }else{
                    alert("注销失败！：" + res.msg);
                }
            }
        });
    }
}