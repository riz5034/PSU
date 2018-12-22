var data = {
	UserPoolId: _config.cognito.userPoolId,
	ClientId: _config.cognito.userPoolClientId,
};
var userPool = new AWSCognito.CognitoIdentityServiceProvider.CognitoUserPool(data);
var cognitoUser = userPool.getCurrentUser();

function createPayload() {
	var salesOrder = {
		"TableName": "Sales_Order",
		"Item": ""
	}

	var date = new Date();

	var payload = {
		"Sales_Order_Number": parseInt(date.getTime()),
		"Customer_ID": "",
		"Shipping_Information": "",
		"Product_Design": [],
		"Date": "",
		"Payment_Method": "",
		"Total": ""
	};

	var shippingInformation = {
		"Shipping_Name": "",
		"Shipping_Address": "",
		"Shipping_City": "",
		"Shipping_State": "",
		"Shipping_Zip_Code": "",
		"Shipping_Country": "",
		"Shipping_Phone_Number": "",
		"Shipping_Email": ""
	};

	// Add customer identifier
	payload.Customer_ID = localStorage.getItem("username");

	// Add shipping information
	shippingInformation.Shipping_Name = document.getElementById("first-name").value 
	+ " " + document.getElementById("last-name").value;
	shippingInformation.Shipping_Address = document.getElementById("street-address").value;
	shippingInformation.Shipping_City = document.getElementById("city").value;
	shippingInformation.Shipping_State = document.getElementById("state").value;
	shippingInformation.Shipping_Zip_Code = document.getElementById("zip-code").value;
	shippingInformation.Shipping_Country = document.getElementById("country").value;
	shippingInformation.Shipping_Phone_Number = document.getElementById("phone-number").value;
	shippingInformation.Shipping_Email = document.getElementById("email").value;

	payload.Shipping_Information = shippingInformation;

	// Create product payload
	var cart = localStorage.getItem("cart");
	var items = cart.split(",");

	// End at index before last to avoid empty string
	for(var i = 0; i < items.length - 1; i++) {
		var productDesign = {
			"Product_Serial_Number": "",
			"Quantity": ""
		}

		productDesign.Product_Serial_Number = items[i];
		productDesign.Quantity = 1;
		payload.Product_Design.push(productDesign);
	}

	// Add date
	var salesDate = `${date.getMonth() + 1}-${date.getDate()}-${date.getFullYear()}`;
	payload.Date = salesDate;

	// Add payment method
	payload.Payment_Method = "Credit";

	// Add total
	total = localStorage.getItem("total");
	payload.Total = total;

	// Add to sales order payload
	salesOrder.Item = payload;
	console.log(salesOrder);


	// POST method
	$.ajax({
		url: "https://citd078vva.execute-api.us-east-1.amazonaws.com/default/SOM",
		type: "POST",
		data: JSON.stringify(salesOrder),
		dataType: "json",
		success: function(salesOrder) {
			console.info(salesOrder);
			alert("Sucessfully created order!")
			localStorage.setItem("cart", null);
			window.location.href = "confirmation.html";
		},
		error: function() {
			alert("An error occured.");
		}
	});
}

// Check on first name
$(document).ready(function(){
    $("input").change(function(){
        var str = document.getElementById("first-name").value;
    var n = str.length;
    if(n>35){
    alert("First name cannot be over 35 characters.");
    }
    });
})

$(document).ready(function(){
    $("input").change(function(){
        var str = document.getElementById("last-name").value;
    var n = str.length;
    if(n>35){
    alert("Last name cannot be over 35 characters.");
    }
    });
})

$(document).ready(function(){
    $("input").change(function(){
        var str = document.getElementById("country").value;
    var n = str.length;
    if(n>3){
    alert("Country code can not be more than 3 characters.");
    }
    });
})

$(document).ready(function(){
    $("input").change(function(){
        var str = document.getElementById("zip-code").value;
    var n = str.length;
    if(n>10){
    alert("Zip code can not be more than 10 characters.");
    }
    });
})

$(document).ready(function(){
    $("input").change(function(){
        var str = document.getElementById("state").value;
    var n = str.length;
    if(n>2){
    alert("State can not be more than 2 characters.");
    }
    });
})

$(document).ready(function(){
    $("input").change(function(){
        var str = document.getElementById("phone-number").value;
    var n = str.length;
    if(n>10){
    alert("Phone number can not be more than 10 characters.");
    }
    });
})

