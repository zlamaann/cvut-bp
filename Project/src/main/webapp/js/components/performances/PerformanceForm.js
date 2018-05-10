import React from 'react';
import PropTypes from 'prop-types';
import TextInput from '../common/TextInput';
import CheckboxInput from "../common/CheckboxInput";

const PerformanceForm = ({performance, onSave, onChange, saving, errors}) => {
    return (
        <form>
            <TextInput
                name="name"
                label="Název"
                value={performance.name}
                onChange={onChange}
                error={errors.name}/>
            <TextInput
                name="length"
                label="Délka"
                value={performance.length}
                onChange={onChange}
                error={errors.length}/>

            <CheckboxInput
                name="isRegular"
                label="Je to běžné představení?"
                checked={performance.isRegular}
                onChange={onChange}
                error={errors.isRegular}/>
            <TextInput
                name="description"
                label="Popis"
                value={performance.description}
                onChange={onChange}
                error={errors.description}/>

            <input
                type="submit"
                disabled={saving}
                value={saving ? 'Ukládám...' : 'Uložit'}
                className=""
                onClick={onSave}/>
        </form>
    );
};

PerformanceForm.propTypes = {
    performance: PropTypes.object.isRequired,
    onSave: PropTypes.func.isRequired,
    onChange: PropTypes.func.isRequired,
    saving: PropTypes.bool,
    errors: PropTypes.object
};

export default PerformanceForm;