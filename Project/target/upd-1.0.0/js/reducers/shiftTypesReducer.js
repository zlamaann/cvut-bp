import initialState from "./initialState";
import * as types from "../actions/actionTypes";

export default function (state = initialState.shiftTypes, action) {
    switch (action.type) {
        case types.LOAD_SHIFT_TYPES_SUCCESS:
            return action.shiftTypes;

        default:
            return state;
    }
}