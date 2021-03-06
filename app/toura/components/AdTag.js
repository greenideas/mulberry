dojo.provide('toura.components.AdTag');

dojo.require('mulberry._Component');

dojo.declare('toura.components.AdTag', mulberry._Component, {
  templateString : dojo.cache('toura.components', 'AdTag/AdTag.haml'),

  adjustMarkup : function() {
    var appConfig = mulberry.app.Config.get('app');

    if (appConfig) {
      this.adConfig = appConfig.ads && appConfig.ads[this.device.type];

      if (this.adConfig) {
        dojo.attr(this.adFrame, "src", this.adConfig);
      }
    }
  },

  startup : function() {
    if (!this.adConfig) {
      this.destroy();
    }
  }
});
