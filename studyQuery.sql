-- 모든 직원의 연봉을 조회하시오(수당을 포함하세요)
select 
employee_id,salary,
TO_CHAR(salary*12+NVL((salary*12*commission_pct),0),'999,999,999') as annual
from 
employees;

select e.employee_id, e.department_id, d.department_name
from employees e
inner join departments d
on e.department_id = d.department_id;

select emp.employee_id, mgr.employee_id, mgr.first_name, mgr.last_name
from employees mgr
inner join employees emp
on mgr.employee_id = emp.manager_id;

select count(*) from employees;
select * from employees;
-- 매니저를 갖지 않는 직원도 출력해라
select emp.employee_id, mgr.employee_id, mgr.first_name, mgr.last_name
from employees mgr
right outer join employees emp
on mgr.employee_id = emp.manager_id;

select count(distinct department_id) from employees;
select distinct department_id from employees;
-- 베치가 완료되지 않은 직원 조회
select e.employee_id,e.first_name, e.department_id, d.department_name
from employees e
left outer join departments d
on e.department_id = d.department_id;
-- 직원이 없는 부서 조회
select e.employee_id,e.first_name, e.department_id, d.department_name
from employees e
right outer join departments d
on e.department_id = d.department_id;

-- 배치가 완료되지 않은 직원과 직원이 없는 부서 조회
select e.employee_id,e.first_name, e.department_id, d.department_name
from employees e
full outer join departments d
on e.department_id = d.department_id;


