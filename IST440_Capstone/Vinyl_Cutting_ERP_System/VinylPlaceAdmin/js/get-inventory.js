var API = "https://4j6xf3c7w5.execute-api.us-east-1.amazonaws.com/default/IM?TableName=Material";
var JSONObject = {}

function setup(json) {
	console.log(json);
	
	var counter = 1;

	for (var i = 0; i < json.Items.length; i++) {
		var materialID = json.Items[i].Material_ID;
		var materialDescription = json.Items[i].Material_Description;
		var materialLevel = json.Items[i].Material_Level;
		var materialComponent = json.Items[i].Material_Components;
		var materialCount = json.Items[i].Material_Count;
		var productSerial = json.Items[i].Product_Serial_Number;
		var warehouseID = json.Items[i].Warehouse_ID;


		var tableRow = `
			<tr>
                <td>${counter}</td>
                <td>${materialID}</td>
                <td>${materialDescription}</td>
                <td>${materialLevel}</td>
                <td>${materialComponent}</td>
                <td>${materialCount}</td>
                <td>${productSerial}</td>
                <td>${warehouseID}</td>
            </tr>
		`;

		// Append to table
		$('#inventoryTable').append(tableRow);
		counter += 1;
	}
}

function createMaterial() {
	var material = {
		"TableName": "Material",
		"Item": ""
	}

	var payload = {
		"Material_ID": document.getElementById("create-material-id").value,
		"Material_Description": document.getElementById("create-material-desc").value,
		"Material_Level": document.getElementById("create-material-level").value,
		"Material_Components": document.getElementById("create-material-component").value,
		"Material_Count": document.getElementById("create-material-count").value,
		"Product_Serial_Number": document.getElementById("create-material-count").value,
		"Warehouse_ID": document.getElementById("create-warehouse-id").value
	}

	material.Item = payload;

	console.log(material);

	$.ajax({
		url: "https://4j6xf3c7w5.execute-api.us-east-1.amazonaws.com/default/IM",
		type: "POST",
		data: JSON.stringify(material),
		dataType: "json",
		success: function() {
			alert("Successfully created material.");
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
		if(payload.Items[i].Material_ID == document.getElementById("update-material-id").value) {
			// Set attribute boxes
			document.getElementById("update-material-desc").value = payload.Items[i].Material_Description;
			document.getElementById("update-material-level").value = payload.Items[i].Material_Level;
			document.getElementById("update-material-component").value = payload.Items[i].Material_Components;
			document.getElementById("update-material-count").value = payload.Items[i].Material_Count;
			document.getElementById("update-product-serial").value = payload.Items[i].Product_Serial_Number;
			document.getElementById("update-warehouse-id").value = payload.Items[i].Warehouse_ID;

		}
	}
}

function updateMaterial() {
	var payload = {
		"TableName": "Material",
		"Key": {
			"Material_ID": document.getElementById("update-material-id").value
		},
		"UpdateExpression": "set Material_Description = :a, Material_Level = :b, Material_Components = :c, Material_Count = :d, Product_Serial_Number = :e, Warehouse_ID = :f",
		"ExpressionAttributeValues": {
			":a": document.getElementById("update-material-desc").value,
			":b": document.getElementById("update-material-level").value,
			":c": document.getElementById("update-material-component").value,
			":d": document.getElementById("update-material-count").value,
			":e": document.getElementById("update-product-serial").value,
			":f": document.getElementById("update-warehouse-id").value
		},
		"ReturnValues": "UPDATED_NEW"
	}

	console.log(payload);

	// PUT method
	$.ajax({
		url: "https://4j6xf3c7w5.execute-api.us-east-1.amazonaws.com/default/IM?TableName=Material",
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

function deleteMaterial() {
	var payload = {
		"TableName": "Material",
		"Key": {
			"Material_ID": document.getElementById("delete-material").value
		}
	}

	// DELETE method
	$.ajax({
		url: "https://4j6xf3c7w5.execute-api.us-east-1.amazonaws.com/default/IM",
		type: "DELETE",
		data: JSON.stringify(payload),
		dataType: "json",
		success: function() {
			alert("Successfully deleted material.");
			location.reload(); 
		},
		error: function() {
			alert("An error occured.");
		}
	});
}

function search() {
	var counter = 1;
	var materialID = document.getElementById("search-material-id").value;
	var materialDescription = document.getElementById("search-material-desc").value;
	var materialLevel = document.getElementById("search-material-level").value;
	var materialComponent = document.getElementById("search-material-component").value;
	var materialCount = document.getElementById("search-material-count").value;
	var productSerial = document.getElementById("search-product-serial").value;
	var warehouseID = document.getElementById("search-warehouse").value;

	payload = JSON.parse(sessionStorage.getItem("payload"));
	payloadRefined = {
		"Items": []
	};
	var add = true;

	for(var i = 0; i < payload.Items.length; i++) {
		add = true;

		if(materialID != "" && !String(payload.Items[i].Material_ID).includes(materialID)) {
			add = false;
		}

		if(materialDescription != "" && !String(payload.Items[i].Material_Description).includes(materialDescription)) {
			add = false;
		}

		if(materialLevel != "" && !String(payload.Items[i].Material_Level).includes(materialLevel)) {
			add = false;
		}

		if(materialComponent != "" && !String(payload.Items[i].Material_Components).includes(materialComponent)) {
			add = false;
		}

		if(materialCount != "" && !String(payload.Items[i].Material_Count).includes(materialCount)) {
			add = false;
		}

		if(productSerial != "" && !String(payload.Items[i].Product_Serial_Number).includes(productSerial)) {
			add = false;
		}

		if(warehouseID != "" && !String(payload.Items[i].Warehouse_ID).includes(warehouseID)) {
			add = false;
		}

		if(add) {
			payloadRefined.Items.push((payload.Items[i]));
		}
	}

	// Remove all elements of the current table
	var myNode = document.getElementById("searchMaterialTable");
	while (myNode.firstChild) {
		myNode.removeChild(myNode.firstChild);
	}
	
	for (var i = 0; i < payloadRefined.Items.length; i++) {
		var materialID = payloadRefined.Items[i].Material_ID;
		var materialDescription = payloadRefined.Items[i].Material_Description;
		var materialLevel = payloadRefined.Items[i].Material_Level;
		var materialComponent = payloadRefined.Items[i].Material_Components;
		var materialCount = payloadRefined.Items[i].Material_Count;
		var productSerial = payloadRefined.Items[i].Product_Serial_Number;
		var warehouseID = payloadRefined.Items[i].Warehouse_ID;

		var tableRow = `
			<tr>
				<td>${counter}</td>
                <td>${materialID}</td>
                <td>${materialDescription}</td>
                <td>${materialLevel}</td>
                <td>${materialComponent}</td>
                <td>${materialCount}</td>
                <td>${productSerial}</td>
                <td>${warehouseID}</td>
            </tr>
		`;

		// Append to table
		$('#searchMaterialTable').append(tableRow);
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