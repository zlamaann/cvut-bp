import * as types from './actionTypes';
import axios from 'axios';

export function loadPerformancesSuccess(performances) {
    return { type: types.LOAD_PERFORMANCES_SUCCESS, performances};
}

export function loadPerformances() {
    return (dispatch) => {
        return axios.get('rest/performance').then(response => {
            dispatch(loadPerformancesSuccess(response.data));
        }).catch(error => {
            throw(error);
        })
    };
}