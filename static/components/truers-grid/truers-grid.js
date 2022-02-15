'use strict'

function TruersGridController() {
	
};

module.component('truersGrid', {
  bindings: {
    items: '=',
	cols: '=',
	onUpdate: '<',
	onDelete: '<',
  },
  templateUrl: '/components/truers-grid/truers-grid.html',
  controller: TruersGridController
});