import * as types from './actionTypes';
import axios from 'axios';

export function loadMessagesSuccess(messages) {
    return { type: types.LOAD_MESSAGES_SUCCESS, messages};
}

export function updateMessageSuccess(message) {
    return { type: types.UPDATE_MESSAGE_SUCCESS, message};
}

export function createMessageSuccess(message) {
    return { type: types.CREATE_MESSAGE_SUCCESS, message};
}

export function deleteMessageSuccess(message) {
    return { type: types.DELETE_MESSAGE_SUCCESS, message};
}

export function loadMessages() {
    return (dispatch) => {
        return axios.get('rest/messages').then(response => {
            dispatch(loadMessagesSuccess(response.data));
        }).catch(error => {
            throw(error);
        })
    };
}

export function deleteMessage(message) {
    return (dispatch) => {
        return axios.delete('rest/messages/' + message.id).then(response => {
            dispatch(deleteMessageSuccess(response.data));
        }).catch(error => {
            throw(error);
        })
    };
}

export function saveMessage(message) {
    return (dispatch, getState) => {
        if (message.id) {
            return axios.put('rest/messages/' + message.id, message)
                .then(response => {
                    dispatch(updateMessageSuccess(response.data))
                }).catch(error => {
                    throw (error);
                });
        } else {
            return axios.post('rest/messages', message)
                .then(response => {
                    dispatch(createMessageSuccess(response.data));
                }).catch(error => {
                    throw (error);
                });
        }

    };
}
