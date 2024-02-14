function getParamByKey(key){
    var params = location.search;
    // ?id=1&name=zhangsan  ?
    params = params.substring(1);
    // id=1&name=zhangsan  &
    var paramArr = params.split("&");
    if(paramArr != null && paramArr.length > 0){
        for(var i = 0; i < paramArr.length; i++){
            // id=1 =
            var item = paramArr[i];
            var itemArr = item.split("=");
            if(itemArr[0] == key){
                // 返回 1
                return itemArr[1];
            }
        }
    }

    return null;
}