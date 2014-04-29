# -*- coding: utf-8 -*-

from jinja2 import Environment, PackageLoader
import ListPageSettings
import os

env = Environment(loader=PackageLoader('listpage', 'templates'))

settings = ListPageSettings.settings



print "======================="
generate_file(env, settings, 'struts-config-part.xml', 'config.xml')
generate_file(env, settings, 'ValueList.jsp', "jsp/" + settings["jsp"]["listfile"])
generate_file(env, settings, 'ValueListSearch.jsp', "jsp/" + settings["jsp"]["listsearchfile"])
generate_file(env, settings, 'ValueListToolBar.jsp', "jsp/" + settings["jsp"]["listtoolbarfile"])
generate_file(env, settings, 'ValueListBody.jsp', "jsp/" + settings["jsp"]["listbodyfile"])
generate_file(env, settings, 'ValueListAction.java', "src/" + settings["action"]["actionClassPath"])
generate_file(env, settings, 'FormBean.java', "src/" + settings["action"]["formBeanClassPath"])
print "======================="



