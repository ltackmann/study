# Setup

* Install dependencies
```bash
    pip3 install pyyaml
    pip3 install flask
```

* Run REST api
```bash
    export FLASK_APP=rest_api_sample.py
    flask run
    curl -X GET 'http://127.0.0.1:5000/programming_languages'
```