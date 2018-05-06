import * as types from '../actions/actionTypes';
import initialState from './initialState';

export default function (state = initialState.performances, action) {
    switch (action.type) {
        case types.LOAD_PERFORMANCES_SUCCESS:
            return action.performances;

        default:
            return state;
    }
}