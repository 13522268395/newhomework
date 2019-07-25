package homework.day8;

/**
 * @Author myy
 * @create 2019/7/25 23:17
 *
 * MySQL中information_schema库下的 几张表的结构熟悉下, COLUMNS,SCHEMATA,TABLES,STATISTICS.
 * 另外基本的SQL语句, 增删改查, 还有修改表结构的语句大家也都复习 复习
 */
public class MysqlDemoBeforeClass {

/**
 * columns      数据库名、表名、列名、列标识符、列的默认值等表中的列信息    show columns from 库名.表名的结果取自此表
 * schemata     提供了当前mysql实例中所有数据库的信息 show databases的结果是取自此表
 * tables       数据库表所属的数据库、表名、表类型、多少行数据、创建时间等表信息 show tables from 库名的结果取自此表
 * statistics   提供有关表索引的信息  show index from 库名.表名的结果取自此表
 */

/**
 *  表的增删改查
 *  创建表create table myy(*****);    auto_increment   primary key   unique
 *  删表  drop table myy;
 *  改表  alter table myy rename myy1;
 *        alter table myy modify name varchar(20);
 *        alter table myy change name stu_name varchar(30);
 *        alter table myy add money float not null after stu_name;
 *        alter table myy drop addr;
 *  查表  show create table myy;
 *        desc myy;
 *        show tables;
 *
 * 数据的增删改查
 * 增：insert into myy(stu_name,money,sex,phone) values('lj',1000,'女',110); 不指定字段时写全
 * 删：delete from myy where stu_name = 'lj';
 *     truncate myy;    删除整个表
 * 改：update myy set money = 10000 where stu_name = 'tom';
 * 查：select * from myy as a where a.stu_name = 'tom';
 *     distinct 去重 in; count; max; min; avg; sum; group by; having; between and; order by
 *     order by在group by之后
 *     多表关联 select * from user a ,accounts b where a.id = b.user_id and a.username = '';
 *     select * from myy a left join score b on a.id = b.s_id;
 *     select * from myy a right join score b on a.id = b.s_id;
 *     select * from myy a inner join score b on a.id = b.s_id;
 *     select * from score a where a.id = (select id from blk where stu_name = '');
 */
}
