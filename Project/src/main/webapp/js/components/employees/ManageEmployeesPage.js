import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import * as employeesActions from '../../actions/employeesActions';
import { bindActionCreators } from 'redux';
import EmployeeForm from './EmployeeForm';
import toastr from 'toastr';

class ManageEmployeesPage extends React.Component {
    constructor(props, context) {
        super(props, context);

        this.state = {
            employee: Object.assign({}, props.employee),
            errors: {},
            saving: false,
            isEditing: false
        };

        this.updateEmployeeState = this.updateEmployeeState.bind(this);
        this.findInArray = this.findInArray.bind(this);
        this.saveEmployee = this.saveEmployee.bind(this);
        this.redirect = this.redirect.bind(this);
        this.toggleEdit = this.toggleEdit.bind(this);
    }

    componentWillMount() {
        this.props.actions.loadEmployees();

    }

    componentWillReceiveProps(nextProps) {
        if (this.props.employee.id !== nextProps.employee.id) {
            this.setState({employee: Object.assign({}, nextProps.employee)});
        }
    }

    updateEmployeeState(event) {
        const field = event.target.name;
        let employee = Object.assign({}, this.state.employee);
        employee[field] = event.target.value;
        return this.setState({employee:employee});
    }

    findInArray(array, id) {
        return array.find( item => item.id === Number(id));
    }

    saveEmployee(event) {
        event.preventDefault();
        this.setState({saving: true});
        let employee = {
            id: this.state.employee.id,
            name: this.state.employee.name,
            surname: this.state.employee.surname,
            email: this.state.employee.email,
            phoneNumber: this.state.employee.phoneNumber,
            address: {
                id: this.state.employee.address.id,
                streetName: this.state.employee.address.streetName,
                streetNumber: this.state.employee.address.streetNumber,
                city: this.state.employee.address.city,
                postalCode: this.state.employee.address.postalCode
            },
            roles: []
        };

        this.props.actions.saveEmployee(employee)
            .then(() => this.redirect())
            .catch(error => {
                toastr.error(error);
                this.setState({saving: false});
            });
    }

    toggleEdit() {
        this.setState({isEditing: !this.state.isEditing});
    }

    redirect() {
        this.setState({saving: false});
        toastr.success('Zaměstnanec uložen.');
        this.context.router.history.push('/persons');
    }

    render() {
        if (this.state.isEditing) {
            return (
                <EmployeeForm
                    performances={[]}
                    roles={[]}
                    employee={this.state.employee}
                    onSave={this.saveEmployee}
                    onChange={this.updateEmployeeState}
                    errors={this.state.errors}
                    saving={this.state.saving}/>
            );
        }

        return (
            <div>
                <h3>{this.state.employee.name + " " + this.state.employee.surname}</h3>
                <p>E-mail: {this.state.employee.email}</p>
                <p>Telefonní číslo: {this.state.employee.phoneNumber}</p>
                <p>Role v představeních: {this.state.employee.performances}</p>
                <p>Uživatelské role: {this.state.employee.roles}</p>
                <input type="submit" value="Upravit" onClick={this.toggleEdit}/>
            </div>
        )

    }
}

ManageEmployeesPage.propTypes = {
    employee: PropTypes.object.isRequired,
    actions: PropTypes.object.isRequired,
};

ManageEmployeesPage.contextTypes = {
    router: PropTypes.object
};

function getEmployeeById(employees, id) {
    const result = employees.filter(employee => employee.id === Number(id));
    if (result.length) return result[0];
    return null;
}

function mapStateToProps(state, ownProps) {
    const employeeId = ownProps.match.params.id; // path '/employee/:id'
    let employee = {
        id: '', name: '', surname: '', email: '', phoneNumber: '', address: { id: '', streetName: '', streetNumber: '',  city: '', postalCode: ''}, roles: []
    };

    if (employeeId && state.employees.length > 0) {
        let result = getEmployeeById(state.employees, employeeId);
        employee = {
            id: result.id.toString(),
            name: result.name,
            surname: result.surname,
            email: result.email,
            phoneNumber: result.phoneNumber.toString(),
            address: {
                id: result.address.id.toString(),
                streetName: result.address.streetName,
                streetNumber: result.address.streetNumber.toString(),
                city: result.address.city,
                postalCode: result.address.postalCode.toString()
            },
            roles: []
        };

    }

    return {
        employee: employee
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators(employeesActions, dispatch)
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(ManageEmployeesPage);