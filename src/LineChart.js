'use strict';

var React = require('react-native');
var BarLineChartMixin = require('./mixins/BarLineChartMixin.js');

var LineChart = React.createClass({
  name: 'LineChart',
  mixins: [BarLineChartMixin],

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
