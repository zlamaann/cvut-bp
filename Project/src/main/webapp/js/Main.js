import React from 'react';
import {Route, Switch} from "react-router-dom";
import Calendar from "./components/calendar/Calendar";
import Shifts from "./components/shifts/Shifts";
import Messages from "./components/messages/Messages";
import Performances from "./components/performances/Performances";
import Rehearsals from "./components/rehearsals/Rehearsals";
import Contacts from "./components/Contacts";
import Employees from "./components/employees/Employees";
import Profile from "./components/Profile";
import Logout from "./components/Logout";

export default class Main extends React.Component {

    render() {
        return (
                <Switch>
                    <Route exact path="/" component={Calendar}/>
                    <Route path="/shifts" component={Shifts}/>
                    <Route path="/messages" component={Messages}/>
                    <Route path="/performance" component={Performances}/>
                    <Route path="/employees" component={Employees}/>
                    <Route path="/rehearsals" component={Rehearsals}/>
                    <Route path="/contacts" component={Contacts}/>
                    <Route path="/profile" component={Profile}/>
                    <Route path="/logout" component={Logout}/>
                </Switch>
        );
    }
}