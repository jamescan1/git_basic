-- employees�� ��� ���� ��ȸ�Ͻÿ�
select * from employees;
-- department_id ��ȸ�Ͻÿ�. ���� --> 80���� 
select department_id,commission_pct from employees
where department_id = 
(select department_id from departments
where department_name = 'Sales');
-- ������ �޴� ��� ������ ��ȸ�Ͻÿ�.
-- ������ ������ ��ȸ�Ͻÿ�.(commission point�� �ִ� ������ ��ȸ�Ͻÿ�)
select * from employees
where commission_pct is not null;

-- �μ��� ��� ������ ����Ͻÿ�
select department_id from departments
where department_name = 'Sales';

-- ��� ������ ������ ��ȸ�Ͻÿ�
select employee_id,salary,salary*12 as annual
from employees;
-- ��� ������ ������ ��ȸ�Ͻÿ�(������ �����ϼ���)
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






