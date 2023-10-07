import yaml

def inlineYaml():
  document = """
    a: 1
    b:
      c: 3
      d: 4
  """
  yaml_data = yaml.load(document)

def yamlFromFile():
  with open('config.yml', 'r') as file:
    prime_service = yaml.safe_load(file)
  print(prime_service['prime_numbers'][0])
  print(prime_service['rest']['url'])

def runAll():
  inlineYaml()

runAll()