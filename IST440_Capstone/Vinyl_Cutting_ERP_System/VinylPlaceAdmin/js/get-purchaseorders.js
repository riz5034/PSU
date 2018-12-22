var API = "https://80w8970v24.execute-api.us-east-1.amazonaws.com/default/POM?TableName=Purchase_Order";
var JSONObject = {}

function createPurchaseOrder() {
	var purchaseOrder = {
		"TableName": "Purchase_Order",
		"Item": ""
	}

	var date = new Date();
	date.setHours(date.getHours()-5);
	date.toISOString();

	var payload = {
		"Purchase_Order_Number": parseInt(date.getTime()),
		"Arrival_Date": "N/A",
		"Date_Issued": date,
		"Material_ID": [],
		"Order_Placement_Date": date,
		"Payment_Method": "",
		"Purchase_Order_Total": "",
		"Supplier_ID": "",
		"Warehouse_ID": ""
	};

	// Calculate costs for materials
	// Set to 0 until getting materials payload is complete
	var materials = document.getElementById("create-material").value;
	materials = materials.split(",");

	// Loop through all materials and add to material id ex. payload.Product_Design.push(productDesign);
	for(var i = 0; i < materials.length; i++) {
		payload.Material_ID.push(materials[i]);
	}

	payload.Purchase_Order_Total = 0;

	payload.Payment_Method = "Credit";
	payload.Supplier_ID = document.getElementById("create-material").value;
	payload.Warehouse_ID = document.getElementById("create-warehouse").value;

	purchaseOrder.Item = payload;

	console.log(purchaseOrder);
	// Perform AJAX query
	// POST method
	$.ajax({
		url: "https://80w8970v24.execute-api.us-east-1.amazonaws.com/default/POM",
		type: "POST",
		data: JSON.stringify(purchaseOrder),
		dataType: "json",
		success: function() {
			alert("Successfully created order.");
			location.reload(); 
		},
		error: function() {
			alert("An error occured.");
		}
	});

}

function setUpdateValues() {
	var payload = JSON.parse(sessionStorage.getItem("payload"));
	
	for(var i = 0; i < payload.Items.length; i++) {
		if(payload.Items[i].Purchase_Order_Number == document.getElementById("update-purchase-num").value) {
			// Set attribute boxes
			document.getElementById("update-arrival-date").value = payload.Items[i].Arrival_Date;
			document.getElementById("update-date-issued").value = payload.Items[i].Date_Issued;
			document.getElementById("update-order-placement-date").value = payload.Items[i].Order_Placement_Date;
			document.getElementById("update-material").value = payload.Items[i].Material_ID;
			document.getElementById("update-payment").value = payload.Items[i].Payment_Method;
			document.getElementById("update-purchase-order-total").value = payload.Items[i].Purchase_Order_Total;
			document.getElementById("update-supplier").value = payload.Items[i].Supplier_ID;
			document.getElementById("update-warehouse").value = payload.Items[i].Warehouse_ID;
		}
	}
}

function updatePurchaseOrder() {
	var payloadMaterials = [];
	var materials = document.getElementById("update-material").value;
	materials = materials.split(",");

	for(var i = 0; i < materials.length; i++) {
		payloadMaterials.push(materials[i]);
	}

	var payload = {
		"TableName": "Purchase_Order",
		"Key": {
			"Purchase_Order_Number": parseInt(document.getElementById("update-purchase-num").value)
		},
		"UpdateExpression": "set Arrival_Date = :a, Date_Issued = :b, Material_ID = :c, Order_Placement_Date = :d, Payment_Method = :e, Purchase_Order_Total = :f, Supplier_ID = :g, Warehouse_ID = :h",
		"ExpressionAttributeValues": {
			":a": document.getElementById("update-arrival-date").value,
			":b": document.getElementById("update-date-issued").value,
			":c": payloadMaterials,
			":d": document.getElementById("update-order-placement-date").value,
			":e": document.getElementById("update-payment").value,
			":f": document.getElementById("update-purchase-order-total").value,
			":g": document.getElementById("update-supplier").value,
			":h": document.getElementById("update-warehouse").value
		}
	}

	console.log(payload);

	// PUT method
	$.ajax({
		url: "https://80w8970v24.execute-api.us-east-1.amazonaws.com/default/POM?TableName=Purchase_Order",
		type: "PUT",
		data: JSON.stringify(payload),
		dataType: "json",
		success: function() {
			alert("Successfully updated order.");
			location.reload(); 
		},
		error: function() {
			alert("An error occured.");
		}
	});
}

function deletePurchaseOrder() {
	var payload = {
		"TableName": "Purchase_Order",
		"Key": {
			"Purchase_Order_Number": parseInt(document.getElementById("delete-order-num").value)
		}
	}

	console.log(payload);

	// DELETE method
	$.ajax({
		url: "https://80w8970v24.execute-api.us-east-1.amazonaws.com/default/POM",
		type: "DELETE",
		data: JSON.stringify(payload),
		dataType: "json",
		success: function() {
			alert("Successfully deleted order.");
			location.reload(); 
		},
		error: function() {
			alert("An error occured.");
		}
	});
}

