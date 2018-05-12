import React from 'react';
import PropTypes from 'prop-types';
import {Link} from 'react-router-dom';

const PerformancesListRow = ({performance, onClick}) => {
    return (
        <tr>
            <td>{performance.name === undefined ? "" : performance.name}</td>
            <td>{performance.length === undefined ? "" : performance.length}</td>
            <td>{performance.isRegular === undefined ? "" : (performance.isRegular ? "Ano" : "Ne")}</td>
            <td><Link to={'/performance/' + performance.id}>Detaily</Link></td>
            <td><a href onClick={(e) => {e.preventDefault(); onClick(performance)}}> Smazat</a></td>
        </tr>
    );
};

PerformancesListRow.propTypes = {
    performance: PropTypes.object.isRequired,
    onClick: PropTypes.func.isRequired
};

export default PerformancesListRow;