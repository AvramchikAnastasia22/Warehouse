document.getElementById('panal_category').onclick=function (event){
    target=event.target;
    if(target.classList==".category_all" || target.tagName=="H5"){
        mas_pos=document.querySelectorAll(".category_all");
        for(i=0;i<mas_pos.length;i++){
            mas_pos[i].style.backgroundColor="";
        }
        if(target.tagName=="H5"){
            target=target.closest(".category_all");
            target.style.backgroundColor="#ec5252";
            document.getElementById('id_product_employye').value=target.childNodes[1].textContent;
            document.getElementById('category_supplier').value=target.childNodes[3].textContent
            console.log(document.getElementById('id_product_employye').value)
        }
        else {
            target.style.backgroundColor="#ec5252";
            document.getElementById('id_product_employye').value=target.childNodes[1].textContent;
            document.getElementById('category_supplier').value=target.childNodes[3].textContent
            console.log(document.getElementById('id_product_employye').value)
        }
    }
}