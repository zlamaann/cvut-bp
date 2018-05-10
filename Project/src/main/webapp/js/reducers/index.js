import {combineReducers} from 'redux';
import calendar from './calendarReducer';
import performances from './performanceReducer';
import shifts from './shiftReducer';
import shiftTypes from './shiftTypesReducer';
import locations from './locationReducer';
import employees from './employeeReducer';
import current from './profileReducer';

const rootReducer  = combineReducers({
   calendar,
    performances,
    shifts, shiftTypes,
    locations,
    employees,
    current
});

export default rootReducer;