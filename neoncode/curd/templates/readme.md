
Create a scaffolding to maintain table {{ tableName }}
===============================================================

Step 1:  Generate java and jsp files
-------------------------------------

Modify the `curd_config.py`
And then run `python curd_code.py` to generate the files
Then Copy the `java` and `jsp` to your Eclipse project `src`.
The `create_table.sql` is sql to create table.


{% include "create_table.sql" ignore missing %}


Step 2:  Config struts-config.xml
-------------------------------------

### 1 Add formbean configuration

        <form-bean name="{{ java.formBeanClassName }}" type="{{ java.formBeanClass }}"/>

### 2 Add action configuration


        <action input="{{ jsp.listfile }}" path="{{ action.list }}" name="{{ java.formBeanClassName }}" type="{{ java.actionClass }}" parameter="list" scope="session">
            <forward name="success" path="{{ jsp.listfile }}"/>
        </action>

        <action input="{{ jsp.createfile }}" path="{{ action.create }}" name="{{ java.formBeanClassName }}" type="{{ java.actionClass }}" parameter="create" scope="session">
            <forward name="success" path="{{ jsp.createfile }}"/>
        </action>

        <action input="{{ jsp.viewfile }}" path="{{ action.view }}" name="{{ java.formBeanClassName }}" type="{{ java.actionClass }}" parameter="view" scope="session">
            <forward name="success" path="{{ jsp.viewfile }}"/>
        </action>

        <action input="{{ jsp.editfile }}" path="{{ action.edit }}" name="{{ java.formBeanClassName }}" type="{{ java.actionClass }}" parameter="edit" scope="session">
            <forward name="success" path="{{ jsp.editfile }}"/>
        </action>

        <action input="{{ jsp.editfile }}" path="{{ action.save }}" name="{{ java.formBeanClassName }}" type="{{ java.actionClass }}" parameter="save" scope="session">
            <forward name="create" path="{{ jsp.createfile }}"/>
            <forward name="edit" path="{{ jsp.editfile }}"/>
            <forward name="success" path="{{ jsp.viewfile }}"/>
        </action>

        <action input="{{ jsp.viewfile }}" path="{{ action.delete }}" name="{{ java.formBeanClassName }}" type="{{ java.actionClass }}" parameter="delete" scope="session">
            <forward name="failure" path=".admin.errorDef" />
            <forward name="view" path="{{ action.view }}.do" redirect="true"/>
            <forward name="list" path="{{ action.list }}.do" redirect="true"/>
        </action>


Step 3: Action Permission
-------------------------

{% if neonAuth %}

Add new AccessPointEnumeration in java class `AccessPointEnumeration`

        public static final AccessPointEnumeration VIEW_{{ tableName|upper }} = new AccessPointEnumeration(
                {{ neonAuth.viewId }}, 
                "View {{ baseName }}", // String name
                "Enable the Permission to View {{ baseName }}s", // String description
                SecurityResourceType.TABLE, // SecurityResourceType resourceType,
                SecurityModuleType.{{ neonAuth.moduleType }}, // SecurityModuleType moduleType,
                SecurityAccessType.VIEW, // SecurityAccessType accessType,
                null, // SecurityPermissionType permissionType,
                null // String dbColumnName
        );

        public static final AccessPointEnumeration NEW_{{ tableName|upper }} = new AccessPointEnumeration(
                {{ neonAuth.newId }}, 
                "New {{ baseName }}", // String name
                "Enable the Permission to Create a {{ baseName }}", // String description
                SecurityResourceType.TABLE, // SecurityResourceType resourceType,
                SecurityModuleType.{{ neonAuth.moduleType }}, // SecurityModuleType moduleType,
                SecurityAccessType.NEW, // SecurityAccessType accessType,
                null, // SecurityPermissionType permissionType,
                null // String dbColumnName
        );
        
        public static final AccessPointEnumeration EDIT_{{ tableName|upper }} = new AccessPointEnumeration(
                {{ neonAuth.editId }},  
                "Edit {{ baseName }}", // String name
                "Enable the Permission to Edit a {{ baseName }}", // String description
                SecurityResourceType.TABLE, // SecurityResourceType resourceType,
                SecurityModuleType.{{ neonAuth.moduleType }}, // SecurityModuleType moduleType,
                SecurityAccessType.EDIT, // SecurityAccessType accessType,
                null, // SecurityPermissionType permissionType,
                null // String dbColumnName
        );

        public static final AccessPointEnumeration DELETE_{{ tableName|upper }} = new AccessPointEnumeration(
                {{ neonAuth.deleteId }}, 
                "Delete {{ baseName }}", // String name
                "Enable the Permission to Delete a {{ baseName }}", // String description
                SecurityResourceType.TABLE, // SecurityResourceType resourceType,
                SecurityModuleType.{{ neonAuth.moduleType }}, // SecurityModuleType moduleType,
                SecurityAccessType.DELETE, // SecurityAccessType accessType,
                null, // SecurityPermissionType permissionType,
                null // String dbColumnName
        );

