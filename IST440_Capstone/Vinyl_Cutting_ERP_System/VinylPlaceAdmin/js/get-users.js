

var API = "https://mpwa36takl.execute-api.us-east-1.amazonaws.com/prod";
var JSONObject = {}

var poolData = {
    UserPoolId: _configUser.cognito.userPoolId,
    ClientId: _configUser.cognito.userPoolClientId
};

var userPool;

if (!(_config.cognito.userPoolId &&
      _config.cognito.userPoolClientId &&
      _config.cognito.region)) {
    $('#noCognitoMessage').show();
}

userPool = new AmazonCognitoIdentity.CognitoUserPool(poolData);

function setup() {
	payload = JSON.parse(sessionStorage.getItem("payload"));
	var counter = 1;

	for (var i = 0; i < payload.Users.length; i++) {
		var customerId = payload.Users[i].Username;
		var customerFirstName = payload.Users[i].Attributes[5].Value;
		var customerLastName = payload.Users[i].Attributes[6].Value;

		var address = (payload.Users[i].Attributes[1].Value).split('\n');
		var streetAddress = address[0];
		var city = address[1];
		var state = address[2];
		var zip = address[3];
		var country = address[4];

		var email = payload.Users[i].Attributes[7].Value;
		var phone = payload.Users[i].Attributes[4].Value;
		var status = payload.Users[i].UserStatus;

		var tableRow = `
			<tr>
                <td>${counter}</td>
                <td>${customerId}</td>
                <td>${customerFirstName}</td>
                <td>${customerLastName}</td>
                <td>${streetAddress}</td>
                <td>${city}</td>
                <td>${state}</td>
                <td>${zip}</td>
                <td>${country}</td>
                <td>${email}</td>
                <td>${phone}</td>
                <td><span class="label label-info">${status}</span></td>
            </tr>
		`;

		// append to table
		$('#customerTable').append(tableRow);
		counter += 1;
	}
}

function register(email, password, onSuccess, onFailure) {
    var attributeList = [];

    var dataEmail = {
        Name: 'email',
        Value: email
    };

    // Attribute list should contain first name, last name, address, email, and phone number
    var dataFirstName = {
        Name : 'given_name',
        Value : $('#create-first-name').val()
    };

    var dataLastName = {
        Name : 'family_name',
        Value : $('#create-last-name').val()
    };
    
    var dataAddress = {
        Name : 'address',
        Value : $('#create-street-address').val() + "\n" + $('#create-city').val() +
            "\n" + $('#create-state').val() + "\n" + $('#create-zip-code').val() + 
            "\n" + $('#create-country').val()

    }; 

    var dataEmail = {
        Name : 'email',
        Value : email
    };

    var dataPhone = {
        Name : 'phone_number',
        Value : '+1'+$('#create-phone-number').val()
    };

    var attributeFirstName = new AmazonCognitoIdentity.CognitoUserAttribute(dataFirstName);
    var attributeLastName = new AmazonCognitoIdentity.CognitoUserAttribute(dataLastName);
    var attributeAddress = new AmazonCognitoIdentity.CognitoUserAttribute(dataAddress);
    var attributeEmail = new AmazonCognitoIdentity.CognitoUserAttribute(dataEmail);
    var attributePhone = new AmazonCognitoIdentity.CognitoUserAttribute(dataPhone);

    attributeList.push(attributeFirstName);
    attributeList.push(attributeLastName);
    attributeList.push(attributeAddress)
    attributeList.push(attributeEmail);
    attributeList.push(attributePhone);

    console.log(attributeList);

    userPool.signUp(email, password, attributeList, null,
        function signUpCallback(err, result) {
            if (!err) {
                onSuccess(result);
                alert("Success! Please check email and verify on customer verification page")
                window.open('http://vinyl-cutting.s3-website-us-east-1.amazonaws.com/verify.html', '_blank');
                location.reload();
            } else {
                onFailure(err);
            }
        }
    );
}

