import React from 'react';
import PropTypes from 'prop-types';

const TextInput = ({name, label, onChange, placeholder, value, error}) => {

    return (
        <div className="">
            <label htmlFor={name}>{label}</label>
            <div className="">
                <input
                    type="datetime-local"
                    name={name}
                    className=""
                    placeholder={placeholder}
                    value={value}
                    onChange={onChange}/>
                {error && <div className="alert">{error}</div>}
            </div>
        </div>
    );
};

TextInput.propTypes = {
    name: PropTypes.string.isRequired,
    label: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
    placeholder: PropTypes.string,
    value: PropTypes.string,
    error: PropTypes.string
};

export default TextInput;