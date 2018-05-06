import * as types from '../actions/actionTypes';
import initialState from './initialState';

export default function (state = initialState.shifts, action) {
    switch (action.type) {
        case types.LOAD_SHIFTS_SUCCESS:
            return action.shifts;

        case types.CREATE_SHIFT_SUCCESS:
            return [
                ...state,
                Object.assign({}, action.shift)
            ];

        case types.UPDATE_SHIFT_SUCCESS:
            return [
                ...state.filter(shift => shift.id !== action.shift.id),
                Object.assign({}, action.shift)
            ];

        case  types.DELETE_SHIFT_SUCCESS:
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