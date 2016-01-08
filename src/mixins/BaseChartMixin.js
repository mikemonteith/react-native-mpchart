'use strict';

var React = require('react-native');

var BaseChartMixin = {
  propTypes: {
    /*
     * inherit all props from the View element
     */
    ...React.View.propTypes,

    /*
     * data to display with the graph
     */
    data: React.PropTypes.shape({
      dataSets: React.PropTypes.arrayOf(React.PropTypes.shape({
        /*
         * Array of values to display with the graph
         */
        values: React.PropTypes.arrayOf(React.PropTypes.number),

        /*
         */
        colors: React.PropTypes.arrayOf(React.PropTypes.string),

        /*
         * Should text labels be drawn for values
         */
        drawValues: React.PropTypes.bool,
      })),

      /*
       * Array of strings defining x-axis labels
       */
      xValues: React.PropTypes.arrayOf(React.PropTypes.string),
    }),

    /*
     * enable touch events on the chart
     */
    touchEnabled: React.PropTypes.bool,

    /*
     * event handler for when a value is selected
     */
    onValueSelect: React.PropTypes.func,

    /*
     * event handler when the selection is cleared
     */
    onClearSelection: React.PropTypes.func,
  },

  _onSelect: function(event){
    if (event.type === 'valueSelect' && this.props.onValueSelect){
      this.props.onValueSelect(event);
    }else if (event.type === 'clearSelection' && this.props.onClearSelection){
      this.props.onClearSelection(event);
    }
  },
};

module.exports = BaseChartMixin;
