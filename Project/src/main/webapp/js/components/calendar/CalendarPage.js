import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import * as calendarActions from '../../actions/calendarActions';
import { bindActionCreators } from 'redux';
import CalendarList from './CalendarList';
import toastr from 'toastr';

class CalendarPage extends React.Component {

    constructor(props, context) {
        super(props, context);

        this.redirectToAddShift = this.redirectToAddShift.bind(this);
        this.deleteShift = this.deleteShift.bind(this);
    }

    componentWillMount() {
        this.props.actions.loadDailyCalendar();
    }

    redirectToAddShift() {
        this.context.router.history.push('/shift');
    }

    deleteShift(shift) {
        this.props.actions.deleteFromCalendar(shift)
            .then(() => {
                toastr.success('Událost smazána.');
            });

    }

    render() {
        const {calendar} = this.props;

        return (
                <div>
                    <input type="submit"
                           value="Přidat událost"
                           className=""
                           onClick={this.redirectToAddShift}
                    />
                    <CalendarList shifts={calendar} onClick={this.deleteShift}/>
                </div>
        );
    }

}

CalendarPage.propTypes = {
    calendar: PropTypes.array.isRequired,
    actions: PropTypes.object.isRequired
};

CalendarPage.contextTypes = {
    router: PropTypes.object
};

function mapStateToProps(state, ownProps) {
    return {
      calendar: state.calendar
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators(calendarActions, dispatch)
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(CalendarPage);