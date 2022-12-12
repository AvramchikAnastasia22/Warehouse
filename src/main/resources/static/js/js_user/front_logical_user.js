function open_product_list_user(){
    document.getElementById('open_list_product_user').style.color="#866c03";
    document.getElementById('open_take_order_user').style.color="";
    document.getElementById('list_servie_user_all').hidden=false;
    document.getElementById('take_order_all').hidden=true;
}
function open_sproduct_take_order(){
    document.getElementById('open_list_product_user').style.color=""
    document.getElementById('open_take_order_user').style.color="#866c03";
    document.getElementById('list_servie_user_all').hidden=true;
    document.getElementById('take_order_all').hidden=false;
}
function open_product_user(){
    document.getElementById('settins_admin').hidden=true;
    document.getElementById('product_user').hidden=false;
    document.getElementById('supplier_user').hidden=true;
    document.getElementById('history_user').hidden=true;
}
function open_settins_user(){
    document.getElementById('settins_admin').hidden=false;
    document.getElementById('product_user').hidden=true;
    document.getElementById('supplier_user').hidden=true;
    document.getElementById('history_user').hidden=true;
}
function open_history_user(){
    document.getElementById('settins_admin').hidden=true;
    document.getElementById('product_user').hidden=true;
    document.getElementById('supplier_user').hidden=true;
    document.getElementById('history_user').hidden=false;
}
function open_personal_history_user(){
    document.getElementById('list_personal_history_user').style.color='#866c03';
    document.getElementById('nearest_day_user').style.color="";
    document.getElementById('personal_history').hidden=false;
    document.getElementById('nearest_day').hidden=true;
}
function open_orders_user(){
    document.getElementById('list_personal_history_user').style.color='';
    document.getElementById('nearest_day_user').style.color="#866c03";
    document.getElementById('personal_history').hidden=true;
    document.getElementById('nearest_day').hidden=false;
}
function open_supplier_user(){
    document.getElementById('settins_admin').hidden=true;
    document.getElementById('product_user').hidden=true;
    document.getElementById('supplier_user').hidden=false;
    document.getElementById('history_user').hidden=true;
}
function open_take_order(){
    document.getElementById('take_order').hidden=true;
    document.getElementById('id_emp').value="";
    document.getElementById('id_ser').value="";
    document.getElementById('address').value="";
    document.getElementById('FIO_supplier').value="";
    document.getElementById('order_date').value="";
    mas=document.querySelectorAll(".emp_order");
    for (i=0;i<mas.length;i++){
        mas[i].hidden=false;
    }
}
function hover_settins_user(){document.getElementById('settins_lab').hidden=false;}
function hover_history_user(){document.getElementById('history_lab').hidden=false;}
function hover_supplier_user(){document.getElementById('supplier_lab').hidden=false;}
function hover_exit_user(){document.getElementById('exit_lab').hidden=false;}
function hover_product_user(){document.getElementById('product_lab').hidden=false;}
function leave_settins_user(){document.getElementById('settins_lab').hidden=true;}
function leave_history_user(){document.getElementById('history_lab').hidden=true;}
function leave_supplier_user(){document.getElementById('supplier_lab').hidden=true;}
function leave_exit_user(){document.getElementById('exit_lab').hidden=true;}
function leave_product_user(){document.getElementById('product_lab').hidden=true;}
function add_new_photo_user_user(){
    let file=document.getElementById('personal_photo').files[0];
    let reader=new FileReader();
    reader.readAsDataURL(file);
    reader.onload=function (){
        document.getElementById('pers_prev').src=reader.result;
    }
}
function search_history(){
    var phrase = document.getElementById('serch_hist');
    var table = document.getElementById('table_history');
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
function search_orders(){
    var phrase = document.getElementById('search_rec');
    var table = document.getElementById('table_orders');
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
