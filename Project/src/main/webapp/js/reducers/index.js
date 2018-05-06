import {combineReducers} from 'redux';
import calendar from './calendarReducer';
import performances from './performanceReducer';
import shifts from './shiftReducer';
import shiftTypes from './shiftTypesReducer';
import locations from './locationReducer';

const rootReducer  = combineReducers({
   calendar,
    performances,
    shifts, shiftTypes,
    locations
});

export default rootReducer;