import * as types from '../actions/actionTypes';
import initialState from './initialState';

export default function (state = initialState.shifts, action) {
    switch (action.type) {
        case types.LOAD_DAILY_CALENDAR_SUCCESS:
            return action.shifts;

        case  types.DELETE_FROM_CALENDAR_SUCCESS:
            const newState = Object.assign([], state);
            const indexOfShiftToDelete = state.findIndex(shift => {
                return shift.id === action.shift.id
            });

            newState.splice(indexOfShiftToDelete, 1);
            return newState;

        default:
            return state;
    }
}