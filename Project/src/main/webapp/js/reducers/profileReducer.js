import * as types from '../actions/actionTypes';
import initialState from './initialState';

export default function (state = initialState.current, action) {
    switch (action.type) {
        case types.LOAD_PROFILE_SUCCESS:
            return action.current;

        case types.UPDATE_PROFILE_SUCCESS:
            return Object.assign({}, action.current);

        default:
            return state;
    }
}