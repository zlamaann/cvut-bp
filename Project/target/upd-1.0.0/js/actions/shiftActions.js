import * as types from './actionTypes';
import axios from 'axios';

export function updateShiftSuccess(shift) {
    return { type: types.UPDATE_SHIFT_SUCCESS, shift};
}

export function createShiftSuccess(shift) {
    return { type: types.CREATE_SHIFT_SUCCESS, shift};
}

export function loadShiftsSuccess(shifts) {
    return { type: types.LOAD_SHIFTS_SUCCESS, shifts};
}

export function deleteShiftSuccess(shift) {
    return { type: types.DELETE_SHIFT_SUCCESS, shift};
}

export function loadShifts() {
    return (dispatch) => {
        return axios.get('rest/shifts').then(response => {
            dispatch(loadShiftsSuccess(response.data));
        }).catch(error => {
            throw(error);
        })
    };
}

export function deleteShift(shift) {
    return (dispatch) => {
        return axios.delete('rest/shifts/'+ shift.id).then(response => {
            dispatch(deleteShiftSuccess(response.data));
        }).catch(error => {
            throw(error);
        })
    };
}

export function saveShift(shift) {
    return (dispatch, getState) => {
        if (shift.id) {
            return axios.put('rest/shifts/' + shift.id, shift)
                .then(response => {
                    console.log("Response put: ", response);
                    dispatch(updateShiftSuccess(response.data))
                }).catch(error => {
                    throw (error);
                });
        } else {
            return axios.post('rest/shifts', shift)
                .then(response => {
                    console.log("Response post: ", response);
                    dispatch(createShiftSuccess(response.data));
                }).catch(error => {
                    throw (error);
                });
        }

    };
}