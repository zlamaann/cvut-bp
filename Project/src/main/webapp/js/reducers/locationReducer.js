import initialState from "./initialState";
import * as types from "../actions/actionTypes";

export default function (state = initialState.locations, action) {
    switch (action.type) {
        case types.LOAD_LOCATIONS_SUCCESS:
            return action.locations;

        default:
            return state;
    }
}