$(document).ready(function(){
    $("input").change(function(){
        var str = document.getElementById("email").value;
    if(!str.includes("@")){
    alert("Invalid email.");
    }
    });
})



// Check if user is logged in before sending an order and cart is not empty
function sendSalesOrder() {
	var cart = localStorage.getItem("cart");
	cart = cart.split(",");

	var allTextFilled = true;
	console.log("length of first name");
	console.log((document.getElementById("first-name").value).length);

	if(document.getElementById("first-name").value == "") {
		allTextFilled = false;
	}

	if(document.getElementById("last-name").value == "") {
		allTextFilled = false;
	}

	if(document.getElementById("street-address").value == "") {
		allTextFilled = false;
	}

	if(document.getElementById("country").value == "") {
		allTextFilled = false;
	}

	if(document.getElementById("city").value == "") {
		allTextFilled = false;
	}

	if(document.getElementById("zip-code").value == "") {
		allTextFilled = false;
	}

	if(document.getElementById("state").value == "") {
		allTextFilled = false;
	}

	if(document.getElementById("phone-number").value == "") {
		allTextFilled = false;
	}

	if(document.getElementById("email").value == "") {
		allTextFilled = false;
	}

	if(document.getElementById("state").value == "") {
		allTextFilled = false;
	}

	if(document.getElementById("card-holder").value == "") {
		allTextFilled = false;
	}

	if(document.getElementById("exp-month").value == "") {
		allTextFilled = false;
	}

	if(document.getElementById("exp-year").value == "") {
		allTextFilled = false;
	}

	if(document.getElementById("cvc").value == "") {
		allTextFilled = false;
	}

	if(allTextFilled) {
		if(cart.length == 1 && (cart[0] == "null" || cart[0] == "")) {
			alert("Cart cannot be empty.");
		} else {
			if(cognitoUser != null ) {
				if(isCardValid()) {
					createPayload();
				}
			} else {
				alert("Please sign in to checkout items.");
			}z
		}
	} else {
		alert("Please fill out missing fields.");
	}
}

function populateShippingInfo() {
	try {
		if (cognitoUser != null) {
			cognitoUser.getSession(function(err, session) {
				if (err) {
					console.log(err);
					return;
				}
			});


			var userinfo = cognitoUser.getUserAttributes(function(err,result){
				if (err) {
            //alert(err);
            return;
        	}
        	var fulladdress = (result[1].getValue()).split('\n');
        	var phone = "";

        	// Remove leading +# in phone
        	if(result[4].getValue()[0] == "+") {
        		phone = result[4].getValue().substring(2);
        	}

        	document.getElementById("first-name").value = result[5].getValue();
        	document.getElementById("last-name").value = result[6].getValue();
        	document.getElementById("street-address").value = fulladdress[0];
        	document.getElementById("country").value = fulladdress[4];
        	document.getElementById("city").value = fulladdress[1];
        	document.getElementById("zip-code").value = fulladdress[3];
        	document.getElementById("state").value = fulladdress[2];
        	document.getElementById("phone-number").value = phone;
        	document.getElementById("email").value = result[7].getValue();
    });
		} else {
			return; 
		}
	}
	catch (e) {
		console.log(e);
		return;
	}
}

function isCardValid()
{
	// Example : 371449635398431 / 4532421174341278 / 5569755825672968 
	if(isValidIdentifier(document.getElementById("card-number").value))
	{
		return true;
	}
	else
		alert("Invalid card number.");
		return false;
}

//Luhn algorithm identifier verification
//MIT Licensed
function isValidIdentifier(identifier) {

    var sum     = 0,
        alt     = false,
        i       = identifier.length-1,
        num;

    if (identifier.length < 13 || identifier.length > 19){
        return false;
    }

    while (i >= 0){

        //get the next digit
        num = parseInt(identifier.charAt(i), 10);

        //if it's not a valid number, abort
        if (isNaN(num)){
            return false;
        }

        //if it's an alternate number...
        if (alt) {
            num *= 2;
            if (num > 9){
                num = (num % 10) + 1;
            }
        } 

        //flip the alternate bit
        alt = !alt;

        //add to the rest of the sum
        sum += num;

        //go to next digit
        i--;
    }

    //determine if it's valid
    return (sum % 10 == 0);
}

window.onload = populateShippingInfo();
