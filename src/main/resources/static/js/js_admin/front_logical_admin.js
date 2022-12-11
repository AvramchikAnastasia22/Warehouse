function open_settins(){
    document.getElementById('settins_admin').hidden=false;
    document.getElementById('product_admin').hidden=true;
    document.getElementById('supplier_admin').hidden=true;
    document.getElementById('ban_list_admin').hidden=true;
    document.getElementById('user_admin').hidden=true;

}
function open_ban_list(){
    document.getElementById('settins_admin').hidden=true;
    document.getElementById('product_admin').hidden=true;
    document.getElementById('supplier_admin').hidden=true;
    document.getElementById('ban_list_admin').hidden=false;
    document.getElementById('user_admin').hidden=true;
}
function open_supplier(){
    document.getElementById('settins_admin').hidden=true;
    document.getElementById('product_admin').hidden=true;
    document.getElementById('supplier_admin').hidden=false;
    document.getElementById('ban_list_admin').hidden=true;
    document.getElementById('user_admin').hidden=true;
}
function open_registration_supplier(){
    document.getElementById('reg_supplier').style.color="#866c03";
    document.getElementById('lis_supplier').style.color="";
    date1=new Date();
    var date="";
    if(date1.getDate()<10){
        date="0";
    }
    date=date+date1.getDate()+".";
    if(date1.getMonth()+1<10)
    {
        date=date+"0";
    }
    date=date+(date1.getMonth()+1)+".";
    date=date+date1.getFullYear();
    document.getElementById('today_date_reg_supplier').value=date;
    document.getElementById('add_supplier').hidden=false;
    document.getElementById('list_supplier').hidden=true;
}
function open_list_supplier(){
    document.getElementById('reg_supplier').style.color="";
    document.getElementById('lis_supplier').style.color="#866c03";
    document.getElementById('add_supplier').hidden=true;
    document.getElementById('list_supplier').hidden=false;
}
function open_user(){
    document.getElementById('settins_admin').hidden=true;
    document.getElementById('product_admin').hidden=true;
    document.getElementById('supplier_admin').hidden=true;
    document.getElementById('ban_list_admin').hidden=true;
    document.getElementById('user_admin').hidden=false;
}
function open_product(){
    document.getElementById('settins_admin').hidden=true;
    document.getElementById('product_admin').hidden=false;
    document.getElementById('supplier_admin').hidden=true;
    document.getElementById('ban_list_admin').hidden=true;
    document.getElementById('user_admin').hidden=true;
}
function open_list_product(){
    document.getElementById('reg_prod').style.color="";
    document.getElementById('list_prod').style.color="#866c03";
    document.getElementById('add_product').hidden=true;
    document.getElementById('list_product').hidden=false;
}
function open_block_menu(data){
    document.getElementById('block_menu').hidden=false;
    console.log(document.getElementById('comment_ban').value);
    document.getElementById('ubnlock_id').value=data.name;
}
function close_block_menu(){
    document.getElementById('block_menu').hidden=true;
}
function open_add_product(){
    document.getElementById('reg_prod').style.color="#866c03";
    document.getElementById('list_prod').style.color="";
    date1=new Date();
    var date="";
    if(date1.getDate()<10){
        date="0";
    }
    date=date+date1.getDate()+".";
    if(date1.getMonth()+1<10)
    {
        date=date+"0";
    }
    date=date+(date1.getMonth()+1)+".";
    date=date+date1.getFullYear();
    document.getElementById('today_date_reg_product').value=date;
    document.getElementById('add_product').hidden=false;
    document.getElementById('list_product').hidden=true;
}
function add_new_photo_user(){
    let file=document.getElementById('personal_photo').files[0];
    let reader=new FileReader();
    reader.readAsDataURL(file);
    reader.onload=function (){
        document.getElementById('pers_prev').src=reader.result;
    }
}
function add_new_photo_product(){
    let file=document.getElementById('add_photo_product').files[0];
    let reader=new FileReader();
    reader.readAsDataURL(file);
    reader.onload=function (){
        document.getElementById('product_prev').src=reader.result;
    }
}
function add_new_photo_supplier(){
    let file=document.getElementById('add_photo_supplier').files[0];
    let reader=new FileReader();
    reader.readAsDataURL(file);
    reader.onload=function (){
        document.getElementById('photo_supplier').src=reader.result;
    }
}
function hover_product(){
    document.getElementById('lab_product').hidden=false;
}
function hover_supplier(){
    document.getElementById('lab_supplier').hidden=false;
}
function hover_exit(){
    document.getElementById('lab_exit').hidden=false;
}
function hover_user(){
    document.getElementById('lab_user').hidden=false;
}
function hover_settins(){
    document.getElementById('lab_settins').hidden=false;
}
function hover_ban_list(){
    document.getElementById('lab_ban_list').hidden=false;
}
function leave_product(){
    document.getElementById('lab_product').hidden=true;
}
function leave_supplier() {
    document.getElementById('lab_supplier').hidden=true;

}
function leave_exti(){
    document.getElementById('lab_exit').hidden=true;

}
function leave_user(){
    document.getElementById('lab_user').hidden=true;

}
function leave_settins(){
    document.getElementById('lab_settins').hidden=true;

}
function leave_ban_list(){
    document.getElementById('lab_ban_list').hidden=true;

}
function search_table_ban_list(){
    var phrase = document.getElementById('input_find_ban_list');
    var table = document.getElementById('table_ban_list');
    var regPhrase = new RegExp(phrase.value, 'i');
    var flag = false;
    for (var i = 1; i < table.rows.length; i++) {
        flag = false;
        for (var j = table.rows[i].cells.length - 1; j >= 0; j--) {
            flag = regPhrase.test(table.rows[i].cells[j].innerHTML);
            if (flag) break;
        }
        if (flag) {
            table.rows[i].style.display = "";
        } else {
            table.rows[i].style.display = "none";
        }

    }
}
