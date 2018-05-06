import React from 'react';
import {Route, Switch} from "react-router-dom";
import CalendarPage from "../calendar/CalendarPage";
import ShiftsPage from "../shifts/ShiftsPage";
import Messages from "../messages/Messages";
import PerformancesPage from "../performances/PerformancesPage";
import ManagePerformancesPage from "../performances/ManagePerformancesPage";
import Rehearsals from "../rehearsals/Rehearsals";
import Employees from "../employees/Employees";
import Profile from "../profile/Profile";
import Logout from "../Logout";
import Login from "../Login";
import ManageShiftPage from "../shifts/ManageShiftPage";

export default class Main extends React.Component {

    render() {
        return (
                <Switch>
                    <Route exact path="/" component={CalendarPage}/>
                    <Route path="/shifts" component={ShiftsPage}/>
                    <Route path="/shift/:id" component={ManageShiftPage}/>
                    <Route path="/shift" component={ManageShiftPage}/>
                    <Route path="/messages" component={Messages}/>
                    <Route path="/performances" component={PerformancesPage}/>
                    <Route path="/performance/:id" component={ManagePerformancesPage}/>
                    <Route path="/employees" component={Employees}/>
                    <Route path="/rehearsals" component={Rehearsals}/>
                    <Route path="/profile" component={Profile}/>
                    <Route path="/logout" component={Logout}/>

                </Switch>
        );
    }
}