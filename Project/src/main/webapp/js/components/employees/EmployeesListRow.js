import React from 'react';
import PropTypes from 'prop-types';
import {Link} from 'react-router-dom';

const EmployeesListRow = ({employee, onClick}) => {
    return (
        <tr>
            <td>{(employee.name === undefined ? "" : employee.name) + " " + (employee.surname === undefined ? "" :  employee.surname)}</td>
            <td>{employee.email === undefined ? "" : employee.email}</td>
            <td>{employee.phoneNumber === undefined ? "" : employee.phoneNumber}</td>
            <td><Link to={'/employee/' + employee.id}>Detaily</Link></td>
            <td><a href="#" onClick={() => onClick(employee)}> Smazat</a></td>
        </tr>
    );
};

EmployeesListRow.propTypes = {
    employee: PropTypes.object.isRequired,
    onClick: PropTypes.func.isRequired
};

export default EmployeesListRow;