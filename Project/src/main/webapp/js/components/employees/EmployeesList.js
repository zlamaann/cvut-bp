import React from 'react';
import PropTypes from 'prop-types';
import EmployeesListRow from './EmployeesListRow';

const EmployeesList = ({employees, onClick}) => {
    return (
        <table>
            <thead>
            <tr>
                <th>Jméno a příjmení</th>
                <th>E-mail</th>
                <th>Telefonní číslo</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            {employees.map(employee =>
                <EmployeesListRow key={employee.id} employee={employee} onClick={onClick}/>
            )}
            </tbody>
        </table>
    );
};

EmployeesList.propTypes = {
    employees: PropTypes.array.isRequired,
    onClick: PropTypes.func.isRequired
};

export default EmployeesList;