function createCustomer() {
	var email = $('#create-email').val();
    var password = $('#create-password').val();
    var password2 = $('#create-confirm').val();

    var onSuccess = function registerSuccess(result) {
        var cognitoUser = result.user;
        var confirmation = ('Registration successful. Please check your email inbox or spam folder for your verification code.'); 
    };

    var onFailure = function registerFailure(err) {
        alert(err);
    };

    if (password === password2) {
        register(email, password, onSuccess, onFailure);
    } else {
        alert('Passwords do not match');
    }
}

function setUpdateValues() {
	var user = document.getElementById("update-customer-id").value;

	// Get attributes
	payload = JSON.parse(sessionStorage.getItem("payload"));
	for (var i = 0; i < payload.Users.length; i++) {
		if(payload.Users[i].Username == user) {
			// Set attributes in text boxes
			document.getElementById("update-first-name").value = payload.Users[i].Attributes[5].Value;
			document.getElementById("update-last-name").value = payload.Users[i].Attributes[6].Value;

			var fulladdress = payload.Users[i].Attributes[1].Value.split('\n');
			document.getElementById("update-street-address").value = fulladdress[0];
			document.getElementById("update-city").value = fulladdress[1];
			document.getElementById("update-state").value = fulladdress[2];
			document.getElementById("update-zip-code").value = fulladdress[3];
			document.getElementById("update-country").value = fulladdress[4];

			document.getElementById("update-email").value = payload.Users[i].Attributes[7].Value;
			document.getElementById("update-phone-number").value = payload.Users[i].Attributes[4].Value;
		}
	}
}

function setUpdateValues() {
	var payload = JSON.parse(sessionStorage.getItem("payload"));
	console.log(payload);
	for(var i = 0; i < payload.Users.length; i++) {
		if(payload.Users[i].Username == document.getElementById("update-customer-id").value) {
			var address = (payload.Users[i].Attributes[1].Value).split("\n");
			var streetAddress = address[0];
			var city = address[1];
			var state = address[2];
			var zip = address[3];
			var country = address[4];

			document.getElementById("update-first-name").value = payload.Users[i].Attributes[5].Value;
			document.getElementById("update-last-name").value = payload.Users[i].Attributes[6].Value;
			document.getElementById("update-street-address").value = streetAddress;
			document.getElementById("update-city").value = city;
			document.getElementById("update-state").value = state;
			document.getElementById("update-zip-code").value = zip;
			document.getElementById("update-country").value = country;
			document.getElementById("update-email").value = payload.Users[i].Attributes[7].Value;
			document.getElementById("update-phone-number").value = payload.Users[i].Attributes[4].Value;
		}
	}
}

