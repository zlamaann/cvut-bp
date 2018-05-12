import * as types from '../actions/actionTypes';
import initialState from './initialState';

export default function (state = initialState.messages, action) {
    switch (action.type) {
        case types.LOAD_MESSAGES_SUCCESS:
            return action.messages;

        case types.CREATE_MESSAGE_SUCCESS:
            return [
                ...state,
                Object.assign({}, action.message)
            ];

        case types.UPDATE_MESSAGE_SUCCESS:
            return [
                ...state.filter(message => message.id !== action.message.id),
                Object.assign({}, action.message)
            ];

        case  types.DELETE_MESSAGE_SUCCESS:
            const newState = Object.assign([], state);
            const indexOfMessageToDelete = state.findIndex(message => {
                return message.id === action.message.id
            });

            newState.splice(indexOfMessageToDelete, 1);
            return newState;

        default:
            return state;
    }
}