{% else %}
    Not need AccessPointEnumeration
{% endif %}

Step 4:  Add CURD methods in Facade and Manager
------------------------------------------------

### 1. Add methods in `{{ Facade }}.java`

        public {{ java.voClassName }} retrieve{{ baseName }}(Integer id, UserContext userContext) throws ApplicationException, SystemException, RemoteException;

        public Page retrieve{{ baseName }}List(Page page, UserContext userContext) throws ApplicationException, SystemException, RemoteException;

        public {{ java.voClassName }} create{{ baseName }}({{ java.voClassName }} {{ baseName|lower0 }}, UserContext userContext) throws ApplicationException, SystemException, RemoteException;

        public {{ java.voClassName }} update{{ baseName }}({{ java.voClassName }} {{ baseName|lower0 }}, UserContext userContext) throws ApplicationException, SystemException, RemoteException;

### 2. Add methods in `{{ Facade }}Bean.java`

        public {{ java.voClassName }} retrieve{{ baseName }}(Integer id, UserContext userContext) throws ApplicationException, SystemException, RemoteException{
            return new {{ Manager }}().retrieve{{ baseName }}(id, userContext);
        }

        public Page retrieve{{ baseName }}List(Page page, UserContext userContext) throws ApplicationException, SystemException, RemoteException{
            return new {{ Manager }}().retrieve{{ baseName }}List(page, userContext);
        }

        public {{ java.voClassName }} create{{ baseName }}({{ java.voClassName }} {{ java.voClassName|lower0 }}, UserContext userContext) throws ApplicationException, SystemException, RemoteException{
            return new {{ Manager }}().create{{ baseName }}({{ baseName|lower0 }}, userContext);
        }

        public {{ java.voClassName }} update{{ baseName }}({{ java.voClassName }} {{ java.voClassName|lower0 }}, UserContext userContext) throws ApplicationException, SystemException, RemoteException{
            return new {{ Manager }}().update{{ baseName }}({{ baseName|lower0 }}, userContext);
        }


### 3. Add methods in `{{ Manager }}.java`

        public {{ java.voClassName }} retrieve{{ baseName }}(Integer id, UserContext userContext) throws ApplicationException, SystemException{
            {{ java.poClassName }} po = ({{ java.poClassName }})(new BasicDAO().retrieve(id, {{java.poClassName}}.class));
            {{ java.voClassName }} vo = new {{ java.transClassName }}().translate(po);
            return vo;
        }

        public Page retrieve{{ baseName }}List(Page page, UserContext userContext) throws ApplicationException, SystemException{
            Page result = new BasicReportingDAO().retrievePaginationList(page);
            return result;
        }

        public {{ java.voClassName }} create{{ baseName }}({{ java.voClassName }} vo, UserContext userContext) throws ApplicationException, SystemException{
            {{ java.poClassName }} po = new {{ java.transClassName }}().translate(vo);
            new BasicDAO().create(po);
            vo = new {{ java.transClassName }}().translate(po);
            return vo;
        }

        public {{ java.voClassName }} update{{ baseName }}({{ java.voClassName }} vo, UserContext userContext) throws ApplicationException, SystemException{
            {{ java.poClassName }} po = new {{ java.transClassName }}().translate(vo);
            new BasicDAO().update(po);
            vo = new {{ java.transClassName }}().translate(po);
            return vo;
        }

### 4.  Add NavigationListType to `NavigationListType.java`

        public static NavigationListType {{ tableName|upper }}_DETAIL = new NavigationListType( "{{ tableName|upper }}_DETAIL" );

Add NavigationController to `NavigationController.java`

At last of method `public String getUrl(NavigationListType type, String cachedValue)`

        else if(type.equals(NavigationListType.{{ tableName|upper }}_DETAIL)){
            return "{{ action.view }}.do?id="+cachedValue;
        }

At last of method `public String getIdParam(NavigationListType type)`

        else if(type.equals(NavigationListType.{{ tableName|upper }}_DETAIL)){
            return "id";
        }


        



Step 5: Test URLs
-----------------------------------
List url:

<https://localhost/np{{ action.list }}.do>

Open debug log:

<https://localhost/np/z2/tools/log4jConfig.jsp>

Tomcat log:

    D:\Develop\tomcat\logs\org-z2\client.log

Jboss Log dir:

    D:\Develop\jboss\server\default\log\org-z2\server.log

Query database:

    select * from {{ tableName }} order by id desc limit 0,10;





