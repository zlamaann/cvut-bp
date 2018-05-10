import React from 'react';
import PropTypes from 'prop-types';
import {Link} from 'react-router-dom';

const CalendarListRow = ({shift, onClick}) => {
    return (
        <tr>
            <td>{shift.timeFrom === undefined ? "" : new Date(shift.timeFrom).toLocaleDateString([], { month: "long", day: "numeric"})}</td>
            <td>{shift.timeFrom === undefined ? "" : new Date(shift.timeFrom).toLocaleTimeString([], {hour: '2-digit', minute:'2-digit', hour12: false})}</td>
            <td>{shift.timeTo === undefined ? "" : new Date(shift.timeTo).toLocaleTimeString([], {hour: '2-digit', minute:'2-digit', hour12: false})}</td>
            <td>{shift.type === undefined ? "" : (shift.type.name === "DEFAULT" ? "" : shift.type.value) }</td>
            <td>{shift.performance === undefined ? "" : <Link to={'/performance/' + shift.id}>{shift.performance.name}</Link>}</td>
            <td>{shift.location === undefined ? "" : shift.location.name}</td>
            <td>{shift.notes === undefined ? "" : shift.notes}</td>
            <td><Link to={'/shift/' + shift.id}>Upravit</Link></td>
            <td><a href="#" onClick={() => onClick(shift)}> Smazat</a></td>
        </tr>
    );
};

CalendarListRow.propTypes = {
    shift: PropTypes.object.isRequired,
    onClick: PropTypes.func.isRequired
};

export default CalendarListRow;