import React from 'react';
import PropTypes from 'prop-types';
import {Link} from 'react-router-dom';

const MessagesGridItem = ({message, onDelete}) => {
    return (
        <section className="grid-item">
            <article className="flex column">
                <h4>{message.subject}</h4>
                <span>{message.description}</span>
                <span>{new Date(message.date).toLocaleString([], {hour: 'numeric', minute:'2-digit', year:"numeric", month: "numeric", day: "numeric"})}</span>
                <span>{`${message.author.name.charAt(0)}. ${message.author.surname}`}</span>
                <article className="flex row justify-content flex-end">
                    <span><Link to={'/message/' + message.id}>Upravit</Link></span>
                    <span className="left-margin-1"><a href onClick={(e) => {e.preventDefault(); onDelete(message)}}>Smazat</a></span>
                </article>
            </article>
        </section>
    );
};

MessagesGridItem.propTypes = {
    message: PropTypes.object.isRequired,
    onDelete: PropTypes.func.isRequired
};

export default MessagesGridItem;