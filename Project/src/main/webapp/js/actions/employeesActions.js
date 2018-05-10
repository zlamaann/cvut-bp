import * as types from './actionTypes';
import axios from 'axios';

export function updateEmployeeSuccess(employee) {
    return { type: types.UPDATE_EMPLOYEE_SUCCESS, employee};
}

export function createEmployeeSuccess(employee) {
    return { type: types.CREATE_EMPLOYEE_SUCCESS, employee};
}

export function loadEmployeesSuccess(employees) {
    return { type: types.LOAD_EMPLOYEES_SUCCESS, employees};
}

export function deleteEmployeeSuccess(employee) {
    return { type: types.DELETE_EMPLOYEE_SUCCESS, employee};
}

export function loadEmployees() {
    return (dispatch) => {
        return axios.get('rest/persons').then(response => {
            dispatch(loadEmployeesSuccess(response.data));
        }).catch(error => {
            throw(error);
        })
    };
}

export function deleteEmployee(employee) {
    return (dispatch) => {
        return axios.delete('rest/persons/' + employee.id.toString()).then(response => {
            dispatch(deleteEmployeeSuccess(employee));
        }).catch(error => {
            throw(error);
        })
    };
}

export function saveEmployee(employee) {
    return (dispatch, getState) => {
        if (employee.id) {
            return axios.put('rest/persons/' + employee.id, employee)
                .then(response => {
                    dispatch(updateEmployeeSuccess(response.data))
                }).catch(error => {
                    throw (error);
                });
        } else {
            return axios.post('rest/persons', employee)
                .then(response => {
                    dispatch(createEmployeeSuccess(response.data));
                }).catch(error => {
                    throw (error);
                });
        }

    };
}