function updateUser() {
	var address = document.getElementById("update-street-address").value + "\n" + document.getElementById("update-city").value
	+ "\n" + document.getElementById("update-state").value + "\n" + document.getElementById("update-zip-code").value
	+ "\n" + document.getElementById("update-country").value;

	var payload = {
		"username": document.getElementById("update-customer-id").value,
		"address": address,
		"email": document.getElementById("update-email").value,
		"phone": document.getElementById("update-phone-number").value,
		"given": document.getElementById("update-first-name").value,
		"family": document.getElementById("update-last-name").value
	}

	console.log(payload);

	// PUT method
	$.ajax({
		url: API,
		type: "PUT",
		data: JSON.stringify(payload),
		dataType: "json",
		success: function() {
			alert("Successfully updated customer.");
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

function deleteCustomer() {
	var payload = {
		"username": document.getElementById("delete-customer-id").value
	}

	console.log(payload);

	// DELETE method
	$.ajax({
		url: API,
		type: "DELETE",
		data: JSON.stringify(payload),
		dataType: "json",
		success: function() {
			alert("Successfully deleted customer.");
			location.reload(); 
		},
		error: function() {
			alert("An error occured.");
		}
	});

}

function search() {
	var counter = 1;
	var id = document.getElementById("search-customer-id").value;
	var firstName = document.getElementById("search-first-name").value;
	var lastName = document.getElementById("search-last-name").value;
	var streetAddress = document.getElementById("search-street-address").value;
	var city = document.getElementById("search-city").value;
	var state = document.getElementById("search-state").value;
	var zip = document.getElementById("search-zip-code").value;
	var country = document.getElementById("search-country").value;
	var email = document.getElementById("search-email").value;
	var phone = document.getElementById("search-phone-number").value;
	var status = document.getElementById("search-status").value;

	payload = JSON.parse(sessionStorage.getItem("payload"));
	payloadRefined = {
		"Users": []
	};

	// Cycle through all cases and check

	var add = true;
	for (var i = 0; i < payload.Users.length; i++) {
		var address = (payload.Users[i].Attributes[1].Value).split('\n');
		add = true;

		if (id != "" && !String(payload.Users[i].Username).includes(id)) {
			add = false;
		}

		if (firstName != "" && !String(payload.Users[i].Attributes[5].Value).includes(firstName)) {
			add = false;
		}

		if (lastName != "" && !String(payload.Users[i].Attributes[6].Value).includes(lastName)) {
			add = false;
		}

		if (streetAddress != "" && !String(address[0]).includes(streetAddress)) {
			add = false;
		}

		if (city != "" && !String(address[1]).includes(city)) {
			add = false;
		}

		if (state != "" && !String(address[2]).includes(state)) {
			add = false;
		}

		if (zip != "" && !String(address[3]).includes(zip)) {
			add = false;
		}

		if (country != "" && !String(address[4]).includes(country)) {
			add = false;
		}

		if (email != "" && !String(payload.Users[i].Attributes[7].Value).includes(email)) {
			add = false;
		}

		if (phone != "" && !String(payload.Users[i].Attributes[4].Value).includes(phone)) {
			add = false;
		}

		if (status != "" && !String(payload.Users[i].UserStatus).includes(status)) {
			add = false;
		}

		if (add) {
			payloadRefined.Users.push(payload.Users[i]);

		}
	}

	// Remove all elements of the current table
	var myNode = document.getElementById("searchCustomerTable");
	while (myNode.firstChild) {
		myNode.removeChild(myNode.firstChild);
	}

	// Print payload after refining search
	for (var i = 0; i < payloadRefined.Users.length; i++) {
		var customerId = payloadRefined.Users[i].Username;
		var customerFirstName = payloadRefined.Users[i].Attributes[5].Value;
		var customerLastName = payloadRefined.Users[i].Attributes[6].Value;

		var address = (payloadRefined.Users[i].Attributes[1].Value).split('\n');
		var streetAddress = address[0];
		var city = address[1];
		var state = address[2];
		var zip = address[3];
		var country = address[4];

		var email = payloadRefined.Users[i].Attributes[7].Value;
		var phone = payloadRefined.Users[i].Attributes[4].Value;
		var status = payloadRefined.Users[i].UserStatus;

		var tableRow = `
			<tr>
                <td>${counter}</td>
                <td>${customerId}</td>
                <td>${customerFirstName}</td>
                <td>${customerLastName}</td>
                <td>${streetAddress}</td>
                <td>${city}</td>
                <td>${state}</td>
                <td>${zip}</td>
                <td>${country}</td>
                <td>${email}</td>
                <td>${phone}</td>
                <td><span class="label label-info">${status}</span></td>
            </tr>
		`;

		// append to table
		$('#searchCustomerTable').append(tableRow);
		counter += 1;
	}
}
//get the payload
function grab() {
	try {
		$.getJSON(API, JSONObject)
			.done(function (json) {
				sessionStorage.setItem("payload", JSON.stringify(json));
				setup();
			});
	} catch (err) {
		console.log(err);
	}
}

window.onload = grab();

