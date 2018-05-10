import * as types from './actionTypes';
import axios from 'axios';

export function updateProfileSuccess(current) {
    return { type: types.UPDATE_PROFILE_SUCCESS, current};
}

export function loadProfileSuccess(current) {
    return { type: types.LOAD_PROFILE_SUCCESS, current};
}

export function loadProfile() {
    return (dispatch) => {
        return axios.get('rest/persons/current').then(response => {
            dispatch(loadProfileSuccess(response.data));
        }).catch(error => {
            throw(error);
        })
    };
}

export function saveProfile(current) {
    return (dispatch, getState) => {
            return axios.put('rest/persons/' + current.id, current)
                .then(response => {
                    dispatch(updateProfileSuccess(response.data))
                }).catch(error => {
                    throw (error);
                });

    };
}