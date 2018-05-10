import React from 'react';
import PropTypes from 'prop-types';

const CheckboxInput = ({name, label, onChange, checked, error}) => {
    let wrapperClass = '';
    if (error && error.length > 0) {
        wrapperClass += " " + 'error';
    }

    return (
        <div className={wrapperClass}>
            <label htmlFor={name}>{label}</label>
            <div className="">
                <input
                    type="checkbox"
                    name={name}
                    className=""
                    checked={checked}
                    onChange={onChange}/>
                {error && <div className="alert">{error}</div>}
            </div>
        </div>
    );
};

CheckboxInput.propTypes = {
    name: PropTypes.string.isRequired,
    label: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
    checked: PropTypes.bool.isRequired,
    error: PropTypes.string
};

export default CheckboxInput;