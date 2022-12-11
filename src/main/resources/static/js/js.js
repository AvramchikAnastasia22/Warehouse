function hidden_object(data){
    data.hidden=true;
}
function background_default(data){
    data.style.backgroundColor="";
}
function color_default(date){
    date.style.color="";
}
function color_border_default(date){
    data.style.border="";
}
function check_photo_supplier(){
    document.getElementById('label_supplier').style  .color="red";
    setTimeout(color_default,5000,document.getElementById('label_supplier'));
}
function check_supplier_category(){
    document.getElementById('category_supplier').style.border="2px solid red";
    setTimeout(color_border_default,5000, document.getElementById('category_supplier'));
}