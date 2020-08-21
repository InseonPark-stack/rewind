CREATE TABLE Employee (
	empNo int Primary key,
    name varchar(10) not null,    
    position varchar(10) not null,
    dept varchar(10) not null
);

select * from employee;

insert employee values(1111,"일번이좋아","대리","경영");
insert employee values(1112,"이번이좋아","사원","경영");

delete from employee;

Select * from employee where name like "%냉장고%";