function search() {
	var counter = 1;
	var purchaseOrderNum =  document.getElementById("search-purchase-order-number").value;
	var arrivalDate = document.getElementById("search-arrival-date").value;
	var dateIssued = document.getElementById("search-date-issued").value;
	var orderPlacementDate = document.getElementById("search-order-placement-date").value;
	var material = document.getElementById("search-material").value;
	var paymentMethod = document.getElementById("search-payment-method").value;
	var orderTotal = document.getElementById("search-purchase-order-total").value;
	var supplier = document.getElementById("search-supplier").value;
	var warehouse = document.getElementById("search-warehouse").value;

	payload = JSON.parse(sessionStorage.getItem("payload"));
	payloadRefined = {
		"Items": []
	};
	var add = true;

	for(var i = 0; i < payload.Items.length; i++) {
		add = true;

		if(purchaseOrderNum != "" && !String(payload.Items[i].Purchase_Order_Number).includes(purchaseOrderNum)) {
			add = false;
		}

		if(arrivalDate != "" && !String(payload.Items[i].Arrival_Date).includes(arrivalDate)) {
			add = false;
		}

		if(dateIssued != "" && !String(payload.Items[i].Date_Issued).includes(dateIssued)) {
			add = false;
		}

		if(orderPlacementDate != "" && !String(payload.Items[i].Order_Placement_Date).includes(orderPlacementDate)) {
			add = false;
		}

		if(material != "" && !String(payload.Items[i].Material_ID).includes(material)) {
			add = false;
		}

		if(paymentMethod != "" && !String(payload.Items[i].Payment_Method).includes(paymentMethod)) {
			add = false;
		}

		if(orderTotal != "" && !String(payload.Items[i].Purchase_Order_Total).includes(orderTotal)) {
			add = false;
		}

		if(supplier != "" && !String(payload.Items[i].Supplier_ID).includes(supplier)) {
			add = false;
		}

		if(warehouse != "" && !String(payload.Items[i].Warehouse_ID).includes(warehouse)) {
			add = false;
		}

		if(add) {
			payloadRefined.Items.push((payload.Items[i]));
		}
	}


	// Remove all elements of the current table
	var myNode = document.getElementById("searchPurchaseTable");
	while (myNode.firstChild) {
		myNode.removeChild(myNode.firstChild);
	}
	
	for (var i = 0; i < payloadRefined.Items.length; i++) {
		var purchaseOrderNum = payloadRefined.Items[i].Purchase_Order_Number;
		var arrivalDate = payloadRefined.Items[i].Arrival_Date;
		var dateIssued =payloadRefined.Items[i].Date_Issued;
		var orderPlacementDate = payloadRefined.Items[i].Order_Placement_Date;
		var material = payloadRefined.Items[i].Material_ID;
		var paymentMethod = payloadRefined.Items[i].Payment_Method;
		var orderTotal = payloadRefined.Items[i].Purchase_Order_Total;
		var supplier = payloadRefined.Items[i].Supplier_ID;
		var warehouse = payloadRefined.Items[i].Warehouse_ID;

		var tableRow = `
			<tr>
				<td>${counter}</td>
                <td>${purchaseOrderNum}</td>
                <td>${arrivalDate}</td>
                <td>${dateIssued}</td>
                <td>${orderPlacementDate}</td>
                <td>${material}</td>
                <td>${paymentMethod}</td>
                <td>${orderTotal}</td>
                <td>${supplier}</td>
                <td>${warehouse}</td>
            </tr>
		`;

		// Append to table
		$('#searchPurchaseTable').append(tableRow);
		counter += 1;
	}
}

function setup(json) {
	var counter = 1;

	for (var i = 0; i < json.Items.length; i++) {
		var purchaseOrderNum = json.Items[i].Purchase_Order_Number;
		var arrivalDate = json.Items[i].Arrival_Date;
		var dateIssued = json.Items[i].Date_Issued;
		var materialId = json.Items[i].Material_ID;
		var orderPlacementDate = json.Items[i].Order_Placement_Date;
		var paymentMethod = json.Items[i].Payment_Method;
		var purcharseOrderTotal = json.Items[i].Purchase_Order_Total;
		var supplierId = json.Items[i].Supplier_ID;
		var warehouseId = json.Items[i].Warehouse_ID;


		var tableRow = `
			<tr>
                <td>${counter}</td>
                <td>${purchaseOrderNum}</td>
                <td>${arrivalDate}</td>
                <td>${dateIssued}</td>
                <td>${materialId}</td>
                <td>${orderPlacementDate}</td>
                <td>${paymentMethod}</td>
                <td>${purcharseOrderTotal}</td>
                <td>${supplierId}</td>
                <td>${warehouseId}</td>
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
				sessionStorage.setItem("payload", JSON.stringify(json));
				setup(json);
			});
	} catch (err) {
		console.log(err);
	}
}

window.onload = grab();

