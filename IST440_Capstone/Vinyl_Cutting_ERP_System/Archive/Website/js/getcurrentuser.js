
function lAttribute(){
var data = {
  UserPoolId: _config.cognito.userPoolId,
  ClientId: _config.cognito.userPoolClientId,
};
var userPool = new AWSCognito.CognitoIdentityServiceProvider.CognitoUserPool(data);
var cognitoUser = userPool.getCurrentUser();

try {
  if (cognitoUser != null) {
    cognitoUser.getSession(function(err, session) {
      if (err) {
        console.log(err);
        return;
      }

      console.log('session validity: ' + session.isValid());
      console.log('session token: ' + session.getIdToken().getJwtToken());
    });
    var userinfo = cognitoUser.getUserAttributes(function(err,result){
        if (err) {
            alert(err);
            return;
        }
        for (i = 0; i < result.length; i++) {
            console.log('attribute ' + result[i].getName() + ' has value ' + result[i].getValue());
        }

        document.getElementById("account-info").innerHTML += '<b>First Name: </b>' + result[5].getValue() + '<br>';
        document.getElementById("account-info").innerHTML += '<b>Last Name: </b>' + result[6].getValue() + '<br>';
        document.getElementById("account-info").innerHTML += '<b>Address: </b>' + result[1].getValue() + '<br>';
        document.getElementById("account-info").innerHTML += '<b>Phone Number: </b>' + result[4].getValue() + '<br>';
        document.getElementById("account-info").innerHTML += '<b>Email: </b>' + result[7].getValue() + '<br>';

    });
  } else {
    console.log(err);
    return;
  }
} catch (e) {
  console.log(e);
  return;
}
}
function deleteUser(){
	var data = {
	  UserPoolId: _config.cognito.userPoolId,
	  ClientId: _config.cognito.userPoolClientId,
	};

	var userPool = new AWSCognito.CognitoIdentityServiceProvider.CognitoUserPool(data);
	var cognitoUser = userPool.getCurrentUser();

	try {

	  if (cognitoUser != null) {
	    cognitoUser.getSession(function(err, session) {
	      if (err) {
	        console.log(err);
	        return;
	      }

	      console.log('session validity: ' + session.isValid());
	      console.log('session token: ' + session.getIdToken().getJwtToken());
	    });
	    cognitoUser.deleteUser(function(err, result) {
	        if (err) {
	            alert(err);
	            return;
	        }
	        console.log('call result: ' + result);
	    });
	  } else {
	    console.log(err);
	    return;
	  }
	} catch (e) {
	  console.log(e);
	  return;
	}

	alert('Your account has been deleted.')
}
function cPassword(){
	var oldPassword =document.getElementById("oldPassword").value;
	var newPassword =document.getElementById("newPassword").value;

	var data = {
	  UserPoolId: _config.cognito.userPoolId,
	  ClientId: _config.cognito.userPoolClientId,
	};
	var userPool = new AWSCognito.CognitoIdentityServiceProvider.CognitoUserPool(data);
	var cognitoUser = userPool.getCurrentUser();
	try {
	  if (cognitoUser != null) {
	    cognitoUser.getSession(function(err, session) {
	      if (err) {
	        console.log(err);
	        return;
	      }

	      console.log('session validity: ' + session.isValid());
	    });
	    cognitoUser.changePassword(oldPassword, newPassword, function(err, result) {
	        if (err) {
	            alert(err);
	            return;
	        }
	        console.log('call result: ' + result);
	    });
	  } else {
	    console.log(err);
	    return;
	  }
	} catch (e) {
	  console.log(e);
	  return;
	}
}

window.onload = lAttribute();

/*
*  Event Handlers
*/
//document.getElementById("changePass").onclick = cPassword;