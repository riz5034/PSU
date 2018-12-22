var aws = require('aws-sdk');
var ses = new aws.SES({
   region: 'us-east-1'
});

exports.handler = function(event, context) {
    console.log("Incoming: ", event);
   // var output = querystring.parse(event);

    var eParams = {
        Destination: {
            ToAddresses: [event.To]
        },
        Message: {
            Body: {
                Text: {
                    Data: event.Details
                }
            },
            Subject: {
                Data: "Vinyl Cutting Receipt"
            }
        },
        Source: "mrm6068@psu.edu"
    };

    console.log('===SENDING EMAIL===');
    var email = ses.sendEmail(eParams, function(err, data){
        if(err) console.log(err);
        else {
            console.log("===EMAIL SENT===");
            console.log(data);


            console.log("EMAIL CODE END");
            console.log('EMAIL: ', email);
            context.succeed(event);

        }
    });

};