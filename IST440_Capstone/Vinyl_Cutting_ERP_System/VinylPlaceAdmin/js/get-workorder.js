var API = "https://7gdbped59k.execute-api.us-east-1.amazonaws.com/prod?TableName=Work_Order";
var JSONObject = {}

function setup(json) {
	console.log(json);
	
	var counter = 1;

	for (var i = 0; i < json.Items.length; i++) {
		var completionDate = json.Items[i].Completion_Date;
		var issuedDate = json.Items[i].Issued_Date;
		var materials = json.Items[i].Materials;
		var productSerialNumber = json.Items[i].Product_Serial_Number;
		var salesOrderNum = json.Items[i].Sales_Order_Number
		var status = json.Items[i].Order_Status;
		var warehouse = json.Items[i].Warehouse_ID;
		var workOrderNum = json.Items[i].Work_Order_Number;


		var tableRow = `
			<tr>
                <td>${counter}</td>
                <td>${workOrderNum}</td>
                <td>${issuedDate}</td>
                <td>${completionDate}</td>
                <td>${materials}</td>
                <td>${productSerialNumber}</td>
                <td>${salesOrderNum}</td>
                <td>${warehouse}</td>
                <td>${status}</td>
            </tr>
		`;

		// Append to table
		$('#workOrderTable').append(tableRow);
		counter += 1;
	}
}

function createOrder() {
	var materials =  document.getElementById("create-materials").value;
	materials = materials.split(",");

	var productSerial = document.getElementById("create-product-serial").value;
	var salesOrderNum = document.getElementById("create-sales-order").value;
	var warehouse = document.getElementById("create-warehouse").value;
	var status = document.getElementById("create-status").value;

	var date = new Date();
	date.setHours(date.getHours()-5);
	date.toISOString();

	var payload = {
		"TableName": "Work_Order",
		"Item": ""
	}

	var item = {
		"Work_Order_Number": (date.getTime()).toString(),
		"Completion_Date": "N/A",
		"Issued_Date": date,
		"Materials": materials,
		"Product_Serial_Number": productSerial,
		"Sales_Order_Number": salesOrderNum,
		"Order_Status": status,
		"Warehouse_ID": warehouse
	}

	payload.Item = item;

	console.log(payload);

	// POST method
	$.ajax({
		url: "https://7gdbped59k.execute-api.us-east-1.amazonaws.com/prod",
		type: "POST",
		data: JSON.stringify(payload),
		dataType: "json",
		success: function() {
			alert("Sucessfully created order!")
			location.reload();
		},
		error: function(jqXHR, textStatus, errorThrown) {
            alert('An error occurred... Look at the console (F12 or Ctrl+Shift+I, Console tab) for more information!');

            $('#result').html('<p>status code: '+jqXHR.status+'</p><p>errorThrown: ' + errorThrown + '</p><p>jqXHR.responseText:</p><div>'+jqXHR.responseText + '</div>');
            console.log('jqXHR:');
            console.log(jqXHR);
            console.log('textStatus:');
            console.log(textStatus);
            console.log('errorThrown:');
            console.log(errorThrown);
        }
	});
}

function setUpdateValues() {
	var payload = JSON.parse(sessionStorage.getItem("payload"));
	for(var i = 0; i < payload.Items.length; i++) {
		if(payload.Items[i].Work_Order_Number == document.getElementById("update-work-order-num").value) {
			document.getElementById("update-issued-date").value = payload.Items[i].Issued_Date;
			document.getElementById("update-completion-date").value = payload.Items[i].Completion_Date;
			document.getElementById("update-materials").value = payload.Items[i].Materials;
			document.getElementById("update-product-serial").value = payload.Items[i].Product_Serial_Number;
			document.getElementById("update-sales-order").value = payload.Items[i].Sales_Order_Number;
			document.getElementById("update-warehouse").value = payload.Items[i].Warehouse_ID;
			document.getElementById("update-status").value = payload.Items[i].Order_Status;
		}
	}
}

function updateOrder() {
	var materials = (document.getElementById("update-materials").value).split(",");

	var payload = {
		"TableName": "Work_Order",
		"Key": {
			"Work_Order_Number": document.getElementById("update-work-order-num").value
		},
		"UpdateExpression": "set Issued_Date = :a, Completion_Date = :b, Materials = :c, Product_Serial_Number = :d, Sales_Order_Number = :e, Warehouse_ID = :f, Order_Status = :g",
		"ExpressionAttributeValues": {
			":a": document.getElementById("update-issued-date").value,
			":b": document.getElementById("update-completion-date").value,
			":c": materials,
			":d": document.getElementById("update-product-serial").value,
			":e": document.getElementById("update-sales-order").value,
			":f": document.getElementById("update-warehouse").value,
			":g": document.getElementById("update-status").value,
		}
	}

	console.log(payload);

	// PUT method
	$.ajax({
		url: "https://7gdbped59k.execute-api.us-east-1.amazonaws.com/prod?TableName=Work_Order",
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

function deleteOrder() {
	var payload = {
		"TableName": "Work_Order",
		"Key": {
			"Work_Order_Number": document.getElementById("delete-work-order").value
		}
	}

	console.log(payload);

	// DELETE method
	$.ajax({
		url: "https://7gdbped59k.execute-api.us-east-1.amazonaws.com/prod",
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