'use strict';

var React = require('react-native');
var BaseChartMixin = require('./mixins/BaseChartMixin.js');

var PieChart = React.createClass({
  name: 'PieChart',
  mixins: [BaseChartMixin],
  propTypes: {
    /*
     * Radius of the pie hole in percent
     */
    holeRadius: React.PropTypes.number,

    /*
     * Angle that the first segment starts at in degrees
     * (0 degrees = 3 oclock)
     */
    rotationAngle: React.PropTypes.number,
  },

  render: function(){
    return (
      <PieChartComponent
        {...this.props}
        onSelect={this._onSelect}
      />
    );
  },

});

var PieChartComponent = React.requireNativeComponent('MPChartPie', PieChart, {
  nativeOnly: {onSelect: true},
});

module.exports = PieChart;
