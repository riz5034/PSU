function Updateuserinfo(attributeList){
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
      });

      cognitoUser.updateAttributes(attributeList, function(err, result) {
      if (err) {
        alert(err);
        return;
      }
      window.location = "account.html";
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

// Check on first name
$(document).ready(function(){
    $("input").change(function(){
        var str = document.getElementById("acc-first-name").value;
    var n = str.length;
    if(n>35){
    alert("First name cannot be over 35 characters.");
    }
    });
})

$(document).ready(function(){
    $("input").change(function(){
        var str = document.getElementById("acc-last-name").value;
    var n = str.length;
    if(n>35){
    alert("Last name cannot be over 35 characters.");
    }
    });
})

$(document).ready(function(){
    $("input").change(function(){
        var str = document.getElementById("acc-Country").value;
    var n = str.length;
    if(n>3){
    alert("Country code can not be more than 3 characters.");
    }
    });
})

$(document).ready(function(){
    $("input").change(function(){
        var str = document.getElementById("acc-Zipcode").value;
    var n = str.length;
    if(n>10){
    alert("Zip code can not be more than 10 characters.");
    }
    });
})

$(document).ready(function(){
    $("input").change(function(){
        var str = document.getElementById("acc-Country").value;
    var n = str.length;
    if(n>3){
    alert("Country code can not be more than 3 characters.");
    }
    });
})

$(document).ready(function(){
    $("input").change(function(){
        var str = document.getElementById("acc-State").value;
    var n = str.length;
    if(n>2){
    alert("State can not be more than 2 characters.");
    }
    });
})

$(document).ready(function(){
    $("input").change(function(){
        var str = document.getElementById("acc-phone").value;
    var n = str.length;
    if(n>10){
    alert("Phone number can not be more than 10 characters.");
    }
    });
})

$(document).ready(function(){
    $("input").change(function(){
        var str = document.getElementById("acc-email").value;
    if(!str.includes("@")){
    alert("Invalid email.");
    }
    });
})

function addAttributes(){
  var attributeList = [];
  var attributename = ["address","email","family_name", "given_name", "phone_number"];// these are the attributes from the cognite that the customers has
  var attrFirstName = document.getElementById("acc-first-name").value;
  var attrLastName =  document.getElementById("acc-last-name").value;
  var attrAddress =  document.getElementById("acc-Streetaddress").value+"\n"+document.getElementById("acc-City").value+"\n"+document.getElementById("acc-State").value+"\n"+document.getElementById("acc-Zipcode").value+"\n"+document.getElementById("acc-Country").value;
  var attrPhone =  "+1"+document.getElementById("acc-phone").value;
  var attrEmail =  document.getElementById("acc-email").value;

  allTextFilled = true;

  if(document.getElementById("acc-first-name").value == "") {
    allTextFilled = false;
  }
  if(document.getElementById("acc-last-name").value == "") {
    allTextFilled = false;
  }
  if(document.getElementById("acc-Country").value == "") {
    allTextFilled = false;
  }
  if(document.getElementById("acc-Zipcode").value == "") {
    allTextFilled = false;
  }
  if(document.getElementById("acc-City").value == "") {
    allTextFilled = false;
  }
  if(document.getElementById("acc-State").value == "") {
    allTextFilled = false;
  }
  if(document.getElementById("acc-phone").value == "") {
    allTextFilled = false;
  }
  if(document.getElementById("acc-email").value == "") {
    allTextFilled = false;
  }

  if(allTextFilled) {
    var attributevalue = [attrAddress, attrEmail, attrLastName, attrFirstName, attrPhone];//update all the attributes , almost copy all feild use your sign up as example grap the dom and feed values 
    for (var i=0; i<attributename.length; i++) {
      var attribute = {
        Name : attributename[i],
        Value : attributevalue[i]
      };
      var attribute = new AmazonCognitoIdentity.CognitoUserAttribute(attribute);
      attributeList.push(attribute);
    }
    console.log(attributeList);
    Updateuserinfo(attributeList);
  } else {
    alert("Please fill out missing fields.")
  }
}