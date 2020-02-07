import json


class Field:
    def __init__(self, fieldName, fieldType):
        self.fieldName = fieldName
        self.fieldType = fieldType

    def __str__(self):
        return "name: " + self.fieldName + "\ntype: " + self.fieldType


def add_override():
    return "\n@override\n"


dart_class = ""
dart_class += "import 'package:exam/domain/IObject.dart';\n\n"

with open('class.json') as json_file:
    data = json.load(json_file)

    className = data['className']
    fields = []
    for field in data['fields']:
        fields.append(Field(field['name'], field['type']))

dart_class += "class " + className + " implements IObject {\n"

# field declaration
for field in fields:
    if field.fieldName == "id":
        dart_class += add_override()
    dart_class += field.fieldType + " " + field.fieldName + ";\n"

# CONSTRUCTORS


# no constructor
dart_class += className + ".noConstr();\n"

# constructor with all fields
dart_class += className + "(" + fields[0].fieldType + " " + fields[0].fieldName
for field in fields[1:]:
    dart_class += ", " + field.fieldType + " " + field.fieldName
dart_class += ")\n{\n"

for field in fields:
    dart_class += "this." + field.fieldName + " = " + field.fieldName + ";\n"
dart_class += "}\n"

# constructor without id
dart_class += className + ".withoutId(" + fields[1].fieldType + " " + fields[1].fieldName
for field in fields[2:]:
    dart_class += ", " + field.fieldType + " " + field.fieldName
dart_class += ")\n{\n"

for field in fields[1:]:
    dart_class += "this." + field.fieldName + " = " + field.fieldName + ";\n"
dart_class += "}\n"

# FROM JSON
dart_class += add_override() + "fromJson(obj) {\n"
for field in fields:
    dart_class += "this." + field.fieldName + " = obj['" + field.fieldName + "'];\n"
dart_class += "}\n"

# FROM DB RES
dart_class += add_override() + "IObject objectFromDBRes(Map<String, dynamic> result) {\n" + className + " newObj = "+className+"(result['id']"
for field in fields[1:]:
    dart_class += ", result['" + field.fieldName + "']"
dart_class += ");\n"
for field in fields:
    dart_class += "this." + field.fieldName + " = result['" + field.fieldName + "'];\n"
dart_class += "return newObj;\n}"

# TO JSON
dart_class += add_override() + "Map<String, dynamic> toJson() {\nvar map = new Map<String, dynamic>();\n"
for field in fields[1:]:
    dart_class += "map['" + field.fieldName + "'] = " + field.fieldName + ";\n"
dart_class += "if(id != null) {\nmap['id'] = id.toString();\n}\nreturn map;\n}\n"

# TO JSON DB
dart_class += add_override() + "Map<String, dynamic> toJsonDB() {\nvar map = new Map<String, dynamic>();\n"
for field in fields[1:]:
    dart_class += "map['" + field.fieldName + "'] = " + field.fieldName + ";\n"
dart_class += "if(id != null) {\nmap['id'] = id;\n}\nreturn map;\n}\n"

# TO STRING
dart_class += add_override() + "String toString(){\nString idStr = id.toString();\nreturn '{id: $idStr\\n"
for field in fields[1:]:
    dart_class += ", " + field.fieldName + ": $" + field.fieldName + "\\n"

dart_class += "}';\n}"

# END
dart_class += "\n}\n"

filename = "../lib/domain/" + className.title() + ".dart"
f = open(filename, "w+")
f.write(dart_class)
f.close()
print(dart_class)