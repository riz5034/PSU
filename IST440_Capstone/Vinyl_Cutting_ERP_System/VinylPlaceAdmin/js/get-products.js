var API ="https://mhq011eieb.execute-api.us-east-1.amazonaws.com/CRUD/?TableName=Product";
var JSONObject = {}

AWS.config.region = 'us-east-1'; // 1. Enter your region

AWS.config.credentials = new AWS.CognitoIdentityCredentials({
    IdentityPoolId: 'us-east-1:37cbddc6-00db-4d92-bb47-0860447a14ff' // 2. Enter your identity pool
});

AWS.config.credentials.get(function (err) {
    if (err) alert(err);
    console.log(AWS.config.credentials);
});

var bucketName = 'vinyl-cutting'; // Enter your bucket name
var bucket = new AWS.S3({
    params: {
        Bucket: bucketName
    }
});

var fileChooser = document.getElementById('file-chooser');
var button = document.getElementById('upload-button');
var results = document.getElementById('results');
button.addEventListener('click', function () {

	var file = fileChooser.files[0];

	if (file) {

		results.innerHTML = '';
		var objKey = 'img/' + file.name;
		var params = {
			Key: objKey,
			ContentType: file.type,
			Body: file,
			ACL: 'public-read'
		};

		bucket.putObject(params, function (err, data) {
			if (err) {
				if ((results.innerHTML = 'ERROR: ' + err) == 'ERROR: AccessDenied: Access Denied') {
					results.innerHTML = 'ERROR: ' + err;
				} else {
				}
			} else {
				results.innerHTML = 'Successful upload';
				//listObjs();
				document.getElementById('create-image').value = './' + objKey;
			}
		});
	} else {
		results.innerHTML = 'Nothing to upload.';
	}
}, false);

function listObjs() {
	var prefix = 'testing';
	bucket.listObjects({
		Prefix: prefix
	}, function (err, data) {
		if (err) {
			results.innerHTML = 'ERROR: ' + err;
		} else {
			var objKeys = "";
			data.Contents.forEach(function (obj) {
				objKeys += obj.Key + "<br>";
			});
			results.innerHTML = objKeys;
		}
	});
}

function deleteit() {
	debugger;
	document.getElementById("uploadPreview").src = "no_image.png";
	document.getElementById("file-chooser").value = "";
	event.preventDefault();
}

function PreviewImage() {
	var oFReader = new FileReader();
	oFReader.readAsDataURL(document.getElementById("file-chooser").files[0]);
	oFReader.onload = function (oFREvent) {
		document.getElementById("uploadPreview").src = oFREvent.target.result;
	};
}

// CRUD Functionalities

function createProduct() {
	var product = {
		"TableName": "Product",
		"Item": ""
	}

	var payload = {
		"Product_Serial_Number": document.getElementById("create-product-serial").value,
		"Image": document.getElementById("create-image").value,
		"Price_In_Bulk_Units": document.getElementById("create-price-bulk").value,
		"Product_Description": document.getElementById("create-product-desc").value,
		"Product_Design": document.getElementById("create-product-design").value,
		"Product_Measurement": document.getElementById("create-product-measurement").value,
		"Product_Name": document.getElementById("create-product-name").value,
		"Product_Price": document.getElementById("create-product-price").value,
		"Product_Type": document.getElementById("create-product-type").value
	}

	product.Item = payload;

	console.log(product);

	$.ajax({
		url: API,
		type: "POST",
		data: JSON.stringify(product),
		dataType: "json",
		success: function() {
			alert("Successfully created product.");
			location.reload(); 
		},
		error: function() {
			alert("An error occured.");
		}
	});
}

function setUpdateValues() {
	var payload = JSON.parse(sessionStorage.getItem("payload"));
	console.log(payload);
	
	for(var i = 0; i < payload.Items.length; i++) {
		if(payload.Items[i].Product_Serial_Number == document.getElementById("update-product-serial").value) {
			// Set attribute boxes
			document.getElementById("update-product-serial").value = payload.Items[i].Product_Serial_Number;
			document.getElementById("update-image").value = payload.Items[i].Image;
			document.getElementById("update-price-bulk").value = payload.Items[i].Price_In_Bulk_Units;
			document.getElementById("update-product-desc").value = payload.Items[i].Product_Description;
			document.getElementById("update-product-design").value = payload.Items[i].Product_Design;
			document.getElementById("update-product-measurement").value = payload.Items[i].Product_Measurement;
			document.getElementById("update-product-name").value = payload.Items[i].Product_Name;
			document.getElementById("update-product-price").value = payload.Items[i].Product_Price;
			document.getElementById("update-product-type").value = payload.Items[i].Product_Type;
		}
	}
}

function updateProduct() {
	var payload = {
		"TableName": "Product",
		"Key": {
			"Product_Serial_Number": document.getElementById("update-product-serial").value
		},
		"UpdateExpression": "set Image = :b, Price_In_Bulk_Units = :c, Product_Description = :d, Product_Design = :e, Product_Measurement = :f, Product_Name = :g, Product_Price = :h, Product_Type = :i",
		"ExpressionAttributeValues": {
			":b": document.getElementById("update-image").value,
			":c": document.getElementById("update-price-bulk").value,
			":d": document.getElementById("update-product-desc").value,
			":e": document.getElementById("update-product-design").value,
			":f": document.getElementById("update-product-measurement").value,
			":g": document.getElementById("update-product-name").value,
			":h": document.getElementById("update-product-price").value,
			":i": document.getElementById("update-product-type").value
		},
		"ReturnValues": "UPDATED_NEW"
	}

	console.log(payload);

	// PUT method
	$.ajax({
		url: API,
		type: "PUT",
		data: JSON.stringify(payload),
		dataType: "json",
		success: function() {
			alert("Successfully updated product.");
			location.reload(); 
		},
		error: function() {
			alert("An error occured.");
		}
	});
}

