'use strict';

var React = require('react-native');
var BarLineChartMixin = require('./mixins/BarLineChartMixin.js');

var LineChart = React.createClass({
  name: 'LineChart',
  mixins: [BarLineChartMixin],

  propTypes: {
    /*
     * data is already defined in the BaseChartMixin
     * but we can add properties to it by declaring props in the usual way
     */
    data: React.PropTypes.shape({
      dataSets: React.PropTypes.arrayOf(React.PropTypes.shape({
        /*
         * Draw circle around each data point
         */
        drawCircles: React.PropTypes.bool,

        /*
         * Draw smooth line of best fit
         */
        drawCubic: React.PropTypes.bool,
      })),
    }),
  },

  render: function(){
    return (
      <LineChartComponent
        {...this.props}
        onSelect={this._onSelect}
      />
    );
  },

});

var LineChartComponent = React.requireNativeComponent('MPChartLine', LineChart, {
  nativeOnly: {onSelect: true},
});

module.exports = LineChart;
