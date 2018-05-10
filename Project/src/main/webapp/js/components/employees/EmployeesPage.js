import React from 'react';
import toastr from "toastr";
import {bindActionCreators} from 'redux';
import * as employeesActions from "../../actions/employeesActions";
import {connect} from 'react-redux';
import PropTypes from "prop-types";
import EmployeesList from "./EmployeesList";

class EmployeesPage extends React.Component {

    constructor(props, context) {
        super(props, context);

        this.redirectToAddEmployee = this.redirectToAddEmployee.bind(this);
        this.deleteEmployee = this.deleteEmployee.bind(this);
    }

    componentWillMount() {
        this.props.actions.loadEmployees();
    }

    redirectToAddEmployee() {
        this.context.router.history.push('/employee');
    }

    deleteEmployee(employee) {
        this.props.actions.deleteEmployee(employee)
            .then(() => {
                toastr.success('Zaměstnanec smazán.');
            });

    }

    render() {
        const {employees} = this.props;

        return (
            <div>
                <input type="submit"
                       value="Přidat zaměstnance"
                       className=""
                       onClick={this.redirectToAddEmployee}
                />
            <EmployeesList employees={employees} onClick={this.deleteEmployee}/>
            </div>
        );
    }

}

EmployeesPage.propTypes = {
    employees: PropTypes.array.isRequired,
    actions: PropTypes.object.isRequired
};

EmployeesPage.contextTypes = {
    router: PropTypes.object
};

function mapStateToProps(state, ownProps) {
    return {
        employees: state.employees
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators(employeesActions, dispatch)
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(EmployeesPage);