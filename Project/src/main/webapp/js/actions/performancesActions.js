import * as types from './actionTypes';
import axios from 'axios';

export function loadPerformancesSuccess(performances) {
    return { type: types.LOAD_PERFORMANCES_SUCCESS, performances};
}

export function updatePerformanceSuccess(performance) {
    return { type: types.UPDATE_PERFORMANCE_SUCCESS, performance};
}

export function createPerformanceSuccess(performance) {
    return { type: types.CREATE_PERFORMANCE_SUCCESS, performance};
}

export function deletePerformanceSuccess(performance) {
    return { type: types.DELETE_PERFORMANCE_SUCCESS, performance};
}

export function loadPerformances() {
    return (dispatch) => {
        return axios.get('rest/performances').then(response => {
            dispatch(loadPerformancesSuccess(response.data));
        }).catch(error => {
            throw(error);
        })
    };
}

export function deletePerformance(performance) {
    return (dispatch) => {
        return axios.delete('rest/performances/' + performance.id).then(response => {
            dispatch(deletePerformanceSuccess(response.data));
        }).catch(error => {
            throw(error);
        })
    };
}

export function savePerformance(performance) {
    return (dispatch, getState) => {
        if (performance.id) {
            return axios.put('rest/performances/' + performance.id, performance)
                .then(response => {
                    dispatch(updatePerformanceSuccess(response.data))
                }).catch(error => {
                    throw (error);
                });
        } else {
            return axios.post('rest/performances', performance)
                .then(response => {
                    dispatch(createPerformanceSuccess(response.data));
                }).catch(error => {
                    throw (error);
                });
        }

    };
}
