import * as types from '../actions/actionTypes';
import initialState from './initialState';

export default function (state = initialState.performances, action) {
    switch (action.type) {
        case types.LOAD_PERFORMANCES_SUCCESS:
            return action.performances;

        case types.CREATE_PERFORMANCE_SUCCESS:
            return [
                ...state,
                Object.assign({}, action.performance)
            ];

        case types.UPDATE_PERFORMANCE_SUCCESS:
            return [
                ...state.filter(performance => performance.id !== action.performance.id),
                Object.assign({}, action.performance)
            ];

        case  types.DELETE_PERFORMANCE_SUCCESS:
            const newState = Object.assign([], state);
            const indexOfPerformanceToDelete = state.findIndex(performance => {
                return performance.id === action.performance.id
            });

            newState.splice(indexOfPerformanceToDelete, 1);
            return newState;

        default:
            return state;
    }
}