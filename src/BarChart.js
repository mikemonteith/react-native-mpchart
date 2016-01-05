var React = require('react-native');

var BarChart = React.createClass({
  name: 'BarChart',
  propTypes: {
    ...React.View.propTypes,

    colors: React.PropTypes.array,
    values: React.PropTypes.array,
    drawValues: React.PropTypes.bool,
    onBarSelect: React.PropTypes.func,
    onBarDeselect: React.PropTypes.func,
  },

  _onSelect: function(event){
    if(event.type === "barSelect" && this.props.onBarSelect){
      this.props.onBarSelect(event);
    }else if(event.type === "barDeselect" && this.props.onBarDeselect){
      this.props.onBarDeselect(event);
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
