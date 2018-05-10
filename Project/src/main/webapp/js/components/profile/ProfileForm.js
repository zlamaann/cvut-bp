import React from 'react';
import PropTypes from 'prop-types';
import TextInput from '../common/TextInput';
import MultipleSelectInput from "../common/MultipleSelectInput";

const ProfileForm = ({current, onSave, onChange, saving, errors}) => {
    return (
        <form>
            <TextInput
                name="name"
                label="Jméno"
                value={current.name}
                onChange={onChange}
                error={errors.name}/>
            <TextInput
                name="surname"
                label="Příjmení"
                value={current.surname}
                onChange={onChange}
                error={errors.surname}/>
            <TextInput
                name="email"
                label="E-mail"
                value={current.email}
                onChange={onChange}
                error={errors.email}/>
            <TextInput
                name="phoneNumber"
                label="Telefonní číslo"
                value={current.phoneNumber}
                onChange={onChange}
                error={errors.phoneNumber}/>
            <h3>Adresa</h3>
            <TextInput
                name="streetName"
                label="Název ulice"
                value={current.address.streetName}
                onChange={onChange}
                error={errors.streetName}/>
            <TextInput
                name="streetNumber"
                label="Číslo popisné"
                value={current.address.streetNumber}
                onChange={onChange}
                error={errors.streetNumber}/>
            <TextInput
                name="city"
                label="Město"
                value={current.address.city}
                onChange={onChange}
                error={errors.city}/>
            <TextInput
                name="postalCode"
                label="PSČ"
                value={current.address.postalCode}
                onChange={onChange}
                error={errors.phoneNumber}/>

            <input
                type="submit"
                disabled={saving}
                value={saving ? 'Ukládám...' : 'Uložit'}
                className=""
                onClick={onSave}/>
        </form>
    );
};

ProfileForm.propTypes = {
    current: PropTypes.object.isRequired,
    onSave: PropTypes.func.isRequired,
    onChange: PropTypes.func.isRequired,
    saving: PropTypes.bool,
    errors: PropTypes.object
};

export default ProfileForm;