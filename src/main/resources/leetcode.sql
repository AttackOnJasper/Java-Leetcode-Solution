
update salary set sex = CHAR(ASCII('f') ^ ASCII('m') ^ ASCII(sex));

-- 176
select max(t.Salary) as SecondHighestSalary
from (select Salary from Employee where Salary <> (select max(Salary) from Employee)) as t

-- 181
select a.Name as Employee
from Employee a inner join Employee b on a.ManagerId=b.Id
where a.Salary>b.Salary;

-- 183
SELECT c.`Name` AS Customers FROM `Customers` c
LEFT JOIN `Orders` o ON(o.`CustomerId` = c.`Id`)
WHERE o.`Id` IS NULL

-- 196
DELETE p1
FROM Person p1, Person p2
WHERE p1.Email = p2.Email AND
p1.Id > p2.Id

-- 197
SELECT wt1.Id
FROM Weather wt1, Weather wt2
WHERE wt1.Temperature > wt2.Temperature
AND TO_DAYS(wt1.DATE)-TO_DAYS(wt2.DATE)=1;

-- 596
select class
from courses
group by class
having count(distinct student) >= 5;

