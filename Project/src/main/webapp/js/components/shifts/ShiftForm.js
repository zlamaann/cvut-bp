import React from 'react';
import PropTypes from 'prop-types';
import TextInput from '../common/TextInput';
import SelectInput from '../common/SelectInput';
import DateInput from '../common/DateInput';

const ShiftForm = ({shift, types, performances, locations, onSave, onChange, saving, errors}) => {
  return (
      <form>
          <SelectInput
              name="performance"
              label="Představení"
              value={shift.performance}
              defaultOption="Vyberte představení"
              onChange={onChange}
              options={performances}
              error={errors.performance}
          />

          <SelectInput
              name="type"
              label="Typ události"
              value={shift.type}
              defaultOption="Vyberte typ"
              onChange={onChange}
              options={types}
              error={errors.type}
          />

          <SelectInput
              name="location"
              label="Místo konání"
              value={shift.location}
              defaultOption="Vyberte místo konání"
              onChange={onChange}
              options={locations}
              error={errors.location}
          />

          <DateInput
              name="timeFrom"
              label="Od"
              value={shift.timeFrom}
              onChange={onChange}
              error={errors.timeFrom}/>

          <DateInput
              name="timeTo"
              label="Do"
              value={shift.timeTo}
              onChange={onChange}
              error={errors.timeTo}/>

          <TextInput
              name="notes"
              label="Poznámka"
              value={shift.notes}
              onChange={onChange}
              error={errors.notes}/>

          <input
            type="submit"
            disabled={saving}
            value={saving ? 'Ukládám...' : 'Uložit'}
            className=""
            onClick={onSave}/>
      </form>
  );
};

ShiftForm.propTypes = {
    shift: PropTypes.object.isRequired,
    types: PropTypes.array,
    performances: PropTypes.array,
    locations: PropTypes.array,
    onSave: PropTypes.func.isRequired,
    onChange: PropTypes.func.isRequired,
    saving: PropTypes.bool,
    errors: PropTypes.object
};

export default ShiftForm;