import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import * as shiftActions from '../../actions/shiftActions';
import { bindActionCreators } from 'redux';
import CalendarList from './CalendarList';
import toastr from 'toastr';

class CalendarPage extends React.Component {

    constructor(props, context) {
        super(props, context);

        this.redirectToAddShift = this.redirectToAddShift.bind(this);
        this.deleteShift = this.deleteShift.bind(this);
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

    render() {
        const {shifts} = this.props;

        return (
                <div>
                    <input type="submit"
                           value="Přidat směnu"
                           className=""
                           onClick={this.redirectToAddShift}
                    />
                    <CalendarList shifts={shifts} onClick={this.deleteShift}/>
                </div>
        );
    }

}

CalendarPage.propTypes = {
    shifts: PropTypes.array.isRequired,
    actions: PropTypes.object.isRequired
};

CalendarPage.contextTypes = {
    router: PropTypes.object
};

function mapStateToProps(state, ownProps) {
    return {
      shifts: state.shifts
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators(shiftActions, dispatch)
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(CalendarPage);