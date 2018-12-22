var API = "https://80w8970v24.execute-api.us-east-1.amazonaws.com/default/POM?TableName=Purchase_Order";
var JSONObject = {}

function setup(json) {
	console.log(json);
	var counter = 1;

	for (var i = 0; i < json.Items.length; i++) {
		var purchaseOrderNum = json.Items[i].Purchase_Order_Number;
		var orderPlaced = json.Items[i].Order_Placement_Date;
		var product = json.Items[i].Product_Design;
		var price = json.Items[i].Pruchase_Order_Price;
		var warehouse = json.Items[i].Warehouse_ID;
		var status = "Processing";

		var tableRow = `
			<tr>
                <td>${counter}</td>
                <td>${purchaseOrderNum}</td>
                <td>${orderPlaced}</td>
                <td>${product}</td>
                <td>${price}</td>
                <td>${warehouse}</td>
                <td><span class="label label-info">${status}</span></td>
            </tr>
		`;

		// Append to table
		$('#purchaseTable').append(tableRow);
		counter += 1;
	}

}

//get the payload
function grab() {
	try {
		$.getJSON(API, JSONObject)
			.done(function (json) {
				setup(json);
			});
	} catch (err) {
		console.log(err);
	}
}

window.onload = grab();

