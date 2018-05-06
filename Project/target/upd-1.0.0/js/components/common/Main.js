import React from 'react';
import {Route, Switch} from "react-router-dom";
import CalendarPage from "../calendar/CalendarPage";
import Shifts from "../shifts/ShiftsPage";
import Messages from "../messages/Messages";
import Performances from "../performances/Performances";
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
                    <Route path="/shifts" component={Shifts}/>
                    <Route path="/shift/:id" component={ManageShiftPage}/>
                    <Route path="/shift" component={ManageShiftPage}/>
                    <Route path="/messages" component={Messages}/>
                    <Route path="/performance" component={Performances}/>
                    <Route path="/performance/:id" component={Performances}/>
                    <Route path="/employees" component={Employees}/>
                    <Route path="/rehearsals" component={Rehearsals}/>
                    <Route path="/profile" component={Profile}/>
                    <Route path="/logout" component={Logout}/>

                </Switch>
        );
    }
}