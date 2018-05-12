import React from 'react';
import PropTypes from 'prop-types';
import MessagesGridItem from "./MessagesGridItem";
import MessageForm from "./MessageForm";

const MessagesGrid = ({messages, message, onDelete, onAdd, onSave, onChange, onClose, isCreating, errors}) => {
    if (isCreating) {
        return (
            <section className="grid-items">
                {messages.map(message =>
                    <MessagesGridItem key={message.id} message={message} onDelete={onDelete} />
                )}
                <section className="grid-item">
                    <article className="flex column">
                        <MessageForm message={message} onSave={onSave} onChange={onChange} onClose={onClose} errors={errors}/>
                    </article>
                </section>
            </section>
        );
    }
    else {
        return (
            <section className="grid-items">
                {messages.map(message =>
                    <MessagesGridItem key={message.id} message={message} onDelete={onDelete}/>
                )}
                <input type="submit"
                       value="+"
                       className="btn-plus"
                       onClick={onAdd}
                />
            </section>
        );
    }

};

MessagesGrid.propTypes = {
    messages: PropTypes.array.isRequired,
    message: PropTypes.object.isRequired,
    onDelete: PropTypes.func.isRequired,
    onAdd: PropTypes.func.isRequired,
    onSave: PropTypes.func.isRequired,
    onChange: PropTypes.func.isRequired,
    onClose: PropTypes.func.isRequired,
    isCreating: PropTypes.bool.isRequired,
    errors: PropTypes.object
};

export default MessagesGrid;