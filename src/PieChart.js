var React = require('react-native');

var PieChartComponent = React.requireNativeComponent('MPChartPie');

module.exports = React.createClass({
  propTypes: {
    colors: React.PropTypes.array,
    values: React.PropTypes.array,
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
        style={[{flex: 1}, this.props.style]}
        colors={this.props.colors}
        values={this.props.values}
        onSelect={this._onSelect}
      />
    );
  },
});
