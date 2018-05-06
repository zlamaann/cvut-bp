import * as types from './actionTypes';
import axios from 'axios';

export function loadDailyCalendarSuccess(shifts) {
    return { type: types.LOAD_DAILY_CALENDAR_SUCCESS, shifts};
}

export function deleteFromCalendarSuccess(shift) {
    return { type: types.DELETE_FROM_CALENDAR_SUCCESS, shift};
}

export function loadDailyCalendar() {
    return (dispatch) => {
        return axios.get('rest/shifts').then(response => {
            dispatch(loadDailyCalendarSuccess(response.data));
        }).catch(error => {
            throw(error);
        })
    };
}

export function deleteFromCalendar(shift) {
    return (dispatch) => {
        return axios.delete('rest/shifts/'+ shift.id.toString()).then(response => {
            dispatch(deleteFromCalendarSuccess(response.data));
        }).catch(error => {
            throw(error);
        })
    };
}