function deleteOrder() {
	var payload = {
		"TableName": "Product",
		"Key": {
			"Product_Serial_Number": document.getElementById("delete-product-serial").value
		}
	}

	// DELETE method
	$.ajax({
		url: API,
		type: "DELETE",
		data: JSON.stringify(payload),
		dataType: "json",
		success: function() {
			alert("Successfully deleted product.");
			location.reload(); 
		},
		error: function() {
			alert("An error occured.");
		}
	});
}

function setup(json) {
	console.log("here");
	console.log(json);
	
	var counter = 1;

	for (var i = 0; i < json.Items.length; i++) {
		var productSerialNumber = json.Items[i].Product_Serial_Number;
		var image = json.Items[i].Image;
		var bulkPrice = json.Items[i].Price_In_Bulk_Units;
		var productDesc = json.Items[i].Product_Description;
		var productDesign = json.Items[i].Product_Design;
		var productMeasurment = json.Items[i].Product_Measurement;
		var productName = json.Items[i].Product_Name;
		var productPrice = json.Items[i].Product_Price;
		var productType = json.Items[i].Product_Type;


		var tableRow = `
			<tr>
                <td>${counter}</td>
                <td>${productSerialNumber}</td>
                <td>${productName}</td>
                <td>${productDesc}</td>
                <td>${productType}</td>
                <td>${image}</td>
                <td>${productDesign}</td>
                <td>${productPrice}</td>
                <td>${bulkPrice}</td>
                <td>${productMeasurment}</td>    
            </tr>
		`;

		// Append to table
		$('#productTable').append(tableRow);
		counter += 1;
	}
}

function search() {
	var counter = 1;
	var productSerialNumber = document.getElementById("search-product-serial").value;
	var image = document.getElementById("search-image").value;
	var bulkPrice = document.getElementById("search-price-bulk").value;
	var productDesc = document.getElementById("search-product-desc").value;
	var productDesign = document.getElementById("search-product-design").value;
	var productMeasurment = document.getElementById("search-product-measurement").value;
	var productName = document.getElementById("search-product-name").value;
	var productPrice = document.getElementById("search-product-price").value;
	var productType = document.getElementById("search-product-type").value;

	payload = JSON.parse(sessionStorage.getItem("payload"));
	payloadRefined = {
		"Items": []
	};
	var add = true;

	for(var i = 0; i < payload.Items.length; i++) {
		add = true;

		if(productSerialNumber != "" && !String(payload.Items[i].Product_Serial_Number).includes(productSerialNumber)) {
			add = false;
		}

		if(image != "" && !String(payload.Items[i].Image).includes(image)) {
			add = false;
		}

		if(bulkPrice != "" && !String(payload.Items[i].Price_In_Bulk_Units).includes(bulkPrice)) {
			add = false;
		}

		if(productDesc != "" && !String(payload.Items[i].Product_Description).includes(productDesc)) {
			add = false;
		}

		if(productDesign != "" && !String(payload.Items[i].Product_Design).includes(productDesign)) {
			add = false;
		}

		if(productMeasurment != "" && !String(payload.Items[i].Product_Measurement).includes(productMeasurment)) {
			add = false;
		}

		if(productName != "" && !String(payload.Items[i].Product_Name).includes(productName)) {
			add = false;
		}

		if(productPrice != "" && !String(payload.Items[i].Product_Price).includes(productPrice)) {
			add = false;
		}

		if(productType != "" && !String(payload.Items[i].Product_Type).includes(productType)) {
			add = false;
		}

		if(add) {
			payloadRefined.Items.push((payload.Items[i]));
		}
	}

	// Remove all elements of the current table
	var myNode = document.getElementById("searchProductTable");
	while (myNode.firstChild) {
		myNode.removeChild(myNode.firstChild);
	}

	for (var i = 0; i < payloadRefined.Items.length; i++) {
		var productSerialNumber = payloadRefined.Items[i].Product_Serial_Number;
		var image = payloadRefined.Items[i].Image;
		var bulkPrice = payloadRefined.Items[i].Price_In_Bulk_Units;
		var productDesc = payloadRefined.Items[i].Product_Description;
		var productDesign = payloadRefined.Items[i].Product_Design;
		var productMeasurment = payloadRefined.Items[i].Product_Measurement;
		var productName = payloadRefined.Items[i].Product_Name;
		var productPrice = payloadRefined.Items[i].Product_Price;
		var productType = payloadRefined.Items[i].Product_Type;

		var tableRow = `
			<tr>
                <td>${counter}</td>
                <td>${productSerialNumber}</td>
                <td>${productName}</td>
                <td>${productDesc}</td>
                <td>${productType}</td>
                <td>${image}</td>
                <td>${productDesign}</td>
                <td>${productPrice}</td>
                <td>${bulkPrice}</td>
                <td>${productMeasurment}</td>   
            </tr>
		`;

		// Append to table
		$('#searchProductTable').append(tableRow);
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