import React from 'react';
import PropTypes from 'prop-types';
import TextInput from '../common/TextInput';
import MultipleSelectInput from "../common/MultipleSelectInput";
import PasswordInput from "../common/PasswordInput";

const EmployeeForm = ({employee, roles, performances, onSave, onChange, saving, errors}) => {
    return (
        <form>
            <TextInput
                name="name"
                label="Jméno"
                value={employee.name}
                onChange={onChange}
                error={errors.name}/>
            <TextInput
                name="surname"
                label="Příjmení"
                value={employee.surname}
                onChange={onChange}
                error={errors.surname}/>
            <TextInput
                name="email"
                label="E-mail"
                value={employee.email}
                onChange={onChange}
                error={errors.email}/>
            <PasswordInput
                name="password"
                label="Heslo"
                value={employee.password}
                onChange={onChange}
                error={errors.password}/>
            <TextInput
                name="phoneNumber"
                label="Telefonní číslo"
                value={employee.phoneNumber}
                onChange={onChange}
                error={errors.phoneNumber}/>
            <h3>Adresa</h3>
            <TextInput
                name="streetName"
                label="Název ulice"
                value={employee.address.streetName}
                onChange={onChange}
                error={errors.streetName}/>
            <TextInput
                name="streetNumber"
                label="Číslo popisné"
                value={employee.address.streetNumber}
                onChange={onChange}
                error={errors.streetNumber}/>
            <TextInput
                name="city"
                label="Město"
                value={employee.address.city}
                onChange={onChange}
                error={errors.city}/>
            <TextInput
                name="postalCode"
                label="PSČ"
                value={employee.address.postalCode}
                onChange={onChange}
                error={errors.phoneNumber}/>

            {/* <MultipleSelectInput
                name="roles"
                label="Uživatelské role"
                value={employee.roles}
                defaultOption="Vyberte uživatelské role"
                onChange={onChange}
                options={roles}
                error={errors.roles}
                multiple={true}
            />

            <MultipleSelectInput
                name="performances"
                label="Role v představeních"
                value={employee.performances}
                defaultOption="Vyberte role"
                onChange={onChange}
                options={performances}
                error={errors.performances}
                multiple={false}
            />*/}

            <input
                type="submit"
                disabled={saving}
                value={saving ? 'Ukládám...' : 'Uložit'}
                className=""
                onClick={onSave}/>
        </form>
    );
};

EmployeeForm.propTypes = {
    employee: PropTypes.object.isRequired,
    roles: PropTypes.array,
    performances: PropTypes.array,
    onSave: PropTypes.func.isRequired,
    onChange: PropTypes.func.isRequired,
    saving: PropTypes.bool,
    errors: PropTypes.object
};

export default EmployeeForm;