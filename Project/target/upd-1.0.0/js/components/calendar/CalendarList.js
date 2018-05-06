import React from 'react';
import PropTypes from 'prop-types';
import CalendarListRow from './CalendarListRow';

const CalendarList = ({shifts, onClick}) => {
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
              <CalendarListRow key={shift.id} shift={shift} onClick={onClick}/>
          )}
          </tbody>
      </table>
  );
};

CalendarList.propTypes = {
    shifts: PropTypes.array.isRequired,
    onClick: PropTypes.func.isRequired
};

export default CalendarList;