dojo.provide('client.components.AdTag');

mulberry.component('AdTag', {
  componentTemplate : dojo.cache('client.components', 'AdTag/AdTag.haml'),

  prep : function() {
    toura.log(this.node);
  },

  init : function() {

  }
});
