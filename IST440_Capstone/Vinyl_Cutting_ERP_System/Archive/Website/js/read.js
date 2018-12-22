var API ="https://mhq011eieb.execute-api.us-east-1.amazonaws.com/product";
var JSONObject ={
	"TableName": "Product",
	"Type" : "Full",
	"id" : "YEL477"

}
function setup(json){

	var Objectdiv = '';
	console.log(Objectdiv);
	for (var i = json.Items.length - 1; i >= 0; i--) {
		Objectdiv+='<div class="box">';
		Objectdiv+='<img src='+json.Items[i].Image+'>';
		Objectdiv+='<h3>'+json.Items[i].Product_Name+'</h3>';
		Objectdiv+='<p>'+json.Items[i].Product_Description+'</p>';
		Objectdiv+='</div>'
	}
	console.log(Objectdiv);

	$('#container_items').append(Objectdiv);
	//Setup the feild
	/*document.getElementById("branch_code").value = json.Item.branch_code;
	document.getElementById("JSONPurchaseOrder_id").value = json.Item.JSONPurchaseOrder_id;
	document.getElementById("Order_total_Price").value = json.Item.Order_total_Price;
	document.getElementById("order_type").value = json.Item.order_type;
	document.getElementById("po_date").value = json.Item.po_date;
	document.getElementById("reference_no").value = json.Item.reference_no;
	document.getElementById("remarks").value= json.Item.remarks;
	document.getElementById("supplier_id").value = json.Item.supplier_id;
	document.getElementById("Supplier_Name").value = json.Item.Supplier_Name;
	document.getElementById("supplier_code").value = json.Item.supplier_code;
	document.getElementById("item_code").value = json.Item.itemList[0].item_code;
	document.getElementById("item_Name").value = json.Item.itemList[0].item_Name;
	document.getElementById("item_type").value = json.Item.itemList[0].item_type;
	document.getElementById("qty").value = json.Item.itemList[0].qty;
	document.getElementById("unit_price").value = json.Item.itemList[0].unit_price;*/
}
function clearall(){
	//clear all
	document.getElementById("branch_code").value="";
	document.getElementById("JSONPurchaseOrder_id").value="";
	document.getElementById("Order_total_Price").value="";
	document.getElementById("order_type").value="";
	document.getElementById("po_date").value="";
	document.getElementById("reference_no").value="";
	document.getElementById("remarks").value="";
	document.getElementById("supplier_id").value="";
	document.getElementById("Supplier_Name").value="";
	document.getElementById("supplier_code").value="";
	document.getElementById("item_code").value="";
	document.getElementById("item_Name").value="";
	document.getElementById("item_type").value="";
	document.getElementById("qty").value="";
	document.getElementById("unit_price").value="";
}

//get the payload
function grab() {
  try {
    $.getJSON(API,JSONObject)
	.done(function (json) {
	setup(json);
	});
  } catch(err){
      console.log(err);
    }
}

window.onload = grab();