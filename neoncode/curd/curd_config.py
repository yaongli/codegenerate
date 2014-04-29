# -*- coding: utf-8 -*-

import os
import sys
import re

sys.path.append("..")
from CodeGen import *

tableName = "ip_blocking" #TODO
titleName = "IP Blocking" #TODO
baseName =  "IpBlocking" #TODO
action_base = "/admin/systemsetting/ipblocking"
actionClassPackage = "com.z2.np.client.action.systemsetting"
formBeanClassPackage = "com.z2.np.client.formbean.admin"
Facade = "SystemSettingFacade"
Manager = "SystemSettingManager"
needNeonAuth = False
neonAuthBaseId = 10000
moduleType = "SYSTEM"
curd_base = action_base
jsp_dirname = action_base

fields = [
    Field(name="ipAddress", title="IP Address", required=True, dataType="String", widgetType="Text", dbType="varchar(100) not null"),
    Field(name="reason", title="Blocking Reason", dataType="String", widgetType="Text", dbType="varchar(1024)"),
    Field(name="detail", title="Detail", dataType="String", widgetType="TextArea", dbType="text"),
]

java = {
    "voClassName" : baseName,
    "poClassName" : baseName + "PO",
    "transClassName" : baseName + "Translator",
    "actionClassName" : baseName + "Action",
    "formBeanClassName" : baseName + "FormBean",
    "actionClassPackage" : actionClassPackage, 
    "formBeanClassPackage" : formBeanClassPackage,
}

java["voClass"] = "com.z2.np.shared.valueobject." + java["voClassName"]
java["poClass"] = "com.z2.np.server.po." + java["poClassName"]
java["transClass"] = "com.z2.np.server.translator." + java["transClassName"]
java["actionClass"] = java["actionClassPackage"] + "." + java["actionClassName"]
java["formBeanClass"] = java["formBeanClassPackage"] + "." + java["formBeanClassName"]

action = {
    "create": curd_base + "/create",
    "edit": curd_base + "/edit",
    "save": curd_base + "/save",
    "view": curd_base + "/view",
    "delete": curd_base + "/delete",
    "list": curd_base + "/list",
}


jsp = {
    "basename" : lower0(baseName),
    "dirname" : jsp_dirname,
    "title" : titleName,
}


curd = {
    "create": curd_base + "Create",
    "update": curd_base + "Edit",
    "save": curd_base + "Edit",
    "detail": curd_base + "Detail",
    "delete": curd_base + "Delete",
}

timebase_fields= [
    Field(name="createBy", title="Create By", dataType="String", widgetType="None"),
    Field(name="createTime", title="Create Time", dataType="Date", widgetType="None"),
    Field(name="lastModifyBy", title="Last Modify By", dataType="String", widgetType="ListOnly"),
    Field(name="lastModifyTime", title="Last Modify Time", dataType="Date", widgetType="ListOnly"),
]

neonAuth = {
    'viewId' : neonAuthBaseId, 
    'newId' : neonAuthBaseId + 1, 
    'editId' : neonAuthBaseId + 2, 
    'deleteId' : neonAuthBaseId + 3,
    'moduleType' : moduleType, 
}

settings = {
    "tableName" : tableName,
    "baseName" : baseName,
    "titleName" : titleName,
    "java" : java,
    "jsp" : jsp,
    "action" : action,
    "curd" : curd,
    "fields" : fields,
    "timebase_fields": timebase_fields,
    "Facade" : Facade,
    "Manager" : Manager,

}
