var React = require('react-native');

var LineChart = React.createClass({
  name: 'LineChart',
  propTypes: {
    ...React.View.propTypes,

    colors: React.PropTypes.array,
    values: React.PropTypes.array,
    drawValues: React.PropTypes.bool,
    onPointSelect: React.PropTypes.func,
    onPointDeselect: React.PropTypes.func,
  },

  _onSelect: function(event){
    if(event.type === "pointSelect" && this.props.onPointSelect){
      this.props.onPointSelect(event);
    }else if(event.type === "pointDeselect" && this.props.onPointDeselect){
      this.props.onPointDeselect(event);
    }
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
