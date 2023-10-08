import yaml
import json
from flask import Flask
app = Flask(__name__)

in_memory_datastore = {
    "COBOL": {"name": "COBOL", "publication_year": 1960, "contribution": "record data"},
    "ALGOL": {"name": "ALGOL", "publication_year": 1958, "contribution": "scoping and nested functions"},
    "APL": {"name": "APL", "publication_year": 1962, "contribution": "array processing"},
}


@app.get('/programming_languages')
def list_programming_languages():
    return {"programming_languages": list(in_memory_datastore.values())}
 
 
@app.get('/yaml')
def list_yaml():
    document = """
    a: 1
    b:
      c: 3
      d: 4
    """
    yaml_data = yaml.full_load(document)
    json_string = json.dumps(yaml_data)
    return json_string
