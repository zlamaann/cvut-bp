import * as types from "./actionTypes";
import axios from "axios/index";

export function loadShiftTypesSuccess(shiftTypes) {
    return { type: types.LOAD_SHIFT_TYPES_SUCCESS, shiftTypes};
}

export function loadShiftTypes() {
    return (dispatch) => {
        return axios.get('rest/shifts/types').then(response => {
            dispatch(loadShiftTypesSuccess(response.data));
        }).catch(error => {
            throw(error);
        })
    };
}