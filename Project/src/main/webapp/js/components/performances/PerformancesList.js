import React from 'react';
import PropTypes from 'prop-types';
import PerformancesListRow from './PerformancesListRow';

const PerformancesList = ({performances, onClick}) => {
    return (
        <table>
            <thead>
            <tr>
                <th>Název představení</th>
                <th>Délka</th>
                <th>Běžné představení</th>
                <th>Popis</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            {performances.map(performance =>
                <PerformancesListRow key={performance.id} performance={performance} onClick={onClick}/>
            )}
            </tbody>
        </table>
    );
};

PerformancesList.propTypes = {
    performances: PropTypes.array.isRequired,
    onClick: PropTypes.func.isRequired
};

export default PerformancesList;