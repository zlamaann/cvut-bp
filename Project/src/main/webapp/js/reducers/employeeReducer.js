import * as types from '../actions/actionTypes';
import initialState from './initialState';

export default function (state = initialState.employees, action) {
    switch (action.type) {
        case types.LOAD_EMPLOYEES_SUCCESS:
            return action.employees;

        case types.CREATE_EMPLOYEE_SUCCESS:
            return [
                ...state,
                Object.assign({}, action.employee)
            ];

        case types.UPDATE_EMPLOYEE_SUCCESS:
            return [
                ...state.filter(employee => employee.id !== action.employee.id),
                Object.assign({}, action.employee)
            ];

        case  types.DELETE_EMPLOYEE_SUCCESS:
            const newState = Object.assign([], state);
            const indexOfEmployeeToDelete = state.findIndex(employee => {
                return employee.id === action.employee.id
            });

            newState.splice(indexOfEmployeeToDelete, 1);
            return newState;

        default:
            return state;
    }
}