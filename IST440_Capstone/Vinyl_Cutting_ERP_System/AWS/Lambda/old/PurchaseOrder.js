'use strict';

console.log('Loading function');
const doc = require('dynamodb-doc');
const dynamo = new doc.DynamoDB();

// All the request info in event
// "handler" is defined on the function creation
exports.phandler = (event, context, callback) => {

    // Callback to finish response
    const done = (err, res) => callback(null, {
        statusCode: err ? '400' : '200',
        body: err ? err.message : JSON.stringify(res),
        headers: {
            'Content-Type': 'application/json',"Access-Control-Allow-Origin": "*"
        }
    });
    if (typeof event.body == 'string')
        event.body = JSON.parse(event.body);
    switch (event.httpMethod) {
        // Table name and key are in payload
        case 'GET':
            if(event.queryStringParameters.Type == 'Item'){
                var params = {
                    ExpressionAttributeValues: {
                    ":a": parseInt(event.queryStringParameters.id) },
                    FilterExpression: "Purchase_Order_Number = :a",
                    TableName:'Purchase_Order'};
                dynamo.scan(params,done);}
            else{
                dynamo.scan({ TableName: event.queryStringParameters.TableName }, done);
            }
            break;
        case 'POST':
            dynamo.putItem(event.body, done)
            break;
        default:
            done(new Error(`Unsupported method "${event.httpMethod}"`));
    }
}