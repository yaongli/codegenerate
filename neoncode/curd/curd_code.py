# -*- coding: utf-8 -*-

from jinja2 import Environment, PackageLoader
import curd_config
import os
import shutil
import sys
sys.path.append("..")
from CodeGen import *

env = getEnvironment("curd")

settings = curd_config.settings

def generate_jsp_file(action_type):
    global settings
    global env
    settings["jsp"]["layoutfile"] = settings["jsp"]["dirname"] + "/" + settings["jsp"]["basename"] + upper0(action_type) + ".jsp"
    settings["jsp"]["bodyfile"] = settings["jsp"]["dirname"] + "/" + settings["jsp"]["basename"] + upper0(action_type) + "Body.jsp"
    generate_file(env, settings, 'layout.jsp', "jsp/" + settings["jsp"]["layoutfile"], needMergeBlankLines=True)
    body_template = action_type + "Body.jsp"
    generate_file(env, settings, body_template, "jsp/" + settings["jsp"]["bodyfile"], needMergeBlankLines=True)
    settings["jsp"][action_type + "file"] = settings["jsp"]["layoutfile"]

print "======================="
generate_file(env, settings, 'create_table.sql', "create_table.sql")
generate_file(env, settings, 'VO.java', "java/" + classFilePath(settings["java"]["voClass"]))
generate_file(env, settings, 'Translator.java', "java/" + classFilePath(settings["java"]["transClass"]))
generate_file(env, settings, 'PO.java', "java/" + classFilePath(settings["java"]["poClass"]))
generate_file(env, settings, 'Action.java', "java/" + classFilePath(settings["java"]["actionClass"]))
generate_file(env, settings, 'FormBean.java', "java/" + classFilePath(settings["java"]["formBeanClass"]))

generate_jsp_file("list")
generate_jsp_file("view")
generate_jsp_file("create")
generate_jsp_file("edit")

#generate_file(env, settings, 'struts-config.xml', 'config.xml')
generate_file(env, settings, 'readme.md', 'readme.md')

print "======================="

"""
print "copy files..."
from distutils.dir_util import copy_tree
copy_tree("dist/java/", r"D:/workspace/neon/src/java/")
copy_tree("dist/jsp/", r"D:/workspace/neon/src/jsp/")
copy_tree("dist/jsp/", r"D:/Develop/tomcat/webapps/np/")
print "Finish."
"""