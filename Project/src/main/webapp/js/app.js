import React from 'react';
import { HashRouter} from 'react-router-dom';
import configureStore from './store/configureStore';
import { render } from 'react-dom';
import { Provider } from 'react-redux';
import Layout from "./components/common/Layout";
import {loadProfile} from "./actions/profileActions";
import {loadPerformances} from "./actions/performancesActions";
import {loadEmployees} from "./actions/employeesActions";
import {loadLocations} from "./actions/locationActions";
import {loadMessages} from "./actions/messagesActions";
import {loadShifts} from "./actions/shiftActions";
import {loadShiftTypes} from "./actions/shiftTypesActions";
//np import "../node_modules/toastr/build/toastr.min.css";

const store = configureStore();
store.dispatch(loadPerformances());
store.dispatch(loadProfile());
store.dispatch(loadShifts());
store.dispatch(loadShiftTypes());
store.dispatch(loadMessages());
store.dispatch(loadEmployees());
store.dispatch(loadLocations());



render(
    <Provider store={store}>
        <HashRouter>
            <Layout/>
        </HashRouter>
    </Provider>,
    document.getElementById('root')
);

