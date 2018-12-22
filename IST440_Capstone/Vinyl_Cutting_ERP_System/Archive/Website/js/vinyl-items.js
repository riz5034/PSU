function getVinylItems() {
	// Get Vinyl Items from database
	var jsonObj = $.getJSON("https://mhq011eieb.execute-api.us-east-1.amazonaws.com/product?TableName=Product&Type=Full&id=YEL470");
	console.log(jsonObj.responseText);
	




	console.log($.getJSON("https://mhq011eieb.execute-api.us-east-1.amazonaws.com/product?TableName=Product&Type=Full&id=YEL470"));
}

window.onload = getVinylItems();