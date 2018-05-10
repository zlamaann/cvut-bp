import React from 'react';
import PropTypes from 'prop-types';

const MultipleSelectInput = ({name, label, onChange, defaultOption, value, error, options}) => {
    return (
        <div className="">
            <label htmlFor={name}>{label}</label>
            <div className="">
                <select
                    type="text"
                    name={name}
                    className=""
                    value={value}
                    multiple={true}
                    onChange={onChange}>
                    <option value="">{defaultOption}</option>
                    {options.map(option => {
                        return <option key={option.value} value={option.value}>{option.text}</option>
                    })}
                </select>
                {error && <div className="alert">{error}</div>}
            </div>
        </div>
    );
};

MultipleSelectInput.propTypes = {
    name: PropTypes.string.isRequired,
    label: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
    defaultOption: PropTypes.string,
    value: PropTypes.array,
    error: PropTypes.string,
    options: PropTypes.arrayOf(PropTypes.object)
};

export default MultipleSelectInput;