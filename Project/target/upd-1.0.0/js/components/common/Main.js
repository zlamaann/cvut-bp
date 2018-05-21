import React from 'react';
import {Route, Switch} from "react-router-dom";
import CalendarPage from "../calendar/CalendarPage";
import ShiftsPage from "../shifts/ShiftsPage";
import MessagesPage from "../messages/MessagesPage";
import PerformancesPage from "../performances/PerformancesPage";
import ManagePerformancesPage from "../performances/ManagePerformancesPage";
import EmployeesPage from "../employees/EmployeesPage";
import ProfilePage from "../profile/ProfilePage";
import Logout from "../Logout";
import ManageShiftPage from "../shifts/ManageShiftPage";
import ManageEmployeesPage from "../employees/ManageEmployeesPage";


export default class Main extends React.Component {

    render() {
        return (
                <Switch>
                    <Route exact path="/" component={CalendarPage}/>
                    <Route path="/shifts" component={ShiftsPage}/>
                    <Route path="/shift/:id" component={ManageShiftPage}/>
                    <Route path="/shift" component={ManageShiftPage}/>
                    <Route path="/messages" component={MessagesPage}/>
                    <Route path="/performances" component={PerformancesPage}/>
                    <Route path="/performance/:id" component={ManagePerformancesPage}/>
                    <Route path="/performance" component={ManagePerformancesPage}/>
                    <Route path="/employees" component={EmployeesPage}/>
                    <Route path="/employee/:id" component={ManageEmployeesPage}/>
                    <Route path="/employee" component={ManageEmployeesPage}/>
                    <Route path="/profile" component={ProfilePage}/>
                    <Route path="/logout" component={Logout}/>

                </Switch>
        );
    }
}