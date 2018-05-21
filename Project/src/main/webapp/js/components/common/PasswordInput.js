import React from 'react';
import PropTypes from 'prop-types';

const PasswordInput = ({name, label, onChange, placeholder, value, error}) => {
    let wrapperClass = '';
    if (error && error.length > 0) {
        wrapperClass += " " + 'error';
    }

    return (
        <div className={wrapperClass}>
            <label htmlFor={name}>{label}</label>
            <div className="">
                <input
                    type="password"
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

PasswordInput.propTypes = {
    name: PropTypes.string.isRequired,
    label: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
    placeholder: PropTypes.string,
    value: PropTypes.string,
    error: PropTypes.string
};

export default PasswordInput;