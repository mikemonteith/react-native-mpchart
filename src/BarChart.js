'use strict';

var React = require('react-native');

var BarChart = React.createClass({
  name: 'BarChart',
  propTypes: {
    ...React.View.propTypes,

    colors: React.PropTypes.array,
    values: React.PropTypes.array,
    drawValues: React.PropTypes.bool,
    onValueSelect: React.PropTypes.func,
    onClearSelection: React.PropTypes.func,
  },

  _onSelect: function(event){
    if (event.type === 'valueSelect' && this.props.onValueSelect){
      this.props.onValueSelect(event);
    }else if (event.type === 'clearSelection' && this.props.onClearSelection){
      this.props.onClearSelection(event);
    }
  },

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
