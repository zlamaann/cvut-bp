import React from 'react';
import PropTypes from 'prop-types';
import TextInput from '../common/TextInput';

const MessageForm = ({message, onSave, onChange, onClose, saving, errors}) => {
    return (
        <div>
            <div className="close flex justify-content flex-end"><span onClick={onClose}>x</span></div>
            <form>
                <TextInput
                    name="subject"
                    label="Předmět"
                    value={message.subject}
                    onChange={onChange}
                    error={errors.subject}/>
                <TextInput
                    name="description"
                    label="Text"
                    value={message.description}
                    onChange={onChange}
                    error={errors.description}/>

                <input
                    type="submit"
                    disabled={saving}
                    value={saving ? 'Ukládám...' : 'Uložit'}
                    className=""
                    onClick={onSave}/>
            </form>
        </div>
    );
};

MessageForm.propTypes = {
    message: PropTypes.object.isRequired,
    onSave: PropTypes.func.isRequired,
    onChange: PropTypes.func.isRequired,
    onClose: PropTypes.func.isRequired,
    saving: PropTypes.bool,
    errors: PropTypes.object
};

export default MessageForm;