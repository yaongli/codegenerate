# -*- coding: utf-8 -*-
import os
from jinja2 import Environment, PackageLoader
import re


class Field(object):
    def __init__(self, name, title, dataType="String", widgetType="Text", mapping=None, dbType=None, required=False):
        self.name = name
        self.title = title
        self.dataType = dataType
        self.widgetType = widgetType
        self.mapping = mapping
        self.dbType = dbType
        self.required = required


def parseClass(fullClass):
    lastDocPos = fullClass.rfind(".")
    className = fullClass[lastDocPos+1:]
    package = fullClass[0:lastDocPos]
    filepath = fullClass.replace(".", "/") + ".java"
    return (className, package, filepath)

def classFilePath(fullClass):
    return fullClass.replace(".", "/") + ".java"


def mergeBlankLines(source):
    source = source.replace("\r", "")
    lines = re.split(r"\n", source)
    nlines = []
    preBlank = False
    for line in lines:
        line = line.replace("\n", "")
        if line.strip() == "":
            if preBlank:
                continue
            else:
                nlines.append(line)
                preBlank = True
        else:
            nlines.append(line)
            preBlank = False

    return "\n".join(nlines)

def generate_file(env, variables, template_file, destination, needMergeBlankLines=True):
    template = env.get_template(template_file)
    source = template.render(variables)
    if needMergeBlankLines:
        source = mergeBlankLines(source)
    destination = "dist/" + destination
    destination = destination.replace("//", "/")
    if not os.path.exists(os.path.dirname(destination)):
        os.makedirs(os.path.dirname(destination))
    with open(destination, 'wb') as f:
            f.write(source)
    print "Generate file: ",destination

def getClassFromTableName(tableName):
    vn = []
    tn = tableName.split("_")
    for item in tn:
        vn.append(item[0:1].upper() + item[1:])
    return "".join(vn)

def upper0(input):
    if input:
        output = input[0:1].upper() + input[1:]
        return output
    else:
        return input

def lower0(input):
    if input:
        output = input[0:1].lower() + input[1:]
        return output
    else:
        return input

def getEnvironment(package):
    env = Environment(loader=PackageLoader(package, 'templates'))
    env.filters['lower0'] = lower0
    env.filters['upper0'] = upper0
    return env


class TM(object):
    """
    Template mapping
    """
    def __init__(self, template, destination, operator):
        self.template = template
        self.destination = destination
        self.operator = operator


class Operator(object):
    def __init__(self):
        pass

    def execute(source, destination):
        """
        source: a block of code
        destination: the path of destination file
        """
        pass

class WriteOperator(Operator):
    """
    Write the source to destination file
    """
    def __init__(self):
        pass

    def execute(source, destination):
        """
        source: a block of code
        destination: the path of destination file
        """
        if not os.path.exists(os.path.dirname(destination)):
            os.makedirs(os.path.dirname(destination))
        with open(destination, 'wb') as f:
            f.write(source)

class AppendOperator(Operator):
    """
    Append the source to destination file
    """
    def __init__(self):
        pass

    def execute(source, destination):
        """
        source: a block of code
        destination: the path of destination file
        """
        if not os.path.exists(os.path.dirname(destination)):
            os.makedirs(os.path.dirname(destination))
        with open(destination, 'ab') as f:
            f.write(source)

