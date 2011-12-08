dojo.provide('client.components.AdTag');

mulberry.component('AdTag', {
  componentTemplate : dojo.cache('client.components', 'AdTag/AdTag.haml'),

  init : function() {
  },

  setupConnections : function() {
    console.log("WINDOW LOCATION" + window.location);

    this.connect(this.adTag, "click", function(){ toura.log("foo"); });

    this.connect(window, "message", function(){ toura.log("foo"); });

    this.subscribe("adClick", function(doc){ 
      toura.log(doc);
    });
  },

  _handleTouch : function() {
  }
});
