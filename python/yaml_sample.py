import yaml

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

def run_all():
  inline_yaml()
  yaml_from_file()

run_all()