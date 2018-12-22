
#Project: Project Diamond
#Team 2
#App5
html = """
<html>
<body>
<p>p tag text</p>
<!!--Comment-->
SERVER_NAME- initializes the server
MONGO_HOST- initializes the local host
MONGO_PORT- initializes the port
MONGO_DBNAME- initializes the DB name
RESOURCE_METHODS- initializes the resource methods
ITEM_METHODS- initializes the item methods
</body>
</html>
"""
import app5constants
# Please note that MONGO_HOST and MONGO_PORT could very well be left
# out as they already default to a bare bones local 'mongod' instance.
#chnage the server port
SERVER_NAME = app5constants.APP5_URL + ':' + app5constants.APP5_PORT
MONGO_HOST = 'localhost'
MONGO_PORT = 27017

MONGO_DBNAME = 'Team_2'
# Enable reads (GET), inserts (POST) and DELETE for resources/collections
# (if you omit this line, the API will default to ['GET'] and provide
# read-only access to the endpoint).
RESOURCE_METHODS = ['GET', 'POST', 'DELETE']

# Enable reads (GET), edits (PATCH), replacements (PUT) and deletes of
# individual items  (defaults to read-only item access).
ITEM_METHODS = ['GET', 'PATCH', 'PUT', 'DELETE']
schema = {
    # Schema definition, based on Cerberus grammar. Check the Cerberus project
    # (https://github.com/pyeve/cerberus) for details.
    'Timestamp': {
        'type': 'string',
        'minlength': 1,
        'maxlength': 250,
        'required':True,
        # talk about hard constraints! For the purpose of the demo
        # 'lastname' is an API entry-point, so we need it to be unique.
        'unique': True,
    },
    'App': {
        'type': 'string',
        'minlength': 1,
        'maxlength': 15,
    },
    # 'role' is a list, and can only contain values from 'allowed'.
    'Status': {
        'type': 'string',
        'allowed': ["Success", "Failed"],
    },
    'Info':{
        'type':'string',
        'minlength': 1,
        'maxlength':300,
    },
}
log = {
    # 'title' tag used in item links. Defaults to the resource title minus
    # the final, plural 's' (works fine in most cases but not for 'people')
    'item_title': 'Status',

    # by default the standard item entry point is defined as
    # '/people/<ObjectId>'. We leave it untouched, and we also enable an
    # additional read-only entry point. This way consumers can also perform
    # GET requests at '/people/<lastname>'.
    'additional_lookup': {
        'url': 'regex("[\w]+")',
        'field': 'Status'
    },

    # We choose to override global cache-control directives for this resource.
    'cache_control': 'max-age=10,must-revalidate',
    'cache_expires': 10,

    # most global settings can be overridden at resource level
    
    'schema': schema
}
DOMAIN={'log': log,}
