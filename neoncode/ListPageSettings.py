# -*- coding: utf-8 -*-

import MySQLdb
from mysql.connector import FieldType
import os
import sys
import re
from CodeGen import Field, parseClass

jsp = {
    "basename" : "emailHoldList",
    "dirname" : "/admin/systemsetting",
    "title" : "Email Hold List",
}

action = {
    "init" : "/admin/systemsetting/emailholdlistInit",
    "list" : "/admin/systemsetting/emailholdlist",
    "actionClass" : "com.z2.np.client.action.systemsetting.EmailHoldListAction",
    "formBeanClass" : "com.z2.np.client.formbean.admin.EmailHoldListFormBean",
}

curd_base = "/admin/systemsetting/emailhold"
curd = {
    "create": curd_base + "Create",
    "update": curd_base + "Edit",
    "save": curd_base + "Edit",
    "detail": curd_base + "Detail",
    "delete": curd_base + "Delete",
}

search = [
    Field(name="email", title="Email", widgetType="Text"),
    Field(name="accountId", title="Account Name", widgetType="Account"),
    Field(name="holdType", title="Hold Type", widgetType="Dropdown", mapping="email_hold_type"),
    Field(name="errorCode", title="Error Code", widgetType="Dropdown", mapping="bounce_error_code"),
    Field(name="fromTime", title="From Time", widgetType="DateTime"),
    Field(name="toTime", title="To Time", widgetType="DateTime"),
]

columnList = [
    Field(name="action", title="Action", widgetType="Text"),
    Field(name="email", title="Email", widgetType="Text"),
    Field(name="accountName", title="Account Name", widgetType="Account"),
    Field(name="holdType", title="Hold Type", widgetType="Dropdown", mapping="email_hold_type"),
    Field(name="errorCode", title="Error Code", widgetType="Dropdown", mapping="bounce_error_code"),
    Field(name="bounceCount", title="Bounce Count", widgetType="Text"),
    Field(name="lastModifyTime", title="Update Time", widgetType="DateTime"),
]

query_sql = """
select email_hold_list.id,
account.id as accountId, 
(case  when  user.middlename  is null then  concat_ws("",user.firstname,' ',user.lastname)  else concat_ws("",user.firstname,' ',user.middlename,' ',user.lastname)  end)  as accountName
, email_hold_list.email, email_hold_list.holdType, email_hold_list.lastModifyTime, email_hold_list.bounceCount, email_hold_list.errorCode
from email_hold_list
left join user on user.userid=email_hold_list.userId
left join account on account.id=user.accountId

"""
query_sql_escape = "\"" + "\"\n        +\" ".join(query_sql.split("\n")) + " \""

jsp["listfile"] = jsp["dirname"] + "/" + jsp["basename"] + ".jsp"
jsp["listsearchfile"] = jsp["dirname"] + "/" + jsp["basename"] + "Search.jsp"
jsp["listtoolbarfile"] = jsp["dirname"] + "/" + jsp["basename"] + "Toolbar.jsp"
jsp["listbodyfile"] = jsp["dirname"] + "/" + jsp["basename"] + "Body.jsp"

action["formBeanClassName"], action["formBeanClassPackage"], action["formBeanClassPath"] = parseClass(action["formBeanClass"])
action["actionClassName"], action["actionClassPackage"], action["actionClassPath"] = parseClass(action["actionClass"])


settings = {
    "jsp" : jsp,
    "action" : action,
    "curd" : curd,
    "search" : search,
    "columnList" : columnList,
    "query_sql" : query_sql,
    "query_sql_escape" : query_sql_escape,
}
