import yaml
import json

# example of inline yaml
def inline_yaml():
  document = """
    a: 1
    b:
      c: 3
      d: 4
  """
  yaml_data = yaml.full_load(document)
  print(yaml.dump(yaml_data))

# example of loading yaml from a file
def yaml_from_file():
  with open('config.yml', 'r') as file:
    prime_service = yaml.safe_load(file)
  print(prime_service['a'])
  print(prime_service['b']['c'])

def yaml_to_json():
  yaml_string="""employee:
    name: John Doe
    age: 35
    job:
      title: Software Engineer
      department: IT
      years_of_experience: 10
    address:
      street: 123 Main St.
      city: San Francisco
      state: CA
      zip: 94102
  """
  print("The YAML string is:")
  print(yaml_string)
  python_dict=yaml.safe_load(yaml_string)
  json_string=json.dumps(python_dict)
  print("The JSON string is:")
  print(json_string)

def run_all():
  inline_yaml()
  yaml_from_file()
  yaml_to_json()

run_all()
