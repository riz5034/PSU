'use strict';

console.log('Loading function');
const doc = require('dynamodb-doc');
const dynamo = new doc.DynamoDB();

// All the request info in event
// "handler" is defined on the function creation
exports.shandler = (event, context, callback) => {

    // Callback to finish response
    const done = (err, res) => callback(null, {
        statusCode: err ? '400' : '200',
        body: err ? err.message : JSON.stringify(res),
        headers: {
            'Content-Type': 'application/json',"Access-Control-Allow-Origin": "*"
        }
    });
    //console.log(event.Records[0].Sns.Message);
    if (typeof event.body == 'string')
        event.body = JSON.parse(event.body);
    switch (event.httpMethod) {
        // Table name and key are in payload
        case 'GET':
            if(event.queryStringParameters.Type == 'getByAttribute'){
                var params = {
                    ExpressionAttributeValues: {
                    ":att": event.queryStringParameters.id
                    },
                    ExpressionAttributeNames: {
                    "#a": event.queryStringParameters.attribute
                    },
                    FilterExpression: "#a = :att",
                    TableName:'Sales_Order'};
                    //console.log("***" + JSON.stringify(params.ExpressionAttributeValues));
                dynamo.scan(params,done);}
            if(event.queryStringParameters.Type == 'getItem'){
                dynamo.getItem({TableName:event.queryStringParameters.TableName,Key:{Sales_Order_Number:parseInt(event.queryStringParameters.id)}},done)
            }
            else{
                dynamo.scan({ TableName: event.queryStringParameters.TableName }, done);
            }
            break;
            
        case 'POST':
            if(dynamo.putItem(event.body, done))
                {emailReceipt(event.body);
                    mrpSns(context);
                }
            break;
            
        case 'DELETE':
                dynamo.deleteItem(event.body, done);
                break;
                
        case 'PUT':
            var params = {
                    ExpressionAttributeValues: {
                    ":att": event.queryStringParameters.id
                    },
                    ExpressionAttributeNames: {
                    "#a": event.queryStringParameters.attribute
                    },
                    FilterExpression: "#a = :att",
                    TableName:'Sales_Order'};
            dynamo.updateItem(event.body, done);
            break;
            
        default:
            done(new Error(`Unsupported method "${event.httpMethod}"`));
    }
}

function emailReceipt(body){
    var aws = require('aws-sdk');
    var lambda = new aws.Lambda({
    region: 'us-east-1' //change to your region
    });
    
    var receipt = "Thanks for your purchase! Here are the details...\\n";
    var emailToSend = body.Item.Shipping_Information.Shipping_Email;  //console.log(emailToSend);
    var somNumber = body.Item.Sales_Order_Number.toString();
    var paymentMethod = body.Item.Payment_Method;
    var items = body.Item.Product_Design;
    var total = body.Item.Total;
    
    // for(var i of items)
    // {
    //     console.log(i)
        
    //     //receipt = receipt + "Item:\\t" + items[i].Product_Serial_Number;
    // }
    
    // items.forEach(function(element) 
    // { 
    //     receipt = receipt + "\\nItem: " + element.Product_Serial_Number;
    //     receipt = receipt + "\\n\\t------Quantity:\\t" + element.Quantity.toString() + "\\n" ;
    //     //console.log(element) 
    // });
//return;

for(let val of items)
{ 
     receipt = receipt + "\\nItem: " + val.Product_Serial_Number;
     receipt = receipt + "\\n\\t------Quantity:\\t" + val.Quantity.toString() + "\\n" ;
}
    
    //Build receipt
    receipt = receipt + "\\nCustomer:\\t" + body.Item.Shipping_Information.Shipping_Name;
    receipt = receipt + "\\n\\nCustomer ID:\\t" + body.Item.Customer_ID ;
    receipt = receipt + "\\n\\nSales Order Number:\\t" + somNumber;
    receipt = receipt + "\\n\\nDate:  :\\t" + body.Item.Date ;
    receipt = receipt + "\\n\\nPayment Method:\\t" + paymentMethod;
    receipt = receipt + "\\n\\nTotal Price:\\t$" + total;
    
    //receipt = receipt + "\\nItems:\\t" + items;
    
    var params = {
    FunctionName: 'sendEmail', // the lambda function we are going to invoke
    InvocationType: 'Event',   
    Payload: '{"To": "' + emailToSend + '", "Details": "' + receipt + '"}'
    //Payload: '{To: ' + emailToSend +', "body": ' + Details + '}'
    };
    //console.log("TTTTTTTTTT" +params.Payload)
    //return;

 lambda.invoke(params, function(err, data) {
  if (err) {
   console.log("Error: " + err);
  } 
  else {
   console.log('Lambda test said '+ data.Payload);
  }
 })
//
};


//Attemp to capture product details before emailReceipt() is called. Not yet working.
function emailReceiptStart(body){
    var aws = require('aws-sdk');
    var lambda = new aws.Lambda({
    region: 'us-east-1' //change to your region
    });
    const doc = require('dynamodb-doc');
    const dynamo = new doc.DynamoDB();
    var util = require('util')
    
    //console.log("tttttttt " + util.inspect(body));
    console.log("ccccccc " + body.Item.Product_Design[0].Product_Serial_Number);
    
    
    //return;
    var params = {
                ExpressionAttributeValues: {
                ":a": body.Item.Product_Design.Product_Serial_Number },
                FilterExpression: "Product_Serial_Number = :a",
                TableName:'Product'
    };
    
    dynamo.scan(params, function(err, data) {
            if (err) {
                console.log("Error", err);
            } 
            else {
                var productNums = [];
                data.Items.Product_Design.forEach(function(element, index, array) {
                     productNums.push(element.Product_Serial_Number);
                     console.log("****&*" + element.Product_Serial_Number);
                     //console.log("%%%%%%%" + body);
                //     //emailReceipt();
                   });
            }
            
        }   );
};

function mrpSns(context){
    var awssns = require("aws-sdk");
    
    var sns = new awssns.SNS();
    var params = {
      Message:'{"name":"zack","Age":26}',
      Subject: "SOMtoMRP",
      TopicArn:"arn:aws:sns:us-east-1:802749809537:SOM_to_MRP"
    };
    sns.publish(params, context.done);
}