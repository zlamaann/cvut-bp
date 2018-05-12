import {combineReducers} from 'redux';
import performances from './performanceReducer';
import shifts from './shiftReducer';
import shiftTypes from './shiftTypesReducer';
import locations from './locationReducer';
import employees from './employeeReducer';
import current from './profileReducer';
import messages from './messageReducer';

const rootReducer  = combineReducers({
    performances,
    shifts, shiftTypes,
    locations,
    employees,
    current,
    messages
});

export default rootReducer;