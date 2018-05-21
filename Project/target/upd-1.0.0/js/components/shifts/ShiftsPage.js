import React from 'react';
import {connect} from "react-redux";
import * as shiftsActions from "../../actions/shiftActions";
import {loadProfile} from "../../actions/profileActions";
import {bindActionCreators} from "redux";
import PropTypes from "prop-types";
import toastr from "toastr";
import ShiftsList from "./ShiftsList";
import SelectInput from "../common/SelectInput";

class ShiftsPage extends React.Component {

    constructor(props, context) {
        super(props, context);

        this.state = {
          employee: Object.assign({}, props.current),
            errors: {}
        };

        this.redirectToAddShift = this.redirectToAddShift.bind(this);
        this.deleteShift = this.deleteShift.bind(this);
        this.updateShiftState = this.updateShiftState.bind(this);
    }

    redirectToAddShift() {
        this.context.router.history.push('/shift');
    }

    deleteShift(shift) {
        this.props.actions.deleteShift(shift)
            .then(() => {
                toastr.success('Směna smazána.');
            });

    }

    updateShiftState(event) {
        let id = event.target.value;
        let employee = this.props.employees.find(employee => employee.id === Number(id));
        return this.setState({employee:employee});
    }

    render() {
        const {employeesFormatted} = this.props;
        let current = this.state.employee;
        let errors = this.state.errors;

        return (
            <div>
                <div className={"flex row space-between"}>
                    <div>
                        <SelectInput
                            onChange={this.updateShiftState}
                            label="Vyberte zaměstnance"
                            name="employee"
                            value={current.id.toString()}
                            defaultOption="Vyberte zaměstance"
                            options={employeesFormatted}
                            error={errors.current}
                        />
                    </div>
                    <div>
                        <input type="submit"
                               value="Přidat novou směnu"
                               className=""
                               onClick={this.redirectToAddShift}
                        />
                    </div>
                </div>
                <h2>{`${current.name} ${current.surname}`}</h2>
                <ShiftsList shifts={current.shifts} onClick={this.deleteShift}/>
            </div>
        );
    }

}

ShiftsPage.propTypes = {
    shifts: PropTypes.array.isRequired,
    actions: PropTypes.object.isRequired,
    current: PropTypes.object.isRequired,
    employees: PropTypes.array.isRequired,
    employeesFormatted: PropTypes.array.isRequired
};

ShiftsPage.contextTypes = {
    router: PropTypes.object
};

function mapStateToProps(state, ownProps) {

    const employeesFormattedForDropdown = state.employees.map(employee => {
        return {
            value: employee.id.toString(),
            text: employee.name + " " + employee.surname
        }
    });

    return {
        shifts: state.shifts,
        current: state.current,
        employees: state.employees,
        employeesFormatted: employeesFormattedForDropdown
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators(shiftsActions, dispatch)
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(ShiftsPage);