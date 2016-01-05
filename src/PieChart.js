var React = require('react-native');

var PieChart = React.createClass({
  name: 'PieChart',
  propTypes: {
    ...React.View.propTypes,

    colors: React.PropTypes.array,
    values: React.PropTypes.array,
    drawValues: React.PropTypes.bool,
    onSegmentSelect: React.PropTypes.func,
    onSegmentDeselect: React.PropTypes.func,
  },

  _onSelect: function(event){
    if(event.type === "segmentSelect" && this.props.onSegmentSelect){
      this.props.onSegmentSelect(event);
    }else if(event.type === "segmentDeselect" && this.props.onSegmentDeselect){
      this.props.onSegmentDeselect(event);
    }
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
