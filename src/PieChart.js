var React = require('react-native');

var PieChart = React.createClass({
  name: 'PieChart',
  propTypes: {
    ...React.View.propTypes,

    colors: React.PropTypes.array,
    values: React.PropTypes.array,
    drawValues: React.PropTypes.bool,
    onValueSelect: React.PropTypes.func,
    onClearSelection: React.PropTypes.func,
  },

  _onSelect: function(event){
    if(event.type === "valueSelect" && this.props.onValueSelect){
      this.props.onValueSelect(event);
    }else if(event.type === "clearSelection" && this.props.onClearSelection){
      this.props.onClearSelection(event);
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
