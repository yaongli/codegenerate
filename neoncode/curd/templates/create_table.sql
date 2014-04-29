

    DROP TABLE IF EXISTS `{{ tableName }}`;
    CREATE TABLE `{{ tableName }}` (
        `id` integer NOT NULL auto_increment,
        {% for field in fields %}`{{ field.name }}` {{ field.dbType }},
        {% endfor %}`createBy` varchar(25) default NULL,
        `createTime` datetime default NULL,
        `lastModifyBy` varchar(25) default NULL,
        `lastModifyTime` datetime default NULL,
        PRIMARY KEY  (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    insert into table_info (tableName, primaryKey, referenceName, identifier) values ('{{ tableName }}', 'id', '{{ titleName }}', 'id');