import * as types from "./actionTypes";
import axios from "axios/index";

export function loadLocationsSuccess(locations) {
    return { type: types.LOAD_LOCATIONS_SUCCESS, locations};
}

export function loadLocations() {
    return (dispatch) => {
        return axios.get('rest/locations').then(response => {
            dispatch(loadLocationsSuccess(response.data));
        }).catch(error => {
            throw(error);
        })
    };
}