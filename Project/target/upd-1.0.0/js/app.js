import React from 'react';
import { HashRouter} from 'react-router-dom';
import configureStore from './store/configureStore';
import { PersistGate } from 'redux-persist/integration/react';
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
// import "../node_modules/toastr/build/toastr.min.css";

const store = configureStore();
store.store.dispatch(loadPerformances());
store.store.dispatch(loadProfile());
store.store.dispatch(loadShifts());
store.store.dispatch(loadShiftTypes());
store.store.dispatch(loadMessages());
store.store.dispatch(loadEmployees());
store.store.dispatch(loadLocations());



render(
    <Provider store={store.store}>
        <PersistGate loading={null} persistor={ store.persistor }>
            <HashRouter>
                <Layout/>
            </HashRouter>
        </PersistGate>
    </Provider>,
    document.getElementById('root')
);

