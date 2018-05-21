import React from 'react';
import PropTypes from 'prop-types';
import ShiftsListRow from './ShiftsListRow';

const ShiftsList = ({shifts, onClick}) => {
    return (
        <table>
            <thead>
            <tr>
                <th>Datum</th>
                <th>Od</th>
                <th>Do</th>
                <th>Typ představení</th>
                <th>Název představení</th>
                <th>Místo konání</th>
                <th>Poznámka</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            {shifts.map(shift =>
                <ShiftsListRow key={shift.id} shift={shift} onClick={onClick}/>
            )}
            </tbody>
        </table>
    );
};

ShiftsList.propTypes = {
    shifts: PropTypes.array.isRequired,
    onClick: PropTypes.func.isRequired
};

export default ShiftsList;