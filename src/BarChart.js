'use strict';

var React = require('react-native');
var BarLineChartMixin = require('./mixins/BarLineChartMixin.js');

var BarChart = React.createClass({
  name: 'BarChart',
  mixins: [BarLineChartMixin],

  render: function(){
    return (
      <BarChartComponent
        {...this.props}
        onSelect={this._onSelect}
      />
    );
  },

});

var BarChartComponent = React.requireNativeComponent('MPChartBar', BarChart, {
  nativeOnly: {onSelect: true},
});

module.exports = BarChart;
