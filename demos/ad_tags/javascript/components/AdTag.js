dojo.provide('client.components.AdTag');

mulberry.component('AdTag', {
  componentTemplate : dojo.cache('client.components', 'AdTag/AdTag.haml'),

  init : function() {
  },

  setupConnections : function() {
    this.subscribe("adClick", function(doc){ 
      toura.log(doc);
    });
  },

  _handleTouch : function() {
  }
});
