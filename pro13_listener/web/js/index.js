function page(pageNo,pageCount){
    console.log(pageNo);
    if(pageNo<=0){//如果请求的页码小于0，不动
    }
    else if(pageNo<=pageCount){//如果请求的页码小于等于总页数，正常请求
        window.location.href="products.do?operate=index&pageNo="+pageNo;
    }
    else{//如果请求的页码大于总页数，不发送请求

    }
}