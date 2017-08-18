
update salary set sex = CHAR(ASCII('f') ^ ASCII('m') ^ ASCII(sex));

-- 181
select a.Name as Employee
from Employee a inner join Employee b on a.ManagerId=b.Id
where a.Salary>b.Salary;